package com.zh.framework.controller.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.Select;
import com.zh.framework.entity.data.TbLevel;
import com.zh.framework.entity.sys.TbResource;
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
@RequestMapping("/data/level")
public class LevelController extends BaseController<TbLevel> {

    @Override
    public String saveBody(@RequestBody TbLevel tbLevel) {
        int result;
        if (service.getById(tbLevel.getId()) != null) {
            result=service.update(tbLevel);
        } else {
            tbLevel.setCreateTime(new Date());
            result=service.save(tbLevel);
        }
        HttpEntity httpEntity = new HttpEntity();
        if (result==1) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + tbLevel.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setMsg("保存成功");
        }else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + tbLevel.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("该等级已存在");
        }
        return tojson(httpEntity);
    }

    @LogAnnotation(content = "修改是否推送", operation = "修改")
    @ResponseBody
    @RequestMapping(value = "updatePush.do", method = RequestMethod.POST)
    public String updatePush(@RequestBody String id){
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("修改成功");
        TbLevel tbLevel=service.getById( id);
        if (tbLevel.getIsPush()==0){
            tbLevel.setIsPush(1);
        }else {
            tbLevel.setIsPush(0);
        }
        service.update(tbLevel);
        return tojson(httpEntity);
    }

    @ResponseBody
    @RequestMapping(value = "getLevelList.do", method = RequestMethod.POST)
    public String getLevelList() {
        HttpEntity httpEntity = new HttpEntity();
        Map<String, Object> param = Maps.newHashMap();
        param.put("deleteStatus_eq", 1);
        List<Select> list= Lists.newArrayList();
        list.addAll(service.findSelectData(param,0,0,"level","id"));
        httpEntity.setObj(list);
        return tojson(httpEntity);
    }
}
