package com.banquito.cbs.txtarjetas.controlador;

import com.banquito.cbs.txtarjetas.controlador.mapper.TransaccionTarjetaPeticionMapper;
import com.banquito.cbs.txtarjetas.controlador.mapper.TransaccionTarjetaRespuestaMapper;
import com.banquito.cbs.txtarjetas.dto.TransaccionTarjetaDto;
import com.banquito.cbs.txtarjetas.dto.TransaccionTarjetaRespuestaDto;
import com.banquito.cbs.txtarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.txtarjetas.servicio.TransaccionTarjetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transacciones de Tarjetas",
        description = "API para la gestión de transacciones con tarjetas de crédito o débito")
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

    @Operation(
            summary = "Listar transacciones por ID de tarjeta",
            description = "Retorna una lista de transacciones filtradas por el ID de la tarjeta"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de transacciones devuelto exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron transacciones para el ID de tarjeta especificado")
    })
    @GetMapping("/")
    public ResponseEntity<List<TransaccionTarjeta>> listar(
            @Parameter(description = "ID de la tarjeta para filtrar las transacciones", required = true)
            @RequestParam("idTarjeta") Integer idTarjeta
    ) {
        List<TransaccionTarjeta> transacciones = this.servicio.listarPorIdTarjeta(idTarjeta);
        return ResponseEntity.status(HttpStatus.OK).body(transacciones);
    }

    @Operation(
            summary = "Obtener transacción por ID",
            description = "Busca y retorna la transacción asociada al ID proporcionado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transacción encontrada"),
            @ApiResponse(responseCode = "404", description = "No se encontró la transacción con el ID proporcionado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransaccionTarjetaRespuestaDto> buscar(
            @Parameter(description = "ID único de la transacción a buscar", required = true)
            @PathVariable("id") String id
    ) {
        TransaccionTarjeta transaccion = this.servicio.buscarPorId(id);
        TransaccionTarjetaRespuestaDto respuesta = this.respuestaMapper.toDto(transaccion);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(
            summary = "Registrar nueva transacción",
            description = "Registra una nueva transacción de tarjeta. Incluye información sobre si es diferido, cuotas, etc."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transacción registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<TransaccionTarjetaRespuestaDto> almacenar(
            @Parameter(description = "Objeto con la información de la transacción a registrar", required = true)
            @RequestBody TransaccionTarjetaDto transaccionPeticion
    ) {
        TransaccionTarjeta transaccionTarjeta = this.peticionMapper.toPersistence(transaccionPeticion);
        this.servicio.registrarTransaccion(
                transaccionTarjeta,
                transaccionPeticion.getDiferido(),
                transaccionPeticion.getCuotas(),
                transaccionPeticion.getInteres()
        );
        TransaccionTarjetaRespuestaDto respuesta = this.respuestaMapper.toDto(transaccionTarjeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
