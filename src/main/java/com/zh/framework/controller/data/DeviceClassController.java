package com.zh.framework.controller.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.Index;
import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.DataEntity;
import com.zh.framework.entity.Select;
import com.zh.framework.entity.data.TbDeviceClass;
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
 * Created by Mrkin on 2016/12/8.
 */
@Controller
@RequestMapping("/data/deviceClass")
public class DeviceClassController extends BaseController<TbDeviceClass> {

    @ResponseBody
    @RequestMapping(value = "getList.do", method = RequestMethod.POST)
    public String getList() {
        String json = super.getGrid(null, "devClaName", "asc", 1, 10);
        HttpEntity httpEntity = (HttpEntity) parse(json, HttpEntity.class);
        return tojson(httpEntity.getObj());
    }

    @ResponseBody
    @RequestMapping(value = "getClassList.do", method = RequestMethod.POST)
    public String getClassList() {
        HttpEntity httpEntity = new HttpEntity();
        Map<String, Object> param = Maps.newHashMap();
        param.put("deleteStatus_eq", 1);
        List<Select> list=Lists.newArrayList();
        list.addAll(service.findSelectData(param,0,0,"devClaName","id"));
        httpEntity.setObj(list);
        return tojson(httpEntity);
    }

    @Override
    public String saveBody(@RequestBody TbDeviceClass tbDeviceClass) {
        int result=0;
        if (service.getById(tbDeviceClass.getId()) != null) {
            result=service.update(tbDeviceClass);
        } else {
            tbDeviceClass.setCreateTime(new Date());
            result=service.save(tbDeviceClass);
        }
        HttpEntity httpEntity = new HttpEntity();
        if (result==1) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + tbDeviceClass.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setMsg("保存成功");
        }else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + tbDeviceClass.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("分类名称重复");
        }
        return tojson(httpEntity);
    }
}
