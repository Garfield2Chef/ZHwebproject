package com.zh.framework.service.sys;

import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.entity.sys.TbUserInfo;
import com.zh.framework.entity.sys.VUserInfo;
import com.zh.framework.mapper.sys.TbUserInfoMapper;
import com.zh.framework.mapper.sys.TbUserMapper;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Marlon on 2016/12/30.
 */
@Service
public class VUserInfoServiceImpl extends BaseServiceImpl<VUserInfo> implements VUserInfoServiceI{
    @Autowired
    public TbUserMapper userMapper;
    @Autowired
    public TbUserInfoMapper userInfoMapper;

    @Override
    public int updateUserAndUserInfo(TbUser user , TbUserInfo userInfo) {
        return userMapper.update(user,TbUser.class)+userInfoMapper.update(userInfo,TbUserInfo.class);
    }
}
