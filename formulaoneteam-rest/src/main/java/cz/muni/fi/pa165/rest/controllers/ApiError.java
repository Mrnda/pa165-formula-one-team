package cz.muni.fi.pa165.rest.controllers;

import java.util.Objects;

public class ApiError {
    private String message;

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiError)) return false;
        ApiError apiError = (ApiError) o;
        return Objects.equals(getMessage(), apiError.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }
}
