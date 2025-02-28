package com.example.rest;

import com.example.counter.PerRequestCounter;
import com.example.counter.TotalCounter;
import com.example.data.Hello;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("/hello")
public class RestHello {

    private final String wave;
    private final Hello hello;
    private final TotalCounter totalCounter;
    private final PerRequestCounter perRequestCounter;
    private static final Logger logger = LoggerFactory.getLogger(RestHello.class);
    private static final ExecutorService executor = Executors.newCachedThreadPool(); //could also inject this for better reuse


    @Inject
    public RestHello(Hello hello, @Named("wave") String wave, TotalCounter totalCounter, PerRequestCounter perRequestCounter) {
        this.wave = wave;
        this.hello = hello;
        this.totalCounter = totalCounter;
        this.perRequestCounter = perRequestCounter;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public String getHello() {
        logger.info("Received sync request for plain text");
        return wave + " " + hello.sayHello() + " " + wave
                + " (total: " + totalCounter.getCount() + ", per request: " + perRequestCounter.getCount() + ")";
    }

    @GET
    @Path("async")
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public void asyncHello(@Suspended AsyncResponse asyncResponse) {
        logger.info("Received async request for /hello/async");
        executor.submit(() -> {
            try {
                Thread.sleep(1000); // Simulate asynchronous processing delay
                String response =   wave + " " + hello.sayHello() + " " + wave
                        + " (total: " + totalCounter.getCount() + ", per request: " + perRequestCounter.getCount() + ")";
                logger.info("Processing complete, resuming response");
                asyncResponse.resume(response);
            } catch (InterruptedException e) {
                logger.error("Processing interrupted", e);
                asyncResponse.resume(e);
            }
        });
    }
}