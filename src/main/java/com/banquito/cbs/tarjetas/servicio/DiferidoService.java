package com.banquito.cbs.tarjetas.servicio;

import com.banquito.cbs.tarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.tarjetas.repositorio.DiferidoRepository;
import org.springframework.stereotype.Service;

@Service
public class DiferidoService {
    private final DiferidoRepository repositorio;

    public DiferidoService(DiferidoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void crearDiferido(TransaccionTarjeta transaccionTarjeta, Integer cuotas, Boolean tieneIntereses) {
        //
    }
}
