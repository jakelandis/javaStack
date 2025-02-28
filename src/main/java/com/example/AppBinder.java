package com.example;

import com.example.counter.PerRequestCounter;
import com.example.counter.TotalCounter;
import com.example.data.Hello;
import com.example.data.HelloFactory;
import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

public class AppBinder extends AbstractBinder {
    @Override
    protected void configure() {
        // Bind the constant for the wave emoji the named string called "wave"
        bind("\uD83D\uDC4B").to(String.class).named("wave");
        // Bind the factory that reads the local to the Hello interface
        bindFactory(HelloFactory.class).to(Hello.class);
        // Tell the binder to create the instances for me in the scope as declared
        bindAsContract(PerRequestCounter.class).in(RequestScoped.class);
        bindAsContract(TotalCounter.class).in(Singleton.class);
    }
}