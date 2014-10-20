package com.epam.jmp.memorymanagement.core;


public class ReferenceTask {
    
    private final int b = 101;
    
    public static void main(String[] args) {
        ReferenceTask a = new ReferenceTask();
        while (true) {
            ReferenceTask.a(a);
            ReferenceTask.b(a.b);
            a = ReferenceTask.c(a);
            System.out.println(a);
        }
    }
    
    private static void a(Object a) {
        a = new ReferenceTask();
    }
    
    private static int b(int b) {
        return b;
    }
    
    private static ReferenceTask c(ReferenceTask c) {
        c = new ReferenceTask();
        return c;
    }
}
