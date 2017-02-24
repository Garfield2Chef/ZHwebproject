package com.zh.framework.controller.sys;

import com.google.common.collect.Maps;
import com.sun.istack.internal.NotNull;
import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.sys.TbResourceRole;
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
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController<TbUser> {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private UserRoleServiceI userRoleServiceI;
    @Autowired
    private UserOrgServiceI userOrgServiceI;
    @ApiOperation(value = "更新密码", notes = "")
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

    @ApiOperation(value = "重置密码", notes = "")
    @ResponseBody
    @RequestMapping(value = "resetPass.do", method = RequestMethod.POST)
    public String resetPass(String id) {
        TbUser tb_user = userServiceI.getById(id);
        HttpEntity httpEntity = new HttpEntity();
        if (tb_user != null) {
            tb_user.setuCredentialsSalt(UUID.randomUUID().toString());
            tb_user.setuPassword(MD5Util.md5(StaticUtil.RESETPASS + tb_user.getuCredentialsSalt()));
            userServiceI.update(tb_user);
            httpEntity.setSuccess(true);
            httpEntity.setMsg("修改密码成功");
        } else {
            httpEntity.setMsg("没有此用户");
            httpEntity.setSuccess(false);
        }
        return tojson(httpEntity);
    }

    @ApiOperation(value = "用户注册", notes = "")
    @ResponseBody
    @RequestMapping(value = "save.do", method = RequestMethod.POST)
    @Override
    public String save(String json, String userId) {
        TbUser tbUser = (TbUser) parse(json, TbUser.class);
        tbUser.setCreateTime(new Date());
        tbUser.setuCredentialsSalt(UUID.randomUUID().toString());
        tbUser.setCreateUserId(userId);
        tbUser.setuPassword(MD5Util.md5(tbUser.getuPassword() + tbUser.getuCredentialsSalt()));
        return super.save(tbUser);
    }
    @ApiOperation(value = "用户单位授权", notes = "")
    @ResponseBody
    @RequestMapping(value = "empowerOrganizationSave.do", method = RequestMethod.POST)
    public String empowerOrganizationSave( String userId, String orgId) {
        int count =  userOrgServiceI.save_orgnization(userId, orgId);
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setObj(count);
        httpEntity.setMsg("授权成功!");
        return tojson(httpEntity);
    }

    @ApiOperation(value = "用户角色授权", notes = "")
    @ResponseBody
    @RequestMapping(value = "empowerRoleSave.do", method = RequestMethod.POST)
    public String empowerRoleSave(String json, String userId) {
        List<TbUserRole> list = parseList(json, TbUserRole.class);
        int count = userRoleServiceI.save_role(list, userId);
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setObj(count);
        httpEntity.setMsg("授权成功!");
        return tojson(httpEntity);
    }
      @ApiOperation(value = "获取已授权单位的数据", notes = "")
        @ResponseBody
        @RequestMapping(value = "getUserOrganization.do", method = RequestMethod.POST)
        public String getUserOrganization(String userId) {
            Map<String,Object> params= Maps.newHashMap();
            params.put("uId_EQ",userId);
            HttpEntity httpEntity=new HttpEntity();
            httpEntity.setObj(userOrgServiceI.findParams(params));
            return tojson(httpEntity);
        }
    @ApiOperation(value = "获取已授权角色的数据", notes = "")
    @ResponseBody
    @RequestMapping(value = "getUserRole.do", method = RequestMethod.POST)
    public String getUserRole(String userId) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("uId_EQ",userId);
        HttpEntity httpEntity=new HttpEntity();
        httpEntity.setObj(userRoleServiceI.findParams(params));
        return tojson(httpEntity);
    }

}
