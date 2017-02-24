package com.zh.framework.service.operation;

import com.google.common.collect.Maps;
import com.zh.framework.entity.data.TbDict;
import com.zh.framework.entity.operation.TbFeedbackInfo;
import com.zh.framework.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ZHLenovo001 on 2017/2/14.
 */
@Service
public class TbFeedbackInfoServiceImpl extends BaseServiceImpl<TbFeedbackInfo> implements TbFeedbackInfoServiceI{

    @Override
    public int save(TbFeedbackInfo tbFeedbackInfo) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("fInfoName_EQ",tbFeedbackInfo.getfInfoName());
        List<TbFeedbackInfo> list = findParams(params);
        if (list.size()>0){
            return 0;
        }else {
            super.save(tbFeedbackInfo);
        }
        return 1;
    }

    @Override
    public int update(TbFeedbackInfo tbFeedbackInfo) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("fInfoName_EQ",tbFeedbackInfo.getfInfoName());
        params.put("id_NEQ",tbFeedbackInfo.getId());
        List<TbFeedbackInfo> list = findParams(params);
        if (list.size()>0){
            return 0;
        }else {
            super.update(tbFeedbackInfo);
        }
        return 1;
    }
}
