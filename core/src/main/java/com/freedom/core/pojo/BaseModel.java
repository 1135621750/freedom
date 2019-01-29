package com.freedom.core.pojo;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 统一数据库必有字段
 */
@Data
public class BaseModel implements Serializable{
    private Long id;//全局主键
    private Date createTime;//新增时间
    private Date updateTime;//修改时间
    private Long createUser;//新增用户
    private Long updateUser;//修改用户
    private Boolean isDelete;//0删除1未删除
    private Boolean status;//0禁用1启用
}
