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
}

application {
    mainClass.set("com.example.Main")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}