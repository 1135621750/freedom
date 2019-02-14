package com.freedom.core.pojo;

/**
 * 初始化数据父类
 * @author Bai
 *
 * @param <E> 初始化的实体
 */
public abstract class BaseDomain<E> {
    protected E po;

    protected BaseDomain(E e) {
        this.po = e;
    }

    public E getPO() {
        return po;
    }


}
