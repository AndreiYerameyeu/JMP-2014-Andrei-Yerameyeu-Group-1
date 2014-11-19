package com.epam.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerProfilerController implements LoggerProfilerControllerMBean {
    
    private final Logger LOG = LoggerFactory.getLogger(LoggerProfilerController.class);
    
    private boolean enableProfiling = true;
    private boolean enableRequestTracing = true;
    
    @Override
    public boolean isEnableProfiling() {
        return enableProfiling;
    }
    
    public void setEnableProfiling(boolean enableProfiling) {
        this.enableProfiling = enableProfiling;
    }
    
    @Override
    public boolean isEnableRequestTracing() {
        return enableRequestTracing;
    }
    
    public void setEnableRequestTracing(boolean enableRequestTracing) {
        this.enableRequestTracing = enableRequestTracing;
    }
    
    @Override
    public void enableProfiling(boolean enable) {
        LOG.warn(enable ? "Logger Profiling is enabled." : "Logger Profiling is disabled.");
        setEnableProfiling(enable);
    }
    
    @Override
    public void enableRequestTracing(boolean enable) {
        LOG.warn(enable ? "Logger Request Tracing is enabled." : "Logger Request Tracing is disabled.");
        setEnableRequestTracing(enable);
    }
    
}
