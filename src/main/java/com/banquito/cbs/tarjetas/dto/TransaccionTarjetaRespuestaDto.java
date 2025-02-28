package com.banquito.cbs.tarjetas.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class TransaccionTarjetaRespuestaDto {
    private Integer _id;
    private String codigoUnico;
    private LocalDateTime fechaHora;
    private BigDecimal monto;
    private String cuentaComercio;
    private String referencia;
}
