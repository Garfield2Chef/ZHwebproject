package com.zh.framework.entity.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.framework.annotation.Index;
import com.zh.framework.annotation.NoMapping;
import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;
import com.zh.framework.entity.State;
import com.zh.framework.service.sys.ResourceServiceI;


import java.util.List;

/**
 * Created by Marlon on 2016/11/16.
 */
@Table(value = "tb_resource")
public class TbResource extends DataEntity {


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
        FUNCTION,MENU
    }
    public enum Kind{
        WEB,APP
    }
    private String sParentId;	//	资源父id
    private String  sName;	//	资源名称
    private int  sType;	//	资源类型 0:菜单;1:功能
    private String  sKind;	//	资源类别 web、app
    private String sUrl;	//	资源url
    private String sIcon;	//	图标
    @Index
    private int sIndex;	//	资源排序
    private int sHide;	//	是否隐藏
    private int level;//层级
    @NoMapping
    private State state=new State();//状态
    public String getsParentId() {
        return sParentId;
    }

    public void setsParentId(String sParentId) {
        this.sParentId = sParentId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsType() {
        return sType;
    }

    public void setsType(Type sType) {
        this.sType = sType==Type.MENU?0:1;
    }
    public void setsType(int sType) {
        this.sType = sType;
    }
    public String getsKind() {
        return sKind;
    }

    public void setsKind(Kind sKind) {
        this.sKind = sKind.name().toLowerCase();
    }
    public void setsKind(String sKind) {
        this.sKind = sKind;
    }
    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public String getsIcon() {
        return sIcon;
    }

    public void setsIcon(String sIcon) {
        this.sIcon = sIcon;
    }

    public int getsIndex() {
        return sIndex;
    }

    public void setsIndex(int sIndex) {
        this.sIndex = sIndex;
    }

    public int getsHide() {
        return sHide;
    }

    public void setsHide(int sHide) {
        this.sHide = sHide;
    }
}
