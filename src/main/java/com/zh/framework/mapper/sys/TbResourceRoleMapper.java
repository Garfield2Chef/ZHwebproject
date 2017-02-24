package com.zh.framework.mapper.sys;

import com.zh.framework.entity.sys.TbResource;
import com.zh.framework.entity.sys.TbResourceRole;
import com.zh.framework.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Mrkin on 2016/12/8.
 */
public interface TbResourceRoleMapper extends BaseMapper<TbResourceRole> {
    @Delete(value = "delete FROM tb_resources_role WHERE rId=#{roleId}")
    public int deleteByRole(@Param("roleId") String roleId);
}
