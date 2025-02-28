package com.example.data;

import jakarta.ws.rs.core.HttpHeaders;
import org.glassfish.hk2.api.Factory;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Locale;

public class HelloFactory implements Factory<Hello> {

    private final HttpHeaders headers;

    @Inject
    public HelloFactory(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override
    public Hello provide() {
        List<Locale> languages = headers.getAcceptableLanguages();
        // Look for Spanish language ("es")
        for (Locale locale : languages) {
            if ("es".equalsIgnoreCase(locale.getLanguage())) {
                return new HelloSpanish();
            }
        }
        // Default to English if Spanish is not accepted
        return new HelloEnglish();
    }

    @Override
    public void dispose(Hello instance) {
        // No disposal required in this simple example
    }
}