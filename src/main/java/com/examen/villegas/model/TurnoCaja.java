package com.examen.villegas.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "turno_caja")
public class TurnoCaja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Integer idTurno;

    @Column(name = "codigo_caja", nullable = false, length = 20)
    private String codigoCaja;

    @Column(name = "codigo_cajero", nullable = false, length = 20)
    private String codigoCajero;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "codigo_turno", nullable = false, length = 50, unique = true)
    private String codigoTurno;

    @Column(name = "inicio_turno", nullable = false)
    private LocalDateTime inicioTurno;

    @Column(name = "monto_inicial", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoInicial;

    @Column(name = "fin_turno")
    private LocalDateTime finTurno;

    @Column(name = "monto_turno", precision = 18, scale = 2)
    private BigDecimal montoTurno;

    @Column(name = "estado", nullable = false, length = 10)
    private String estado;

    @Version
    @Column(name = "version")
    private Long version;

    // Relaciones de hijo a padre (solo referencia, no navegación)
    // Si se agregan entidades Caja y Cajero, se pueden mapear así:
    // @ManyToOne
    // @JoinColumn(name = "codigo_caja", referencedColumnName = "codigo_caja", insertable = false, updatable = false)
    // private Caja caja;
    //
    // @ManyToOne
    // @JoinColumn(name = "codigo_cajero", referencedColumnName = "codigo_cajero", insertable = false, updatable = false)
    // private Cajero cajero;

    public TurnoCaja() {
    }

    public TurnoCaja(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCodigoTurno() {
        return codigoTurno;
    }

    public void setCodigoTurno(String codigoTurno) {
        this.codigoTurno = codigoTurno;
    }

    public LocalDateTime getInicioTurno() {
        return inicioTurno;
    }

    public void setInicioTurno(LocalDateTime inicioTurno) {
        this.inicioTurno = inicioTurno;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public LocalDateTime getFinTurno() {
        return finTurno;
    }

    public void setFinTurno(LocalDateTime finTurno) {
        this.finTurno = finTurno;
    }

    public BigDecimal getMontoTurno() {
        return montoTurno;
    }

    public void setMontoTurno(BigDecimal montoTurno) {
        this.montoTurno = montoTurno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        TurnoCaja that = (TurnoCaja) o;
        return idTurno != null && idTurno.equals(that.idTurno);
    }

    @Override
    public int hashCode() {
        return idTurno != null ? idTurno.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TurnoCaja{" +
                "idTurno=" + idTurno +
                ", codigoCaja='" + codigoCaja + '\'' +
                ", codigoCajero='" + codigoCajero + '\'' +
                ", fecha=" + fecha +
                ", codigoTurno='" + codigoTurno + '\'' +
                ", inicioTurno=" + inicioTurno +
                ", montoInicial=" + montoInicial +
                ", finTurno=" + finTurno +
                ", montoTurno=" + montoTurno +
                ", estado='" + estado + '\'' +
                ", version=" + version +
                '}';
    }
} 