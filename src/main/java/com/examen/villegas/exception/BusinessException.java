package com.examen.villegas.exception;

public class BusinessException extends RuntimeException {
    private final String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Error de negocio: " + this.message;
    }
} 