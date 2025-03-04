package com.banquito.cbs.txtarjetas;

import com.banquito.cbs.txtarjetas.excepcion.EntidadNoEncontradaException;
import com.banquito.cbs.txtarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.txtarjetas.repositorio.TransaccionTarjetaRepository;
import com.banquito.cbs.txtarjetas.servicio.DiferidoService;
import com.banquito.cbs.txtarjetas.servicio.TransaccionTarjetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransaccionTarjetaServiceTest {

    @Mock
    private TransaccionTarjetaRepository repositorio;

    @Mock
    private DiferidoService diferidoService;

    @InjectMocks
    private TransaccionTarjetaService service;

    private TransaccionTarjeta transaccion;

    @BeforeEach
    void setUp() {
        transaccion = new TransaccionTarjeta();
        transaccion.setIdTarjeta(123);
        transaccion.setIdCuentaTarjeta(456);
        transaccion.setTipo("COMPRA");
        transaccion.setCodigoUnico("ABC123");
        transaccion.setFechaHora(LocalDateTime.now());
        transaccion.setMonto(new BigDecimal("100.00"));
        transaccion.setCuentaComercio("ComercioX");
        transaccion.setReferencia("Ref001");
        transaccion.setAplicaImpuesto(true);
        transaccion.setTransaccionEncriptada("EncData");
        transaccion.setCodigoTransaccionPadre(null);
    }

    @Test
    void buscarPorId_TransaccionExistente_RetornaTransaccion() {
        when(repositorio.findById("1")).thenReturn(Optional.of(transaccion));

        TransaccionTarjeta resultado = service.buscarPorId("1");

        assertNotNull(resultado);
        assertEquals("1", resultado.get_id());
    }

    @Test
    void buscarPorId_TransaccionNoExistente_LanzaExcepcion() {
        when(repositorio.findById("2")).thenReturn(Optional.empty());

        assertThrows(EntidadNoEncontradaException.class, () -> service.buscarPorId("2"));
    }

    @Test
    void listarPorIdTarjeta_DevuelveLista() {
        when(repositorio.findByIdTarjeta(123)).thenReturn(List.of(transaccion));

        List<TransaccionTarjeta> resultado = service.listarPorIdTarjeta(123);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(123, resultado.get(0).getIdTarjeta());
    }

    @Test
    void listarPorIdCuentaTarjeta_DevuelveLista() {
        when(repositorio.findByidCuentaTarjeta(456)).thenReturn(List.of(transaccion));

        List<TransaccionTarjeta> resultado = service.listarPorIdCuentaTarjeta(456);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(456, resultado.get(0).getIdCuentaTarjeta());
    }

    @Test
    void registrarTransaccion_SinDiferido_GuardaTransaccion() {
        service.registrarTransaccion(transaccion, false, 0, BigDecimal.ZERO);

        verify(repositorio, times(1)).save(transaccion);
        verify(diferidoService, never()).crearDiferido(any(), anyInt(), any());
    }

    @Test
    void registrarTransaccion_ConDiferido_CreaDiferidoYGuarda() {
        service.registrarTransaccion(transaccion, true, 12, new BigDecimal("5.00"));

        verify(diferidoService, times(1)).crearDiferido(transaccion, 12, new BigDecimal("5.00"));
        verify(repositorio, times(1)).save(transaccion);
    }
}
