package com.zh.framework.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.*;
import com.zh.framework.entity.DataEntity;
import com.zh.framework.entity.Grid;
import com.zh.framework.entity.sys.TbLogInfo;
import com.zh.framework.entity.sys.VLogInfo;
import com.zh.framework.service.BaseServiceI;
import com.zh.framework.service.sys.LogInfoServiceI;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.ReflectionUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 基本controller  里面包含保存、删除、逻辑删除grid、tree的方法
 * Created by Mrkin on 2016/11/3.
 */
public class BaseController<T> {
    @Autowired
    private LogInfoServiceI logInfoServiceI;
    @Autowired
    protected BaseServiceI<T> service;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
    /**
     * @param object
     * @return
     */
    public String tojson(Object object) {
        try {
            return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return "tojsonerror";
        }
    }

    /**
     * @param object
     * @param include 需要转换的属性
     * @return jsonstring
     */
    public String includetojson(Object object, String[] include) {
        try {
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(include);
            return JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return "tojsonerror";
        }
    }

    /**
     * @param object
     * @param notInclude 不需要转换的属性
     * @return jsonstring
     */
    public String notIncludetojson(Object object, String[] notInclude) {
        try {
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
            for (int i = 0; i < notInclude.length; i++) {
                filter.getExcludes().add(notInclude[i]);
            }
            return JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return "tojsonerror";
        }

    }

    @ApiOperation(value = "保存", notes = "json数据中不用传创建时间和创建人id")
    @ResponseBody
    @RequestMapping(value = "saveBody.do", method = RequestMethod.POST)
    public String saveBody(@RequestBody T t) {

        String id = this.getId(t);
        T temp = null;
        if ((temp = service.getById(id)) != null) {
            service.update(t);
        } else {
            ((DataEntity) t).setCreateTime(new Date());
            service.save(t);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        saveLog(request, "保存", "对" + t.getClass().getAnnotation(Table.class).value() + "表保存数据");
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("保存成功");
        return tojson(httpEntity);
    }

    @ApiOperation(value = "保存", notes = "json数据中不用传创建时间和创建人id")
    @ResponseBody
    @RequestMapping(value = "save.do", method = RequestMethod.POST)
    public String save(String json, String userId) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T t = (T) parse(json, c);
        String id = this.getId(t);
        if ((service.getById(id)) != null) {
            service.update(t);
        } else {
            ((DataEntity) t).setCreateTime(new Date());
            service.save(t);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        saveLog(request, "保存", "对" + c.getAnnotation(Table.class).value() + "表保存数据");
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("保存成功");
        return tojson(httpEntity);
    }

    /**
     * 非继承DataEntity的save
     *
     * @param t
     * @return
     */
    public String save(T t) {
        String id = this.getId(t);
        if (service.getById(id) != null) {
            service.update(t);
        } else {
            service.save(t);
        }
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("保存成功");
        return tojson(httpEntity);
    }

    @ApiOperation(value = "获取数据", notes = "根据id获取数据")
    @ResponseBody
    @RequestMapping(value = "getById.do", method = RequestMethod.POST)
    public String getById(String id) {
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("获取成功");
        httpEntity.setObj(service.getById(id));
        return tojson(httpEntity);
    }

    @ApiOperation(value = "真实删除数据", notes = "根据id获取数据")
    @ResponseBody
    @RequestMapping(value = "delete.do", method = RequestMethod.DELETE)
    public String delete(String id) {
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("删除成功");
        httpEntity.setObj(service.delete(id));
        return tojson(httpEntity);
    }

    @ApiOperation(value = "逻辑删除数据", notes = "根据id获取数据")
    @ResponseBody
    @RequestMapping(value = "logicDelete.do", method = RequestMethod.POST)
    public String logicDelete(String id) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("删除成功");
        httpEntity.setObj(service.deleteLogic(id));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        saveLog(request, "逻辑删除", "对" + c.getAnnotation(Table.class).value() + "表进行逻辑删除数据id:" + id);
        return tojson(httpEntity);
    }

    @ApiOperation(value = "批量逻辑删除数据", notes = "根据id获取数据")
    @ResponseBody
    @RequestMapping(value = "logicDeleteBatch.do", method = RequestMethod.POST)
    public String logicDeleteBatch(@RequestBody List<Object> ids) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("删除成功");
        httpEntity.setObj(service.deleleLogicBatch(ids));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (Object id:ids) {
            saveLog(request, "批量逻辑删除", "对" + c.getAnnotation(Table.class).value() + "表进行逻辑删除数据id:" + id);
        }
        return tojson(httpEntity);
    }


    @ApiOperation(value = "获取list数据", notes = "")
    @ResponseBody
    @RequestMapping(value = "getGrid.do", method = RequestMethod.POST)
    public String getGrid(String params, String sidx, String sord, final int page, int rows) {
        Map<String, Object> paramMap = Maps.newHashMap();
        Map<String, String> orderMap = Maps.newHashMap();
        if (params != null) {
            paramMap.putAll(JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
            }));
        }
        if (sidx != null) {
            orderMap.put(sidx, sord);
        }
        List<T> list = Lists.newArrayList();
        if ((rows == 0) && paramMap.size() == 0 && orderMap.size() == 0) {
            list.addAll(service.find());
        } else if (paramMap.size() > 0 && orderMap.size() == 0 && rows == 0) {
            list.addAll(service.findParams(paramMap));
        } else if (paramMap.size() > 0 && orderMap.size() == 0 && rows != 0) {
            list.addAll(service.findParams(paramMap, page, rows));
        } else if (paramMap.size() > 0 && orderMap.size() > 0 && rows == 0) {
            list.addAll(service.findParams(paramMap, orderMap));
        } else if (paramMap.size() > 0 && orderMap.size() > 0 && rows != 0) {
            list.addAll(service.findParams(paramMap, orderMap, page, rows));
        } else if (paramMap.size() == 0 && orderMap.size() == 0 && rows != 0) {
            list.addAll(service.find(page, rows));
        } else if (paramMap.size() == 0 && orderMap.size() > 0 && rows == 0) {
            list.addAll(service.find(orderMap));
        } else if (paramMap.size() == 0 && orderMap.size() > 0 && rows != 0) {
            list.addAll(service.find(orderMap, page, rows));
        }
        long total;
        if (paramMap.size() > 0) {
            total = service.count(paramMap);
        } else {
            total = service.count();
        }
        long records = total;
        total = total % rows == 0 ? total / rows : total / rows + 1;
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

    @ApiOperation(value = "获取tree数据", notes = "根据id获取数据")
    @ResponseBody
    @RequestMapping(value = "getTree.do", method = RequestMethod.POST)
    public String getTree() {

        return "";
    }

    /**
     * json 解析成对象
     *
     * @param json
     * @param t
     * @return
     */
    public Object parse(String json, Class t) {

        return JSON.parseObject(json, t);
    }


    /**
     * json 解析成List<T>
     *
     * @param json json数据
     * @param t    类型
     * @return
     */
    public List parseList(String json, Class t) {
        return JSON.parseArray(json, t);
    }


    /**
     * 获取id字段
     *
     * @param t
     * @return
     */
    public String getId(T t) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String id = null;
        for (Field f : ReflectionUtil.getDeclaredFields(c)) {
            String filedName = f.getName();
            f.setAccessible(true);
            Annotation annotation = f.getAnnotation(Id.class);
            //不更新不映射的属性
            if (annotation != null) {
                //1、获取属性上的指定类型的注释
                try {
                    if (f.get(t) != null)
                        id = f.get(t).toString();
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

    /** 此方法不适合增加条件过滤 如果需要增加条件过滤请重写sql语句
     * @param params
     * @param parentColum
     * @return
     */
    public String getTreeGrid(Map<String, Object> params, String parentColum) {
        HttpEntity httpEntity = new HttpEntity();
//        Map<String,Object> paramsMap=Maps.newHashMap();
//        String deleteColumn = "";
//        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        deleteColumn=ReflectionUtil.getColumn(c,LogicDelete.class);
//        if (deleteColumn!=null&&!deleteColumn.equals("")) {
//            paramsMap.put("a."+deleteColumn + "_EQ", 1);
//        }
//        paramsMap.putAll(params);
        // httpEntity.setObj(service.allTree(Maps.<String,Object>newHashMap(),"oName","oParentId", Lists.<String>newArrayList()));
        httpEntity.setObj(service.findTreeGrid(params, parentColum));
        return tojson(httpEntity);
    }


    public void saveLog(HttpServletRequest request, String operation, String content) {
        String userId = request.getParameter("userId");
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        TbLogInfo logInfo = new TbLogInfo();
        logInfo.setlContent(content);
        logInfo.setlOperation(operation);
        logInfo.setUrl(url);
        logInfo.setuId(userId);
        logInfo.setCreateTime(new Date());
        logInfo.setlIp(ip);
        if (url.contains("/app/")) {
            logInfo.setType("app");
        } else {
            logInfo.setType("web");
        }
        logInfoServiceI.save(logInfo);
    }

}
