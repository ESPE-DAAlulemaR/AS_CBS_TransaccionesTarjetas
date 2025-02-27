package com.banquito.cbs.tarjetas.controlador;

import com.banquito.cbs.tarjetas.dto.TransaccionTarjetaRespuestaDto;
import com.banquito.cbs.tarjetas.servicio.TransaccionTarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transacciones-tarjetas")
public class TransaccionTarjetaController {
    private final TransaccionTarjetaService servicio;

    public TransaccionTarjetaController(TransaccionTarjetaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<TransaccionTarjetaRespuestaDto> listar(Integer idTarjeta)
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.servicio.listarPorIdTarjeta(idTarjeta));
    }
}
