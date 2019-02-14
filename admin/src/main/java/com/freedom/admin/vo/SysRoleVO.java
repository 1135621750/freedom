package com.freedom.admin.vo;

import com.freedom.core.pojo.Limit;
import lombok.Data;

/**
* 角色表
* @author Bai
* @date 2019/02/12 12:16
*/
@Data
public class SysRoleVO extends Limit{

    /**
    *角色名称
    */
    private String roleName;
    /**
    *备注
    */
    private String remark;


}