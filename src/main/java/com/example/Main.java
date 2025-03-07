package com.example;

import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create a Jetty server on port 8080
        Server server = new Server(8080);

        // Set up the context handler at the root context ("/")
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        setupOpenApi();

        // Create a ResourceConfig and register the package and the binder
        ResourceConfig config = new ResourceConfig();
        config.property(ServerProperties.WADL_FEATURE_DISABLE, true);
        config.packages("com.example");
        config.register(new AppBinder());
        config.register(OpenApiResource.class); // expose /openapi.json

        // Create a ServletHolder for Jersey's ServletContainer using the ResourceConfig
        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        jerseyServlet.setInitOrder(0);
        context.addServlet(jerseyServlet, "/*");

        // Start the server
        server.start();
        server.join();
    }

    private static void setupOpenApi() throws OpenApiConfigurationException {
        Info info = new Info()
                .title("Sample App")
                .description("This is a sample.")
                .version("1.0.0")
                .contact(new Contact()
                        .email("me@example.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));
        OpenAPI oas = new OpenAPI();
        oas.info(info);
        //https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Integration-and-configuration#jax-rs-application
        SwaggerConfiguration openApiConfig = new SwaggerConfiguration();
        // Restrict scanning to your desired packages
        openApiConfig.openAPI(oas);
        openApiConfig.setResourcePackages(new HashSet<>(Arrays.asList("com.example")));
        openApiConfig.setPrettyPrint(true);
        openApiConfig.setIgnoredRoutes(Set.of("/swagger-ui"));
        new JaxrsOpenApiContextBuilder().openApiConfiguration(openApiConfig).buildContext(true);
    }
}