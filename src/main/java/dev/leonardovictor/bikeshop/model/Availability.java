package dev.leonardovictor.bikeshop.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Availability {
    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable");

    private String status;

    private Availability(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
