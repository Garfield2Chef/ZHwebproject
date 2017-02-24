package com.zh.framework.controller.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.Select;
import com.zh.framework.entity.data.VDevice;
import com.zh.framework.entity.data.VDevicePoint;
import com.zh.framework.util.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Marlon on 2016/11/7.
 */

@Controller(value = "VDeviceController")
@RequestMapping("/data/vdevice")
public class VDeviceController extends BaseController<VDevice> {
    @ResponseBody
    @RequestMapping(value = "getDeviceList.do", method = RequestMethod.POST)
    public String getDeviceList() {
        HttpEntity httpEntity = new HttpEntity();
        Map<String, Object> param = Maps.newHashMap();
        param.put("deleteStatus_eq", 1);
        List<Select> list= Lists.newArrayList();
        list.addAll(service.findSelectData(param,0,0,"devName","id"));
        httpEntity.setObj(list);
        return tojson(httpEntity);
    }

}
