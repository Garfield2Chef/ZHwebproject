package com.zh.framework.service.sys;

import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.service.BaseServiceI;

/**
 * Created by Mrkin on 2016/11/4.
 */
public interface UserServiceI extends BaseServiceI<TbUser> {

    /**登录service
     * @param loginName 用户名
     * @return
     */
    public TbUser login(String loginName);


}
