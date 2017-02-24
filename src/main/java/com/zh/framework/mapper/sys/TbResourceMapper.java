package com.zh.framework.mapper.sys;

import com.zh.framework.entity.sys.TbResource;
import com.zh.framework.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Mrkin on 2016/12/5.
 */
public interface TbResourceMapper extends BaseMapper<TbResource>{
    @Select(value = "select resource.*  from tb_role_user role_user LEFT JOIN tb_user user ON" +
            " user.id=role_user.uId LEFT JOIN tb_role role ON role_user.rId=role.id " +
            "LEFT JOIN tb_resources_role r_role ON r_role.rId=role.id" +
            " LEFT JOIN tb_resource resource ON resource.id=r_role.sId WHERE resource.deleteStatus=1 AND " +
            " user.id=#{userId}  AND resource.sKind=#{kind} AND resource.sType = #{type}")
    public List<TbResource> getListByUserId(@Param("userId") String userId,@Param("kind")String kind ,@Param("type") int type);


    @Select(value = "select resource.*  from tb_role_user role_user LEFT JOIN tb_user user ON" +
            " user.id=role_user.uId LEFT JOIN tb_role role ON role_user.rId=role.id " +
            "LEFT JOIN tb_resources_role r_role ON r_role.rId=role.id" +
            " LEFT JOIN tb_resource resource ON resource.id=r_role.sId WHERE resource.deleteStatus=1 AND " +
            " user.id=#{userId} AND resource.sKind=#{kind}")
    public List<TbResource> getListByTypeUserId(@Param("userId") String userId,@Param("kind")String kind);


}
