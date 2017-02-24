package com.zh.framework.entity.data;

import com.zh.framework.annotation.Id;
import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by xz on 2017/1/9.
 */
@Table(value = "tb_dict")
public class TbDict extends DataEntity implements Serializable {
    private static final long serialVersionUID = 6646291357719287131L;

    private String  dcId;//   字典分类id
    private  int    dIndex;//  排序
    private String  dName;//   名称
    private String  dPyCode;//  拼音码
    private String  dMemo;  //  备注

    public String getDcId() {
        return dcId;
    }

    public void setDcId(String dcId) {
        this.dcId = dcId;
    }

    public int getdIndex() {
        return dIndex;
    }

    public void setdIndex(int dIndex) {
        this.dIndex = dIndex;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdPyCode() {
        return dPyCode;
    }

    public void setdPyCode(String dPyCode) {
        this.dPyCode = dPyCode;
    }

    public String getdMemo() {
        return dMemo;
    }

    public void setdMemo(String dMemo) {
        this.dMemo = dMemo;
    }
}
