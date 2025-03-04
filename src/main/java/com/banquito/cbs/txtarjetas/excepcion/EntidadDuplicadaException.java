package com.banquito.cbs.txtarjetas.excepcion;

public class EntidadDuplicadaException extends RuntimeException {
    public EntidadDuplicadaException(String message) {
        super(message);
    }
}