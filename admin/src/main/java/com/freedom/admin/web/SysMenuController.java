package com.freedom.admin.web;

import com.freedom.admin.model.SysMenu;
import com.freedom.admin.service.SysMenuService;
import com.freedom.admin.vo.SysMenuVO;
import com.freedom.core.result.JsonResult;
import com.freedom.core.utils.BeanMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
* 菜单
* @author Bai
* @date 2019/02/12 12:16
*/
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /*
    * 动态列表查询
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<?> findList(SysMenuVO vo) throws Exception{
        return JsonResult.success(
                sysMenuService.queryAll(
                    BeanMapper.map(vo, SysMenu.class), vo.getPageNum(), vo.getPageSize(),vo.getOrderBy()
                ));
    }

    /*
    * 详情
    */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JsonResult<?> one(@PathVariable Long id) throws Exception{
        return JsonResult.success(sysMenuService.queryById(id));
    }

    /*
    * 新增
    */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult<?> add(@RequestBody SysMenuVO vo) throws Exception{
        sysMenuService.add(BeanMapper.map(vo, SysMenu.class));
        return JsonResult.success();
    }

    /*
    * 修改
    */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<?> update(@RequestBody SysMenuVO vo) throws Exception{
        sysMenuService.update(BeanMapper.map(vo, SysMenu.class));
        return JsonResult.success();
    }

    /*
    * 删除
    */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public JsonResult<?> delete(@PathVariable Long id) throws Exception{
        sysMenuService.delete(id);
        return JsonResult.success();
    }
}