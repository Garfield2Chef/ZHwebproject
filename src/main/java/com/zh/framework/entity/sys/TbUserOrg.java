package com.zh.framework.entity.sys;

import com.zh.framework.annotation.Table;

/** 用户单位管理表
 * Created by Mrkin on 2016/12/8.
 */
@Table(value = "tb_user_org")
public class TbUserOrg {
    private String uId;
    private String oId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }
}
