package com.banquito.cbs.tarjetas.modelo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@ToString
@Document(collection = "diferidos")
public class Diferido {
    @MongoId
    private String _id;

    private TransaccionTarjeta transaccionTarjeta;
    private BigDecimal montoTotal;
    private Integer numCuotas;
    private BigDecimal interes;

    private List<Cuota> cuotas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diferido diferido = (Diferido) o;
        return Objects.equals(_id, diferido._id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_id);
    }
}
