package com.examen.villegas.controller.mapper;

import com.examen.villegas.controller.dto.TurnoCajaDTO;
import com.examen.villegas.model.TurnoCaja;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TurnoCajaMapper {
    TurnoCajaDTO toDTO(TurnoCaja model);
    TurnoCaja toModel(TurnoCajaDTO dto);
} 