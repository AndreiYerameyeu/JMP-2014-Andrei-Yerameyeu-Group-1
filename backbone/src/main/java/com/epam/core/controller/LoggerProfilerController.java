package com.epam.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerProfilerController implements LoggerProfilerControllerMBean {
    
    private final Logger LOG = LoggerFactory.getLogger(LoggerProfilerController.class);
    
    private boolean enableProfiling;
    
    public boolean isEnableProfiling() {
        return enableProfiling;
    }
    
    public void setEnableProfiling(boolean enableProfiling) {
        this.enableProfiling = enableProfiling;
    }
    
    @Override
    public void enableProfiling(boolean enable) {
        LOG.warn(enable ? "Logger Profiling is enabled." : "Logger Profiling is disabled.");
        setEnableProfiling(enable);
    }
    
}
