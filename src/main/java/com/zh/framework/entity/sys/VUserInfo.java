package com.zh.framework.entity.sys;

import com.zh.framework.annotation.Table;

import java.util.Date;

@Table("v_user_info")
public class VUserInfo {
    private String id;

    private String uname;

    private String upassword;

    private Integer deletestatus;

    private Integer ulocked;

    private String udescription;

    private String ucredentialssalt;

    private String createuserid;

    private Date usercreatetime;

    private Date ulastonline;

    private String urealname;

    private Integer usex;

    private Date ubirthday;

    private String utelephone;

    private String uemail;

    private String uaddress;

    private Date userinfocreatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public Integer getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(Integer deletestatus) {
        this.deletestatus = deletestatus;
    }

    public Integer getUlocked() {
        return ulocked;
    }

    public void setUlocked(Integer ulocked) {
        this.ulocked = ulocked;
    }

    public String getUdescription() {
        return udescription;
    }

    public void setUdescription(String udescription) {
        this.udescription = udescription == null ? null : udescription.trim();
    }

    public String getUcredentialssalt() {
        return ucredentialssalt;
    }

    public void setUcredentialssalt(String ucredentialssalt) {
        this.ucredentialssalt = ucredentialssalt == null ? null : ucredentialssalt.trim();
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid == null ? null : createuserid.trim();
    }

    public Date getUsercreatetime() {
        return usercreatetime;
    }

    public void setUsercreatetime(Date usercreatetime) {
        this.usercreatetime = usercreatetime;
    }

    public Date getUlastonline() {
        return ulastonline;
    }

    public void setUlastonline(Date ulastonline) {
        this.ulastonline = ulastonline;
    }

    public String getUrealname() {
        return urealname;
    }

    public void setUrealname(String urealname) {
        this.urealname = urealname == null ? null : urealname.trim();
    }

    public Integer getUsex() {
        return usex;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }

    public Date getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(Date ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getUtelephone() {
        return utelephone;
    }

    public void setUtelephone(String utelephone) {
        this.utelephone = utelephone == null ? null : utelephone.trim();
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail == null ? null : uemail.trim();
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress == null ? null : uaddress.trim();
    }

    public Date getUserinfocreatetime() {
        return userinfocreatetime;
    }

    public void setUserinfocreatetime(Date userinfocreatetime) {
        this.userinfocreatetime = userinfocreatetime;
    }
}