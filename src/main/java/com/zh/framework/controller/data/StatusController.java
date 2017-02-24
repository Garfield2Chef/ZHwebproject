package com.zh.framework.controller.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.Select;
import com.zh.framework.entity.data.TbStatus;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.StaticUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fxzh on 2017/2/17.
 */
@Controller
@RequestMapping("/data/status")
public class StatusController extends BaseController<TbStatus> {

    @ResponseBody
    @RequestMapping(value = "getStatusList.do", method = RequestMethod.POST)
    public String getStatusList() {
        HttpEntity httpEntity = new HttpEntity();
        Map<String, Object> param = Maps.newHashMap();
        param.put("deleteStatus_eq", 1);
        List<Select> list= Lists.newArrayList();
        list.addAll(service.findSelectData(param,0,0,"statusName","id"));
        httpEntity.setObj(list);
        return tojson(httpEntity);
    }

    @Override
    public String saveBody(@RequestBody TbStatus t) {
        int result=0;
        if (service.getById(t.getId()) != null) {
            result=service.update(t);
        } else {
            t.setCreateTime(new Date());
            result=service.save(t);
        }
        HttpEntity httpEntity = new HttpEntity();
        if (result==1) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + t.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setMsg("保存成功");
        }else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + t.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("名称重复");
        }
        return tojson(httpEntity);
    }
}
