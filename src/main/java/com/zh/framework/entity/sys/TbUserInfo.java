package com.zh.framework.entity.sys;

import com.zh.framework.annotation.Table;

import java.util.Date;

/**
 * Created by Mrkin on 2016/12/14.
 */
@Table(value = "tb_user_info")
public class TbUserInfo {
    private String id;//	用户id
    private String uRealName;//	真实姓名
    private int uSex;//	性别 0:男;1:女 默认0
    private Date uBirthday;//	出生日期
    private String uTelephone;//	电话
    private String uEmail;//	邮箱
    private String uAddress;//	住址
    private Date createTime;//	创建时间


    public TbUserInfo(String id, String uRealName, int uSex, Date uBirthday, String uTelephone, String uEmail, String uAddress, Date createTime) {
        this.id = id;
        this.uRealName = uRealName;
        this.uSex = uSex;
        this.uBirthday = uBirthday;
        this.uTelephone = uTelephone;
        this.uEmail = uEmail;
        this.uAddress = uAddress;
        this.createTime = createTime;
    }

    public TbUserInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuRealName() {
        return uRealName;
    }

    public void setuRealName(String uRealName) {
        this.uRealName = uRealName;
    }

    public int getuSex() {
        return uSex;
    }

    public void setuSex(int uSex) {
        this.uSex = uSex;
    }

    public Date getuBirthday() {
        return uBirthday;
    }

    public void setuBirthday(Date uBirthday) {
        this.uBirthday = uBirthday;
    }

    public String getuTelephone() {
        return uTelephone;
    }

    public void setuTelephone(String uTelephone) {
        this.uTelephone = uTelephone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
