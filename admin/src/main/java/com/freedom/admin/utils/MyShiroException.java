package com.freedom.admin.utils;

import com.freedom.core.result.R;
import com.freedom.core.result.ReturnType;
import org.apache.shiro.authc.AuthenticationException;

public class MyShiroException extends AuthenticationException {

    private ReturnType returnType;

    public MyShiroException(ReturnType ce) {
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


}
