package com.banquito.cbs.tarjetas.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class TransaccionTarjetaDto {
    private Integer idCuentaTarjeta;
    private Integer idTarjeta;
    private String tipo;
    private String codigoUnico;
    private LocalDateTime fechaHora;
    private BigDecimal monto;
    private String cuentaComercio;
    private String referencia;
    private Boolean aplicaImpuesto;
    private String transaccionEncriptada;
    private String codigoTransaccionPadre;
}
