package com.example;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create a Jetty server on port 8080
        Server server = new Server(8080);

        // Set up the context handler at the root context ("/")
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Create a ResourceConfig and register the package and the binder
        ResourceConfig config = new ResourceConfig();
        config.packages("com.example"); //will scan this and all sub packages on startup
        config.register(new AppBinder());

        // Create a ServletHolder for Jersey's ServletContainer using the ResourceConfig
        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        jerseyServlet.setInitOrder(0);
        context.addServlet(jerseyServlet, "/*");

        // Start the server
        server.start();
        server.join();
    }
}