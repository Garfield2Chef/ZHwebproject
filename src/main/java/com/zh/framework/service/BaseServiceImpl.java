package com.zh.framework.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.Index;
import com.zh.framework.entity.DataEntity;
import com.zh.framework.entity.Select;
import com.zh.framework.entity.Tree;
import com.zh.framework.mapper.BaseMapper;
import com.zh.framework.sql.BaseSql;
import com.zh.framework.util.ReflectionUtil;
import com.zh.framework.util.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/10/28.
 */
@Service
public class BaseServiceImpl<T> implements BaseServiceI<T> {
    private Sort sort = Sort.ASC;
    @Autowired
    public BaseMapper<T> baseMapper;

    public T getById(String Id) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.getById(Id, c);
    }

    @Override

    public List<T> find() {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.find(c);
    }

    @Override
    public List<T> find(Map<String, String> order) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findOrder(c, order);
    }


    @Override
    public List<T> find(Map<String, String> order, int page, int rows) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return baseMapper.findPageAndOrder(c, page, rows, order);
    }

    @Override
    public List<T> find(int page, int rows) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findPage(c, page, rows);
    }

    @Override
    public List<T> findParams(Map<String, Object> params) {
        updateParams(params);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findParam(params, c);
    }

    @Override
    public List<T> findParams(Map<String, Object> params, Map<String, String> order) {
        updateParams(params);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findParamAndOrder(params, c, order);
    }

    @Override
    public List<T> findParams(Map<String, Object> params, int page, int rows) {
        updateParams(params);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        baseMapper.findParamAndPage(params, page, rows, c);
        return null;
    }

    @Override
    public List<T> findParams(Map<String, Object> params, Map<String, String> order, int page, int rows) {
        updateParams(params);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.findParamAndOrderAndPage(params, page, rows, c, order);
    }

    @Override
    public int delete(String id) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.delete(id, c);
    }

    @Override
    public int save(T t) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.insert(t, c);
    }

    @Override
    public int update(T t) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.update(t, c);
    }

    @Override
    public T getByParam(Map<String, Object> map) {
        updateParams(map);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        List<T> list = baseMapper.findParam(map, c);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public int saveBatch(List<T> list) {
        int count = 0;
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        for (int i = 0; i < list.size(); i++) {
            if (baseMapper.insert(list.get(i), c) > 0) ;
            count++;
        }
        return count;
    }

    @Override
    public int deleleBatch(List<Object> ids) {
        String result = "";
        for (Object id : ids) {
            if (id instanceof String) {
                result += "'" + id + "',";
            } else {
                result += "" + id + ",";
            }
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.deleleBatch(result, c);
    }

    @Override
    public int deleleLogicBatch(List<Object> ids) {
        String result = "";
        for (Object id : ids) {
            if (id instanceof String) {
                result += "'" + id + "',";
            } else {
                result += "" + id + ",";
            }
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.deleteLogicBatch(result,c);
    }

    @Override
    public long count() {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.count(c);
    }

    @Override
    public long count(Map<String, Object> map) {
        updateParams(map);
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.countParam(map, c);
    }

    @Override
    public List<Tree> allTree(Map<String, Object> params, String textColumn, String parentColumn, List<String> showColumn) {
        params.put(parentColumn + "_NU", "");
        List<Tree> trees = Lists.newArrayList();
        trees.addAll(getRecursiveTree("", params, textColumn, parentColumn, showColumn));
        return trees;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    //排序
    public enum Sort {
        ASC, DESC
    }

    /**
     * 获取一层树的方法
     *
     * @param id
     * @param params
     * @param textColumn
     * @param parentColumn
     * @param showColumn
     * @return
     */
    public List<Tree> getTree(String id, Map<String, Object> params, String textColumn, String parentColumn, List<String> showColumn) {
        Map<String, String> orders = Maps.newHashMap();
        orders.putAll(getTreeOrders());
        List<Tree> trees = Lists.newArrayList();
        trees.addAll(getTrees(id, params, textColumn, parentColumn, showColumn, orders, false));
        return trees;
    }

    @Override
    public int deleteLogic(String id) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseMapper.logicDelete(id, c);
    }

    @Override
    public List<T> findTreeGrid(Map<String, Object> params, String parentColumn) {
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        List<T> list = baseMapper.findTreeGridByParams(params, parentColumn, c);
        List<T> result = Lists.newArrayList();
        List<T> root = Lists.newArrayList();
        //第一次获取所有根节点数据
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (ReflectionUtil.getValue(t, parentColumn) == null) {
                root.add(t);
                list.remove(t);//删除数据
                i--;
            }
        }
        for (T t : root) {
            result.add(t);
            sortTrees(list, t, parentColumn, result);
        }
        return result;
    }

    @Override
    public List<Select> findSelectData(Map<String, Object> params, int page, int rows, String textColumn,String idColumn) {
        List<T> list=Lists.newArrayList();
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (page==0){
            list.addAll(baseMapper.findParam(params,c));
        }else{
            list.addAll(baseMapper.findParamAndPage(params,page,rows,c));
        }
        List<Select> result=Lists.newArrayList();
        for (T t:list){
            Select select=new Select();
            select.setId(ReflectionUtil.getValue(t,idColumn).toString());
            select.setText(ReflectionUtil.getValue(t,textColumn).toString());
            result.add(select);
        }
        return result;
    }

    /**
     * 递归追加孩子数据
     *
     * @param list
     * @param root
     * @param parentColumn
     * @param result
     */
    public void sortTrees(List<T> list, T root, String parentColumn, List<T> result) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (ReflectionUtil.getValue(t, parentColumn)
                    .equals(ReflectionUtil.getValue(root, new BaseSql().getIdName(root.getClass())))) {
                result.add(t);
                list.remove(t);
                i=0;
                sortTrees(list, t, parentColumn, result);
            }
        }
    }


    /**
     * 获取所有树的方法
     *
     * @param id
     * @param params
     * @param textColumn
     * @param parentColumn
     * @param showColumn
     * @return
     */
    public List<Tree> getRecursiveTree(String id, Map<String, Object> params, String textColumn, String parentColumn, List<String> showColumn) {

        Map<String, String> orders = Maps.newHashMap();
        List<Tree> trees = Lists.newArrayList();
        orders.putAll(getTreeOrders());
        trees.addAll(getTrees(id, params, textColumn, parentColumn, showColumn, orders, true));
        return trees;
    }


    /**
     * 获取index的条件
     *
     * @return
     */
    public Map<String, String> getTreeOrders() {
        Map<String, String> orders = Maps.newHashMap();
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        for (Field field : ReflectionUtil.getDeclaredFields(c)) {
            if (field.getAnnotation(Index.class) != null) {
                orders.put(field.getName(), this.getSort().name());
                break;
            }
        }
        return orders;
    }

    private List<Tree> getTrees(String id, Map<String, Object> params, String textColumn, String parentColumn,
                                List<String> showColumn, Map<String, String> orders, boolean isAll) {
        List<T> list = Lists.newArrayList();
        List<Tree> trees = Lists.newArrayList();
        Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (id == null || id.equals("")) {
            params.remove(parentColumn + "_EQ");
            params.put(parentColumn + "_NL", id);
        } else {
            params.remove(parentColumn + "_NL");
            params.put(parentColumn + "_EQ", id);
        }
        list.addAll(this.findParams(params, orders));

        for (int i = 0; i < list.size(); i++) {
            Tree tree = new Tree();
            for (Field field : ReflectionUtil.getDeclaredFields(c)) {
                field.setAccessible(true);
                if (field.getName().equals(new BaseSql<T>().getIdName(c))) {
                    try {
                        tree.setId(field.get(list.get(i)).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (field.getName().equals(parentColumn)) {
                    try {
                        tree.setParentId(field.get(list.get(i)) == null ? null : field.get(list.get(i)).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (field.getName().equals(textColumn)) {
                    try {
                        tree.setText(field.get(list.get(i)) == null ? null : field.get(list.get(i)).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                for (String column : showColumn) {
                    try {
                        if (column == field.getName()) {
                            tree.getMap().put(column, field.get(list.get(i)) == null ? null : field.get(list.get(i)).toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Map<String, Object> newParams = Maps.newHashMap();
            newParams.putAll(params);
            newParams.remove(parentColumn + "_NL");
            newParams.put(parentColumn + "_EQ", ((DataEntity) list.get(i)).getId());
            if (this.count(newParams) > 0) {
                tree.getState().setExpanded(true);
                if (isAll) {
                    tree.setNodes(this.getTrees(((DataEntity) list.get(i)).getId(), params, textColumn, parentColumn, showColumn, orders, isAll));
                }
            } else {
                tree.setNodes(null);
                tree.getState().setExpanded(false);
            }
            trees.add(tree);
        }
        return trees;
    }

    /**更新一下like参数值
     * @param params
     */
    public void updateParams(Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String[] conditions = entry.getKey().split(StaticUtil.SQL_SPLIT);
            String condiction = conditions[1].toUpperCase();
            switch (condiction) {
                case "LK":
                    params.put(entry.getKey(), "%" + entry.getValue() + "%");
                    break;
            }
        }
    }



   /* public  void setBaseMapper(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }*/
}
