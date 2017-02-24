package com.zh.framework.controller.sys;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.Tree;
import com.zh.framework.entity.sys.TbResourceRole;
import com.zh.framework.entity.sys.TbRole;
import com.zh.framework.service.sys.ResourceRoleServiceI;
import com.zh.framework.service.sys.RoleServiceI;
import com.zh.framework.util.HttpEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/12/8.
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController<TbRole> {
    @Autowired
    private ResourceRoleServiceI resourceRoleServiceI;
    @LogAnnotation(content = "资源授权",operation = "授权")
    @ApiOperation(value = "资源授权")
    @ResponseBody
    @RequestMapping(value = "empowerResources.do",method = RequestMethod.POST)
    public String empowerResources(String json,String roleId){
        List<TbResourceRole> list= parseList(json,TbResourceRole.class);
        int count=resourceRoleServiceI.save_empower(list,roleId);
        HttpEntity httpEntity=new HttpEntity();
        httpEntity.setObj(count);
        httpEntity.setMsg("授权成功!");
        return tojson(httpEntity);
    }
    @ApiOperation(value = "获取所有角色树", notes = "")
    @ResponseBody
    @RequestMapping(value = "getRoleTree.do", method = RequestMethod.POST)
    public String getRoleTree() {
       Map<String,Object> params= Maps.newHashMap();
        params.put("deleteStatus_EQ",1);
        List<TbRole>  list =  service.findParams(params);
        List<Tree>    roleTree = new ArrayList<Tree>();
        for(TbRole role : list){
            Tree tree = new  Tree();
            tree.setId(role.getId());
            tree.setText(role.getrName());
            roleTree.add(tree);
        }
        HttpEntity httpEntity=new HttpEntity();
        httpEntity.setObj( roleTree );
       return tojson(httpEntity);
   }
}
