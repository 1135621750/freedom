package com.freedom.admin.service;

import com.freedom.admin.model.SysRole;
import com.freedom.admin.mapper.SysRoleMapper;
import com.freedom.core.pojo.BaseService;
import com.freedom.core.pojo.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
* 角色表
* @author Bai
* @date 2019/02/12 12:16
*/
@Service
public class SysRoleService extends BaseService{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /*
    * 分页查询
    */
    public PageUtils queryAll(SysRole sysRole, int pageNum, int pageSize, String orderBy) throws Exception{
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<SysRole> info = new PageInfo<>(sysRoleMapper.findByListDynamic(sysRole));
        return new PageUtils(info);
    }
    /*
    *主键查询
    */
    public SysRole queryById(Long id) throws Exception {
        return sysRoleMapper.findById(id);
    }
    /*
    *新增
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void add(SysRole sysRole) throws Exception{
        sysRoleMapper.insertDynamic(sysRole);
    }
    /*
    *修改
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void update(SysRole sysRole) throws Exception {
        sysRoleMapper.updateDynamic(sysRole);
    }
    /*
    *删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) throws Exception {
        sysRoleMapper.remove(id);
    }
    /*
    * 批量删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void deleteAll(Long[] ids)throws Exception {
        sysRoleMapper.batchRemove(ids);
    }

}