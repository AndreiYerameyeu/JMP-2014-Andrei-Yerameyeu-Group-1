package com.epam.edp.json;

import java.io.IOException;
import java.time.LocalDate;

import com.epam.edp.util.DateImpl;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class DateImplJsonSerializer extends JsonSerializer<DateImpl> {
  
  @Override
  public void serialize(DateImpl value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    LocalDate localDate = value.getDate();
    jgen.writeString(localDate.toString());
  }
  
}
