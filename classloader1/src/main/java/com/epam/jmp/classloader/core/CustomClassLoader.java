package com.epam.jmp.classloader.core;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomClassLoader extends ClassLoader {
    
    private static final Logger log = LoggerFactory.getLogger(CustomClassLoader.class);
    private static final Logger console = LoggerFactory.getLogger("Console");
    private static final CustomClassLoader INSTANCE = new CustomClassLoader();
    
    private Class<?> userCommandClass;
    
    public static CustomClassLoader getInstance() {
        return INSTANCE;
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
                } catch (Exception e) {
                    
                }
            }
            if (null == c) {
                c = findClass(name);
                if (null != c) {
                    console.info("Class was loaded by Custom Classloader");
                }
            } else {
                console.info("Class was loaded by System Classloader");
            }
        }
        if (null == c) {
            c = super.loadClass(name, resolve);
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
    
    @Override
    protected Class<?> findClass(String _name) throws ClassNotFoundException {
        
        for (int i = 0; i < _name.length(); i++) {
            char c = _name.charAt(i);
            if (c == '.' || Character.isJavaIdentifierPart(c)) {
                continue;
            }
            throw new ClassNotFoundException(_name);
        }
        
        String classFileName = _name.replace('.', java.io.File.separatorChar) + ".class";
        java.net.URL url = getResource(classFileName);
        if (url == null) {
            url = ClassLoader.getSystemResource(classFileName);
        }
        if (url == null) {
            url = this.getClass().getResource(classFileName);
        }
        if (url == null) {
            url = this.getClass().getClassLoader().getResource(classFileName);
        }
        if (url == null) {
            throw new ClassNotFoundException(_name);
        }
        byte[] bytes = null;
        java.io.InputStream is = null;
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            is = url.openStream();
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = is.read(buffer)) >= 0) {
                baos.write(buffer, 0, i);
            }
            bytes = baos.toByteArray();
        } catch (Exception e) {
            throw new ClassNotFoundException(_name, e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error("IOException", e);
            }
        }
        
        Class<?> c = defineClass(_name, bytes, 0, bytes.length);
        return c;
    }
    
}
