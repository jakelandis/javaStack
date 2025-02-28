package com.example;

import com.example.data.Hello;
import com.example.data.HelloFactory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class AppBinder extends AbstractBinder {
    @Override
    protected void configure() {
        // Bind the constant for the wave emoji the named string called "wave"
        bind("\uD83D\uDC4B").to(String.class).named("wave");
        bindFactory(HelloFactory.class).to(Hello.class);
    }
}