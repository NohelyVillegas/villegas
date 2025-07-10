package com.examen.villegas.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TurnoCajaDTO {
    @Schema(description = "ID único del turno (caja-cajero-fecha)")
    private String id;

    @NotBlank(message = "El código de caja es requerido")
    private String codigoCaja;

    @NotBlank(message = "El código de cajero es requerido")
    private String codigoCajero;

    @NotBlank(message = "El estado es requerido")
    private String estado;

    @Schema(description = "Fecha del turno")
    private LocalDateTime fecha;

    @Schema(description = "Fecha y hora de inicio del turno")
    private LocalDateTime inicioTurno;

    @Schema(description = "Fecha y hora de fin del turno")
    private LocalDateTime finTurno;

    @DecimalMin(value = "0.0", message = "El monto inicial no puede ser negativo")
    private BigDecimal montoInicial;

    @DecimalMin(value = "0.0", message = "El monto del turno no puede ser negativo")
    private BigDecimal montoTurno;

    @Schema(description = "Versión del documento")
    private Long version;
} 