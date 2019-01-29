package com.freedom.core.pojo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    //总记录数
    private Long totalCount;
    //每页记录数
    private Integer pageSize;
    //总页数
    private Integer totalPage;
    //当前页数
    private Integer pageNum;
    //列表数据
    private List<?> list;

    /**
     * 分页
     * @param list        列表数据
     * @param totalCount  总记录数
     * @param pageSize    每页记录数
     * @param pageNum    当前页数
     */
    public PageUtils(List<?> list, Long totalCount, Integer pageSize, Integer pageNum) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    /**
     * 分页
     */
    public PageUtils(PageInfo<?> page) {
        this.list = page.getList();
        this.totalCount = page.getTotal();
        this.pageSize = page.getSize();
        this.pageNum = page.getPageNum();
        this.totalPage = page.getPages();
    }


}