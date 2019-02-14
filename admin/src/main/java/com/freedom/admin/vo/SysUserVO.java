package com.freedom.admin.vo;

import com.freedom.core.pojo.Limit;
import lombok.Data;

/**
* 用户表
* @author Bai
* @date 2019/01/29 18:05
*/
@Data
public class SysUserVO extends Limit{

    /**
    *用户名
    */
    private String userName;
    /**
    *昵称
    */
    private String nickName;
    /**
    *密码
    */
    private String password;
    /**
    *盐
    */
    private String salt;
    /**
    *邮箱
    */
    private String email;
    /**
    *手机号
    */
    private String mobile;
    /**
    *头像
    */
    private String picture;
    /**
    *性别0:未设置1:男2:女
    */
    private Integer sex;


}