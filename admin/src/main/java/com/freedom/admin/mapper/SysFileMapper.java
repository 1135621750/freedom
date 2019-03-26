package com.freedom.admin.mapper;

import com.freedom.admin.model.SysFile;
import com.freedom.core.pojo.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 文件图片头像记录
* @author Bai
* @date 2019/02/22 11:26
*/
@Mapper
public interface SysFileMapper extends BaseMapper<SysFile,Long> {

    SysFile isFile(String sha1);

}