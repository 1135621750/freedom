package com.freedom.core.exceptions;

import com.freedom.core.result.JsonResult;
import com.freedom.core.result.R;
import com.freedom.core.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理全局异常异常信息
 * @author Bai
 *
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

	@ResponseBody
	@ExceptionHandler(value = org.apache.shiro.authz.UnauthorizedException.class)
	public JsonResult<?> handle(org.apache.shiro.authz.UnauthorizedException e) {
		log(e);
		return JsonResult.error(R.NO_PERMISSIONS);
	}

	//接口不存在
	@ResponseBody
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public JsonResult<?> handle(NoHandlerFoundException e) {
		log(e);
		return JsonResult.error(R.NOT_FOUND);
	}

	//其他异常
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public JsonResult<?> handle(Exception e) {
		log(e);
		return JsonResult.error(R.FAILURE);
	}
	//业务异常
	@ResponseBody
	@ExceptionHandler(value = BusiException.class)
	public JsonResult<?> handle(BusiException e) {
		log(e);
		return e.createJsonResult();
	}

	private static void log(Throwable e){
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		log.error("接口 [" + request.getRequestURI() + "] 发生异常",e);
	}
}
