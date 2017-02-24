package com.zh.framework.service.sys;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.entity.Tree;
import com.zh.framework.entity.sys.TbResource;
import com.zh.framework.mapper.sys.TbResourceMapper;
import com.zh.framework.service.BaseServiceImpl;
import com.zh.framework.sql.BaseSql;
import com.zh.framework.util.ReflectionUtil;
import com.zh.framework.util.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/12/5.
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<TbResource> implements ResourceServiceI {
    private final String WEB = "web";
    private final String APP = "app";
    @Autowired
    private TbResourceMapper resourceMapper;

    @Override
    public List<TbResource> getWebListByUserId(String userId, Type type) {
        return getListBykind(userId, type, WEB);
    }

    @Override
    public List<TbResource> getAppListByUserId(String userId, Type type) {
        return getListBykind(userId, type, APP);
    }

    @Override
    public List<Tree> getWebMenusById(String userId) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("deleteStatus_EQ",1);
        params.put("sHide_EQ",1);
        String condition="";
        for (TbResource resource:resourceMapper.getListByUserId(userId,WEB, StaticUtil.RESOURCE_TYPE_MENU)){
            condition+="'"+resource.getId()+"',";
        }
        if (condition.length()>0){
            condition=condition.substring(0,condition.length()-1);
        }
        params.put("id_IN",condition);
        List<String> showList= Lists.newArrayList();
        showList.add("sUrl");
        showList.add("sIcon");
        return super.allTree(params,"sName","sParentId",showList);
    }

    @Override
    public List<TbResource> deleteResource(String id) {
        List<TbResource> result=Lists.newArrayList();
        List<TbResource> temp=Lists.newArrayList();
        String parentColumn="sParentId";
        temp.add(super.getById(id));
        result.addAll(temp);
        getChildData(temp,parentColumn,result);
        for (TbResource tbResource:result){
            super.deleteLogic(tbResource.getId());
        }
        return result;
    }

    public void getChildData(List<TbResource> list,String parentColumn,List<TbResource> result){
        for (int i = 0; i < list.size(); i++) {
            TbResource t = list.get(i);
            Map<String,Object> param=Maps.newHashMap();
            param.put(parentColumn+"_EQ",t.getId());
            List<TbResource> temp=super.findParams(param);
            result.addAll(temp);
            getChildData(temp, parentColumn,result);
        }
    }

    public List<TbResource> getListBykind(String userId, Type type, String kind) {
        List<TbResource> resourceList = Lists.newArrayList();
        switch (type) {
            case ALL:
                resourceList.addAll(resourceMapper.getListByTypeUserId(userId, kind));
                break;
            case FUNCTION:
                resourceList.addAll(resourceMapper.getListByUserId(userId, kind, 1));
                break;
            case MEUN:
                resourceList.addAll(resourceMapper.getListByUserId(userId, kind, 0));
                break;
        }
        return resourceList;
    }

}
