package com.freedom.admin.web;

import com.freedom.admin.model.User;
import com.freedom.admin.service.UserService;
import com.freedom.admin.shiro.JWTUtil;
import com.freedom.core.config.MyYml;
import com.freedom.core.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController("/user")
public class LoginController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserService service;
/*
用户登录成功后使用token获取用户专属角色[包括菜单]
点击菜单后获取菜单对应按钮权限??
 */
@RequestMapping("/login")
public JsonResult<?> find(@Validated @RequestBody User user, BindingResult result){
    user.setId(123L);
    user.setUserName("pppp");
    service.add(user);
    return JsonResult.success(user);
}

}
