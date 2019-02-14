package com.freedom.core.result;
/**
 * 枚举接口
 * @author Bai
 *
 */
public interface  ReturnType {
	//此处对应枚举的字段
    //那么这里定义这个三个字段的get方法,可以获取到所有的字段
    
    public abstract String getCode();

    public abstract String getMsgKey();

    public abstract String getMsgVal();


}
