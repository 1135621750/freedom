package com.freedom.core.pojo;

import java.io.Serializable;

/**
 * 分页实体继承类（pagehelper）
 *
 * @author Bai
 */
public class Limit implements Serializable{

    public Limit() {
        pageNum = 1;
        pageSize = 10;
        orderBy = "id desc";
    }

    public int getpageNum() {
        return pageNum;
    }

    public void setpageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int pageSize;//每页条数
    public int pageNum;//当前页
    public String orderBy;//排序

    /**
     *PageHelper中默认PageInfo的成员变量
     //当前页
     private int pageNum;
     //每页的数量
     private int pageSize;
     //当前页的数量
     private int size;
     //当前页面第一个元素在数据库中的行号
     private int startRow;
     //当前页面最后一个元素在数据库中的行号
     private int endRow;
     //总记录数
     private long total;
     //总页数
     private int pages;
     //结果集
     private List<T> list;
     //第一页
     private int firstPage;
     //前一页
     private int prePage;
     //是否为第一页
     private boolean isFirstPage;
     //是否为最后一页
     private boolean isLastPage;
     //是否有前一页
     private boolean hasPreviousPage;
     //是否有下一页
     private boolean hasNextPage;
     //导航页码数
     private int navigatePages;
     //所有导航页号
     private int[] navigatepageNums;


     */
}
