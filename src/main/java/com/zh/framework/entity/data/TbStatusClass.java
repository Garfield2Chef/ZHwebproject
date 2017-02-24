package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by Administrator on 2017/2/17.
 */
@Table(value = "tb_status_class")
public class TbStatusClass extends DataEntity {

    private String statusClaName;//分类名称 100

    public String getStatusClaName() {
        return statusClaName;
    }

    public void setStatusClaName(String statusClaName) {
        this.statusClaName = statusClaName;
    }
}