package com.freedom.generator;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Generator {
    // JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/freedom";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    // 模板位置
    private static final String TEMPLATE_FILE_PATH = "D:\\webeclipse\\idea_ma\\freedom\\generator\\src\\main\\resources\\template";

    public static void main(String[] args){
        getCode("sys_user");
    }
    public static void getCode(String... tableNames){
        for(String s : tableNames){
            getJpa(s);
        }
    }
    
    //jpa查询
    public static void getJpa(String tableName){
        JavaBeanGenerator bean = new JavaBeanGenerator();
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap();
        map.put("tinyint","Integer");
        map.put("smallint","Integer");
        map.put("mediumint","Integer");
        map.put("int","Integer");
        map.put("integer","Integer");
        map.put("bigint","Long");
        map.put("float","Float");
        map.put("double","Double");
        map.put("decimal","BigDecimal");
        map.put("bit","Boolean");
        map.put("char","String");
        map.put("varchar","String");
        map.put("tinytext","String");
        map.put("text","String");
        map.put("mediumtext","String");
        map.put("longtext","String");
        map.put("date","Date");
        map.put("datetime","Date");
        map.put("timestamp","Date");
        list.add(map);
        bean.setTypes(list);
        bean.setTableName(tableName);
        getTable(bean);
        getColumns(bean);
        generate(bean.getModelPackagePath() + bean.getModelNameUpperCamel(), "java", "model.ftl", bean);
    }
    //查询表的数据
    public static void getTable(JavaBeanGenerator bean){
        try{
            Connection conn = getConnection();
            ResultSet rs = conn.prepareStatement(
                    "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES "
                            +"WHERE TABLE_SCHEMA = '"+JDBC_URL.split("/")[3]+"' AND TABLE_NAME = '"+bean.getTableName()+"'"
            ).executeQuery();
            while (rs.next()){
                if(!bean.getTableName().equals(rs.getString("TABLE_NAME"))){
                    log.error("查询表名和传入表名不一致");
                }
                bean.setTableNotes(rs.getString("TABLE_COMMENT"));//备注
                bean.setModelNameUpperCamel(tableNameConvertUpperCamel(bean.getTableName()));
                bean.setModelNameLowerCamel(tableNameConverLowerCamel(bean.getTableName()));
            }
        }catch (SQLException e){
            log.info("查询表信息失败",e);
        }
    }
    //查询列的数据
    public static void getColumns(JavaBeanGenerator bean) {
        try{
            Connection conn = getConnection();
            ResultSet rs = conn.prepareStatement(
                    "SELECT COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT FROM information_schema.COLUMNS "
                            +"WHERE TABLE_SCHEMA = '"+JDBC_URL.split("/")[3]+"' AND TABLE_NAME = '"+bean.getTableName()+"'"
            ).executeQuery();
            List<ColumnClass> columnClassList = new ArrayList<>();
            ColumnClass columnClass = null;
            while (rs.next()){
                //id字段略过
                if(rs.getString("COLUMN_NAME").equals("ID")) {
                    continue;
                }
                columnClass = new ColumnClass();
                //获取字段名称
                columnClass.setColumnName(rs.getString("COLUMN_NAME"));
                //获取字段类型
                columnClass.setColumnType(rs.getString("DATA_TYPE"));
                //转换字段名称，如 sys_name 变成 SysName
                columnClass.setChangeColumnName(tableNameConverLowerCamel(rs.getString("COLUMN_NAME")));
                //字段在数据库的注释
                columnClass.setColumnComment(rs.getString("COLUMN_COMMENT"));
                columnClassList.add(columnClass);
            }
            bean.setColumnsList(columnClassList);
        }catch (SQLException e){
            log.info("查询列信息失败",e);
        }
    }

    /**
     * 模板替换引擎
     *
     * @param pathClassName 项目路径+文件名,如com.code.model 拼接
     *                      大驼峰名 如:user_info 对应UserInfo、UserInfoController、UserInfoServer等,生成为.java文件
     * @param suffix        生成文件后缀
     * @param templateName  模板名称 如：Controller.ftl、Server.ftl
     * @param data          存储的Map替换数据
     */
    private static void generate(String pathClassName, String suffix, String templateName, JavaBeanGenerator data) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            File file = new File(pathClassName + "." + suffix);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate(templateName).process(data, new FileWriter(file));
            System.err.println(pathClassName + "生成成功");
        } catch (IOException e) {
            log.error("IO异常",e);
        } catch (TemplateException e) {
            log.error("模板引擎生成异常",e);
        }
    }

    /**
     * 模板引擎
     * @return
     * @throws IOException
     */
    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static Connection getConnection() {
        try {
            Class.forName(JDBC_DIVER_CLASS_NAME);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            log.error("forName错误",e);
        } catch (SQLException e) {
            log.error("connection连接错误",e);
        }
        return null;
    }

    //驼峰转换首字母大写   table_name TableName
    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    //驼峰转换首字母小写   table_name_test tableNameTest
    private static String tableNameConverLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }
    //com.demo转换/com/demo/
    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
