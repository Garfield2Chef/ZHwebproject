package com.zh.framework.service.sys;

import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.entity.sys.TbOrganization;
import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.mapper.sys.TbOrganizationMapper;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/11/4.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TbUser> implements UserServiceI {
    @Autowired
    private TbOrganizationMapper organizationMapper;

    @LogAnnotation(operation = "登录",content = "登录成功service",type = LogAnnotation.Type.service )
    @Override
    public TbUser login(String loginName) {
        Map<String, Object> map = new HashMap<>();
        map.put("uName_EQ", loginName);
        map.put("deleteStatus_EQ", 1);
        //map.put("uLocked_EQ", 1);
        TbUser tb_user = this.getByParam(map);
        if (tb_user != null) {
            TbOrganization tbOrganization = organizationMapper.getByUserId(tb_user.getId());
            if (tbOrganization != null) {
                if (tbOrganization.getoType() != 0) {
                    if (tbOrganization.getoParentId() != null) {
                        TbOrganization org = organizationMapper.getById(tbOrganization.getoParentId(), TbOrganization.class);
                        tb_user.setOrganization(org);
                        tb_user.setDepartment(tbOrganization);
                    }
                } else {
                    tb_user.setOrganization(tbOrganization);
                }
            }
        }
        return tb_user;
    }
    @Override
    public int save(TbUser tbUser) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("uName_EQ",tbUser.getuName());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.save(tbUser);
        }
        return 1;
    }

}
