package com.freedom.admin.model;

import lombok.Data;
import com.freedom.core.pojo.BaseModel;

/**
* 菜单
* @author Bai
* @date 2019/02/12 12:16
*/
@Data
public class SysMenu extends BaseModel{

    /**
    *父菜单id，1级id为0
    */
    private Long pid;
    /**
    *菜单名称
    */
    private String menuName;
    /**
    *菜单路径
    */
    private String url;
    /**
    *菜单备注
    */
    private String remark;
    /**
    *菜单授权(多个用逗号分隔，如：user:list,user:create)
    */
    private String perms;
    /**
    *类型   0：目录   1：菜单   2：按钮
    */
    private Integer type;
    /**
    *菜单图标
    */
    private String icon;
    /**
    *排序
    */
    private Integer sort;


}
