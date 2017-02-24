package com.zh.framework.util;

/**
 * 静态存储类
 * Created by Mrkin on 2016/11/8.
 */
public class StaticUtil {
    public static String SESSION = "session";//session

    public static String SQL_SPLIT = "_";//SQL语句中的分离

    public static int CODE_SUCCEFUL = 200;//成功

    public static String RESETPASS="123456"; //默认密码
    /**
     * CONTROLLER异常
     */
    public static int CODE_CONTROLLER = 201;

    /**
     * token 错误
     */
    public static int CODE_ERROR_TOKEN = 202;


    /**
     * 操作失败
     */
    public static  int CODE_ERROR_FAIL=203;
    /**
     * 没有方法权限
     */
    public static int CODE_ERROR_PERMISSION = 203;
    public static String LOGIN_TYPE_WEB = "web"; //登录类型web
    public static String LOGIN_TYPE_APP = "app";//登录类型app
    public static int RESOURCE_TYPE_MENU = 0;//菜单
    public static int RESOURCE_TYPE_FUNCTION = 1;//功能
}
