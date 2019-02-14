package com.freedom.core.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * 服务端返回给客户端的数据封装对象
 */
@ToString
@Data
public class JsonResult<T> implements Serializable {


    private static final long serialVersionUID = -7227854916001288491L;

    /**
     * 服务端业务逻辑是否执行成功
     */
    private Boolean success;

    /**
     * 错误编号
     */
    private String code = "";

    /**
     * 信息(如果发生错误，那么代表错误信息)
     */
    private String msg = "";

    /**
     * 返回给客户端的数据对象
     */
    private T data;

    public JsonResult() {

    }

    public JsonResult(boolean success, ReturnType returnType, T data) {
        this.success = success;
        this.code = returnType.getCode();
        this.msg = returnType.getMsgVal();
        this.data = data;
    }

    public JsonResult(boolean success, String message, T data) {
        this.success = success;
        this.code = R.FAIL.getCode();
        this.msg = message;
        this.data = data;
    }

    public JsonResult(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public static <T> JsonResult<T> success() {
        return new JsonResult<>(true, R.OK, null);
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<>(true, R.OK, data);
    }

    public static <T> JsonResult<T> success(ReturnType returnType, T data) {
        return new JsonResult<>(true, returnType, data);
    }

    public static <T> JsonResult<T> success(ReturnType returnType) {
        return new JsonResult<>(true, returnType, null);
    }

    public static <T> JsonResult<T> error() {
        return new JsonResult<>(false, R.FAIL, null);
    }

    public static <T> JsonResult<T> error(ReturnType returnType, T data) {
        return new JsonResult<>(false, returnType, data);
    }

    public static <T> JsonResult<T> error(ReturnType returnType) {
        return new JsonResult<>(false, returnType, null);
    }

    public static <T> JsonResult<T> error(String message) {
        return new JsonResult<>(false, message, null);
    }

    public static <T> JsonResult<T> error(String code, String message) {
        return new JsonResult<>(false, code, message, null);
    }
}