package com.freedom.core.pojo;

/**
 * 公共的mapper
 * @param <T> 对应实体
 * @param <ID> 主键
 */
public interface BaseMapper<T,ID> {

    /**
     * 主键删除
     * @param id 主键
     * @return
     */
    int deleteByPrimaryKey(ID id);

    /**
     * 主键新增全部字段数据
     * @param entity 表空间对应实体
     * @return
     */
    int insert(T entity);

    /**
     * 动态新增,null值不新增
     * @param entity 表空间对应实体
     * @return
     */
    int insertSelective(T entity);

    /**
     * 主键查询
     * @param id 主键
     * @return 表空间对应实体
     */
    T selectByPrimaryKey(ID id);

    /**
     * 动态更新
     * @param entity 表空间对应实体
     * @return
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     * 主键修改全部字段数据
     * @param entity 表空间对应实体
     * @return
     */
    int updateByPrimaryKey(T entity);

}
