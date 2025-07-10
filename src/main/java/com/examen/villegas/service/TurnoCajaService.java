package com.examen.villegas.service;

import com.examen.villegas.controller.dto.TurnoCajaDTO;
import com.examen.villegas.controller.dto.TransaccionTurnoDTO;
import com.examen.villegas.controller.mapper.TurnoCajaMapper;
import com.examen.villegas.controller.mapper.TransaccionTurnoMapper;
import com.examen.villegas.exception.BusinessException;
import com.examen.villegas.exception.NotFoundException;
import com.examen.villegas.model.TurnoCaja;
import com.examen.villegas.model.TransaccionTurno;
import com.examen.villegas.repository.TurnoCajaRepository;
import com.examen.villegas.repository.TransaccionTurnoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoCajaService {
    private static final Logger logger = LoggerFactory.getLogger(TurnoCajaService.class);

    private final TurnoCajaRepository turnoCajaRepository;
    private final TransaccionTurnoRepository transaccionTurnoRepository;
    private final TurnoCajaMapper turnoCajaMapper;
    private final TransaccionTurnoMapper transaccionTurnoMapper;

    public TurnoCajaService(TurnoCajaRepository turnoCajaRepository,
                            TransaccionTurnoRepository transaccionTurnoRepository,
                            TurnoCajaMapper turnoCajaMapper,
                            TransaccionTurnoMapper transaccionTurnoMapper) {
        this.turnoCajaRepository = turnoCajaRepository;
        this.transaccionTurnoRepository = transaccionTurnoRepository;
        this.turnoCajaMapper = turnoCajaMapper;
        this.transaccionTurnoMapper = transaccionTurnoMapper;
    }

    @Transactional
    public TurnoCajaDTO iniciarTurno(TurnoCajaDTO dto) {
        logger.info("Iniciando turno para caja {} y cajero {}", dto.getCodigoCaja(), dto.getCodigoCajero());
        String idTurno = dto.getId();
        if (turnoCajaRepository.existsById(idTurno)) {
            throw new BusinessException("Ya existe un turno abierto con ese ID");
        }
        dto.setEstado("ABIERTO");
        dto.setFecha(LocalDateTime.now());
        dto.setInicioTurno(LocalDateTime.now());
        TurnoCaja turno = turnoCajaMapper.toModel(dto);
        turnoCajaRepository.save(turno);
        return turnoCajaMapper.toDTO(turno);
    }

    @Transactional
    public TransaccionTurnoDTO procesarTransaccion(TransaccionTurnoDTO dto) {
        logger.info("Procesando transacción {} para turno {}", dto.getTipoTransaccion(), dto.getCodigoTurno());
        Optional<TurnoCaja> turnoOpt = turnoCajaRepository.findById(dto.getCodigoTurno());
        if (turnoOpt.isEmpty()) {
            throw new NotFoundException(dto.getCodigoTurno(), "TurnoCaja");
        }
        TurnoCaja turno = turnoOpt.get();
        if (!"ABIERTO".equals(turno.getEstado())) {
            throw new BusinessException("No se pueden procesar transacciones en un turno cerrado");
        }
        dto.setFecha(LocalDateTime.now());
        TransaccionTurno transaccion = transaccionTurnoMapper.toModel(dto);
        transaccionTurnoRepository.save(transaccion);
        return transaccionTurnoMapper.toDTO(transaccion);
    }

    @Transactional
    public TurnoCajaDTO cerrarTurno(String idTurno, List<TransaccionTurnoDTO.DenominacionDTO> denominacionesFinales) {
        logger.info("Cerrando turno {}", idTurno);
        Optional<TurnoCaja> turnoOpt = turnoCajaRepository.findById(idTurno);
        if (turnoOpt.isEmpty()) {
            throw new NotFoundException(idTurno, "TurnoCaja");
        }
        TurnoCaja turno = turnoOpt.get();
        if (!"ABIERTO".equals(turno.getEstado())) {
            throw new BusinessException("El turno ya está cerrado");
        }
        // Calcular el monto final declarado
        BigDecimal montoFinal = denominacionesFinales.stream()
                .map(d -> d.getMonto())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // Calcular el monto esperado según las transacciones
        List<TransaccionTurno> transacciones = transaccionTurnoRepository.findAll()
                .stream()
                .filter(t -> idTurno.equals(t.getCodigoTurno()))
                .toList();
        BigDecimal montoEsperado = turno.getMontoInicial();
        for (TransaccionTurno t : transacciones) {
            if ("DEPOSITO".equalsIgnoreCase(t.getTipoTransaccion())) {
                montoEsperado = montoEsperado.add(t.getMontoTotal());
            } else if ("RETIRO".equalsIgnoreCase(t.getTipoTransaccion())) {
                montoEsperado = montoEsperado.subtract(t.getMontoTotal());
            }
        }
        // Validar diferencia
        if (montoFinal.compareTo(montoEsperado) != 0) {
            logger.warn("Diferencia detectada al cerrar turno {}: declarado={}, esperado={}", idTurno, montoFinal, montoEsperado);
            throw new BusinessException("Diferencia de efectivo al cerrar turno. Declarado: " + montoFinal + ", Esperado: " + montoEsperado);
        }
        turno.setFinTurno(LocalDateTime.now());
        turno.setMontoTurno(montoFinal);
        turno.setEstado("CERRADO");
        turnoCajaRepository.save(turno);
        return turnoCajaMapper.toDTO(turno);
    }

    public TurnoCajaDTO obtenerTurnoPorId(String idTurno) {
        return turnoCajaRepository.findById(idTurno)
                .map(turnoCajaMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(idTurno, "TurnoCaja"));
    }

    public List<TurnoCajaDTO> listarTurnos() {
        return turnoCajaRepository.findAll().stream().map(turnoCajaMapper::toDTO).toList();
    }

    public List<TransaccionTurnoDTO> listarTransaccionesPorTurno(String idTurno) {
        return transaccionTurnoRepository.findAll().stream()
                .filter(t -> idTurno.equals(t.getCodigoTurno()))
                .map(transaccionTurnoMapper::toDTO)
                .toList();
    }
} 