package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbUserRole;
import com.zh.framework.service.BaseServiceI;

import java.util.List;

/**
 * Created by Mrkin on 2016/12/15.
 */
public interface UserRoleServiceI extends BaseServiceI<TbUserRole> {
    public int save_role(List<TbUserRole> list,String userId);
}
