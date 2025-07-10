package com.examen.villegas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "turnos_caja")
public class TurnoCaja {
    @Id
    private String id; 

    private String codigoCaja;
    private String codigoCajero;
    private String estado; 
    private LocalDateTime fecha;
    private LocalDateTime inicioTurno;
    private LocalDateTime finTurno;
    private BigDecimal montoInicial;
    private BigDecimal montoTurno;

    @Version
    private Long version;

    public TurnoCaja() {
    }

    public TurnoCaja(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoCaja() {
        return codigoCaja;
    }

    public void setCodigoCaja(String codigoCaja) {
        this.codigoCaja = codigoCaja;
    }

    public String getCodigoCajero() {
        return codigoCajero;
    }

    public void setCodigoCajero(String codigoCajero) {
        this.codigoCajero = codigoCajero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getInicioTurno() {
        return inicioTurno;
    }

    public void setInicioTurno(LocalDateTime inicioTurno) {
        this.inicioTurno = inicioTurno;
    }

    public LocalDateTime getFinTurno() {
        return finTurno;
    }

    public void setFinTurno(LocalDateTime finTurno) {
        this.finTurno = finTurno;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public BigDecimal getMontoTurno() {
        return montoTurno;
    }

    public void setMontoTurno(BigDecimal montoTurno) {
        this.montoTurno = montoTurno;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurnoCaja turnoCaja = (TurnoCaja) o;
        return Objects.equals(id, turnoCaja.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TurnoCaja{" +
                "id='" + id + '\'' +
                ", codigoCaja='" + codigoCaja + '\'' +
                ", codigoCajero='" + codigoCajero + '\'' +
                ", estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", inicioTurno=" + inicioTurno +
                ", finTurno=" + finTurno +
                ", montoInicial=" + montoInicial +
                ", montoTurno=" + montoTurno +
                ", version=" + version +
                '}';
    }
} 