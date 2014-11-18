package com.epam.core.controller;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import com.epam.common.exception.CoreException;


public class MBeanRegisterService {
    
    private final LoggerProfilerController loggerProfilerController = new LoggerProfilerController();
    
    public void init() {
        MBeanServer platformMbeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            platformMbeanServer.registerMBean(loggerProfilerController, new ObjectName("LoggerProfiler", "Name", "Controller"));
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            throw new CoreException("Can't register MBean server: LoggerProfiler.Controller", e);
        }
    }
    
    public LoggerProfilerControllerMBean getLoggerProfilerController() {
        return loggerProfilerController;
    }
    
}
