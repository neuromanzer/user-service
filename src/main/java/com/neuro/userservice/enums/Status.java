package com.neuro.userservice.enums;

public enum Status {
    EMPTY_REQUEST("Empty request"),
    USER_CREATED("User created"),
    EMAIL_SERVICE_NOT_AVAILABLE("Email service is not available"),
    USER_ALREADY_EXISTS("Пользователь с таким email уже существует"),
    USER_UPDATED("User updated"),
    USER_NOT_FOUND("User not found"),
    USER_DELETED("User deleted"),
    NO_USER_FOR_DELETE("Not found for delete operation"),
    VALIDATION_ERROR("Validation error");

    private final String message;

    Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
