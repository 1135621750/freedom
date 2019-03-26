package com.freedom.admin.mapper;

import com.freedom.admin.model.SysRole;
import com.freedom.core.pojo.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 角色表
* @author Bai
* @date 2019/02/12 12:16
*/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole,Long> {
    }