package com.freedom.admin.model;

import lombok.Data;
import com.freedom.core.pojo.BaseModel;

/**
* 角色表
* @author Bai
* @date 2019/02/12 12:16
*/
@Data
public class SysRole extends BaseModel{

    /**
    *角色名称
    */
    private String roleName;
    /**
    *备注
    */
    private String remark;


}
