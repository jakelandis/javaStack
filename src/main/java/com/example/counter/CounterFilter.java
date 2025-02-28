package com.example.counter;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class CounterFilter implements ContainerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CounterFilter.class);

    @Inject
    //need to use a provider since the filter itself is singleton scoped and can't inject request scoped objects directly into
    // a singleton scoped object
    private jakarta.inject.Provider<PerRequestCounter> perRequestCounterProvider;

    @Inject
    private TotalCounter totalCounter;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.info("Filtering request to path: " + requestContext.getUriInfo().getPath());
        // For example, increment the counters for every incoming request.
        totalCounter.increment();
        perRequestCounterProvider.get().increment();
    }
}