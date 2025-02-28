package com.example.counter;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class PerRequestCounter {
    private static final Logger logger = LoggerFactory.getLogger(PerRequestCounter.class);

    public PerRequestCounter() {
        logger.info("PerRequestCounter created");
    }

    private int count = 0;

    //Generally no need to protect against concurrent access since this is request scoped
    // and generally don't have concurrency within the request scope, however you can have fully async , non-blocking requests
    // in which case you may need to protect against concurrent access to request scoped object
    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
