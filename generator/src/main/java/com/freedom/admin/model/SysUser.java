package com.freedom.admin.model;

import lombok.Data;
import com.freedom.core.pojo.BaseModel;

/**
* 
* @author Bai
* @date 2019/01/28 18:25
*/
@Data
public class SysUser extends BaseModel{

    /**
    *
    */
    private String userName;
    /**
    *
    */
    private String password;
    /**
    *
    */
    private String salt;


}
