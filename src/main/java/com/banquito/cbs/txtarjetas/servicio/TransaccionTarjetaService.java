package com.banquito.cbs.txtarjetas.servicio;

import com.banquito.cbs.txtarjetas.excepcion.EntidadNoEncontradaException;
import com.banquito.cbs.txtarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.txtarjetas.repositorio.TransaccionTarjetaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void registrarTransaccion(TransaccionTarjeta transaccion, Boolean diferido, Integer cuotas, BigDecimal interes) {
        if (diferido)
            this.diferidoService.crearDiferido(transaccion, cuotas, interes);

        this.repositorio.save(transaccion);
    }
}
