package com.banquito.cbs.txtarjetas.repositorio;

import com.banquito.cbs.txtarjetas.modelo.Diferido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiferidoRepository extends MongoRepository<Diferido, String> {
}
