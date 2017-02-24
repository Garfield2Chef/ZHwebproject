package com.zh.framework.controller.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.Select;
import com.zh.framework.entity.data.TbDict;
import com.zh.framework.entity.data.TbDictClass;
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
 * Created by ZHLenovo001 on 2017/1/10.
 */
@Controller
@RequestMapping("/data/dictionaryClass")
public class TbDictClassController extends BaseController<TbDictClass> {
    @ResponseBody
    @RequestMapping(value = "getClassList.do", method = RequestMethod.POST)
    public String getClassList() {
        HttpEntity httpEntity = new HttpEntity();
        Map<String, Object> param = Maps.newHashMap();
        param.put("deleteStatus_eq", 1);
        List<Select> list= Lists.newArrayList();
        list.addAll(service.findSelectData(param,0,0,"dcName","id"));
        httpEntity.setObj(list);
        return tojson(httpEntity);
    }

    @Override
    public String saveBody(@RequestBody TbDictClass tbDictClass) {
        int result=0;
        if (service.getById(tbDictClass.getId())!= null) {
            result=service.update(tbDictClass);
        } else {
            tbDictClass.setCreateTime(new Date());
            result=service.save(tbDictClass);
        }
        HttpEntity httpEntity = new HttpEntity();
        if (result==1) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + tbDictClass.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setMsg("保存成功");
        }else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + tbDictClass.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("分类名称重复");
        }
        return tojson(httpEntity);
    }
}
