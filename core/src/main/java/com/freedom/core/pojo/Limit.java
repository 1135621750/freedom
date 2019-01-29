package com.freedom.core.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页实体继承类（pagehelper）
 *
 * @author Bai
 */
@Data
public class Limit extends BaseModel implements Serializable{

    public Limit() {
        pageNum = 1;
        pageSize = 10;
        orderBy = "id desc";
    }

    public Integer pageSize;//每页条数
    public Integer pageNum;//当前页
    public String orderBy;//排序

}
