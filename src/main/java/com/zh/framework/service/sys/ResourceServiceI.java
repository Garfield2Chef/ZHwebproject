package com.zh.framework.service.sys;

import com.zh.framework.entity.Tree;
import com.zh.framework.entity.sys.TbResource;
import com.zh.framework.service.BaseServiceI;

import java.util.List;
import java.util.Map;

/**
 * Created by Mrkin on 2016/12/5.
 */
public interface ResourceServiceI extends BaseServiceI<TbResource> {
    public enum Type {
        FUNCTION, MEUN, ALL
    }

    /**
     * 根据用户id获取web资源列表
     *
     * @param userId
     * @return
     */
    public List<TbResource> getWebListByUserId(String userId, Type type);


    /**根据用户id 获取app资源列表
     * @param userId
     * @param type
     * @return
     */
    public List<TbResource> getAppListByUserId(String userId, Type type);


    /** 根据用户id获取web菜单
     * @param userId
     * @return
     */
    public List<Tree> getWebMenusById(String userId);


    /**删除数据
     * @param id
     * @return
     */
    public List<TbResource> deleteResource(String id);
}
