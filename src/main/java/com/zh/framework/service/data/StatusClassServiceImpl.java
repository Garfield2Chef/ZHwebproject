package com.zh.framework.service.data;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbStatusClass;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by fxzh on 2017/2/17.
 */
@Service
public class StatusClassServiceImpl extends BaseServiceImpl<TbStatusClass> implements StatusClassServiceI {
    @Override
    public int save(TbStatusClass t) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("statusClaName_EQ",t.getStatusClaName());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.save(t);
        }
        return 1;
    }

    @Override
    public int update(TbStatusClass t) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",t.getId());
        params.put("statusClaName_EQ",t.getStatusClaName());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.update(t);
        }
        return 1;
    }
}