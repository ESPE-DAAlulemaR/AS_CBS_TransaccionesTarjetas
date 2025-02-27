package com.banquito.cbs.tarjetas.repositorio;

import com.banquito.cbs.tarjetas.modelo.TransaccionTarjeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionTarjetaRepository extends MongoRepository<TransaccionTarjeta, String> {
    List<TransaccionTarjeta> findByIdTarjeta(Integer idTarjeta);
    List<TransaccionTarjeta> findByidCuentaTarjeta(Integer idCuentaTarjeta);
}
