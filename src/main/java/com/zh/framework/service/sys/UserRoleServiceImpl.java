package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbUserRole;
import com.zh.framework.mapper.sys.TbUserRoleMapper;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mrkin on 2016/12/15.
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<TbUserRole> implements UserRoleServiceI {

    @Override
    public int save_role(List<TbUserRole> list, String userId) {
        ((TbUserRoleMapper)baseMapper).deleteByUser(userId);
        return super.saveBatch(list);
    }
}
