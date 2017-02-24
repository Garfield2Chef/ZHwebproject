package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by fxzh on 2017/2/17.
 */
@Table(value = "tb_status")
public class TbStatus extends DataEntity {
    private String statusClaId;//	状态分类id 36

    private String statusName;//	状态名称  100

    private int statusProcess;//    状态进程

    public String getStatusClaId() {
        return statusClaId;
    }

    public void setStatusClaId(String statusClaId) {
        this.statusClaId = statusClaId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getStatusProcess() {
        return statusProcess;
    }

    public void setStatusProcess(int statusProcess) {
        this.statusProcess = statusProcess;
    }
}
