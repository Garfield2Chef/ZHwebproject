package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;

/**
 * Created by Mrkin on 2017/1/11.
 */
@Table("tb_point_device")
public class TbPointDevice {
    private String pId;//点位id
    private String devId;//设备id

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }
}
