package com.zh.framework.controller.app;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Marlon on 2016/12/30.
 */

@Controller(value = "UserInfoControllerApp")
@RequestMapping("/app/sys/userInfo")
public class UserInfoController extends BaseController<VUserInfo> {

    @Autowired
    private VUserInfoServiceI vUserInfoServiceI;
    @Autowired
    private UserInfoServiceI userInfoServiceI;
    @Autowired
    private UserServiceI userServiceI;
    @ApiOperation(value = "修改用户信息")
    @ResponseBody
    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    public String update(@RequestBody TbUserInfo tbUserInfo) {
        HttpEntity httpEntity = new HttpEntity();
        if (userInfoServiceI.getById(tbUserInfo.getId())!=null||userServiceI.getById(tbUserInfo.getId())!=null) {
            userInfoServiceI.save(tbUserInfo);
            httpEntity.setMsg("修改成功");
            return tojson(httpEntity);
        }
        else {
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("用户不存在");
            return tojson(httpEntity);
        }
    }








}
