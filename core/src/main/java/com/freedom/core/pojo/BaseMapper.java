package com.freedom.core.pojo;

import java.util.List;

/**
 * 公共的mapper
 * @param <T> 对应实体
 * @param <ID> 主键
 */
public interface BaseMapper<T,ID> {

    /**
     * 主键查询
     * @param id 主键
     * @return
     */
    T findById(ID id);

    /**
     * 列表动态查询
     * @param entity 表空间对应实体
     * @return
     */
    List<T> findByListDynamic(T entity);

    /**
     * 批量全字段新增
     * @param list 表空间对应实体
     * @return
     */
    int insertListModel(List<T> list);

    /**
     * 动态新增
     * @param entity 表空间对应实体
     * @return
     */
    int insertDynamic(T entity);

    /**
     * 动态更新
     * @param entity 表空间对应实体
     * @return
     */
    int updateDynamic(T entity);

    /**
     * 删除一条
     * @param id 主键
     * @return
     */
    int remove(ID id);

    /**
     * 批量删除
     * @param ids 主键
     * @return
     */
    int batchRemove(ID[] ids);

}
