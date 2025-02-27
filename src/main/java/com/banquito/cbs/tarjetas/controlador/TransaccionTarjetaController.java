package com.banquito.cbs.tarjetas.controlador;

import com.banquito.cbs.tarjetas.controlador.mapper.TransaccionTarjetaPeticionMapper;
import com.banquito.cbs.tarjetas.controlador.mapper.TransaccionTarjetaRespuestaMapper;
import com.banquito.cbs.tarjetas.dto.TransaccionTarjetaDto;
import com.banquito.cbs.tarjetas.dto.TransaccionTarjetaRespuestaDto;
import com.banquito.cbs.tarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.tarjetas.servicio.TransaccionTarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/transacciones-tarjetas")
public class TransaccionTarjetaController {
    private final TransaccionTarjetaService servicio;
    private final TransaccionTarjetaPeticionMapper peticionMapper;
    private final TransaccionTarjetaRespuestaMapper respuestaMapper;

    public TransaccionTarjetaController(
            TransaccionTarjetaService servicio,
            TransaccionTarjetaPeticionMapper peticionMapper,
            TransaccionTarjetaRespuestaMapper respuestaMapper
    ) {
        this.servicio = servicio;
        this.peticionMapper = peticionMapper;
        this.respuestaMapper = respuestaMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<TransaccionTarjeta>> listar(Integer idTarjeta)
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.servicio.listarPorIdTarjeta(idTarjeta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionTarjetaRespuestaDto> buscar(@PathVariable("id") String id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.respuestaMapper.toDto(this.servicio.buscarPorId(id)));
    }

    @PostMapping("/")
    public ResponseEntity<TransaccionTarjetaRespuestaDto> almacenar(@RequestBody TransaccionTarjetaDto transaccionPeticion) {
        TransaccionTarjeta transaccionTarjeta = this.peticionMapper.toPersistence(transaccionPeticion);
        this.servicio.registrarTransaccion(
                transaccionTarjeta,
                transaccionPeticion.getDiferido(),
                transaccionPeticion.getCuotas(),
                transaccionPeticion.getConIntereses()
        );
        TransaccionTarjetaRespuestaDto respuesta = this.respuestaMapper.toDto(transaccionTarjeta);

        return  ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
