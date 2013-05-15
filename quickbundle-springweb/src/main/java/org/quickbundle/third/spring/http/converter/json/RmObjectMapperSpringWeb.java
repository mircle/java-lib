package org.quickbundle.third.spring.http.converter.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RmObjectMapperSpringWeb extends ObjectMapper {
	private static final long serialVersionUID = 1L;
	
	boolean writeNumbersAsStrings = false;
	
	public boolean isWriteNumbersAsStrings() {
		return writeNumbersAsStrings;
	}


	public void setWriteNumbersAsStrings(boolean writeNumbersAsStrings) {
		this.writeNumbersAsStrings = writeNumbersAsStrings;
		super.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, writeNumbersAsStrings);
	}

	public RmObjectMapperSpringWeb() {
		super();
	}
}
