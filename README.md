
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
