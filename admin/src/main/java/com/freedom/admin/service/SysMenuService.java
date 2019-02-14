package com.freedom.admin.service;

import com.freedom.admin.model.SysMenu;
import com.freedom.admin.mapper.SysMenuMapper;
import com.freedom.core.pojo.BaseService;
import com.freedom.core.pojo.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
* 菜单
* @author Bai
* @date 2019/02/12 12:16
*/
@Service
public class SysMenuService extends BaseService{

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /*
    * 分页查询
    */
    public PageUtils queryAll(SysMenu sysMenu, int pageNum, int pageSize, String orderBy) throws Exception{
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<SysMenu> info = new PageInfo<>(sysMenuMapper.findByListDynamic(sysMenu));
        return new PageUtils(info);
    }
    /*
    *主键查询
    */
    public SysMenu queryById(Long id) throws Exception {
        return sysMenuMapper.findById(id);
    }
    /*
    *新增
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void add(SysMenu sysMenu) throws Exception{
        sysMenuMapper.insertDynamic(sysMenu);
    }
    /*
    *修改
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void update(SysMenu sysMenu) throws Exception {
        sysMenuMapper.updateDynamic(sysMenu);
    }
    /*
    *删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) throws Exception {
        sysMenuMapper.remove(id);
    }
    /*
    * 批量删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void deleteAll(Long[] ids)throws Exception {
        sysMenuMapper.batchRemove(ids);
    }

}