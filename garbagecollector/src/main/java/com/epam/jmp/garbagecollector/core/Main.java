package com.epam.jmp.garbagecollector.core;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;


public class Main {
    
    public static void main(String... args) {
        System.out.println("MaxMemory:" + Runtime.getRuntime().maxMemory());
        System.out.println("TotalMemory:" + Runtime.getRuntime().totalMemory());
        
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        for (String s : arguments) {
            System.out.println("Command Line Argument:" + s);
        }
    }
    
}
