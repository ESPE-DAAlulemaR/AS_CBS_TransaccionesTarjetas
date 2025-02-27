package com.banquito.cbs.tarjetas.servicio;

import com.banquito.cbs.tarjetas.dto.TransaccionTarjetaDto;
import com.banquito.cbs.tarjetas.excepcion.EntidadNoEncontradaException;
import com.banquito.cbs.tarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.tarjetas.repositorio.TransaccionTarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionTarjetaService {
    private final TransaccionTarjetaRepository repositorio;

    public TransaccionTarjetaService(TransaccionTarjetaRepository repositorio) {
        this.repositorio = repositorio;
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

    public void registrarTransaccion(TransaccionTarjeta transaccion) {
        this.repositorio.save(transaccion);
    }
}
