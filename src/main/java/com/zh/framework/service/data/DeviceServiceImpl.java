package com.zh.framework.service.data;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbDevice;
import com.zh.framework.entity.data.TbDeviceClass;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2017/1/11.
 */
@Service
public class DeviceServiceImpl extends BaseServiceImpl<TbDevice> implements DeviceServiceI {
    @Override
    public int save(TbDevice tbDevice) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("devName_EQ",tbDevice.getDevName());
        params.put("devNo_EQ",tbDevice.getDevNo());
        params.put("devClaId_EQ",tbDevice.getDevClaId());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.save(tbDevice);
        }
        return 1;
    }

    @Override
    public int update(TbDevice tbDevice) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",tbDevice.getId());
        params.put("devName_EQ",tbDevice.getDevName());
        params.put("devNo_EQ",tbDevice.getDevNo());
        params.put("devClaId_EQ",tbDevice.getDevClaId());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.update(tbDevice);
        }
        return 1;
    }
}
