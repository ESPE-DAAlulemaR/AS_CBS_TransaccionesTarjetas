package com.banquito.cbs.tarjetas.repositorio;

import com.banquito.cbs.tarjetas.modelo.Diferido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiferidoRepository extends MongoRepository<Diferido, String> {
}
