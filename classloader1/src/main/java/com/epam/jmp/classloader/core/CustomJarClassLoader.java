package com.epam.jmp.classloader.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.Resource;
import sun.misc.URLClassPath;

//TODO almost the same code, why you write different class... 
//TODO you should not use internal classes of the hotspot
public class CustomJarClassLoader extends ClassLoader {
    
    private static final Logger log = LoggerFactory.getLogger(CustomJarClassLoader.class);
    private static final Logger console = LoggerFactory.getLogger("Console");
    
    private final URLClassPath ucp;
    
    public CustomJarClassLoader(String jarPath) throws MalformedURLException {
        URL url = new File(jarPath).toURI().toURL();
        ucp = new URLClassPath(new URL[] { url });
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
                        CustomJarClassLoader.console.info("Class " + name + " was loaded by parent System Classloader");
                    }
                } catch (Exception e) {
                    CustomJarClassLoader.log.error("Can't load class " + name + " by parent", e);
                }
            }
            if (null == c) {
                c = findClass(name);
                if (null != c) {
                    CustomJarClassLoader.console.info("Class " + name + " was loaded by Custom JarClassloader");
                }
            }
        }
        if (null == c) {
            c = super.loadClass(name, resolve);
            if (null != c) {
                CustomJarClassLoader.console.info("Class " + name + " was loaded by super Classloader");
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
    
    @Override
    protected Class<?> findClass(String _name) throws ClassNotFoundException {
        Resource res = ucp.getResource(getClassFileName(_name), false);
        if (res != null) {
            try {
                return defineClass(_name, res);
            } catch (IOException e) {
                throw new ClassNotFoundException(_name, e);
            }
        } else {
            throw new ClassNotFoundException(_name);
        }
    }
    
    private Class<?> defineClass(String name, Resource res) throws IOException {
        java.nio.ByteBuffer bb = res.getByteBuffer();
        if (bb != null) {
            return defineClass(name, bb.array(), 0, bb.position());
        } else {
            byte[] b = res.getBytes();
            return defineClass(name, b, 0, b.length);
        }
    }
    
    private String getClassFileName(String _name) {
        String[] result = StringUtils.split(_name, java.io.File.separatorChar);
        String className = result[result.length - 1];
        String classPostfix = ".class";
        if (_name.endsWith(classPostfix)) {
            className = className.substring(0, className.length() - classPostfix.length());
        }
        String classFileName = _name.replace('.', '/');
        classFileName += classPostfix;
        return classFileName;
    }
    
}
