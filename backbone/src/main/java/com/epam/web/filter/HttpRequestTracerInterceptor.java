package com.epam.web.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.epam.core.controller.LoggerProfilerControllerMBean;
import com.epam.core.controller.MBeanRegisterService;


public class HttpRequestTracerInterceptor extends HandlerInterceptorAdapter {
    
    private final static Logger LOG = LoggerFactory.getLogger(HttpRequestTracerInterceptor.class);
    
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isRequestTracingEnabled()) {
            traceRequest(request);
        }
        return true;
    }
    
    protected boolean isRequestTracingEnabled() {
        return loggerProfilerController.isEnableRequestTracing();
    }
    
    protected void traceRequest(HttpServletRequest request) {
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
        HttpRequestTracerInterceptor.LOG.info(sb.toString());
    }
    
}
