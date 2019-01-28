package ${modelPackage};

import lombok.Data;

import java.util.Date;

/**
* ${tableNotes}
* @author ${author}
* @date ${date}
*/
@Data
public class ${modelNameUpperCamel} {

<#if columnsList?exists>
    <#list columnsList as model>
        <#list types as map>
            <#list map?keys as itemKey>
                <#if (model.columnType = itemKey) >
    /**
    *${model.columnComment!}
    */
    private ${map[itemKey]} ${model.changeColumnName};
                </#if>
            </#list>
        </#list>
    </#list>
</#if>


}
