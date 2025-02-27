package com.banquito.cbs.tarjetas.servicio;

import com.banquito.cbs.tarjetas.modelo.Diferido;
import com.banquito.cbs.tarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.tarjetas.repositorio.DiferidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DiferidoService {
    private final DiferidoRepository repositorio;

    public DiferidoService(DiferidoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void crearDiferido(TransaccionTarjeta transaccionTarjeta, Integer cuotas, BigDecimal interes) {
        Diferido diferido = new Diferido();

        diferido.setTransaccionTarjeta(transaccionTarjeta);
        diferido.setInteres(interes);
        diferido.setNumCuotas(cuotas);

        BigDecimal principal = transaccionTarjeta.getMonto();
        BigDecimal tasaAnual = interes;

        BigDecimal tasaAnualDecimal = tasaAnual.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
        BigDecimal tasaMensual = tasaAnualDecimal.divide(BigDecimal.valueOf(12), 6, RoundingMode.HALF_UP);
        BigDecimal factorCompuesto = BigDecimal.ONE.add(tasaMensual).pow(cuotas);
        BigDecimal valorDeuda = principal.multiply(factorCompuesto).setScale(2, RoundingMode.HALF_UP);

        diferido.setMontoTotal(valorDeuda);
        
        this.repositorio.save(diferido);
    }
}
