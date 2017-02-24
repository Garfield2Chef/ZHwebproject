package com.zh.framework.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.Grid;
import com.zh.framework.entity.sys.TbLogInfo;
import com.zh.framework.entity.sys.VLogInfo;
import com.zh.framework.service.sys.VLogInfoServiceI;
import com.zh.framework.util.HttpEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/12/22.
 */
@Controller
@RequestMapping("/sys/log")
public class LogController extends BaseController<TbLogInfo> {
    @Autowired
    private VLogInfoServiceI vLogInfoServiceI;
    @ApiOperation(value = "获取日志数据", notes = "")
    @ResponseBody
    @RequestMapping(value = "getGrid.do", method = RequestMethod.POST)
    @Override
    public String getGrid(String params,String sidx ,String sord, final int page, int rows) {
        Map<String, Object> paramMap = Maps.newHashMap();
        Map<String, String> orderMap = Maps.newHashMap();
        if (params != null) {
            paramMap.putAll(JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
            }));
        }
        if (sidx != null) {
            orderMap.put(sidx,sord);
        }
        List<VLogInfo> list = Lists.newArrayList();
        if ((rows == 0) && paramMap.size() == 0 && orderMap.size() == 0) {
            list.addAll(vLogInfoServiceI.find());
        } else if (paramMap.size() > 0 && orderMap.size() == 0 && rows == 0) {
            list.addAll(vLogInfoServiceI.findParams(paramMap));
        } else if (paramMap.size() > 0 && orderMap.size() == 0 && rows != 0) {
            list.addAll(vLogInfoServiceI.findParams(paramMap, page, rows));
        } else if (paramMap.size() > 0 && orderMap.size() > 0 && rows == 0) {
            list.addAll(vLogInfoServiceI.findParams(paramMap, orderMap));
        } else if (paramMap.size() > 0 && orderMap.size() > 0 && rows != 0) {
            list.addAll(vLogInfoServiceI.findParams(paramMap, orderMap, page, rows));
        } else if (paramMap.size() == 0 && orderMap.size() == 0 && rows != 0) {
            list.addAll(vLogInfoServiceI.find(page, rows));
        } else if (paramMap.size() == 0 && orderMap.size() > 0 && rows == 0) {
            list.addAll(vLogInfoServiceI.find(orderMap));
        } else if (paramMap.size() == 0 && orderMap.size() > 0 && rows != 0) {
            list.addAll(vLogInfoServiceI.find(orderMap, page, rows));
        }
        long total;
        if (paramMap.size() > 0) {
            total = vLogInfoServiceI.count(paramMap);
        } else {
            total = vLogInfoServiceI.count();
        }
        long records=total;
        total=total%rows==0?total/rows:total/rows+1;
        Grid grid = new Grid();
        grid.setRows(list);
        grid.setPage(page);
        grid.setTotal(total);
        grid.setRecords(records);
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("获取成功");
        httpEntity.setObj(grid);
        return tojson(httpEntity);

    }
}
