package com.freedom.admin.model;

import com.freedom.core.pojo.BaseModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class User extends BaseModel{
    @NotNull(message = "用户名称不能为null")
    private String userName;
    @NotNull(message = "密码不能为null")
    private String password;
    private String permission;//权限
    private String role;//角色

}
