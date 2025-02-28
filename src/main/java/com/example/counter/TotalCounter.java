package com.example.counter;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class TotalCounter {

    private static final Logger logger = LoggerFactory.getLogger(TotalCounter.class);

    public TotalCounter() {
        logger.info("TotalCounter created");
    }

    private int count = 0;

    //Need to protect against concurrent access
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
