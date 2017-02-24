package com.zh.framework.service.data;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbDeviceClass;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2017/1/11.
 */
@Service
public class DeviceClassServiceImpl extends BaseServiceImpl<TbDeviceClass> implements DeviceClassServiceI {
    @Override
    public int save(TbDeviceClass tbDeviceClass) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("devClaName_EQ",tbDeviceClass.getDevClaName());
       if (findParams(params).size()>0){
           return 0;
       }else {
           super.save(tbDeviceClass);
       }
        return 1;
    }

    @Override
    public int update(TbDeviceClass tbDeviceClass) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",tbDeviceClass.getId());
        params.put("devClaName_EQ",tbDeviceClass.getDevClaName());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.update(tbDeviceClass);
        }
        return 1;
    }
}
