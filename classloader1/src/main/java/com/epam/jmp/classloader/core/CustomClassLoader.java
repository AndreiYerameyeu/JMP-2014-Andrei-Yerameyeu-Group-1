package com.epam.jmp.classloader.core;

import java.io.IOException;


public class CustomClassLoader extends ClassLoader {
    
    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (null == c) {
            ClassLoader parent = getParent();
            if (null != parent) {
                try {
                    // Should we delegate classloading to parent first?
                    // c = parent.loadClass(name);
                } catch (Exception e) {
                    
                }
            }
            if (null == c) {
                c = findClass(name);
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        Class<?> c = defineClass(_name, bytes, 0, bytes.length);
        return c;
    }
}
