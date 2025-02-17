package com.banquito.cbs.tarjetas.controlador;

import com.banquito.cbs.tarjetas.servicio.TransaccionTarjetaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transacciones-tarjetas")
public class TransaccionTarjetaController {
    private final TransaccionTarjetaService transaccionTarjetaService;

    public TransaccionTarjetaController(TransaccionTarjetaService transaccionTarjetaService) {
        this.transaccionTarjetaService = transaccionTarjetaService;
    }
}
