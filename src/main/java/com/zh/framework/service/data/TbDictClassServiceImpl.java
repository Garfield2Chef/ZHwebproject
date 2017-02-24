package com.zh.framework.service.data;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbDict;
import com.zh.framework.entity.data.TbDictClass;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ZHLenovo001 on 2017/1/10.
 */
@Service
public class TbDictClassServiceImpl extends BaseServiceImpl<TbDictClass> implements TbDictClassServiceI{
    @Override
    public int save(TbDictClass tbDictClass) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("dcName_EQ",tbDictClass.getDcName());
        List<TbDictClass> list = findParams(params);
        if (list.size()>0){
            return 0;
        }else {
            super.save(tbDictClass);
        }
        return 1;
    }

    @Override
    public int update(TbDictClass tbDictClass) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("id_NEQ",tbDictClass.getId());
        params.put("dcName_EQ",tbDictClass.getDcName());
        List<TbDictClass> list = findParams(params);
        if (list.size()>0){
            return 0;
        }else {
            super.update(tbDictClass);
        }
        return 1;
    }
}
