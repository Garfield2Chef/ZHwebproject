package com.zh.framework.controller.sys;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.DataEntity;
import com.zh.framework.entity.sys.TbResource;
import com.zh.framework.service.sys.ResourceRoleServiceI;
import com.zh.framework.service.sys.ResourceServiceI;
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
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**资源controller
 * Created by Mrkin on 2016/12/7.
 */
@Controller
@RequestMapping("/sys/resource")
public class ResourceController extends BaseController<TbResource> {

    @Autowired
    private ResourceRoleServiceI resourceRoleServiceI;
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private ResourceServiceI resourceService;

    @ApiOperation(value = "获取菜单", notes = "")
    @ResponseBody
    @RequestMapping(value = "getMenus.do", method = RequestMethod.POST)
    public String getMenus(String userId){
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("获取成功");
        httpEntity.setObj(((ResourceServiceI)service).getWebMenusById(userId));
        Map<String,Object> params=Maps.newHashMap();
        params.put("deleteStatus_=",1);
        List<String> showList= Lists.newArrayList();
        showList.add("sUrl");
        showList.add("sIcon");
        //httpEntity.setObj(resourceServiceI.allTree(params,"sName","sParentId" ,showList));
        return tojson(httpEntity);
    }

    @ApiOperation(value = "获取所有资源", notes = "")
    @ResponseBody
    @RequestMapping(value = "getResourceAll.do", method = RequestMethod.POST)
    public String getResourceAll() {
        return super.getTreeGrid(Maps.<String,Object>newHashMap(),"sParentId");
    }

    @ApiOperation(value = "获取所有资源树", notes = "")
    @ResponseBody
    @RequestMapping(value = "getResourceTree.do", method = RequestMethod.POST)
    public String getResourceTree() {
        Map<String,Object> params= Maps.newHashMap();
        params.put("deleteStatus_EQ",1);
        List<String> showList=Lists.newArrayList();
        showList.add("sType");
        showList.add("sKind");
        HttpEntity httpEntity=new HttpEntity();
        httpEntity.setObj(service.allTree(params,"sName","sParentId",showList));
        return tojson(httpEntity);
    }

    @LogAnnotation(content = "获取已授权的数据", operation = "修改")
    @ResponseBody
    @RequestMapping(value = "getRoleResource.do", method = RequestMethod.POST)
    public String getRoleResource(String roleId) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("rId_EQ",roleId);
        HttpEntity httpEntity=new HttpEntity();
        httpEntity.setObj(resourceRoleServiceI.findParams(params));
        return tojson(httpEntity);
    }



    @LogAnnotation(content = "保存", operation = "修改")
    @ResponseBody
    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
    public String insert(@RequestBody TbResource resource,HttpServletRequest request){
        HttpEntity httpEntity = new HttpEntity();
        String temp_id=resource.getId();
        resource.setCreateTime(new Date());
        resource.setCreateUserId(request.getAttribute("userId").toString());
        try {
            resource.setLevel(resourceService.getById(resource.getsParentId()).getLevel() + 1);
        }catch (Exception e){
            httpEntity.setSuccess(false);
        }
        try {
            if(resourceService.getById(temp_id)!=null) {
                resourceService.update(resource);
            }else {
                resourceService.save(resource);
            }
            httpEntity.setSuccess(true);
            httpEntity.setMsg("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setSuccess(false);
            httpEntity.setMsg("保存失败");
        }
        return tojson(httpEntity);
    }

    @ResponseBody
    @RequestMapping(value = "deleteResource.do", method = RequestMethod.POST)
    public String deleteResource(@RequestBody String id){
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("删除成功");
        List<TbResource> list=resourceService.deleteResource( id);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (TbResource tbResource:list) {
            saveLog(request, "批量逻辑删除", "对" + TbResource.class.getAnnotation(Table.class).value() + "表进行逻辑删除数据id:" + tbResource.getId());
        }
        return tojson(httpEntity);
    }
    @LogAnnotation(content = "修改是否隐藏", operation = "修改")
    @ResponseBody
    @RequestMapping(value = "updateHide.do", method = RequestMethod.POST)
    public String updateHide(@RequestBody String id){
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("修改成功");
        TbResource tbResource=resourceService.getById( id);
        if (tbResource.getsHide()==0){
            tbResource.setsHide(1);
        }else {
            tbResource.setsHide(0);
        }
        resourceService.update(tbResource);
        return tojson(httpEntity);
    }
}
