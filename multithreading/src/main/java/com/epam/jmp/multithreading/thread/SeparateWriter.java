package com.epam.jmp.multithreading.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.jmp.multithreading.exception.BasicException;


public class SeparateWriter implements Runnable {
    
    private final BlockingQueue<String> writeQueue = new SynchronousQueue<String>(true);
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final FileWorker worker;
    private final SeparateReader sr;
    
    public SeparateWriter(String path) {
        worker = FileWorker.getInstance(path);
        sr = new SeparateReader(path);
    }
    
    @Override
    public void run() throws BasicException {
        while (true) {
            String strToWrite = writeQueue.poll();
            if (null != strToWrite) {
                log.debug("Attempt to write: " + strToWrite);
                String res = sr.call();
                writeToFile(res + strToWrite + "\n");
                log.debug(strToWrite + " was successfully written");
            }
        }
    }
    
    public void addToWrite(String s) throws InterruptedException {
        writeQueue.put(s);
        log.debug("Was added to write: " + s);
    }
    
    private void writeToFile(String s) throws BasicException {
        worker.write(worker.getWriter(), s);
    }
    
}
