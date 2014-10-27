package com.epam.jmp.multithreading.thread;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.jmp.multithreading.exception.BasicException;


public class SeparateReader implements Callable<String> {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final FileWorker worker;
    
    public SeparateReader(String path) {
        worker = FileWorker.getInstance(path);
    }
    
    @Override
    public String call() throws BasicException {
        String s = readFile();
        log.debug("File was successfully read");
        return s;
    }
    
    private String readFile() throws BasicException {
        return worker.read(worker.getReader());
    }
    
}
