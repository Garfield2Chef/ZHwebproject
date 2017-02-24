package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbResourceRole;
import com.zh.framework.service.BaseServiceI;

import java.util.List;

/**
 * Created by Mrkin on 2016/12/8.
 */
public interface ResourceRoleServiceI extends BaseServiceI<TbResourceRole>{
    /** 授权
     * @param list
     * @param roleId
     * @return
     */
    public int save_empower(List<TbResourceRole> list, String roleId);
}
