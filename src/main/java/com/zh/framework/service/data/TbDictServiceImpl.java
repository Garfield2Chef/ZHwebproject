package com.zh.framework.service.data;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbDeviceClass;
import com.zh.framework.entity.data.TbDict;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ZHLenovo001 on 2017/1/10.
 */
@Service
public class TbDictServiceImpl  extends BaseServiceImpl<TbDict> implements TbDictServiceI {
    @Override
    public int save(TbDict tbDict) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("dcId_EQ",tbDict.getDcId());
        params.put("dName_EQ",tbDict.getdName());
        List<TbDict> list = findParams(params);
        if (list.size()>0){
            return 0;
        }else {
            super.save(tbDict);
        }
        return 1;
    }

    @Override
    public int update(TbDict tbDict) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",tbDict.getId());
        params.put("dcId_EQ",tbDict.getDcId());
        params.put("dName_EQ",tbDict.getdName());
        List<TbDict> list = findParams(params);
        if (list.size()>0) {
            return 0;
        }else {
            super.update(tbDict);
        }
        return 1;
    }
}
