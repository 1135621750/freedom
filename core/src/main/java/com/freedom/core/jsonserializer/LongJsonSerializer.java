package com.freedom.core.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("rawtypes")
public class LongJsonSerializer extends JsonSerializer {

	public LongJsonSerializer() {
	}

	public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		if (Objects.isNull(o))
			return;
		if (o instanceof Long)
			jsonGenerator.writeString(o.toString());
	}

}
