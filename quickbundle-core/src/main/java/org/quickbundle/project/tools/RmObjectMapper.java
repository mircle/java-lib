package org.quickbundle.project.tools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RmObjectMapper {
	static ObjectMapper defaultObjectMapper = new ObjectMapper();
	static {
		defaultObjectMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
	}
	public static ObjectMapper getInstance() {
		return defaultObjectMapper;
	}
}
