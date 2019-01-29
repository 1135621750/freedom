package com.freedom.core.exceptions;

import com.freedom.core.result.JsonResult;
import com.freedom.core.result.R;
import com.freedom.core.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 捕获Contoller层的异常信息
 * @author Bai
 *
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {
	//接口不存在
	@ResponseBody
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public JsonResult<?> handle(HttpRequestMethodNotSupportedException e) {
		log(e);
		return JsonResult.error(R.PARAM_ERROR);
	}
	//接口不存在
	@ResponseBody
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public JsonResult<?> handle(HttpMessageNotReadableException e) {
		log(e);
		return JsonResult.error(R.PARAM_ERROR);
	}
	//接口不存在
	@ResponseBody
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public JsonResult<?> handle(NoHandlerFoundException e,HttpServletRequest request) {
		log.error("接口 [" + request.getRequestURI() + "] 不存在");
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
		return JsonResult.error(R.FAILURE);
	}
	/*// 捕捉shiro的异常
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = ShiroException.class)
	public JsonResult<?> handle(ShiroException e) {
		log(e);
		return JsonResult.error(R.UNAUTHORIZED);
	}
	// 捕捉UnauthorizedException
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = UnauthorizedException.class)
	public JsonResult<?> handle(UnauthorizedException e) {
		log(e);
		return JsonResult.error(R.UNAUTHORIZED);
	}*/
	private static void log(Throwable e){
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		log.error("接口 [" + request.getRequestURI() + "] 发生异常");
		log.error("异常信息---->",e);
	}
}
