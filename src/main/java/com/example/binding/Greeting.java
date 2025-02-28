package com.example.binding;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Greeting {
    private String message;
    private int totalCount;
    private int requestCount;

    // Default constructor is required for JAXB
    public Greeting() {
    }

    public Greeting(String message) {
        this.message = message;
     }

    public Greeting(String message, int totalCount, int requestCount) {
        this.message = message;
        this.totalCount = totalCount;
        this.requestCount = requestCount;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }
}