package com.freedom.core.exceptions;

import com.freedom.core.result.JsonResult;
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

    /**
     * 默认为系统异常
     */
    private Exception cause;
    private ReturnType returnType;

    public BusiException(ReturnType ce) {
        returnType = ce;
    }
    public ReturnType getCodeEnum() {
        return returnType;
    }

    public String getCode() {
        return returnType.getCode();
    }

    public String getMsgKey() {
        return returnType.getMsgKey();
    }

    public String getMsgVal() {
        return returnType.getMsgVal();
    }

    public Exception getCause() {
        return cause;
    }

    public JsonResult createJsonResult(){
        log.error("---捕获到异常 e={},{}",returnType.getCode(),returnType.getMsgVal());
        return JsonResult.error(returnType);
    }
}
