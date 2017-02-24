package com.zh.framework.controller.sys;

import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.service.sys.UserInfoServiceI;
import com.zh.framework.service.sys.UserServiceI;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.JwtUtil;
import com.zh.framework.util.MD5Util;
import com.zh.framework.util.StaticUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Marlon on 2016/11/7.
 */

@Controller
@RequestMapping("/sys")
public class LoginController extends BaseController<TbUser> {
    @Autowired
    private UserInfoServiceI userInfoServiceI;

    @Autowired
    private UserServiceI userService;
    @LogAnnotation(content = "登录成功", operation = "登录", type = LogAnnotation.Type.web)
    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(String loginName, String password) {
        TbUser tb_user = userService.login(loginName);
        HttpEntity httpEntity = new HttpEntity();
        if (null == tb_user) {
            httpEntity.setMsg("账号不存在!");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
        }else if (tb_user.getuLocked() == 0){
            httpEntity.setMsg("账号已经被锁定!");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
        } else if (password == null ||!MD5Util.md5(password+tb_user.getuCredentialsSalt()).equals(tb_user.getuPassword())) {
            httpEntity.setMsg("密码不正确!");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
        } else {
            String token = JwtUtil.createJWT(tb_user.getId(), StaticUtil.LOGIN_TYPE_WEB, "");
            tb_user.setUserInfo(userInfoServiceI.getById(tb_user.getId()));
            TbUser  user = userService.getById(tb_user.getId());
                    user.setuLastOnLine(new Date());
            userService.update(user);
            httpEntity.setSuccess(true);
            httpEntity.setToken(token);
            httpEntity.setMsg("登录成功");
            httpEntity.setObj(tb_user);
        }
//        System.out.println(MD5Util.md5(password+tb_user.getuCredentialsSalt()).equals(tb_user.getuPassword()));
        String result = tojson(httpEntity);
        return result;
    }

    /**
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    public String logout(String userid) {
        HttpEntity httpEntity = new HttpEntity();
        JwtUtil.getTokenValidStore().remove(userid);
        httpEntity.setSuccess(true);
        httpEntity.setMsg("退出成功");
        httpEntity.setObj(userid);
        String result = tojson(httpEntity);
        return result;
    }

}
