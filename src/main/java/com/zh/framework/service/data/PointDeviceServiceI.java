package com.zh.framework.service.data;

import com.zh.framework.entity.data.TbPoint;
import com.zh.framework.entity.data.TbPointDevice;
import com.zh.framework.service.BaseServiceI;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2017/1/9.
 */
public interface PointDeviceServiceI extends BaseServiceI<TbPointDevice> {
    public int savePointDevice(List<TbPointDevice> list, String pointId);

}
