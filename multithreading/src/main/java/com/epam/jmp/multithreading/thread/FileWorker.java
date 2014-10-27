package com.epam.jmp.multithreading.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.jmp.multithreading.exception.BasicException;


public class FileWorker {
    
    private static final Map<String, ReentrantReadWriteLock> rwLockMaps = new ConcurrentHashMap<String, ReentrantReadWriteLock>();
    private static final Map<String, SoftReference<FileWorker>> cache = new ConcurrentHashMap<String, SoftReference<FileWorker>>();
    private static final Lock singletonLock = new ReentrantLock();
    
    private static final Logger log = LoggerFactory.getLogger(FileWorker.class);
    
    private final File file;
    private final Lock readLock;
    private final Lock writeLock;
    
    private FileWorker(String filePath) {
        file = new File(filePath);
        if (!file.exists()) {
            throw new BasicException(new IllegalArgumentException("File " + filePath + " doesn't exist."));
        }
        ReentrantReadWriteLock rwLock = FileWorker.rwLockMaps.get(filePath);
        if (null == rwLock) {
            rwLock = new ReentrantReadWriteLock(true);
            FileWorker.rwLockMaps.put(filePath, rwLock);
        }
        readLock = rwLock.readLock();
        writeLock = rwLock.writeLock();
    }
    
    public static FileWorker getInstance(String filePath) {
        if (!FileWorker.cache.containsKey(filePath)) {
            return FileWorker._getInstance(filePath);
        } else {
            FileWorker res = FileWorker.cache.get(filePath).get();
            if (null == res) {
                return FileWorker._getInstance(filePath);
            } else {
                return res;
            }
        }
    }
    
    public BufferedReader getReader() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return reader;
        } catch (FileNotFoundException e) {
            throw new BasicException(e);
        }
    }
    
    public BufferedWriter getWriter() {
        try {
            FileOutputStream out = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            return writer;
        } catch (FileNotFoundException e) {
            throw new BasicException(e);
        }
    }
    
    private static FileWorker _getInstance(String filePath) {
        try {
            FileWorker.singletonLock.lock();
            FileWorker fw = new FileWorker(filePath);
            FileWorker.cache.put(filePath, new SoftReference<FileWorker>(fw));
            FileWorker.log.info("New FileWorker was created for filePath=" + filePath);
            return fw;
        } finally {
            FileWorker.singletonLock.unlock();
        }
    }
    
    public String read(BufferedReader fr) {
        try {
            readLock.lock();
            StringBuilder sb = new StringBuilder();
            while (fr.ready()) {
                sb.append(fr.readLine());
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw new BasicException(e);
        } finally {
            readLock.unlock();
            try {
                fr.close();
            } catch (IOException e) {
                throw new BasicException(e);
            }
        }
    }
    
    public void write(BufferedWriter fw, String toWrite) {
        try {
            writeLock.lock();
            fw.write(toWrite);
        } catch (Exception e) {
            throw new BasicException(e);
        } finally {
            writeLock.unlock();
            try {
                fw.close();
            } catch (IOException e) {
                throw new BasicException(e);
            }
        }
    }
}
