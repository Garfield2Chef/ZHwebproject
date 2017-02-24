package com.zh.framework.service.sys;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.entity.sys.TbOrganization;
import com.zh.framework.mapper.sys.TbOrganizationMapper;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/12/12.
 */
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<TbOrganization> implements OrganizationServiceI {
    @Autowired
    private TbOrganizationMapper organizationMapper;
    @Override
    public TbOrganization getByUserId(String userId) {
        return organizationMapper.getByUserId(userId);
    }

    @Override
    public int save(TbOrganization tbOrganization) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("oName_EQ",tbOrganization.getoName());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.save(tbOrganization);
        }
        return 1;
    }

    @Override
    public int update(TbOrganization tbOrganization) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",tbOrganization.getId());
        params.put("oName_EQ",tbOrganization.getoName());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.update(tbOrganization);
        }
        return 1;
    }

    @Override
    public List<TbOrganization> deleteOrganization(String id) {
        List<TbOrganization> result= Lists.newArrayList();
        List<TbOrganization> temp=Lists.newArrayList();
        String parentColumn="oParentId";
        super.getById(id).setoParentId(null);//置当前被删除的机构父ID为空
        super.update(super.getById(id));
        temp.add(super.getById(id));
        result.addAll(temp);
        getChildData(temp,parentColumn,result);
        for (TbOrganization Organization:result){
            super.deleteLogic(Organization.getId());
        }
        return result;
    }

    public void getChildData(List<TbOrganization> list,String parentColumn,List<TbOrganization> result){
        for (int i = 0; i < list.size(); i++) {
            TbOrganization t = list.get(i);
            Map<String,Object> param=Maps.newHashMap();
            param.put(parentColumn+"_EQ",t.getId());
            List<TbOrganization> temp=super.findParams(param);
            result.addAll(temp);
            getChildData(temp, parentColumn,result);
        }
    }

}
