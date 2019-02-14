package com.freedom.admin.service;

import com.freedom.admin.mapper.SysMenuMapper;
import com.freedom.admin.mapper.SysUserMapper;
import com.freedom.admin.model.SysMenu;
import com.freedom.admin.model.SysUser;
import com.freedom.core.config.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ShiroService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    /*
    用户权限列表
     */
    public Set<String> getUserPermissions(Long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysMenu> menuList = sysMenuMapper.findByListDynamic(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenu menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserMapper.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }
    /*
    根据用户ID，查询用户
     */
    public SysUser queryUser(Long id) {
        return sysUserMapper.findById(id);
    }
}
