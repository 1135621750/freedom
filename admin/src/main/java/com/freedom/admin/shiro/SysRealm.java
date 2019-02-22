package com.freedom.admin.shiro;

import com.freedom.admin.model.SysUser;
import com.freedom.admin.service.ShiroService;
import com.freedom.admin.utils.MyShiroException;
import com.freedom.core.jwt.JWTUtil;
import com.freedom.core.result.R;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 权限认证
 */
@Component
public class SysRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token;
    }

    /**
     权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        Long userId = user.getId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证
     * Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getPrincipal();
        //token是否过期
        if(StringUtils.isBlank(token) || ! jwtUtil.verify(token)){
            throw new MyShiroException(R.TOKEN_INVALID);
        }
        //查询用户信息
        String id = jwtUtil.getId(token);
        SysUser user = shiroService.queryUser(new Long(id));
        //用户不存在
        if(user == null){
            throw new MyShiroException(R.USER_DIDNT_EXISTED);
        }
        //账号锁定
        if(!user.getStatus()){
            throw new MyShiroException(R.ACCOUNT_LOCKOUT);
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
