package com.examen.villegas.controller.mapper;

import com.examen.villegas.controller.dto.TransaccionTurnoDTO;
import com.examen.villegas.model.TransaccionTurno;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TransaccionTurnoMapper {
    TransaccionTurnoDTO toDTO(TransaccionTurno model);
    TransaccionTurno toModel(TransaccionTurnoDTO dto);
} 