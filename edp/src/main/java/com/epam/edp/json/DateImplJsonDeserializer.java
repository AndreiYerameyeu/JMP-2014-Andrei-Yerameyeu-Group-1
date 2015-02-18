package com.epam.edp.json;

import java.io.IOException;

import com.epam.edp.util.DateImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


public class DateImplJsonDeserializer extends JsonDeserializer<DateImpl> {
  
  @Override
  public DateImpl deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return new DateImpl(jp.getText());
  }
  
}
