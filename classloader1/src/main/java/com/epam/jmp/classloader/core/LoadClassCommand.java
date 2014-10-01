package com.epam.jmp.classloader.core;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoadClassCommand implements Command {
    
    private static final Logger log = LoggerFactory.getLogger(LoadClassCommand.class);
    
    private static CustomClassLoader classLoader = CustomClassLoader.getInstance();
    
    private final String[] items = { "Load class", "Load Jar", "Return" };
    
    private final Logger out;
    private final BufferedReader reader;
    
    public LoadClassCommand(Logger out, BufferedReader reader) {
        this.out = out;
        this.reader = reader;
    }
    
    public void display() {
        for (int i = 0; i < items.length; i++) {
            out.info(Integer.toString(i + 1).concat(". ").concat(items[i]));
        }
    }
    
    @Override
    public int execute() throws IOException {
        display();
        String posStr = reader.readLine();
        LoadClassCommand.log.info(posStr);
        int pos = NumberUtils.toInt(posStr, -1);
        if (pos < 0 || pos > items.length) {
            return 0;
        } else {
            switch (pos) {
                case 1:
                    out.info("Print path to class file:");
                    String val = reader.readLine();
                    try {
                        Class<?> result = LoadClassCommand.classLoader.loadClass(val);
                        out.info("Class " + val + " has been successfully loaded.");
                        LoadClassCommand.classLoader.setUserCommandClass(result);
                    } catch (ClassNotFoundException e) {
                        LoadClassCommand.log.error("Class " + val + " can't be loaded", e);
                        out.info(e.getMessage());
                    }
                    return 1;
                case 2:
                    out.info("Print path to jar file:");
                    String jarPath = reader.readLine();
                    ClassLoader jarLoader = new CustomJarClassLoader(jarPath);
                    out.info("Print class name:");
                    val = reader.readLine();
                    try {
                        Class<?> result = jarLoader.loadClass(val);
                        out.info("Class " + val + " has been successfully loaded.");
                        LoadClassCommand.classLoader.setUserCommandClass(result);
                    } catch (ClassNotFoundException e) {
                        LoadClassCommand.log.error("Class " + val + " can't be loaded", e);
                        out.info(e.getMessage());
                    }
                    return 1;
                case 3:
                    return 0;
                default:
                    return 0;
            }
        }
    }
    
}
