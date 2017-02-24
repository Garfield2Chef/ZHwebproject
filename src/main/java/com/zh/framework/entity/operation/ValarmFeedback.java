package com.zh.framework.entity.operation;

import com.zh.framework.annotation.Id;
import com.zh.framework.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZHLenovo001 on 2017/2/13.
 */
@Table(value = "v_alarm_feedback")
public class ValarmFeedback  implements Serializable {
    private static final long serialVersionUID = -6420776825790818946L;
    @Id
    private  String feeId;
    private  String alaId;
    private  String alaNo;
    private  String feeMemo;
    private  String createUserId;
    private  Date createTime;

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getAlaId() {
        return alaId;
    }

    public void setAlaId(String alaId) {
        this.alaId = alaId;
    }

    public String getAlaNo() {
        return alaNo;
    }

    public void setAlaNo(String alaNo) {
        this.alaNo = alaNo;
    }

    public String getFeeMemo() {
        return feeMemo;
    }

    public void setFeeMemo(String feeMemo) {
        this.feeMemo = feeMemo;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
