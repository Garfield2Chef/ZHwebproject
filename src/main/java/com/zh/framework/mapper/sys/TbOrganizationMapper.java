package com.zh.framework.mapper.sys;

import com.zh.framework.entity.sys.TbOrganization;
import com.zh.framework.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by 46595 on 2016/12/4.
 */
@Repository
public interface TbOrganizationMapper extends BaseMapper<TbOrganization> {

    @Select("SELECT org.* FROM tb_org org RIGHT JOIN tb_user_org uo ON org.id=uo.oId WHERE org.deleteStatus=1 AND uo.uId=#{userId}")
    public TbOrganization getByUserId(@Param("userId") String userId);
}
