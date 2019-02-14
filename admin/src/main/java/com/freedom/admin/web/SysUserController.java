package com.freedom.admin.web;

import com.freedom.admin.model.SysUser;
import com.freedom.admin.service.SysUserService;
import com.freedom.admin.vo.SysUserVO;
import com.freedom.core.result.JsonResult;
import com.freedom.core.utils.BeanMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
* 用户表
* @author Bai
* @date 2019/01/29 18:05
*/
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /*
    * 动态列表查询
    */
    @RequiresPermissions("sys:user:list")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<?> findList(SysUserVO vo) throws Exception{
        return JsonResult.success(
                sysUserService.queryAll(
                    BeanMapper.map(vo, SysUser.class), vo.getPageNum(), vo.getPageSize(),vo.getOrderBy()
                ));
    }

    /*
    * 详情
    */
    @RequiresPermissions("sys:user:one")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JsonResult<?> one(@PathVariable Long id) throws Exception{
        throw new Exception("ccccc");
        //return JsonResult.success(sysUserService.queryById(id));
    }

    /*
    * 新增
    */
    @RequiresPermissions("sys:user:add")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult<?> add(@RequestBody SysUserVO vo) throws Exception{
        sysUserService.add(BeanMapper.map(vo, SysUser.class));
        return JsonResult.success();
    }

    /*
    * 修改
    */
    @RequiresPermissions("sys:user:update")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<?> update(@RequestBody SysUserVO vo) throws Exception{
        sysUserService.update(BeanMapper.map(vo, SysUser.class));
        return JsonResult.success();
    }

    /*
    * 删除
    */
    @RequiresPermissions("sys:user:delete")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public JsonResult<?> delete(@PathVariable Long id) throws Exception{
        sysUserService.delete(id);
        return JsonResult.success();
    }
}