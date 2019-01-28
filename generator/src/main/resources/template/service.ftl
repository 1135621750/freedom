package com.tianyalei.jipiao.core.manager;

import com.alibaba.fastjson.JSONObject;
import com.tianyalei.jipiao.core.model.${modelNameUpperCamel}Entity;
import com.tianyalei.jipiao.core.repository.${modelNameUpperCamel}Repository;
import com.tianyalei.jipiao.global.bean.SimplePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
* ${table_comment}
* @author ${author}
* @date ${date}
*/
@Service
public class ${modelNameUpperCamel}Manager {

    @Resource
    private ${modelNameUpperCamel}Repository ${modelNameLowerCamel}Repository;

    /*
    分页查询
    */
    public SimplePage<${modelNameUpperCamel}Entity> list(${modelNameUpperCamel}Entity entity, Integer page, Integer size, String ord) {
        Pageable pageable = PageRequest.of(
                page, size,
                new Sort(Sort.Direction.ASC, ord)
        );
        //动态查询器
        Page<${modelNameUpperCamel}Entity> all = ${modelNameLowerCamel}Repository.findAll(new Specification<${modelNameUpperCamel}Entity>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<${modelNameUpperCamel}Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //动态查询语句
                <#--if (!StringUtils.isEmpty(entity.getCardNum())) {
                    predicates.add(cb.and(cb.equal(root.get("cardNum"), entity.getCardNum())));
                }-->
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
        return new SimplePage<>(all.getTotalPages(), all
                    .getTotalElements(), all.getContent());
    }

    /*
    删除逻辑更改可用状态
    */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void updateIsEnable(Integer id) throws Exception{
        ${modelNameUpperCamel}Entity entity = one(id);
        entity.setIsEnable(false);
        delete(entity);
    }
    /*
    详情
    */
    public ${modelNameUpperCamel}Entity one(Integer id) {
        return ${modelNameLowerCamel}Repository.getOne(id);
    }
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public ${modelNameUpperCamel}Entity add(${modelNameUpperCamel}Entity entity){
        return ${modelNameLowerCamel}Repository.save(entity);
    }
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public ${modelNameUpperCamel}Entity update(${modelNameUpperCamel}Entity entity){
        return ${modelNameLowerCamel}Repository.save(entity);
    }
    private void delete(${modelNameUpperCamel}Entity entity) {
        //更新可用状态
        update(entity);
    }

}