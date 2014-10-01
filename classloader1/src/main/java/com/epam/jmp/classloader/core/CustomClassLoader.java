package com.epam.jmp.classloader.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomClassLoader extends ClassLoader {
    
    private static final Logger log = LoggerFactory.getLogger(CustomClassLoader.class);
    private static final Logger console = LoggerFactory.getLogger("Console");
    private static final CustomClassLoader INSTANCE = new CustomClassLoader();
    
    private Class<?> userCommandClass;
    
    public static CustomClassLoader getInstance() {
        return CustomClassLoader.INSTANCE;
    }
    
    private CustomClassLoader() {
        
    }
    
    public Class<?> getUserCommandClass() {
        return userCommandClass;
    }
    
    public void setUserCommandClass(Class<?> userCommandClass) {
        this.userCommandClass = userCommandClass;
    }
    
    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (null == c) {
            ClassLoader parent = getParent();
            if (null != parent) {
                try {
                    c = parent.loadClass(name);
                    if (null != c) {
                        CustomClassLoader.console.info("Class " + name + " was loaded by parent System Classloader");
                    }
                } catch (Exception e) {
                    CustomClassLoader.log.error("Can't load class " + name + " by parent", e);
                }
            }
            if (null == c) {
                c = findClass(name);
                if (null != c) {
                    CustomClassLoader.console.info("Class " + name + " was loaded by Custom Classloader");
                }
            }
        }
        if (null == c) {
            c = super.loadClass(name, resolve);
            if (null != c) {
                CustomClassLoader.console.info("Class " + name + " was loaded by super Classloader");
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
    
    @Override
    protected Class<?> findClass(String _name) throws ClassNotFoundException {
        String className = getClassName(_name);
        String classFileName = getClassFilePath(_name);
        File f = new File(classFileName);
        byte[] bytes = null;
        InputStream is = null;
        try {
            is = f.toURI().toURL().openStream();
            byte[] buffer = new byte[1024];
            int i = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((i = is.read(buffer)) >= 0) {
                baos.write(buffer, 0, i);
            }
            bytes = baos.toByteArray();
        } catch (Exception e) {
            throw new ClassNotFoundException(className, e);
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                CustomClassLoader.log.error("IOException", e);
            }
        }
        if (bytes.length > 0) {
            
            Class<?> c = defineClass(className, bytes, 0, bytes.length);
            return c;
        }
        throw new ClassNotFoundException("Can't load file " + _name);
    }
    
    private String getClassName(String _name) {
        String classPostfix = ".class";
        String[] res = StringUtils.split(_name, java.io.File.separatorChar);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < res.length; i++) {
            sb.append(res[i]);
            if (i < res.length - 1) {
                sb.append('.');
            }
        }
        String className = sb.toString();
        if (className.endsWith(classPostfix)) {
            className = className.substring(0, className.length() - classPostfix.length());
        }
        return className;
    }
    
    private String getClassFilePath(String _name) {
        String classFileName = _name;
        String classPostfix = ".class";
        if (classFileName.endsWith(classPostfix)) {
            classFileName = classFileName.substring(0, classFileName.length() - classPostfix.length());
        }
        classFileName = classFileName.replace('.', java.io.File.separatorChar);
        classFileName += classPostfix;
        return classFileName;
    }
}
