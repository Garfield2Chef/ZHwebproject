package com.zh.framework.util;

/**
 * 
 * JSON模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 * @author 陈晓亮
 * 
 */
public class HttpEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = -5405913030191680871L;

	private boolean success = true;

	private String msg = "";

	private Object obj = null;

	private String token;
	private int code=StaticUtil.CODE_SUCCEFUL;
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}