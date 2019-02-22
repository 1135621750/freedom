package com.freedom.admin.web;

import com.freedom.admin.model.SysFile;
import com.freedom.admin.service.SysFileService;
import com.freedom.admin.vo.SysFileVO;
import com.freedom.core.result.JsonResult;
import com.freedom.core.utils.BeanMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
* 文件图片头像记录
* @author Bai
* @date 2019/02/22 11:26
*/
@RestController
@RequestMapping("/sysFile")
public class SysFileController {

    @Autowired
    private SysFileService sysFileService;

    /*
    * 动态列表查询
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<?> findList(SysFileVO vo) throws Exception{
        return JsonResult.success(
                sysFileService.queryAll(
                    BeanMapper.map(vo, SysFile.class), vo.getPageNum(), vo.getPageSize(),vo.getOrderBy()
                ));
    }

    /*
    * 详情
    */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JsonResult<?> one(@PathVariable Long id) throws Exception{
        return JsonResult.success(sysFileService.queryById(id));
    }

    /*
    * 新增
    */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult<?> add(@RequestBody SysFileVO vo) throws Exception{
        sysFileService.add(BeanMapper.map(vo, SysFile.class));
        return JsonResult.success();
    }

    /*
    * 修改
    */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<?> update(@RequestBody SysFileVO vo) throws Exception{
        sysFileService.update(BeanMapper.map(vo, SysFile.class));
        return JsonResult.success();
    }

    /*
    * 删除
    */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public JsonResult<?> delete(@PathVariable Long id) throws Exception{
        sysFileService.delete(id);
        return JsonResult.success();
    }
}