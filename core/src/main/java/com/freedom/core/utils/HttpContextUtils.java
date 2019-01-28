package com.freedom.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.freedom.core.result.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpContextUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpContextUtils.class);
	/**
	 * 获取requset
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getHttpSession(){
		return getHttpServletRequest().getSession();
	}
	/**
	 * 获取response
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/** 
	* 浏览器返回信息
	* @Param: [response, result] 
	* @return: void 
	* @Author: Bai
	* @Date: 2018/11/20 
	*/
	public static void responseResult(HttpServletResponse response, JsonResult<Object> result) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setStatus(200);
		try {
			response.getWriter().write(JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue));
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
	}

	/** 
	* 取request中的key value数据封装到map 返回 
	* @Param: [] 
	* @return: java.util.Map<java.lang.String,java.lang.String> 
	* @Author: Bai
	* @Date: 2018/11/20 
	*/
	public static Map<String,String> getRequestParameters() {
		Map<String,String> dataMap = new HashMap<>();
		Enumeration enums = getHttpServletRequest().getParameterNames();
		while (enums.hasMoreElements()) {
			String paraName = (String)enums.nextElement();
			String paraValue = getHttpServletRequest().getParameter(paraName);
			if(null!=paraValue && !"".equals(paraValue)) {
				dataMap.put(paraName,paraValue);
			}
		}
		return dataMap;
	}

	/** 
	* 获取request中的body json 数据转化为map
	* @Param: [request] 
	* @return: java.util.Map<java.lang.String,java.lang.String> 
	* @Author: Bai
	* @Date: 2018/11/20 
	*/
	@SuppressWarnings("unchecked")
	public static Map<String, String> getRequestBodyMap(ServletRequest request) {
		Map<String ,String > dataMap = new HashMap<>();
		// 判断是否已经将 inputStream 流中的 body 数据读出放入 attribute
		if (request.getAttribute("body") != null) {
			// 已经读出则返回attribute中的body
			return (Map<String,String>)request.getAttribute("body");
		} else {
			try {
				Map<String,String > maps = JSON.parseObject(request.getInputStream(),Map.class);
				dataMap.putAll(maps);
				request.setAttribute("body",dataMap);
			}catch (IOException e) {
				e.printStackTrace();
			}
			return dataMap;
		}
	}

	/** 
	* key获取值
	* @Param: [request, key] 
	* @return: java.lang.String 
	* @Author: Bai
	* @Date: 2018/11/20 
	*/
	public static String getParameter(ServletRequest request, String key) {
		return getHttpServletRequest().getParameter(key);
	}

	/* *
     *  key获取头
     * @Param [request, key]
     * @Return java.lang.String
     */
	public static String getHeader(ServletRequest request, String key) {
		return getHttpServletRequest().getHeader(key);
	}

	/* *
     *  获取全部头,使用map封装
     * @Param [request]
     * @Return java.util.Map<java.lang.String,java.lang.String>
     */
	public static Map<String,String> getRequestHeaders(ServletRequest request) {
		Map<String,String> headerMap = new HashMap<>();
		Enumeration enums = getHttpServletRequest().getHeaderNames();
		while (enums.hasMoreElements()) {
			String name = (String) enums.nextElement();
			String value = getHttpServletRequest().getHeader(name);
			if (null != value && !"".equals(value)) {
				headerMap.put(name,value);
			}
		}
		return headerMap;
	}
}
