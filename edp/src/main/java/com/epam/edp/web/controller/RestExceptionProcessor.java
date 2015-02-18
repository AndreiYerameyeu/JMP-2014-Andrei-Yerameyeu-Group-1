package com.epam.edp.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.edp.exception.ModelException;
import com.epam.edp.web.view.model.error.ModelErrorInfo;
import com.epam.edp.web.view.model.error.ModelFieldErrorInfo;

@ControllerAdvice
public class RestExceptionProcessor {
  
  private static final Logger LOG = LoggerFactory.getLogger(RestExceptionProcessor.class);
  
  @ExceptionHandler(ModelException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public ModelErrorInfo modelExceptionHandle(HttpServletRequest req, ModelException ex) {
    RestExceptionProcessor.LOG.debug("Exception proceeding REST request", ex);
    
    String errorURL = req.getRequestURL().toString();
    String methodType = req.getMethod();
    
    ModelErrorInfo modelErrorInfo = new ModelErrorInfo(errorURL, ex.getMessage());
    modelErrorInfo.setMethodType(methodType);
    
    Map<String, String> errors = ex.getErrorMessages();
    if (null != errors && !errors.isEmpty()) {
      for (String error : errors.keySet()) {
        ModelFieldErrorInfo modelFieldErrorInfo = new ModelFieldErrorInfo(error, errors.get(error));
        modelErrorInfo.addModelFieldError(modelFieldErrorInfo);
      }
    }
    
    return modelErrorInfo;
  }
  
}
