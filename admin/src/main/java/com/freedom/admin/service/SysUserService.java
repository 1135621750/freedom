package com.freedom.admin.service;

import cn.hutool.core.util.ObjectUtil;
import com.freedom.admin.mapper.SysUserMapper;
import com.freedom.admin.model.SysUser;
import com.freedom.core.pojo.BaseService;
import com.freedom.core.pojo.PageUtils;
import com.freedom.core.utils.IdWorker;
import com.freedom.core.utils.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
* 用户表
* @author Bai
* @date 2019/01/29 18:05
*/
@Service
public class SysUserService extends BaseService{

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser queryUser(String userName)throws Exception{
        return sysUserMapper.findByUserName(userName);
    }
    /*
    * 分页查询
    */
    public PageUtils queryAll(SysUser sysUser, int pageNum, int pageSize, String orderBy) throws Exception{
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<SysUser> info = new PageInfo<>(sysUserMapper.findByListDynamic(sysUser));
        return new PageUtils(info);
    }
    /*
    *主键查询
    */
    public SysUser queryById(Long id) throws Exception {
        return sysUserMapper.findById(id);
    }
    /*
    *新增
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void add(SysUser sysUser) throws Exception{
        if(ObjectUtil.isNull(sysUser.getId())){
            sysUser.setId(IdWorker.getInstance().nextId());
        }
        Utils.getRandomString(6);
        sysUserMapper.insertDynamic(sysUser);
    }
    /*
    *修改
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void update(SysUser sysUser) throws Exception {
        sysUserMapper.updateDynamic(sysUser);
    }
    /*
    *删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) throws Exception {
        sysUserMapper.remove(id);
    }
    /*
    * 批量删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void deleteAll(Long[] ids)throws Exception {
        sysUserMapper.batchRemove(ids);
    }

}