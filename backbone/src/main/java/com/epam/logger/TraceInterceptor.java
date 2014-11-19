package com.epam.logger;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

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
    protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {
        if (isRequestTracingEnabled(invocation, logger)) {
            Object[] args = invocation.getArguments();
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest)args[i];
                    traceRequest(request, logger);
                }
            }
        }
        return super.invokeUnderTrace(invocation, logger);
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
    
    protected boolean isRequestTracingEnabled(MethodInvocation invocation, Log logger) {
        return loggerProfilerController.isEnableRequestTracing();
    }
    
    protected void traceRequest(HttpServletRequest request, Log logger) {
        StringBuilder sb = new StringBuilder();
        sb.append("URL: [");
        sb.append(request.getRequestURL().toString());
        sb.append("], request Method: [");
        sb.append(request.getMethod());
        sb.append("], remote host: [");
        sb.append(request.getRemoteHost());
        sb.append("], sessionid: [");
        sb.append(request.getSession().getId());
        sb.append("], params: [");
        sb.append(request.getParameterMap());
        sb.append("], headers: [");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            sb.append(headerName);
            sb.append("={");
            sb.append(request.getHeader(headerName));
            sb.append("},");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        writeToLog(logger, sb.toString());
    }
    
}
