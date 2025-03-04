package com.banquito.cbs.txtarjetas.excepcion;

public class OperacionInvalidaException extends RuntimeException {
    public OperacionInvalidaException(String message) {
        super(message);
    }
}