package com.zh.framework.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.sys.TbOrganization;
import com.zh.framework.service.sys.OrganizationServiceI;
import com.zh.framework.service.sys.UserServiceI;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.StaticUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/11/4.
 */
@Controller
@RequestMapping("/sys/org")
public class OrganizationController extends BaseController<TbOrganization> {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private OrganizationServiceI organizationService;
    @ApiOperation(value = "获取所有单位", notes = "")
    @ResponseBody
    @RequestMapping(value = "getOrgAll.do", method = RequestMethod.POST)
    public String getOrgAll(String params) {
        Map<String,Object> paramsMap=Maps.newHashMap();
        if (params != null) {
            paramsMap.putAll(JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
            }));
        }
        return super.getTreeGrid(paramsMap,"oParentId");
    }
    @ApiOperation(value = "获取所有资源树", notes = "")
    @ResponseBody
    @RequestMapping(value = "getOrganizationTree.do", method = RequestMethod.POST)
    public String getOrganizationTree() {
        Map<String,Object> params= Maps.newHashMap();
        params.put("deleteStatus_EQ",1);
        List<String> showList=Lists.newArrayList();
        HttpEntity httpEntity=new HttpEntity();
        httpEntity.setObj(service.allTree(params,"oName","oParentId",showList));
        return tojson(httpEntity);
    }
    @ApiOperation(value = "保存", notes = "")
    @ResponseBody
    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
    public String insert(@RequestBody TbOrganization organization, HttpServletRequest request){
        HttpEntity httpEntity = new HttpEntity();
        String temp_id=organization.getId();
        int result=0;
       if( organization.getoType()==0&&organization.getoParentId()!=null){//1：机构 0：部门
           organization.setoParentOrgId(organizationService.getById(organization.getoParentId()).getoParentId());
           organization.setLevel(organizationService.getById(organization.getoParentId()).getLevel() + 1);
       }else if(organization.getoType()==1&&organization.getoParentId()!=null) {
           organization.setoParentOrgId(organizationService.getById(organization.getoParentId()).getId());
           organization.setLevel(organizationService.getById(organization.getoParentId()).getLevel() + 1);
       }else{
           organization.setLevel(0);
       }
        if(organizationService.getById(temp_id)!=null) {
            //organizationService.update(organization);
            if(organization.getoParentId().equals(temp_id)){
                httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
                httpEntity.setMsg("上级单位不能为自己");
                 return tojson(httpEntity);
            }
            result=organizationService.update(organization);
        }else {
            organization.setCreateTime(new Date());
            organization.setCreateUserId(request.getAttribute("userId").toString());
            result=organizationService.save(organization);
        }
        if (result==1) {
            saveLog(request, "保存", "对" + organization.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setMsg("保存成功");
        }else {
            saveLog(request, "保存", "对" + organization.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("机构名称重复");
        }
        return tojson(httpEntity);
    }

    @ResponseBody
    @RequestMapping(value = "deleteOrganization.do", method = RequestMethod.POST)
    public String deleteOrganization(@RequestBody String id){
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("删除成功");
        List<TbOrganization> list=organizationService.deleteOrganization( id);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (TbOrganization Organization:list) {
            saveLog(request, "批量逻辑删除", "对" + TbOrganization.class.getAnnotation(Table.class).value() + "表进行逻辑删除数据id:" + Organization.getId());
        }
        return tojson(httpEntity);
    }

}
