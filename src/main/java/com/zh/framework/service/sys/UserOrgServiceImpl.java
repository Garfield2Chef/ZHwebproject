package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbUserOrg;
import com.zh.framework.mapper.sys.TbUserOrgMapper;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by ZHLenovo001 on 2017/1/5.
 */
@Service
public class UserOrgServiceImpl extends BaseServiceImpl<TbUserOrg> implements UserOrgServiceI{
    @Override
    public int save_orgnization(String userId, String orgId) {
          ((TbUserOrgMapper)baseMapper).deleteByUser(userId);
          TbUserOrg  t = new TbUserOrg();
                     t.setoId(orgId);
                     t.setuId(userId);
        return super.save(t);
    }
}
