package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by Mrkin on 2017/1/11.
 */
@Table(value = "tb_device")
public class TbDevice extends DataEntity {
    private String devClaId;//	设备分类id 36
    private String devNo;//	设备编号  100
    private String devName;//	设备名称  100

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
