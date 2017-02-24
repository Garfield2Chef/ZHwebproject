package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by Administrator on 2017/1/9.
 */
@Table(value = "tb_point")
public class TbPoint extends DataEntity {
    private String pExtSysFlag;
    private String dldRegion;
    private String pName;
    private String pPyCode;
    private String pAddress;
    private String pMemo;

    public String getpExtSysFlag() {
        return pExtSysFlag;
    }

    public void setpExtSysFlag(String pExtSysFlag) {
        this.pExtSysFlag = pExtSysFlag;
    }

    public String getDldRegion() {
        return dldRegion;
    }

    public void setDldRegion(String dldRegion) {
        this.dldRegion = dldRegion;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPyCode() {
        return pPyCode;
    }

    public void setpPyCode(String pPyCode) {
        this.pPyCode = pPyCode;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpMemo() {
        return pMemo;
    }

    public void setpMemo(String pMemo) {
        this.pMemo = pMemo;
    }
}
