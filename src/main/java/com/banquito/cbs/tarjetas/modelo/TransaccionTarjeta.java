package com.banquito.cbs.tarjetas.modelo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@ToString
@Document(collection = "transacciones_tarjeta")
public class TransaccionTarjeta {
    @MongoId
    private String _id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaccionTarjeta that = (TransaccionTarjeta) o;
        return Objects.equals(_id, that._id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_id);
    }
}
