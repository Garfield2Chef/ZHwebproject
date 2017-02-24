package com.zh.framework.entity.operation;

import com.zh.framework.annotation.Id;
import com.zh.framework.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fxzh on 2017/2/13.
 */
@Table(value = "v_sign")
public class VSign implements Serializable {
    private static final long serialVersionUID = -4525284223917448101L;
    @Id
    private String signId;
    private String uName;
    private Date createTime;//	创建日期
    private String pName;

    public String getSignId(){
        return signId;
    }
    public void setSignId (String signId){
        this.signId = signId;
    }

    public String getUName() {
        return uName;
    }
    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getPName() {
        return pName;
    }
    public void setPName(String pName) {
        this.pName = pName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
