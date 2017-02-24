package com.zh.framework.service;
import com.zh.framework.entity.Select;
import com.zh.framework.entity.Tree;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/10/28.
 * 查询条件连接不区分大小写
 * params key->value key field_symbol  demo(username_EQ)查询用户名等于 value
 *  like demo(username_like)
 *  EQ 等于 NEQ 不等于  GT 大于 GTE 大于等于 LT 小于 LTE 小于等于  NL 是空  NN 不为空
 *  IN in NIN not in demo(username_IN)(username_NIN)  value 字符串'1','2','3'  int或者long  1,2,3
 *  不区分大小写 但尽量使用大写
 *  or条件查询 暂时不支持
 *  局部条件添加不支持(括号添加)
 *  不支持between 查询
 */
public interface BaseServiceI<T> {

    /**
     * @param Id
     * @return T
     */
    public T getById(String Id);

    /**查询所有数据
     * @return
     */
    public List<T> find();

    /**查询所有数据并排序
     * @param order key 属性名(列名)  value  asc/desc
     * @return
     */
    public List<T> find(Map<String, String> order);
    /**分页查询 排序
     * @param order key 属性名(列名)  value  asc/desc
     * @param page 页数
     * @param rows 查询一页的条数
     * @return
     */
    public List<T> find(Map<String, String> order, int page, int rows);
    /**
     * @param page 页数
     * @param rows 查询一页的条数
     * @return
     */
    public List<T> find(int page, int rows);
    /**
     * @param params key->value key field_symbol  demo(username_=)查询用户名等于 value
     * @return
     */
    public List<T> findParams(Map<String, Object> params);
    /**根据条件查询
     * @param params key->value key field_symbol  demo(username_=)查询用户名等于 value
     * @param order 排序字段 升降  key->value(column+desc/asc)或者(column)
     * @return
     */
    public List<T> findParams(Map<String, Object> params, Map<String, String> order);

    /**根据条件和分页查询
     * @param params
     * @param page
     * @param rows
     * @return
     */
    public List<T> findParams(Map<String, Object> params, int page, int rows);
    /**根据条件、排序、分页查询
     * @param params key->value key field_symbol  demo(username_=)查询用户名等于 value
     * @param order 排序字段 升降  key->value(column+desc/asc)或者(column)
     * @param page 页数
     * @param rows 查询一页的条数
     * @return
     */
    public List<T> findParams(Map<String, Object> params, Map<String, String> order, int page, int rows);

    /**删除
     * @param id
     * @return
     */
    public int delete(String id);

    /** 插入数据
     * @param t
     * @return
     */
    public int save(T t);

    /**更新数据
     * @param t
     * @return
     */
    public int update(T t);

    /**根据条件
     * @param map
     * @return
     */
    public T getByParam(Map<String, Object> map);

    /**批量插入
     * @param list
     * @return
     */
    public int saveBatch(List<T> list);

    /** 批量删除
     * @param ids
     * @return
     */
    public int deleleBatch(List<Object> ids);

    /** 批量逻辑删除
     * @param ids
     * @return
     */
    public int deleleLogicBatch(List<Object> ids);

    /**获取数量
     * @return
     */
    public long count();

    /**获取数量
     * @param params 查询条件
     * @return
     */
    public long count(Map<String, Object> params);

    /**获取所有树
     * @param params 查询条件
     * @param parentColumn
     * @param showColumn
     * @return
     */
    public List<Tree> allTree(Map<String,Object> params,String textColumn, String parentColumn, List<String> showColumn);


    /** 获取一层树
     * @param id
     * @param params
     * @param textColumn
     * @param parentColumn
     * @param showColumn
     * @return
     */
    public List<Tree> getTree(String id,Map<String, Object> params,String textColumn, String parentColumn, List<String> showColumn);

    /**逻辑删除 删除标志位需要添加LogicDelete注解
     * @param id
     * @return
     */
    public int deleteLogic(String id);


    /** TreeGrid 数据加载
     * @param params
     * @param parentColumn 父节id字段名称
     * @return
     */
    public List<T> findTreeGrid(Map<String, Object> params,String parentColumn);


    /** 查询数据只显示id和name
     * @param params
     * @param page
     * @param rows
     * @param textColumn 显示字段
     * @param idColumn id
     * @return
     */
    public List<Select> findSelectData(Map<String, Object> params,int page,int rows,String textColumn,String idColumn);
}