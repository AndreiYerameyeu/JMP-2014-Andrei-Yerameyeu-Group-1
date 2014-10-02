package com.epam.jmp.classloader.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EntryPoint {
    
    private static final Logger consoleLog = LoggerFactory.getLogger("Console");
    
    public static void main(String... args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean done = false;
        EntryPoint.consoleLog.info("Classloader task 1.");
        //TODO Why are you passing log to constructor? 
        Menu menu = new Menu(EntryPoint.consoleLog, reader);
        try {
            while (!done) { //TODO why not just while(true)?
                menu.display();
                Command command = menu.read();
                if (command != null) {
                    if (command.execute() < 0) {
                        break;
                    }
                } else {
                    EntryPoint.consoleLog.info("Not a valid choise.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
