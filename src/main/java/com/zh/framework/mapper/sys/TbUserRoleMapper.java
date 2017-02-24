package com.zh.framework.mapper.sys;

import com.zh.framework.entity.sys.TbUserRole;
import com.zh.framework.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Mrkin on 2016/12/15.
 */
public interface TbUserRoleMapper extends BaseMapper<TbUserRole> {
    @Delete(value = "delete FROM tb_role_user WHERE uId=#{uId}")
    public int deleteByUser(@Param("uId") String uId);
}
