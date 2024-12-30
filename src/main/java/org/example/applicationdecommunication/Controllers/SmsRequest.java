package org.example.applicationdecommunication.Controllers;

import jakarta.validation.constraints.NotNull;

public class SmsRequest {

    @NotNull(message = "Phone number is required")
    private String to;

    @NotNull(message = "Message is required")
    private String message;

    // Getters and Setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
