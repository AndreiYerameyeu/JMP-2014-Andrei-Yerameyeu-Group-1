package com.epam.logger;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebControllerLogger {
    
    public Object proceedRequest(ProceedingJoinPoint joinpoint, HttpServletRequest request) {
        return proceedRequestWithArgs(joinpoint, request, null);
    }
    
    public Object proceedRequestWithArgs(ProceedingJoinPoint joinpoint, HttpServletRequest request, Object args) {
        Logger log = getLogger(joinpoint);
        StringBuilder sb = new StringBuilder();
        sb.append("URL: [");
        sb.append(request.getContextPath());
        sb.append("], ip: [");
        sb.append(request.getLocalAddr());
        sb.append("], sessionid: [");
        sb.append(request.getSession().getId());
        sb.append("], params: [");
        sb.append(request.getParameterMap());
        sb.append("]");
        log.debug(sb.toString());
        Throwable ex = null;
        try {
            if (null != args) {
                return joinpoint.proceed(new Object[] { request, args });
            } else {
                return joinpoint.proceed(new Object[] { request });
            }
        } catch (Throwable t) {
            ex = t;
        } finally {
            if (null != ex) {
                sb.append("Catched Exception=");
                sb.append(ex);
            }
            log.debug(sb.toString());
        }
        return null;
    }
    
    private Logger getLogger(JoinPoint jp) {
        String loggerName = jp.getSignature().getDeclaringType().getCanonicalName();
        return LoggerFactory.getLogger(loggerName);
    }
    
}
