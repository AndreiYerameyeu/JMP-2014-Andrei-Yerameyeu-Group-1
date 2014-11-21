package com.epam.web.rest.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.epam.common.exception.ModelException;


public abstract class AbstractRestController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MessageSource messageSource;
    
    protected MessageSource getMessageSource() {
        return messageSource;
    }
    
    protected void processRestRequest(Object body, BindingResult results) {
        log.info("Body: " + body);
        if (results.hasErrors()) {
            processErrors(results);
        }
    }
    
    protected void processErrors(BindingResult results) {
        Locale locale = LocaleContextHolder.getLocale();
        ModelException ex = new ModelException("Can't proceed with Bookmark entity");
        for (FieldError error : results.getFieldErrors()) {
            String msg = getLocalizedMessage(error.getDefaultMessage(), null, locale);
            ex.addErrorMessage(error.getField(), msg);
        }
        throw ex;
    }
    
    protected String getLocalizedMessage(String code, Object[] params) {
        Locale locale = LocaleContextHolder.getLocale();
        return getLocalizedMessage(code, params, locale);
    }
    
    protected String getLocalizedMessage(String code, Object[] params, Locale loc) {
        return messageSource.getMessage(code, params, loc);
    }
    
}
