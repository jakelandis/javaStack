plugins {
    java
    application
}

group = "com.example"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("org.eclipse.jetty:jetty-server:12.0.16")
    implementation("org.eclipse.jetty.ee10:jetty-ee10-servlet:12.0.16")
    implementation("org.glassfish.jersey.containers:jersey-container-servlet:3.1.10")
    implementation("org.glassfish.jersey.inject:jersey-hk2:3.1.10")
    implementation("org.glassfish.hk2:hk2-utils:3.1.1")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.9")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:3.1.10")
    //https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Integration-and-configuration
    implementation("io.swagger.core.v3:swagger-jaxrs2-jakarta:2.2.8")
    implementation("io.swagger.core.v3:swagger-annotations-jakarta:2.2.8")

}

application {
    mainClass.set("com.example.Main")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}