package com.zh.framework.controller.app;

import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.service.sys.UserInfoServiceI;
import com.zh.framework.service.sys.UserServiceI;
import com.zh.framework.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Marlon on 2016/11/7.
 */

@Controller(value = "LoginControllerApp")
@RequestMapping("/app/sys/")
public class LoginController extends BaseController<TbUser> {

    @Autowired
    private UserInfoServiceI userInfoServiceI;
    @Autowired
    private UserServiceI userService;

    @LogAnnotation(content = "APP登录成功", operation = "登录", type = LogAnnotation.Type.app)
    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(String loginName, String password) {
        try {
            loginName=RSAUtil.decrypt(loginName);
            password=RSAUtil.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbUser tb_user = userService.login(loginName);
        HttpEntity httpEntity = new HttpEntity();
        if (null == tb_user) {
            httpEntity.setMsg("账号不存在!");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
        } else if (tb_user.getuLocked() == 0) {
            httpEntity.setMsg("账号已经被锁定!");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
        } else if (password == null || !MD5Util.md5(password + tb_user.getuCredentialsSalt()).equals(tb_user.getuPassword())) {
            httpEntity.setMsg("密码不正确!");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
        } else {
            String token = JwtUtil.createJWT(tb_user.getId(), StaticUtil.LOGIN_TYPE_APP, "");
            tb_user.setUserInfo(userInfoServiceI.getById(tb_user.getId()));
            httpEntity.setSuccess(true);
            httpEntity.setToken(token);
            httpEntity.setMsg("登录成功");
            httpEntity.setObj(tb_user);
        }
        String result = tojson(httpEntity);
        return result;
    }

    /**
     * @param userid
     * @return
     */
    @LogAnnotation(content = "APP退出登录", operation = "登出", type = LogAnnotation.Type.app)
    @ResponseBody
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    public String logout(String userid) {
        HttpEntity httpEntity = new HttpEntity();
        JwtUtil.getTokenValidStore().remove(userid);
        httpEntity.setMsg("退出成功");
        httpEntity.setObj(userid);
        return  tojson(httpEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/getPublicKey.do", method = RequestMethod.POST)
    public String getPublicKey() {
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("获取成功");
        httpEntity.setObj(RSAUtil.getPublicKey());
        return tojson(httpEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/getString.do", method = RequestMethod.POST)
    public String getString(String name) {
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("获取成功");
        try {
            String result=RSAUtil.encrypt(name);
            httpEntity.setObj(result);
            System.out.println("加密结果"+result);
           // System.out.println("解密结果"+RSAUtil.decrypt((String) httpEntity.getObj()));
        }catch (Exception e){
            e.printStackTrace();
        }

        return tojson(httpEntity);
    }
    @ResponseBody
    @RequestMapping(value = "/getDecrypt.do", method = RequestMethod.POST)
    public String getDecrypt(String name) {
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("获取成功");
        try {
            httpEntity.setObj(RSAUtil.decrypt(name));
            //System.out.println(RSAUtil.decrypt((String) httpEntity.getObj()));
        }catch (Exception e){
            e.printStackTrace();
            httpEntity.setCode(201);
        }
        return tojson(httpEntity);
    }
}
