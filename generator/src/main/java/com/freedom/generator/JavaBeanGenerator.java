package com.freedom.generator;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 模板对应参数
 */
@Data
@ToString
public class JavaBeanGenerator implements Serializable {
    private String tableName;//表名
    private String tableNotes;//表注释
    private String author = "Bai";//author注释
    private String date = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());//类创建时间
    private List<ColumnClass> columnsList;//字段、字段注释、类型等信息
    private String modelNameUpperCamel;//驼峰后的名称 TableName
    private String modelNameLowerCamel;//驼峰后名称 tableName
    private List<Map<String,String>> types;//数据库类型和
    private String baseColumnList;

    private String javaPath = "admin/src/main/java"; // java文件路径
    private String resourcesPath = "admin/src/main/resources";// 资源文件路径
    private String basePackage = "com.freedom.admin";//项目包路径
    // 生成的包路径
    private String webPackage = basePackage + ".web";
    private String servicePackage = basePackage + ".service";
    private String modelPackage = basePackage + ".model";
    private String mapperPackage = basePackage + ".mapper";
    private String voPackage = basePackage + ".vo";
    // 生成路径
    private String webPackagePath = javaPath + packageConvertPath(webPackage);
    private String servicePackagePath = javaPath + packageConvertPath(servicePackage);
    private String modelPackagePath = javaPath + packageConvertPath(modelPackage);
    private String mapperPackagePath = javaPath + packageConvertPath(mapperPackage);
    private String voPackagePath = javaPath + packageConvertPath(voPackage);
    //xml
    private String xmlPath = resourcesPath + packageConvertPath("/mybatis/mapper");

    //com.demo转换/com/demo/
    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}
