package com.freedom.core.result;

/**
 * 状态枚举
 */
public enum R implements ReturnType {

	OK("200","","成功"),
	FAIL("400","","失败"),
    FAILURE("500","failure","服务器内部错误"),
	NOT_FOUND("404","error","错误请求路径"),
	PARAM_ERROR("406","param.error","参数错误或GET,POST错误"),
    UNAUTHORIZED("401", "unauthorized","签名认证失败,请联系管理员"),
	INSERT_ERROR("10003", "insert.error","新增失败"),
	ELETE_ERROR("10004", "delete.error","删除失败"),
	UPDATE_ERROR("10005", "update.error","修改失败"),
	SELECT_ERROR("10006", "select.error","查询失败"),
	LONGIN_ERROR("10007","longin.error","登录失败,账号或密码错误"),
	LOGIN("10008","login","请登录"),
	LOGIN_DATE("10009","login_date","登录过期，请从新登录"),
	IS_LOGIN("10010","is.login","账号已在它处登录,请从新登录"),
	UNAUTHZ("4403","unauthz","未授权,拒接访问");

	private String code;
	private String msgKey;
	private String msgVal;

	R(String code, String msgKey, String msgVal) {
		this.code = code;
		this.msgKey = msgKey;
		this.msgVal = msgVal;
	}
	@Override
	public String getCode() {
		return code;
	}
	@Override
	public String getMsgKey() {
		return msgKey;
	}
	@Override
	public String getMsgVal() {
		return msgVal;
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

  	/**
  	 * 返回指定名称的'枚举'
  	 * 
  	 * @param msgKey
  	 * @return SharedObjTypeEnum
  	 * @throws
  	 */
  	public static <T extends ReturnType> T getEnumByName(Class<T> clazz, String msgKey) {
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
  	public static <T extends ReturnType> T getEnumByDesc(Class<T> clazz, String msgVal) {
  		for (T _enum : clazz.getEnumConstants()) {
  			if (msgVal.equals(_enum.getMsgVal())) {
  				return _enum;
  			}
  		}
  		return null;
  	}


}