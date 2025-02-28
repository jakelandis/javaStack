package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create a Jetty server on port 8080
        Server server = new Server(8080);

        // Set up the context handler at the root context ("/")
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Create a ServletHolder for Jersey's ServletContainer
        ServletHolder jerseyServlet = new ServletHolder(ServletContainer.class);
        jerseyServlet.setInitOrder(0);
        // Configure Jersey to scan for resources in the com.example package
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.example");
        context.addServlet(jerseyServlet, "/*");

        // Start the server
        server.start();
        server.join();
    }
}