package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.entity.sys.TbUserInfo;
import com.zh.framework.entity.sys.VUserInfo;
import com.zh.framework.service.BaseServiceI;

/**
 * Created by Marlon on 2016/12/30.
 */
public interface VUserInfoServiceI extends BaseServiceI<VUserInfo> {

    public int updateUserAndUserInfo(TbUser user, TbUserInfo userInfo);
}
