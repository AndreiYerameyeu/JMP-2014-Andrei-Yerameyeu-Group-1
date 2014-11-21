package com.epam.logger;

import java.util.Objects;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.util.StopWatch;

import com.epam.common.exception.ModelException;
import com.epam.core.controller.LoggerProfilerControllerMBean;
import com.epam.core.controller.MBeanRegisterService;


public class TraceInterceptor extends CustomizableTraceInterceptor {
    
    private static final long serialVersionUID = 2140488747293182139L;
    
    protected static final String DEFAULT_ENTER_MESSAGE = "Entering method '"
                                                          + CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME
                                                          + "' of class ["
                                                          + CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME
                                                          + "]";
    protected static final String DEFAULT_EXIT_MESSAGE = "Exiting method '"
                                                         + CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME
                                                         + "' of class ["
                                                         + CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME
                                                         + "]";
    protected static final String DEFAULT_EXCEPTION_MESSAGE = "Exception thrown in method '"
                                                              + CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME
                                                              + "' of class ["
                                                              + CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME
                                                              + "]";
    
    
    private LoggerProfilerControllerMBean loggerProfilerController;
    
    private MBeanRegisterService mBeanRegisterService;
    
    private String enterMessage = TraceInterceptor.DEFAULT_ENTER_MESSAGE;
    private String exitMessage = TraceInterceptor.DEFAULT_EXIT_MESSAGE;
    private String exceptionMessage = TraceInterceptor.DEFAULT_EXCEPTION_MESSAGE;
    
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
    public void setEnterMessage(String enterMessage) throws IllegalArgumentException {
        super.setEnterMessage(enterMessage);
        this.enterMessage = enterMessage;
    }
    
    public String getEnterMessage() {
        return enterMessage;
    }
    
    @Override
    public void setExitMessage(String exitMessage) {
        super.setExitMessage(exitMessage);
        this.exitMessage = exitMessage;
    }
    
    public String getExitMessage() {
        return exitMessage;
    }
    
    @Override
    public void setExceptionMessage(String exceptionMessage) {
        super.setExceptionMessage(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    
    public String getExceptionMessage() {
        return exceptionMessage;
    }
    
    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {
        String name = invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName();
        StopWatch stopWatch = new StopWatch(name);
        Object returnValue = null;
        try {
            stopWatch.start(name);
            writeToLog(logger, replacePlaceholders(getEnterMessage(), invocation, null, null, -1));
            returnValue = invocation.proceed();
            return returnValue;
        } catch (Throwable ex) {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            writeToLog(logger, replacePlaceholders(getExceptionMessage(), invocation, null, ex, stopWatch.getTotalTimeMillis()), ex);
            throw ex;
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            writeToLog(logger, replacePlaceholders(getExitMessage(), invocation, returnValue, null, stopWatch.getTotalTimeMillis()));
        }
    }
    
    @Override
    protected void writeToLog(Log logger, String message, Throwable ex) {
        if (ex != null && !Objects.equals(ModelException.class, ex.getClass())) {
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
