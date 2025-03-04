package com.banquito.cbs.txtarjetas.servicio;

import com.banquito.cbs.txtarjetas.modelo.Cuota;
import com.banquito.cbs.txtarjetas.modelo.Diferido;
import com.banquito.cbs.txtarjetas.modelo.TransaccionTarjeta;
import com.banquito.cbs.txtarjetas.repositorio.DiferidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiferidoService {
    private final DiferidoRepository repositorio;

    private static final String ESTADO_COUTA_PENDIENTE = "PEN";
    private static final String ESTADO_COUTA_PAGADA = "PAG";
    private static final String ESTADO_COUTA_VENCIDA = "VEN";
    private static final String ESTADO_COUTA_MORA = "MOR";

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
        BigDecimal valorDeuda = principal
                .multiply(factorCompuesto)
                .setScale(2, RoundingMode.HALF_UP);

        diferido.setMontoTotal(valorDeuda);
        List<Cuota> listaCuotas = new ArrayList<>();
        BigDecimal cuotaFija = valorDeuda
                .divide(BigDecimal.valueOf(cuotas), 2, RoundingMode.HALF_UP);
        BigDecimal saldoPendiente = principal;

        for (int i = 1; i <= cuotas; i++) {
            BigDecimal interesCuota = saldoPendiente
                    .multiply(tasaMensual)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal capitalCuota = cuotaFija
                    .subtract(interesCuota)
                    .setScale(2, RoundingMode.HALF_UP);

            saldoPendiente = saldoPendiente
                    .add(interesCuota)
                    .subtract(cuotaFija)
                    .setScale(2, RoundingMode.HALF_UP);

            Cuota cuota = new Cuota();
            cuota.setNumeroCuota(i);
            cuota.setCapital(capitalCuota);
            cuota.setInteres(interesCuota);
            cuota.setTotal(cuotaFija);
            cuota.setEstado(DiferidoService.ESTADO_COUTA_PENDIENTE);

            listaCuotas.add(cuota);
        }

        diferido.setCuotas(listaCuotas);
        this.repositorio.save(diferido);
    }
}
