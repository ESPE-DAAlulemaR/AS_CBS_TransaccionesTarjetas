package com.banquito.cbs.tarjetas.excepcion;

public class EntidadDuplicadaException extends RuntimeException {
    public EntidadDuplicadaException(String message) {
        super(message);
    }
}