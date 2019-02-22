package com.freedom.admin.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.freedom.admin.utils.MyShiroException;
import com.freedom.core.config.MyYml;
import com.freedom.core.result.JsonResult;
import com.freedom.core.result.R;
import com.freedom.core.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 */
@Slf4j
public class SysFilter extends BasicHttpAuthenticationFilter {

    /*@Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String authorization = getToken((HttpServletRequest) request);
        return authorization != null;
    }*/
//TODO 请求的验证还没做
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        //获取请求token
        String token = getToken(WebUtils.toHttp(servletRequest));
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return StringUtils.isBlank(token) ? null : new Token(token);
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String authorization = getToken((HttpServletRequest) request);

        Token token = new Token(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try{
            getSubject(request, response).login(token);
            // 如果没有抛出异常则代表登入成功，返回true
            return true;
        }catch (Exception e){
            if(e instanceof MyShiroException){//自定义异常
                responseResult((HttpServletResponse)response,R.getEnumByKey(R.class,((MyShiroException) e).getMsgKey()));
            }else{//其他
                responseResult((HttpServletResponse)response,R.FAILURE);
            }
            return false;
        }
    }

    private void responseResult(HttpServletResponse response, R r) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(JsonResult.error(r), SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //此处可以写一些验证（如请求路径验证）
        /*if(((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }*/

        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


    /**
     * 获取请求的token
     */
    protected String getToken(HttpServletRequest request) {
        //从header中获取token
        String token = request.getHeader(SpringContextHolder.getBean(MyYml.class).getTokenName());
        return StringUtils.isBlank(token) ? null : token;
    }
}
