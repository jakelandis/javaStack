package com.example.rest;

import com.example.data.Hello;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Path("/hello")
public class RestHello {

    private final String wave;
    private final Hello hello;

    @Inject
    public RestHello(Hello hello, @Named("wave") String wave) {
        this.wave = wave;
        this.hello = hello;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public String getHello() {
        return wave + " " + hello.sayHello() + " " + wave;
    }
}