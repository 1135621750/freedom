<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${mapperPackage}.${modelNameUpperCamel}Mapper">

    <resultMap id="BaseResultMap" type="${modelPackage}.${modelNameUpperCamel}">
    <#if columnsList?exists>
        <#list columnsList as model>
            <#if model.columnName == "id">
        <id column="${model.columnName}" jdbcType="${model.columnType?upperCase}" property="${model.changeColumnName}" />
            <#else>
        <result column="${model.columnName}" jdbcType="${model.columnType?upperCase}" property="${model.changeColumnName}" />
            </#if>
        </#list>
    </#if>
    </resultMap>


    <sql id="Base_Column_List">
        ${baseColumnList}
    </sql>

    <#--查询一条-->
    <select id="findById" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${tableName} WHERE id = <#noParse>#</#noParse>{id}
    </select>

    <#--查询列表，不为空的字段查询-->
    <select id="findByListDynamic" resultMap="BaseResultMap" parameterType="${modelPackage}.${modelNameUpperCamel}">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${tableName}
        <where>
        <#if columnsList?exists>
            <#list columnsList as model>
            <if test="${model.changeColumnName} != null"> AND ${model.changeColumnName} = <#noParse>#</#noParse>{${model.changeColumnName},jdbcType=${model.columnType?upperCase}} </if>
            </#list>
        </#if>
        </where>
    </select>

    <#--批量新增-->
    <insert id="insertListModel" parameterType="java.util.List" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#if columnsList?exists>
            <#list columnsList as model>
            <if test="${model.changeColumnName} != null">
            ${model.columnName},
            </if>
            </#list>
        </#if>
        </trim>
        <trim prefix="values ">
            <foreach collection="list" item="item" index="index"
                     separator=",">
                (
            <#if columnsList?exists>
                <#list columnsList as model>
                    <if test="${model.changeColumnName} != null">
                <#noParse>#</#noParse>{${model.changeColumnName},jdbcType=${model.columnType?upperCase}},
                    </if>
                </#list>
            </#if>
                )
            </foreach>
        </trim>
    </insert>

    <#--新增动态-->
    <insert id="insertDynamic" parameterType="${modelPackage}.${modelNameUpperCamel}">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#if columnsList?exists>
            <#list columnsList as model>
            <if test="${model.changeColumnName} != null">
            ${model.columnName},
            </if>
            </#list>
        </#if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#if columnsList?exists>
            <#list columnsList as model>
                <if test="${model.changeColumnName} != null">
                    <#noParse>#</#noParse>{${model.changeColumnName},jdbcType=${model.columnType?upperCase}},
                </if>
            </#list>
        </#if>
        </trim>
    </insert>

    <#--修改动态-->
    <update id="updateDynamic" parameterType="${modelPackage}.${modelNameUpperCamel}">
        update ${tableName}
        <set>
        <#if columnsList?exists>
            <#list columnsList as model>
            <if test="${model.changeColumnName} != null">
            ${model.changeColumnName} = <#noParse>#</#noParse>{${model.changeColumnName},jdbcType=${model.columnType?upperCase}},
            </if>
            </#list>
        </#if>
        </set>
        where id = <#noParse>#</#noParse>{id}
    </update>

    <#--批量修改-->

    <#--删除一条-->
    <delete id="remove">
        DELETE FROM ${tableName} WHERE id = <#noParse>#</#noParse>{id}
    </delete>

    <#--删除多条-->
    <delete id="batchRemove">
        DELETE FROM ${tableName} WHERE id in
        <foreach item="id" collection="array" open="(" separator="," close=")">
        <#noParse>#</#noParse>{id}
        </foreach>
    </delete>

</mapper>