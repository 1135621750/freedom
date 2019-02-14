package com.freedom.core.result;

/**
 * 状态枚举
 */
public enum R implements ReturnType {
	/**
	 * 基础
	 */
	OK("200","OK","成功"),
	FAIL("400","FAIL","失败"),
    FAILURE("500","FAILURE","服务器内部错误"),
	NOT_FOUND("404","NOT_FOUND","错误请求路径"),

	/**
	 * 账户问题
	 */
	USER_EXIST("401","USER_EXIST","该用户名已经存在"),
	USER_PWD_NULL("402","USER_PWD_NULL","密码不能为空"),
	USER_INEQUALITY("403","USER_INEQUALITY","两次密码不一致"),
	USER_OLD_PWD_ERROR("404","USER_OLD_PWD_ERROR","原来密码不正确"),
	USER_CAPTCHA_ERROR("406","USER_CAPTCHA_ERROR","验证码错误"),
	ACCOUNT_LOCKOUT("401","ACCOUNT_LOCKOUT","账号已被锁定,请联系管理员"),
	LONGIN_ERROR("1005","LONGIN_ERROR","账号或密码错误"),


	/**
	 * 权限问题
	 */
	NO_PERMISSIONS("401","NO_PERMISSIONS","权限不足！"),
	NO_ADMIN_AUTH("500","NO_ADMIN_AUTH","不允许操作超级管理员"),
	NO_ADMIN_STATUS("501","NO_ADMIN_STATUS","不能修改超级管理员状态"),
	NO_ADMINROLE_AUTH("500","NO_ADMINROLE_AUTH","不允许操作管理员角色"),
	NO_ADMINROLE_STATUS("501","NO_ADMINROLE_STATUS","不能修改管理员角色状态"),
	TOKEN_INVALID("401","TOKEN_INVALID","token invalid"),
	USER_DIDNT_EXISTED("401","USER_DIDNT_EXISTED","User didn't existed!"),

	/**
	 * 文件操作
	 */
	NO_FILE_NULL("401","NO_FILE_NULL","文件不能为空"),
	NO_FILE_TYPE("402","NO_FILE_TYPE","不支持该文件类型"),


	PARAM_ERROR("406","PARAM_ERROR","参数错误或GET,POST错误"),
	INSERT_ERROR("1000","INSERT_ERROR","新增失败"),
	ELETE_ERROR("1001","ELETE_ERROR","删除失败"),
	UPDATE_ERROR("1002","UPDATE_ERROR","修改失败"),
	SELECT_ERROR("1003","SELECT_ERROR","查询失败"),
	CONTENT_TYPE("1012","CONTENT_TYPE","Content-Type类型错误")

	;

	private String code;
	private String msgKey;
	private String msgVal;

	R(String code,String msgKey,String msgVal) {
		this.code = code;
		this.msgKey = msgKey;
		this.msgVal = msgVal;
	}
	@Override
	public String getCode() {
		return code;
	}
	@Override
	public String getMsgVal() {
		return msgVal;
	}
	@Override
	public String getMsgKey() {
		return msgKey;
	}

	  //-----------------通过枚举属性获得枚举------------工具


  	/**
  	 * 返回指定编码的'枚举'
  	 * 
  	 * @param code
  	 * @return SharedObjTypeEnum
  	 * @throws
  	 */
  	public static <T extends ReturnType> T getEnumBycode(Class<T> clazz, String code) {
  		for (T _enum : clazz.getEnumConstants()) {
  			if (code.equals(_enum.getCode())) {
  				return _enum;
  			}
  		}
  		return null;
  	}

	public static <T extends ReturnType> T getEnumByKey(Class<T> clazz, String msgKey) {
		for (T _enum : clazz.getEnumConstants()) {
			if (msgKey.equals(_enum.getMsgKey())) {
				return _enum;
			}
		}
		return null;
	}

  	/**
  	 * 返回指定描述的'枚举'
  	 * 
  	 * @param msgVal
  	 * @return SharedObjTypeEnum
  	 * @throws
  	 */
  	public static <T extends ReturnType> T getEnumByVal(Class<T> clazz, String msgVal) {
  		for (T _enum : clazz.getEnumConstants()) {
  			if (msgVal.equals(_enum.getMsgVal())) {
  				return _enum;
  			}
  		}
  		return null;
  	}


}