package com.epam.edp.json;

import java.io.IOException;

import com.epam.edp.util.TimeImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


public class TimeImplJsonDeserializer extends JsonDeserializer<TimeImpl> {
  
  @Override
  public TimeImpl deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return new TimeImpl(jp.getText());
  }
  
}
