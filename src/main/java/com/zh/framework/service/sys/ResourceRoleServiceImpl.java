package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbResourceRole;
import com.zh.framework.mapper.sys.TbResourceRoleMapper;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mrkin on 2016/12/8.
 */
@Service
public class ResourceRoleServiceImpl extends BaseServiceImpl<TbResourceRole> implements  ResourceRoleServiceI {
    @Override
    public int save_empower(List<TbResourceRole> list, String roleId) {
        ((TbResourceRoleMapper)baseMapper).deleteByRole(roleId);
        return this.saveBatch(list);
    }
}
