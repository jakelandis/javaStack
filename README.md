# Java Stack

A small application to help showcase a basic Java stack for building REST services using Jersey, HK2, Jetty, Gradle, SLF4J, and logback with support for basic authentication, JSON data binding, and async processing.

## Run the project

### Command line

```./gradlew run```

### Intellij

Ensure that you have the project set to Java 23 (or newer)

![Java23](img/intellij1.jpg)
![Start](img/intellij2.jpg)

### API

Flex "hello world" based on language using Dependency injection for language selection.

#### sync hello world in plain text
``` curl -u admin:password localhost:8080/hello```
``` curl -u admin:password -H "Accept-Language: es" localhost:8080/hello```

#### async hello world in plain text
``` curl -u admin:password localhost:8080/hello/async```
``` curl -u admin:password -H "Accept-Language: es" localhost:8080/hello/async```

#### echo the message passed in json back in json 
``` curl -u admin:password -X POST -H "Content-Type: application/json" -d '{"message": "Hello, there!"}' http://localhost:8080/echo```

## Notes

This sample application shows how to use JAX-RS (Jersey) in a servlet container (Jetty) with Dependency Injection (HK2) with 
support for basic authentication, JSON data binding (Jackson) using JAX-B annotations and async processing with Gradle as the build system and logback with 
SLF4J as the logger.

Control starts with the `Main` class which initializes all the required components and starts the Jetty server registering the JAX-RS and 
HJ2 components. HK2 is used for dependency injection and Jersey for JAX-RS and appears to be heavily inspired by Guice. The AppBinder
class is used to instruct HK2 on how to bind the components together.

The `rest` package has the REST resources which includes the JAX-RS annonations to define the REST endpoints. It also shows different
ways to inject dependencies into the resources, protecting resources with basic auth via filtering requests, and async processing. 

The `data` package shows an example of how you can use Inversion of Control in this setup to have different implementation that are 
loosely coupled and changes to the implementation requires no changes to the REST resources. 

The `counter` package shows and example of how you can use `@RequestScoped` vs. `@Singleton` to have a counter that is unique to each 
request vs. a counter that is shared across all requests. This is a very powerful feature since it allows your DI fully manage the lifecycle
of the objects, all of which are injectable across many different concerns.

The `binding` package shows how you can use JAX-B annotations to bind JSON data to Java objects, and how easily they can be serialized
to/from JSON. 

This is also provides an example of using Gradle with Kotlin DSL to build the project and use of SLF4J with logback for logging.

There is still much more that can be done with this setup and I have used these components as the basic stack in many different production
systems with good success.



