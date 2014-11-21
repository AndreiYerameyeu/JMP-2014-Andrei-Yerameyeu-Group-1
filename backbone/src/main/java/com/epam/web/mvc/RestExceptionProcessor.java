package com.epam.web.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.common.exception.ModelException;
import com.epam.web.view.model.error.ModelErrorInfo;
import com.epam.web.view.model.error.ModelFieldErrorInfo;

@ControllerAdvice
public class RestExceptionProcessor {
    
    @Autowired
    private MessageSource messageSource;
    
    @ExceptionHandler(ModelException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ModelErrorInfo modelExceptionHandle(HttpServletRequest req, ModelException ex) {
        String errorURL = req.getRequestURL().toString();
        ModelErrorInfo modelErrorInfo = new ModelErrorInfo(errorURL, ex.getMessage());
        
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
