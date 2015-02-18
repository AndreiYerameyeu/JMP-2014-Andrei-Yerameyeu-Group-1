package com.epam.edp.json;

import java.io.IOException;
import java.time.LocalDateTime;

import com.epam.edp.util.TimeImpl;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class TimeImplJsonSerializer extends JsonSerializer<TimeImpl> {
  
  @Override
  public void serialize(TimeImpl value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    LocalDateTime time = value.getTime();
    jgen.writeString(time.toString());
  }
  
}
