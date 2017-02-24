package com.zh.framework.service.data;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbDevice;
import com.zh.framework.entity.data.TbPoint;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * Created by Administrator on 2017/1/9.
 */
@Service
public class PointServiceImpl  extends BaseServiceImpl<TbPoint> implements PointServiceI {
    @Override
    public int save(TbPoint tbPoint) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("pName_EQ",tbPoint.getpName());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.save(tbPoint);
        }
        return 1;
    }

    @Override
    public int update(TbPoint tbPoint) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",tbPoint.getId());
        params.put("pName_EQ",tbPoint.getpName());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.update(tbPoint);
        }
        return 1;
    }
}
