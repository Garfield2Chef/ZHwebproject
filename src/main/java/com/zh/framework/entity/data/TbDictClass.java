package com.zh.framework.entity.data;

import com.zh.framework.annotation.Id;
import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ZHLenovo001 on 2017/1/10.
 */
@Table(value = "tb_dict_class")
public class TbDictClass extends DataEntity implements Serializable {
    private static final long serialVersionUID = 5508349954832929370L;


    private  String  dcName; // 字典分类名称
    private  int     dclsSelf;  //字典自用标志

    public String getId() {
        if(StringUtils.isBlank(this.id)){
            this.id= UUID.randomUUID().toString();
        }
        return id;
    }
    public String getDcName() {
        return dcName;
    }

    public void setDcName(String dcName) {
        this.dcName = dcName;
    }

    public int getDclsSelf() {
        return dclsSelf;
    }

    public void setDclsSelf(int dclsSelf) {
        this.dclsSelf = dclsSelf;
    }

}
