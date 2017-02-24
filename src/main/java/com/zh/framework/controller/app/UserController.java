package com.zh.framework.controller.app;

import com.google.common.collect.Maps;
import com.sun.istack.internal.NotNull;
import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.entity.sys.TbUserRole;
import com.zh.framework.service.sys.UserOrgServiceI;
import com.zh.framework.service.sys.UserRoleServiceI;
import com.zh.framework.service.sys.UserServiceI;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.MD5Util;
import com.zh.framework.util.StaticUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Mrkin on 2016/11/4.
 */
@Controller(value = "UserControllerApp")
@RequestMapping("/app/sys/user")
public class UserController extends BaseController<TbUser> {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private UserRoleServiceI userRoleServiceI;
    @Autowired
    private UserOrgServiceI userOrgServiceI;
    @LogAnnotation(operation = "修改", content = "修改密码",type = LogAnnotation.Type.app)
    @ResponseBody
    @RequestMapping(value = "updatePass.do", method = RequestMethod.POST)
    public String updatePass(String id, String oldPassword, @NotNull String newPassword) {
        TbUser tb_user = userServiceI.getById(id);
        HttpEntity httpEntity = new HttpEntity();
        if (tb_user.getuPassword().equals(MD5Util.md5(oldPassword)) && newPassword.length() >= 6) {
            tb_user.setuCredentialsSalt(UUID.randomUUID().toString());
            tb_user.setuPassword(MD5Util.md5(newPassword + tb_user.getuCredentialsSalt()));
            userServiceI.update(tb_user);
            httpEntity.setSuccess(true);
            httpEntity.setMsg("修改密码成功");
        } else {
            if (newPassword.length() < 6) {
                httpEntity.setMsg("新密码长度不小于6位");
            } else {
                httpEntity.setMsg("旧密码错误！");
            }
            httpEntity.setSuccess(false);
        }
        return tojson(httpEntity);
    }



}
