package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbOrganization;
import com.zh.framework.service.BaseServiceI;

import java.util.List;

/**
 * Created by Mrkin on 2016/12/12.
 */
public interface OrganizationServiceI extends BaseServiceI<TbOrganization> {
    public TbOrganization getByUserId(String userId);
    public List<TbOrganization> deleteOrganization(String id);
}
