package ${servicePackage};

import ${modelPackage}.${modelNameUpperCamel};
import ${mapperPackage}.${modelNameUpperCamel}Mapper;
import com.freedom.core.pojo.BaseService;
import com.freedom.core.pojo.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
* ${tableNotes}
* @author ${author}
* @date ${date}
*/
@Service
public class ${modelNameUpperCamel}Service extends BaseService{

    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    /*
    * 分页查询
    */
    public PageUtils queryAll(${modelNameUpperCamel} ${modelNameLowerCamel}, int pageNum, int pageSize, String orderBy) throws Exception{
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<${modelNameUpperCamel}> info = new PageInfo<>(${modelNameLowerCamel}Mapper.findByListDynamic(${modelNameLowerCamel}));
        return new PageUtils(info);
    }
    /*
    *主键查询
    */
    public ${modelNameUpperCamel} queryById(Long id) throws Exception {
        return ${modelNameLowerCamel}Mapper.findById(id);
    }
    /*
    *新增
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void add(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
        ${modelNameLowerCamel}Mapper.insertDynamic(${modelNameLowerCamel});
    }
    /*
    *修改
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void update(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception {
        ${modelNameLowerCamel}Mapper.updateDynamic(${modelNameLowerCamel});
    }
    /*
    *删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) throws Exception {
        ${modelNameLowerCamel}Mapper.remove(id);
    }
    /*
    * 批量删除
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void deleteAll(Long[] ids)throws Exception {
        ${modelNameLowerCamel}Mapper.batchRemove(ids);
    }

}