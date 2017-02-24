package com.zh.framework.entity.sys;

import com.zh.framework.annotation.NoMapping;
import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Marlon on 2016/11/17.
 */
@Table(value = "tb_user")
public class TbUser extends DataEntity {

    private String uName;//	账户名称
    private String uPassword;//	用户密码
    private int uLocked;//	是否锁定
    private String uDescription;//	用户描述
    private String uCredentialsSalt;//	加密盐
    private Date uLastOnLine;//	最后上线时间
    @NoMapping
    private TbUserInfo userInfo;//用户信息扩展表
    @NoMapping
    private TbOrganization organization;    // 归属公司
    @NoMapping
    private TbOrganization department;    // 归属部门

    public TbUser(String id,int deleteStatus,String uName, String uPassword, int uLocked, String uDescription, String uCredentialsSalt, Date uLastOnLine,Date createTime) {
        super.id = id;
        super.deleteStatus = deleteStatus;
        this.uName = uName;
        this.uPassword = uPassword;
        this.uLocked = uLocked;
        this.uDescription = uDescription;
        this.uCredentialsSalt = uCredentialsSalt;
        this.uLastOnLine = uLastOnLine;
        super.createTime = createTime;
    }

    public TbUser() {
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public int getuLocked() {
        return uLocked;
    }

    public void setuLocked(int uLocked) {
        this.uLocked = uLocked;
    }

    public String getuDescription() {
        return uDescription;
    }

    public void setuDescription(String uDescription) {
        this.uDescription = uDescription;
    }

    public String getuCredentialsSalt() {
        if (StringUtils.isBlank(this.uCredentialsSalt)) {
            this.uCredentialsSalt = UUID.randomUUID().toString();
        }
        return this.uCredentialsSalt;
    }

    public void setuCredentialsSalt(String uCredentialsSalt) {

        this.uCredentialsSalt = uCredentialsSalt;
    }

    public Date getuLastOnLine() {
        return uLastOnLine;
    }

    public void setuLastOnLine(Date uLastOnLine) {
        this.uLastOnLine = uLastOnLine;
    }

    public TbOrganization getDepartment() {
        return department;
    }

    public void setDepartment(TbOrganization department) {
        this.department = department;
    }

    public TbOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(TbOrganization organization) {
        this.organization = organization;
    }

    public TbUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TbUserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
