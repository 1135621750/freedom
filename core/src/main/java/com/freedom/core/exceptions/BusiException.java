package com.freedom.core.exceptions;

import com.freedom.core.result.ReturnType;
import lombok.extern.slf4j.Slf4j;


/**
 * 通用异常
 *
 * @author Bai
 */
@Slf4j
public class BusiException extends RuntimeException {
    private static final long serialVersionUID = -8095232506548749734L;
    private ReturnType returnType;

    public BusiException(ReturnType ce) {
        returnType = ce;
    }
    public ReturnType getCodeEnum() {
        return returnType;
    }

    public String getCode() {
        return returnType != null ? returnType.getCode() : "未知错误码";
    }

    public String getMsgKey() {
        return returnType != null ? returnType.getMsgKey() : "未知错误解析主键";
    }

    public String getMsgVal() {
        return returnType != null ? returnType.getMsgVal() : "未知错误解析解释";
    }

}
