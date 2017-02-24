package com.zh.framework.entity.sys;

import com.zh.framework.annotation.Table;

/**资源角色关联表
 * Created by Mrkin on 2016/12/8.
 */
@Table(value = "tb_resources_role")
public class TbResourceRole {
    private String sId;
    private String rId;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }
}
