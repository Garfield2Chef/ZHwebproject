package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbUserOrg;
import com.zh.framework.service.BaseServiceI;

/**
 * Created by ZHLenovo001 on 2017/1/5.
 */
public interface UserOrgServiceI  extends BaseServiceI<TbUserOrg> {
    public int save_orgnization(String userId, String orgId);
}
