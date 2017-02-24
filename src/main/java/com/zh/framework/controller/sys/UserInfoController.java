package com.zh.framework.controller.sys;

import com.google.common.collect.Maps;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.entity.sys.TbUserInfo;
import com.zh.framework.entity.sys.VUserInfo;
import com.zh.framework.service.sys.UserInfoServiceI;
import com.zh.framework.service.sys.UserServiceI;
import com.zh.framework.service.sys.VUserInfoServiceI;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.MD5Util;
import com.zh.framework.util.StaticUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Marlon on 2016/12/30.
 */

@RestController
@RequestMapping("/sys/userInfo")
public class UserInfoController extends BaseController<VUserInfo> {

    @Autowired
    private VUserInfoServiceI vUserInfoServiceI;
    @Autowired
    private UserInfoServiceI userInfoServiceI;
    @Autowired
    private UserServiceI userServiceI;

    @ApiOperation(value = "用户信息")
    @ResponseBody
    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    public String getUserInfo( String user){
        HttpEntity httpEntity = new HttpEntity();
        TbUser u = (TbUser) parse(user,TbUser.class);

        TbUserInfo ui = (TbUserInfo) parse(user,TbUserInfo.class);

        TbUser user_ = null;
        TbUserInfo userInfo_ = null;

       try {
           if (!StringUtils.isBlank(u.getId())){
               user_ = userServiceI.getById(u.getId());
               Map<String,Object> params= Maps.newHashMap();
               params.put("id_NEQ",u.getId());
               params.put("uName_EQ",u.getuName());
               List<TbUser> list=userServiceI.findParams(params);
               if(list.size()>0){
                   httpEntity.setSuccess(false);
                   httpEntity.setMsg("用户名字已存在");
                   return tojson(httpEntity);
               }
               userInfo_ = userInfoServiceI.getById(u.getId());
           }
           if (!StringUtils.isBlank(u.getuName())){
               user_.setuName(u.getuName());
           }
           if (!StringUtils.isBlank(u.getuDescription())){
               user_.setuDescription(u.getuDescription());
           }

           if (userInfo_ != null){
               if (!StringUtils.isBlank(ui.getuRealName())){
                   userInfo_.setuRealName(ui.getuRealName());
               }
               if(ui.getuSex() != 0){
                   userInfo_.setuSex(ui.getuSex());
               }
               if (ui.getuBirthday() != null){
                   userInfo_.setuBirthday(ui.getuBirthday());
               }
               if (!StringUtils.isBlank(ui.getuTelephone())){
                   userInfo_.setuTelephone(ui.getuTelephone());
               }
               if (!StringUtils.isBlank(ui.getuEmail())){
                   userInfo_.setuEmail(ui.getuEmail());
               }
               if (!StringUtils.isBlank(ui.getuAddress())){
                   userInfo_.setuAddress(ui.getuAddress());
               }
           }else if (ui!=null){
               ui.setId(user_.getId());
               userInfoServiceI.save(ui);
           }else{
              userServiceI.update(user_);
           }


           vUserInfoServiceI.updateUserAndUserInfo(user_,userInfo_);

           httpEntity.setSuccess(true);
           httpEntity.setMsg("更新成功");
       }catch (Exception e){
           httpEntity.setSuccess(false);
           httpEntity.setMsg(e.getMessage());
       }
        return tojson(httpEntity);
    }

    @ApiOperation(value = "用户信息")
    @ResponseBody
    @RequestMapping(value = "saveUser.do",method = RequestMethod.POST)
    public String saveUserInfo(String user, String userInfo, HttpServletRequest  request){
        HttpEntity httpEntity = new HttpEntity();
        TbUser u = (TbUser) parse(user,TbUser.class);
        TbUserInfo ui = (TbUserInfo) parse(userInfo,TbUserInfo.class);
        String uuid = UUID.randomUUID().toString();
        String salt = UUID.randomUUID().toString();
        Date date = new Date();
        try{
            if (u != null){
                u.setId(uuid);
                u.setCreateTime(date);
                u.setCreateUserId((String) request.getAttribute("userId"));
                u.setuLocked(1);
                u.setuPassword(MD5Util.md5("123456"+salt));
                u.setuCredentialsSalt(salt);
                int a =userServiceI.save(u);
                if(a<1){
                    httpEntity.setSuccess(false);
                    httpEntity.setMsg("用户名字已存在");
                    return tojson(httpEntity);
                }
            }
            if (ui != null && !StringUtils.isBlank(ui.getuRealName())){
                ui.setId(uuid);
                ui.setCreateTime(date);
                userInfoServiceI.save(ui);
            }
            httpEntity.setSuccess(true);
            httpEntity.setMsg("保存成功");
        }catch (Exception e){
            httpEntity.setSuccess(false);
            httpEntity.setMsg(e.getMessage());
        }
        return tojson(httpEntity);
    }


    @ApiOperation(value = "重置密码")
    @ResponseBody
    @RequestMapping(value = "resetPassword.do",method = RequestMethod.POST)
    public String resetPassword(@RequestParam String userId){
        HttpEntity httpEntity = new HttpEntity();
        try {
            TbUser user = userServiceI.getById(userId);
            String salt = UUID.randomUUID().toString();
            user.setuCredentialsSalt(salt);
            user.setuPassword(MD5Util.md5("123456"+salt));
            userServiceI.update(user);
            httpEntity.setMsg("密码重置成功");
            httpEntity.setSuccess(true);
        }catch (Exception e){
            httpEntity.setMsg("重置失败");
        }
        return tojson(httpEntity);
    }

    @ApiOperation(value = "批量逻辑删除")
    @ResponseBody
    @RequestMapping(value = "logicDeleteUser.do",method = RequestMethod.POST)
    public String logicDelete(@RequestBody List<Object> ids){
        HttpEntity httpEntity = new HttpEntity();
        try {
            userServiceI.deleleLogicBatch(ids);
            httpEntity.setMsg("删除成功");
            httpEntity.setSuccess(true);
        }catch (Exception e){
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("删除失败");
        }
        return tojson(httpEntity);
    }
    @ApiOperation(value = "用户设置密码")
    @ResponseBody
    @RequestMapping(value = "setPassword.do",method = RequestMethod.POST)
    public String setPassword(String userId,String password){
        HttpEntity httpEntity = new HttpEntity();
        try {
            TbUser user = userServiceI.getById(userId);
            String salt = UUID.randomUUID().toString();
            user.setuCredentialsSalt(salt);
            user.setuPassword(MD5Util.md5(password+salt));
            userServiceI.update(user);
            httpEntity.setMsg("密码设置成功");
            httpEntity.setSuccess(true);
        }catch (Exception e){
            httpEntity.setMsg("密码设置失败");
        }
        return tojson(httpEntity);
    }
    @ApiOperation(value = "锁定状态修改")
    @ResponseBody
    @RequestMapping(value = "setLock.do",method = RequestMethod.POST)
    public String setLock(String userId){
        HttpEntity httpEntity = new HttpEntity();
        try {
            TbUser user = userServiceI.getById(userId);
            if (user.getuLocked() == 1){
                user.setuLocked(0);
            }else{
                user.setuLocked(1);
            }
            userServiceI.update(user);
            httpEntity.setMsg("状态修改成功");
            httpEntity.setSuccess(true);
        }catch (Exception e){
            httpEntity.setMsg("修改失败");
        }
        return tojson(httpEntity);
    }

}
