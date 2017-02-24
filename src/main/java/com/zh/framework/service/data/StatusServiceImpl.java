package com.zh.framework.service.data;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbStatus;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by fxzh on 2017/2/17.
 */
@Service
public class StatusServiceImpl extends BaseServiceImpl<TbStatus> implements StatusServiceI {
    @Override
    public int save(TbStatus t) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("statusName_EQ",t.getStatusName());
        params.put("statusClaId_EQ",t.getStatusClaId());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.save(t);
        }
        return 1;
    }

    @Override
    public int update(TbStatus t) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",t.getId());
        params.put("statusName_EQ",t.getStatusName());
        params.put("statusClaId_EQ",t.getStatusClaId());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.update(t);
        }
        return 1;
    }
}