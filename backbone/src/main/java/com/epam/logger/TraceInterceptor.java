package com.epam.logger;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

import com.epam.core.controller.LoggerProfilerControllerMBean;
import com.epam.core.controller.MBeanRegisterService;


public class TraceInterceptor extends CustomizableTraceInterceptor {
    
    private static final long serialVersionUID = 36323368610564953L;
    
    private LoggerProfilerControllerMBean loggerProfilerController;
    
    private MBeanRegisterService mBeanRegisterService;
    
    public void init() {
        loggerProfilerController = mBeanRegisterService.getLoggerProfilerController();
    }
    
    public MBeanRegisterService getmBeanRegisterService() {
        return mBeanRegisterService;
    }
    
    public void setmBeanRegisterService(MBeanRegisterService mBeanRegisterService) {
        this.mBeanRegisterService = mBeanRegisterService;
    }
    
    @Override
    protected void writeToLog(Log logger, String message, Throwable ex) {
        if (ex != null) {
            logger.info(message, ex);
        } else {
            logger.info(message);
        }
    }
    
    @Override
    protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
        return loggerProfilerController.isEnableProfiling();
    }
    
}
