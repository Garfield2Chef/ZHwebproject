package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by Mrkin on 2017/1/10.
 */
@Table(value = "tb_device_class")
public class TbDeviceClass extends DataEntity {
    private String devClaName;//分类名称 100
    private  String  devClaMemo;// 备注  200

    public String getDevClaName() {
        return devClaName;
    }

    public void setDevClaName(String devClaName) {
        this.devClaName = devClaName;
    }

    public String getDevClaMemo() {
        return devClaMemo;
    }

    public void setDevClaMemo(String devClaMemo) {
        this.devClaMemo = devClaMemo;
    }
}
