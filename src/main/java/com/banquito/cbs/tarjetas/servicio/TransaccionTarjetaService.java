package com.banquito.cbs.tarjetas.servicio;

import com.banquito.cbs.tarjetas.excepcion.EntidadNoEncontradaException;
import com.banquito.cbs.tarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.tarjetas.repositorio.TransaccionTarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionTarjetaService {
    private final TransaccionTarjetaRepository repositorio;
    private final DiferidoService diferidoService;

    public TransaccionTarjetaService(TransaccionTarjetaRepository repositorio, DiferidoService diferidoService) {
        this.repositorio = repositorio;
        this.diferidoService = diferidoService;
    }

    public TransaccionTarjeta buscarPorId(String id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("No existe ninguna transacción con ID: " + id));
    }

    public List<TransaccionTarjeta> listarPorIdTarjeta(Integer idTarjeta) {
        return repositorio.findByIdTarjeta(idTarjeta);
    }

    public List<TransaccionTarjeta> listarPorIdCuentaTarjeta(Integer idCuentaTarjeta) {
        return repositorio.findByidCuentaTarjeta(idCuentaTarjeta);
    }

    public void registrarTransaccion(TransaccionTarjeta transaccion, Boolean diferido, Integer cuotas, Boolean conIntereses) {
        if (diferido)
            this.diferidoService.crearDiferido(transaccion, cuotas, conIntereses);

        this.repositorio.save(transaccion);
    }
}
