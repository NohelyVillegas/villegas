package com.examen.villegas.controller;

import com.examen.villegas.controller.dto.TurnoCajaDTO;
import com.examen.villegas.controller.dto.TransaccionTurnoDTO;
import com.examen.villegas.exception.BusinessException;
import com.examen.villegas.exception.NotFoundException;
import com.examen.villegas.service.TurnoCajaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/turnos-caja")
@Tag(name = "Turnos de Caja", description = "API para la gestión de turnos de caja y transacciones")
public class TurnoCajaController {
    private static final Logger logger = LoggerFactory.getLogger(TurnoCajaController.class);
    private final TurnoCajaService turnoCajaService;

    public TurnoCajaController(TurnoCajaService turnoCajaService) {
        this.turnoCajaService = turnoCajaService;
    }

    @Operation(summary = "Iniciar turno de caja")
    @PostMapping("/iniciar")
    public ResponseEntity<TurnoCajaDTO> iniciarTurno(@Valid @RequestBody TurnoCajaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoCajaService.iniciarTurno(dto));
    }

    @Operation(summary = "Procesar transacción de turno")
    @PostMapping("/transacciones")
    public ResponseEntity<TransaccionTurnoDTO> procesarTransaccion(@Valid @RequestBody TransaccionTurnoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoCajaService.procesarTransaccion(dto));
    }

    @Operation(summary = "Cerrar turno de caja")
    @PatchMapping("/cerrar/{idTurno}")
    public ResponseEntity<TurnoCajaDTO> cerrarTurno(
            @PathVariable String idTurno,
            @Valid @RequestBody List<TransaccionTurnoDTO.DenominacionDTO> denominacionesFinales) {
        return ResponseEntity.ok(turnoCajaService.cerrarTurno(idTurno, denominacionesFinales));
    }

    @Operation(summary = "Obtener turno por ID")
    @GetMapping("/{idTurno}")
    public ResponseEntity<TurnoCajaDTO> obtenerTurnoPorId(@PathVariable String idTurno) {
        return ResponseEntity.ok(turnoCajaService.obtenerTurnoPorId(idTurno));
    }

    @Operation(summary = "Listar todos los turnos")
    @GetMapping
    public ResponseEntity<List<TurnoCajaDTO>> listarTurnos() {
        return ResponseEntity.ok(turnoCajaService.listarTurnos());
    }

    @Operation(summary = "Listar transacciones de un turno")
    @GetMapping("/{idTurno}/transacciones")
    public ResponseEntity<List<TransaccionTurnoDTO>> listarTransaccionesPorTurno(@PathVariable String idTurno) {
        return ResponseEntity.ok(turnoCajaService.listarTransaccionesPorTurno(idTurno));
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<String> handleBusiness(BusinessException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
} 