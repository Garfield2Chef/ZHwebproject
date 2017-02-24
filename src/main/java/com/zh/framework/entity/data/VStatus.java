package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by fxzh on 2017/2/17.
 */
@Table("v_status")
public class VStatus  extends DataEntity {
    private String statusClaId;     //	状态分类id 36
    private String statusClaName;  //  状态分类名称
    private String statusName;      //	状态名称  100
    private int statusProcess;      //  状态进度

    public String getStatusClaId() {
        return statusClaId;
    }

    public void setStatusClaId(String statusClaId) {
        this.statusClaId = statusClaId;
    }

    public String getStatusClaName() {
        return statusClaName;
    }

    public void setStatusClaName(String statusClaName) {
        this.statusClaName = statusClaName;
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
