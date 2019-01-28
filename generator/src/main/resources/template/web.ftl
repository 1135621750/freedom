package ${webPackage};

import ${servicePackage}.${modelNameUpperCamel}Manager;
import ${modelPackage}.${modelNameUpperCamel}Entity;
import com.tianyalei.jipiao.global.bean.BaseData;
import com.tianyalei.jipiao.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* ${tableNotes}
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Manager ${modelNameLowerCamel}Manager;

    /**
    * 动态列表查询
    * @param entity 动态参数
    * @param page 当前页
    * @param size 每页显示条数
    * @param ord 排序字段 默认为id
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public BaseData findList(${modelNameUpperCamel}Entity entity,
                                @RequestParam(value = "page",defaultValue = "0") Integer page,
                                @RequestParam(value = "size",defaultValue = "10") Integer size,
                                @RequestParam(value = "size",defaultValue = "id") String ord){
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel}Manager.list(entity, page, size,ord));
    }

    /**
    * 详情
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public BaseData one(@PathVariable Integer id) {
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel}Manager.one(id));
    }

    /**
    * 新增
    * @param entity 实体
    * @return
    */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseData add(@RequestBody ${modelNameUpperCamel}Entity entity) {
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel}Manager.add(entity));
    }

    /**
    * 修改
    * @param entity
    * @return
    */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public BaseData update(@RequestBody ${modelNameUpperCamel}Entity entity) {
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel}Manager.update(entity));
    }

    /**
    * 删除
    * @param id
    * @return
    */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public BaseData update(@PathVariable Integer id) {
        try {
            ${modelNameLowerCamel}Manager.updateIsEnable(id);
        }catch (Exception e){
            return ResultGenerator.genFailResult("删除数据失败");
        }
        return ResultGenerator.genSuccessResult();
    }
}