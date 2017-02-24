package com.zh.framework.service.data;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbLevel;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Mrkin on 2017/1/11.
 */
@Service
public class LevelServiceImpl extends BaseServiceImpl<TbLevel> implements LevelServiceI {
    @Override
    public int save(TbLevel tbLevel) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("level_EQ",tbLevel.getLevel());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.save(tbLevel);
        }
        return 1;
    }

    @Override
    public int update(TbLevel tbLevel) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",tbLevel.getId());
        params.put("level_EQ",tbLevel.getLevel());
        if (findParams(params).size()>0){
            return 0;
        }else {
            super.update(tbLevel);
        }
        return 1;
    }
}
