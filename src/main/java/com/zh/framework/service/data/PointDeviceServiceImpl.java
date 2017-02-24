package com.zh.framework.service.data;

import com.zh.framework.entity.data.TbPointDevice;
import com.zh.framework.mapper.data.TbPointDeviceMapper;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2017/1/9.
 */
@Service
public class PointDeviceServiceImpl extends BaseServiceImpl<TbPointDevice> implements PointDeviceServiceI {
    @Override
    public int savePointDevice(List<TbPointDevice> list, String pointId) {
        ((TbPointDeviceMapper)baseMapper).deleteByPoint(pointId);
        return super.saveBatch(list);
    }

}
