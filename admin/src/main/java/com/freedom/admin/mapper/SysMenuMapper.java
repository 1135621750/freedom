package com.freedom.admin.mapper;

import com.freedom.admin.model.SysMenu;
import com.freedom.core.pojo.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 菜单
* @author Bai
* @date 2019/02/12 12:16
*/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu,Long> {
    }