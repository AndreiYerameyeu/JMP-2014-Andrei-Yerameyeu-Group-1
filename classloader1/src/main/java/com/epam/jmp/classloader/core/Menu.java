package com.epam.jmp.classloader.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Menu {
    
    private static final Logger log = LoggerFactory.getLogger(Menu.class);
    
    private final String[] items = { "Load class", "Reload class", "Run user command", "Exit" };
    
    private final Logger out;
    private final BufferedReader reader;
    private final CustomClassLoader classLoader = CustomClassLoader.getInstance();
    
    
    public Menu(Logger out, BufferedReader reader) {
        this.out = out;
        this.reader = reader;
    }
    
    public void display() {
        for (int i = 0; i < items.length; i++) {
            out.info(Integer.toString(i + 1).concat(". ").concat(items[i]));
        }
    }
    
    public Command read() throws IOException {
        String posStr = reader.readLine();
        Menu.log.info(posStr);
        int pos = NumberUtils.toInt(posStr, -1);
        if (pos < 0 || pos > items.length) {
            return null;
        } else {
            Command com = null;
            switch (pos) {
                case 1:
                    com = new LoadClassCommand(out, reader);
                    break;
                case 2:
                case 3:
                    Class<?> res = classLoader.getUserCommandClass();
                    if (null != res) {
                        try {
                            com = (Command)res.getConstructors()[0].newInstance(out, reader);
                        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
                            log.error("Can't create new istance of " + res.getCanonicalName(), e);
                        }
                    }
                    break;
                case 4:
                    com = new ExitCommand(out, reader);
                    break;
                default:
                    break;
            }
            return com;
        }
    }
    
}
