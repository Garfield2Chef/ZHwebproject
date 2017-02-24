package com.zh.framework.controller.data;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.Table;
import com.zh.framework.entity.Select;
import com.zh.framework.entity.data.TbPoint;
import com.zh.framework.entity.data.TbPointDevice;
import com.zh.framework.service.BaseServiceI;
import com.zh.framework.service.data.PointDeviceServiceI;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.StaticUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zh.framework.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by Administrator on 2017/1/9.
 */
@Controller
@RequestMapping("/data/point")
public class PointController extends BaseController<TbPoint>{
    @Autowired
    private PointDeviceServiceI  pointDeviceService;

    @ApiOperation(value = "点位添加", notes = "")
    @ResponseBody
    @RequestMapping(value = "savePoint.do", method = RequestMethod.POST)
    public String savePoint(String pointJson,String deviceJson) {
        TbPoint point  = (TbPoint) parse(pointJson, TbPoint.class);

            point.setCreateTime(new Date());
            point.setId(UUID.randomUUID().toString());
            super.save(point);

        String tempString =deviceJson.replaceAll("rep",point.getId());
        List<TbPointDevice> list = parseList(tempString, TbPointDevice.class);
        int count = pointDeviceService.savePointDevice(list, point.getId());
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setObj(count);
        httpEntity.setMsg("保存成功!");
        return tojson(httpEntity);

    }
    @ApiOperation(value = "更新添加", notes = "")
    @ResponseBody
    @RequestMapping(value = "updatePoint.do", method = RequestMethod.POST)
    public String updatePoint(String pointJson,String deviceJson) {
        int result=0;
        HttpEntity httpEntity = new HttpEntity();
        TbPoint point  = (TbPoint) parse(pointJson, TbPoint.class);
       // super.save(point);
        if(service.getById(point.getId())!=null){
            result=service.update(point);
        }else
        {
            result=service.save(point);

        }
        if(result==1){
            String tempString =deviceJson.replaceAll("rep",point.getId());
            List<TbPointDevice> list = parseList(tempString, TbPointDevice.class);
            int count = pointDeviceService.savePointDevice(list, point.getId());
            httpEntity.setObj(count);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + point.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setMsg("保存成功");
        }else
        {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + point.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("点位名称重复");
        }

        return tojson(httpEntity);

    }
    @ApiOperation(value = "获取某个点位下的设备", notes = "")
    @ResponseBody
    @RequestMapping(value = "getPointDevice.do", method = RequestMethod.POST)
    public String getRoleResource(String pointId) {
        Map<String,Object> params= Maps.newHashMap();
        params.put("pId_EQ",pointId);
        HttpEntity httpEntity=new HttpEntity();
        httpEntity.setObj(pointDeviceService.findParams(params));
        return tojson(httpEntity);
    }

    @ResponseBody
    @RequestMapping(value = "getPointList.do", method = RequestMethod.POST)
    public String getPointList() {
        HttpEntity httpEntity = new HttpEntity();
        Map<String, Object> param = Maps.newHashMap();
        param.put("deleteStatus_eq", 1);
        List<Select> list= Lists.newArrayList();
        list.addAll(service.findSelectData(param,0,0,"pName","id"));
        httpEntity.setObj(list);
        return tojson(httpEntity);
    }
}
