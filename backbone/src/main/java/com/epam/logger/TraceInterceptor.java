package com.epam.logger;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

import com.epam.common.exception.CoreException;
import com.epam.core.controller.LoggerProfilerController;


public class TraceInterceptor extends CustomizableTraceInterceptor {
    
    private static final long serialVersionUID = 36323368610564953L;
    
    private final LoggerProfilerController loggerProfilerController = new LoggerProfilerController();
    
    public TraceInterceptor() {
        MBeanServer platformMbeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            platformMbeanServer.registerMBean(loggerProfilerController, new ObjectName("LoggerProfiler", "Name", "Controller"));
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            throw new CoreException("Can't register MBean server: LoggerProfiler.Controller", e);
        }
    }
    
    protected void writeToLog(Logger logger, String message, Throwable ex) {
        if (ex != null) {
            logger.info(message, ex);
        } else {
            logger.info(message);
        }
    }
    
    protected boolean isInterceptorEnabled(MethodInvocation invocation, Logger logger) {
        return loggerProfilerController.isEnableProfiling();
    }
    
}
