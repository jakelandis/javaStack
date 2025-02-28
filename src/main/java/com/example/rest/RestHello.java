package com.example.rest;

import com.example.counter.PerRequestCounter;
import com.example.counter.TotalCounter;
import com.example.data.Hello;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class RestHello {

    private final String wave;
    private final Hello hello;
    private final TotalCounter totalCounter;
    private final PerRequestCounter perRequestCounter;
    private static final Logger logger = LoggerFactory.getLogger(RestHello.class);

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
        logger.info("Received request for plain text");
        return wave + " " + hello.sayHello() + " " + wave
                + " (total: " + totalCounter.getCount() + ", per request: " + perRequestCounter.getCount() + ")";
    }
}