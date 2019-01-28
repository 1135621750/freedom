package com.freedom.core.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 序列化，转换为字符串->使用方式
 * @JsonSerialize(using = BigDecimalJsonSerializer.class)
 * @author Bai
 *数字返回给前端长度过长存在丢失精度问题处理
 */
@SuppressWarnings("rawtypes")
public class BigDecimalJsonSerializer extends JsonSerializer {

	public BigDecimalJsonSerializer() {
	}

	public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		if (Objects.isNull(o))
			return;
		if (o instanceof BigDecimal)
			jsonGenerator.writeString(o.toString());
	}
}
