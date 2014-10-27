package com.epam.jmp.multithreading.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.jmp.multithreading.thread.SeparateReader;
import com.epam.jmp.multithreading.thread.SeparateWriter;


public class Main {
    
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        String filePath = "D:\\Multithreading.txt";
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        for (String s : arguments) {
            System.out.println("Command Line Argument:" + s);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ExecutorService es = Executors.newFixedThreadPool(2);
        SeparateWriter sw = new SeparateWriter(filePath);
        SeparateReader sr = new SeparateReader(filePath);
        es.execute(sw);
        
        try {
            boolean exit = false;
            while (!exit) {
                Main.display();
                String posStr = reader.readLine();
                int pos = NumberUtils.toInt(posStr, -1);
                if (pos < 0 || pos > 3) {
                    return;
                }
                
                switch (pos) {
                    case 1:
                        String res = es.submit(sr).get();
                        System.out.println(res);
                        break;
                    case 2:
                        System.out.println("Enter what to write:");
                        String gg = reader.readLine();
                        sw.addToWrite(gg);
                        break;
                    case 3:
                    default:
                        exit = true;
                        break;
                }
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            Main.log.error("", e);
        }
    }
    
    private static void display() {
        System.out.println("1. Read.\n2. Write.\n3. Exit.");
    }
}
