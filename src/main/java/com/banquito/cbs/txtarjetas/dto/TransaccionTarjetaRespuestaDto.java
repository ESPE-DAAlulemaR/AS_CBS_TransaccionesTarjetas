package com.banquito.cbs.txtarjetas.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class TransaccionTarjetaRespuestaDto {
    private String _id;
    private String codigoUnico;
    private LocalDateTime fechaHora;
    private BigDecimal monto;
    private String cuentaComercio;
    private String referencia;
}
