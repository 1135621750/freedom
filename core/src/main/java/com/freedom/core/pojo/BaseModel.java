package com.freedom.core.pojo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
public class BaseModel {
    private Long id;//全局主键
    private Date createTime;//新增时间
    private Date updateTime;//修改时间
    private Long createUser;//新增用户
    private Long updateUser;//修改用户
    private Boolean isDelete;//1未删除2删除
    private Boolean isDisable;//1启用2关闭
}
