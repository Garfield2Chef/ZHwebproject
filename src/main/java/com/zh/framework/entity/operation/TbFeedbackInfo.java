package com.zh.framework.entity.operation;

import com.zh.framework.annotation.Id;
import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

import java.io.Serializable;

/**
 * Created by ZHLenovo001 on 2017/2/14.
 */
@Table(value = "tb_feedback_info")
public class TbFeedbackInfo extends DataEntity implements Serializable {
    private static final long serialVersionUID = -1006281530119209865L;

       private String  fInfoName;

    public String getfInfoName() {
        return fInfoName;
    }

    public void setfInfoName(String fInfoName) {
        this.fInfoName = fInfoName;
    }
}
