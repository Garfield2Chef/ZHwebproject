package com.zh.framework.mapper.data;

import com.zh.framework.entity.data.TbPointDevice;
import com.zh.framework.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Mrkin on 2017/1/11.
 */
@Repository
public interface TbPointDeviceMapper extends BaseMapper<TbPointDevice> {
    @Delete(value = "delete FROM tb_point_device WHERE pId=#{pId}")
    public int deleteByPoint(@Param("pId") String pId);
}
