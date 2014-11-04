package com.epam.bean.postprocessor;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.epam.bean.annotation.LoggerProfiler;
import com.epam.common.exception.CoreException;
import com.epam.core.controller.LoggerProfilerController;


public class LoggerProfilerAnnotationBeanPostprocessor implements BeanPostProcessor {
    
    private final Logger LOG = LoggerFactory.getLogger("LoggerProfiler");
    
    private final Map<String, Class<?>> map = new HashMap<String, Class<?>>();
    
    private final LoggerProfilerController loggerProfilerController = new LoggerProfilerController();
    
    public LoggerProfilerAnnotationBeanPostprocessor() {
        MBeanServer platformMbeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            platformMbeanServer.registerMBean(loggerProfilerController, new ObjectName("LoggerProfiler", "Name", "Controller"));
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            throw new CoreException("Can't register MBean server: LoggerProfiler.Controller", e);
        }
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(LoggerProfiler.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = map.get(beanName);
        if (null != beanClass) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (loggerProfilerController.isEnableProfiling()) {
                        long l = System.currentTimeMillis();
                        Throwable ex = null;
                        Object retVal = null;
                        try {
                            retVal = method.invoke(proxy, args);
                        } catch (Throwable t) {
                            ex = t;
                        }
                        long res = System.currentTimeMillis() - l;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Executed method '");
                        sb.append(method.getName());
                        sb.append("' on class='");
                        sb.append(method.getDeclaringClass().getCanonicalName());
                        sb.append("'");
                        sb.append(". Method was running for ");
                        sb.append(res);
                        sb.append(" milliseconds.");
                        if (null != ex) {
                            sb.append(" Exception was catched:");
                            LOG.debug(sb.toString());
                            LOG.error("", ex);
                            throw ex;
                        } else {
                            LOG.debug(sb.toString());
                        }
                        return retVal;
                    } else {
                        return method.invoke(proxy, args);
                    }
                }
            });
        }
        return bean;
    }
    
}
