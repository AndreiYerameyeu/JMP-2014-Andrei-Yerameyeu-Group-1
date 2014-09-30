package com.epam.jmp.classloader.core;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoadClassCommand implements Command {
    
    private static final Logger log = LoggerFactory.getLogger(LoadClassCommand.class);
    
    private static ClassLoader classLoader = new CustomClassLoader();
    
    private final Logger out;
    private final BufferedReader reader;
    
    public LoadClassCommand(Logger out, BufferedReader reader) {
        this.out = out;
        this.reader = reader;
    }
    
    public void execute() throws IOException {
        out.info("Print class name:");
        String val = reader.readLine();
        try {
            LoadClassCommand.classLoader.loadClass(val);
            out.info("YRA");
        } catch (ClassNotFoundException e) {
            LoadClassCommand.log.error("Class " + val + " can't be loaded", e);
            out.info(e.getMessage());
        }
    }
    
}
