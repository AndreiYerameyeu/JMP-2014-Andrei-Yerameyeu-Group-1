package com.epam.core.controller;


public interface LoggerProfilerControllerMBean {
    
    public void enableProfiling(boolean enable);
    
    public boolean isEnableProfiling();
    
    public void enableRequestTracing(boolean enable);
    
    public boolean isEnableRequestTracing();
}
