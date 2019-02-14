package com.freedom.admin.web;

import com.freedom.admin.model.SysRole;
import com.freedom.admin.service.SysRoleService;
import com.freedom.admin.vo.SysRoleVO;
import com.freedom.core.result.JsonResult;
import com.freedom.core.utils.BeanMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
* 角色表
* @author Bai
* @date 2019/02/12 12:16
*/
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /*
    * 动态列表查询
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<?> findList(SysRoleVO vo) throws Exception{
        return JsonResult.success(
                sysRoleService.queryAll(
                    BeanMapper.map(vo, SysRole.class), vo.getPageNum(), vo.getPageSize(),vo.getOrderBy()
                ));
    }

    /*
    * 详情
    */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JsonResult<?> one(@PathVariable Long id) throws Exception{
        return JsonResult.success(sysRoleService.queryById(id));
    }

    /*
    * 新增
    */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult<?> add(@RequestBody SysRoleVO vo) throws Exception{
        sysRoleService.add(BeanMapper.map(vo, SysRole.class));
        return JsonResult.success();
    }

    /*
    * 修改
    */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<?> update(@RequestBody SysRoleVO vo) throws Exception{
        sysRoleService.update(BeanMapper.map(vo, SysRole.class));
        return JsonResult.success();
    }

    /*
    * 删除
    */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public JsonResult<?> delete(@PathVariable Long id) throws Exception{
        sysRoleService.delete(id);
        return JsonResult.success();
    }
}