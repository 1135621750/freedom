package ${webPackage};

import ${modelPackage}.${modelNameUpperCamel};
import ${servicePackage}.${modelNameUpperCamel}Service;
import ${voPackage}.${modelNameUpperCamel}VO;
import com.freedom.core.result.JsonResult;
import com.freedom.core.utils.BeanMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
* ${tableNotes}
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller {

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /*
    * 动态列表查询
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<?> findList(${modelNameUpperCamel}VO vo) throws Exception{
        return JsonResult.success(
                ${modelNameLowerCamel}Service.queryAll(
                    BeanMapper.map(vo, ${modelNameUpperCamel}.class), vo.getPageNum(), vo.getPageSize(),vo.getOrderBy()
                ));
    }

    /*
    * 详情
    */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JsonResult<?> one(@PathVariable Long id) throws Exception{
        return JsonResult.success(${modelNameLowerCamel}Service.queryById(id));
    }

    /*
    * 新增
    */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult<?> add(@RequestBody ${modelNameUpperCamel}VO vo) throws Exception{
        ${modelNameLowerCamel}Service.add(BeanMapper.map(vo, ${modelNameUpperCamel}.class));
        return JsonResult.success();
    }

    /*
    * 修改
    */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResult<?> update(@RequestBody ${modelNameUpperCamel}VO vo) throws Exception{
        ${modelNameLowerCamel}Service.update(BeanMapper.map(vo, ${modelNameUpperCamel}.class));
        return JsonResult.success();
    }

    /*
    * 删除
    */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public JsonResult<?> delete(@PathVariable Long id) throws Exception{
        ${modelNameLowerCamel}Service.delete(id);
        return JsonResult.success();
    }
}