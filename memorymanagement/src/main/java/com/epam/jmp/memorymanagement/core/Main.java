package com.epam.jmp.memorymanagement.core;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;


public class Main {
    
    public static void main(String[] args) {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        for (String s : arguments) {
            System.out.println("Command Line Argument:" + s);
        }
    }
}
