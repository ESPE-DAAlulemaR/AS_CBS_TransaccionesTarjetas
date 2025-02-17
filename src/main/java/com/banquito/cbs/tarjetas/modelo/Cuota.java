package com.banquito.cbs.tarjetas.modelo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
public class Cuota {
    private Integer numeroCuota;
    private BigDecimal capital;
    private BigDecimal interes;
    private BigDecimal total;
    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;
    private String estado;
}
