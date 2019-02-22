package com.freedom.admin.model;

import lombok.Data;
import com.freedom.core.pojo.BaseModel;

/**
* 文件图片头像记录
* @author Bai
* @date 2019/02/22 11:26
*/
@Data
public class SysFile extends BaseModel{

    /**
    *文件名
    */
    private String name;
    /**
    *文件路径
    */
    private String path;
    /**
    *MIME文件类型
    */
    private String mime;
    /**
    *文件大小
    */
    private Long size;
    /**
    *MD5值
    */
    private String md5;
    /**
    *SHA1值
    */
    private String sha1;


}
