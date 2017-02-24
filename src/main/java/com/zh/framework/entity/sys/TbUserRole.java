package com.zh.framework.entity.sys;

import com.zh.framework.annotation.Table;

/** 用户角色关联表
 * Created by Mrkin on 2016/12/8.
 */
@Table(value = "tb_role_user")
public class TbUserRole {
    private String uId;
    private String rId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }
}
