package com.freedom.admin.mapper;

import com.freedom.admin.model.SysUser;
import com.freedom.core.pojo.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 用户表
* @author Bai
* @date 2019/01/29 18:05
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser,Long> {
    /*
    查询用户的所有权限
     */
    List<String> queryAllPerms(Long userId);

    SysUser findByUserName(String userName);
}