package com.zh.framework.entity.sys;

import com.zh.framework.annotation.NoMapping;
import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;
import com.zh.framework.entity.State;


import java.util.List;

/**
 * Created by Marlon on 2016/11/16.
 */
@Table(value = "tb_org")
public class TbOrganization extends DataEntity {
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum Type{
        ORG,DEPARTMENT
    }
    private int oType;//	单位类型 0：单位；1：部门
    private String oName;//	单位名称
    private String oParentId;//	父id
    private String oParentOrgId;//	单位父id
    private String oShortName;//	单位简称
    private String oAddress;//	单位地址
    private String oContact;//	联系人
    private String oTel;//	联系电话
    private int level;//层级
    @NoMapping
    private State state=new State();//状态
    public int getoType() {
        return oType;
    }
    public void setoType(int oType) {
        this.oType =oType;
    }
    public void setoType(Type oType) {
        this.oType = (oType==Type.ORG?0:1);
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getoParentId() {
        return oParentId;
    }

    public void setoParentId(String oParentId) {
        this.oParentId = oParentId;
    }

    public String getoParentOrgId() {        return oParentOrgId;    }

    public void setoParentOrgId(String oParentOrgId) {        this.oParentOrgId = oParentOrgId;    }

    public String getoShortName() {
        return oShortName;
    }

    public void setoShortName(String oShortName) {
        this.oShortName = oShortName;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    public String getoContact() {
        return oContact;
    }

    public void setoContact(String oContact) {
        this.oContact = oContact;
    }

    public String getoTel() {
        return oTel;
    }

    public void setoTel(String oTel) {
        this.oTel = oTel;
    }
}
