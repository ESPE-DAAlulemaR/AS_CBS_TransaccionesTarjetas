package com.banquito.cbs.txtarjetas.excepcion;

public class EntidadNoEncontradaException extends RuntimeException {
    public EntidadNoEncontradaException(String message) {
        super(message);
    }
}