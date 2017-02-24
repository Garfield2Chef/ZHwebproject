package com.zh.framework.entity.sys;

import com.google.common.collect.Lists;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;
import org.apache.commons.lang3.StringUtils;


import java.util.List;

/**
 * Created by Marlon on 2016/11/16.
 */
@Table(value = "tb_role")
public class TbRole extends DataEntity {

    private String rName; 	// 角色名称
    private String rDescription;//角色描述

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrDescription() {
        return rDescription;
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }
}