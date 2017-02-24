package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by Mrkin on 2017/1/11.
 */
@Table("v_device_point")
public class VDevicePoint extends DataEntity {
    private String devClaName;//项目分类名称
    private String pId;//点位Id
    private String devClaId;//	设备分类id 36
    private String devNo;//	设备编号  100
    private String devName;//	设备名称  100

    public String getDevClaName() {
        return devClaName;
    }

    public void setDevClaName(String devClaName) {
        this.devClaName = devClaName;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getDevClaId() {
        return devClaId;
    }

    public void setDevClaId(String devClaId) {
        this.devClaId = devClaId;
    }

    public String getDevNo() {
        return devNo;
    }

    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }
}
