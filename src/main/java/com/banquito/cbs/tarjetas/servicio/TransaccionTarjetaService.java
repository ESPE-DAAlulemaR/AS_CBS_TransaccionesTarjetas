package com.banquito.cbs.tarjetas.servicio;

import com.banquito.cbs.tarjetas.repositorio.DiferidoRepository;
import com.banquito.cbs.tarjetas.repositorio.TransaccionTarjetaRepository;
import org.springframework.stereotype.Service;

@Service
public class TransaccionTarjetaService {
    private final TransaccionTarjetaRepository transaccionTarjetaRepository;
    private final DiferidoRepository diferidoRepository;

    public TransaccionTarjetaService(TransaccionTarjetaRepository transaccionTarjetaRepository, DiferidoRepository diferidoRepository) {
        this.transaccionTarjetaRepository = transaccionTarjetaRepository;
        this.diferidoRepository = diferidoRepository;
    }
}
