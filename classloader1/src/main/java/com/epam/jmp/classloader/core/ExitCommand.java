package com.epam.jmp.classloader.core;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExitCommand implements Command {
    
    private static final Logger log = LoggerFactory.getLogger(ExitCommand.class);
    
    private final Logger out;
    
    public ExitCommand(Logger out, BufferedReader reader) {
        this.out = out;
    }
    
    public void execute() throws IOException {
        out.info("Exiting...");
        ExitCommand.log.info("Exiting...");
        System.exit(0);
    }
    
}
