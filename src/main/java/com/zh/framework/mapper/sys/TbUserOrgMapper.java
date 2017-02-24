package com.zh.framework.mapper.sys;

import com.zh.framework.entity.sys.TbUserOrg;
import com.zh.framework.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
/**
 * Created by ZHLenovo001 on 2017/1/5.
 */
public interface TbUserOrgMapper extends BaseMapper<TbUserOrg> {
    @Delete(value = "delete FROM tb_user_org WHERE uId=#{uId}")
    public int deleteByUser(@Param("uId") String uId);
}
