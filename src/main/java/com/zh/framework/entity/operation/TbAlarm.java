package com.zh.framework.entity.operation;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by Administrator on 2017/2/13.
 */
@Table("tb_alarm")
public class TbAlarm extends DataEntity{
    private String alaNo;
    private String pId;
    private String pLevel;
    private String alaMemo;
    private Integer alaIsExtSys;
    private String alaPic1;
    private String alaPic2;
    private String alaPic3;
    private String alaPic4;
    private String alaRelateNo;
    private String dIdStatus;

    public String getAlaNo() {
        return alaNo;
    }

    public void setAlaNo(String alaNo) {
        this.alaNo = alaNo;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpLevel() {
        return pLevel;
    }

    public void setpLevel(String pLevel) {
        this.pLevel = pLevel;
    }

    public String getAlaMemo() {
        return alaMemo;
    }

    public void setAlaMemo(String alaMemo) {
        this.alaMemo = alaMemo;
    }

    public Integer getAlaIsExtSys() {
        return alaIsExtSys;
    }

    public void setAlaIsExtSys(Integer alaIsExtSys) {
        this.alaIsExtSys = alaIsExtSys;
    }

    public String getAlaPic1() {
        return alaPic1;
    }

    public void setAlaPic1(String alaPic1) {
        this.alaPic1 = alaPic1;
    }

    public String getAlaPic2() {
        return alaPic2;
    }

    public void setAlaPic2(String alaPic2) {
        this.alaPic2 = alaPic2;
    }

    public String getAlaPic3() {
        return alaPic3;
    }

    public void setAlaPic3(String alaPic3) {
        this.alaPic3 = alaPic3;
    }

    public String getAlaPic4() {
        return alaPic4;
    }

    public void setAlaPic4(String alaPic4) {
        this.alaPic4 = alaPic4;
    }

    public String getAlaRelateNo() {
        return alaRelateNo;
    }

    public void setAlaRelateNo(String alaRelateNo) {
        this.alaRelateNo = alaRelateNo;
    }

    public String getdIdStatus() {
        return dIdStatus;
    }

    public void setdIdStatus(String dIdStatus) {
        this.dIdStatus = dIdStatus;
    }
}
