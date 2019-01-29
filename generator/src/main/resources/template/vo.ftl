package ${voPackage};

import com.freedom.core.pojo.Limit;
import lombok.Data;

/**
* ${tableNotes}
* @author ${author}
* @date ${date}
*/
@Data
public class ${modelNameUpperCamel}VO extends Limit{

<#if columnsList?exists>
    <#list columnsList as model>
    <#--一些字段使用继承的方式，判断跳过输出-->
        <#if model.columnName == "id">
        <#elseif model.columnName == "create_time">
        <#elseif model.columnName == "update_time">
        <#elseif model.columnName == "create_user">
        <#elseif model.columnName == "update_user">
        <#elseif model.columnName == "is_delete">
        <#elseif model.columnName == "status">
        <#else >
            <#list types as map>
                <#list map?keys as itemKey>
                    <#if (model.columnType = itemKey) >
    /**
    *${model.columnComment}
    */
    private ${map[itemKey]} ${model.changeColumnName};
                    </#if>
                </#list>
            </#list>
        </#if>
    </#list>
</#if>


}