package com.epam.logger;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.common.exception.CoreException;
import com.epam.core.controller.LoggerProfilerController;


public class RepositoryLogger {
    
    private final LoggerProfilerController loggerProfilerController = new LoggerProfilerController();
    
    public RepositoryLogger() {
        MBeanServer platformMbeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            platformMbeanServer.registerMBean(loggerProfilerController, new ObjectName("LoggerProfiler", "Name", "Controller"));
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            throw new CoreException("Can't register MBean server: LoggerProfiler.Controller", e);
        }
    }
    
    public Object measurePerformance(ProceedingJoinPoint joinpoint) {
        return measurePerformanceWithArgs(joinpoint, null);
    }
    
    public Object measurePerformanceWithArgs(ProceedingJoinPoint joinpoint, Object args) {
        if (loggerProfilerController.isEnableProfiling()) {
            Logger log = getLogger(joinpoint);
            Throwable ex = null;
            StringBuilder sb = new StringBuilder();
            sb.append("Attempt to call ");
            sb.append(joinpoint.getSignature().toLongString());
            sb.append(": ");
            long start = System.currentTimeMillis();
            try {
                if (null != args) {
                    return joinpoint.proceed(new Object[] { args });
                } else {
                    return joinpoint.proceed();
                }
            } catch (Throwable t) {
                ex = t;
            } finally {
                long result = System.currentTimeMillis() - start;
                sb.append(" executed in ");
                sb.append(result);
                sb.append(" milliseconds ");
                if (null != args) {
                    sb.append("with args=");
                    sb.append(args);
                } else {
                    sb.append("with no args");
                }
                sb.append(".");
                if (null != ex) {
                    sb.append("Catched Exception=");
                    sb.append(ex);
                }
                log.info(sb.toString());
            }
        } else {
            try {
                if (null != args) {
                    return joinpoint.proceed(new Object[] { args });
                } else {
                    return joinpoint.proceed();
                }
            } catch (Throwable e) {
                Logger log = getLogger(joinpoint);
                log.error("Catched an exception while executing RepositoryLogger with " + joinpoint.getSignature().toLongString(), e);
            }
        }
        return null;
    }
    
    private Logger getLogger(JoinPoint jp) {
        String loggerName = jp.getSignature().getDeclaringType().getCanonicalName();
        return LoggerFactory.getLogger(loggerName);
    }
    
}
