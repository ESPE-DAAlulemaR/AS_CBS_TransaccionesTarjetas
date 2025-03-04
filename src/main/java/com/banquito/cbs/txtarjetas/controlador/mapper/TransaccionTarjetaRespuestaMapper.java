package com.banquito.cbs.txtarjetas.controlador.mapper;

import com.banquito.cbs.txtarjetas.dto.TransaccionTarjetaRespuestaDto;
import com.banquito.cbs.txtarjetas.modelo.TransaccionTarjeta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TransaccionTarjetaRespuestaMapper {
    TransaccionTarjetaRespuestaDto toDto(TransaccionTarjeta transaccionTarjeta);
}
