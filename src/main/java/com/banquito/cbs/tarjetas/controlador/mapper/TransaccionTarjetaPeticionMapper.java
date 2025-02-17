package com.banquito.cbs.tarjetas.controlador.mapper;

import com.banquito.cbs.tarjetas.dto.TransaccionTarjetaDto;
import com.banquito.cbs.tarjetas.modelo.TransaccionTarjeta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TransaccionTarjetaPeticionMapper {
    TransaccionTarjetaDto toDto(TransaccionTarjeta transaccionTarjeta);
    TransaccionTarjeta toPersistence(TransaccionTarjetaDto holidayDto);
}
