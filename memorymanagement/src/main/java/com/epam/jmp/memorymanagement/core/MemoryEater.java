package com.epam.jmp.memorymanagement.core;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: Ivan_Spresov
 * Date: 3/3/14
 */
public class MemoryEater {
    
    static final Logger logger = Logger.getLogger("MemoryEater.class");
    
    public static void main(String[] args) {
        List v = new ArrayList();
        
        
        while (true) {
            Runtime rt = Runtime.getRuntime();
            byte b[] = new byte[10 * 1024 * 1024]; // 10 MB
            if (rt.totalMemory() % rt.freeMemory() < rt.totalMemory() * 0.1) {
                v.clear();
            }
            
            v.add(b);
            
            
            MemoryEater.logger.info("free memory: " + rt.freeMemory());
            MemoryEater.logger.info("total memory: " + rt.totalMemory() + "\n");
            
            /*
             * try {
             * Thread.sleep(100);
             * } catch (InterruptedException e) {
             * }
             */
        }
    }
}
