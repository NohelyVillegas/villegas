package com.examen.villegas.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccion_turno")
public class TransaccionTurno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;

    @Column(name = "id_turno", nullable = false)
    private Integer idTurno;

    @Column(name = "tipo_transaccion", nullable = false, length = 20)
    private String tipoTransaccion;

    @Column(name = "monto_total", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoTotal;

    @Column(name = "fecha_transaccion", nullable = false)
    private LocalDateTime fechaTransaccion;

    @Version
    @Column(name = "version")
    private Long version;

    // Relación hijo a padre
    // @ManyToOne
    // @JoinColumn(name = "id_turno", referencedColumnName = "id_turno", insertable = false, updatable = false)
    // private TurnoCaja turnoCaja;

    public TransaccionTurno() {}

    public TransaccionTurno(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Integer getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(Integer idTransaccion) { this.idTransaccion = idTransaccion; }
    public Integer getIdTurno() { return idTurno; }
    public void setIdTurno(Integer idTurno) { this.idTurno = idTurno; }
    public String getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
    public BigDecimal getMontoTotal() { return montoTotal; }
    public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal = montoTotal; }
    public LocalDateTime getFechaTransaccion() { return fechaTransaccion; }
    public void setFechaTransaccion(LocalDateTime fechaTransaccion) { this.fechaTransaccion = fechaTransaccion; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaccionTurno that = (TransaccionTurno) o;
        return idTransaccion != null && idTransaccion.equals(that.idTransaccion);
    }

    @Override
    public int hashCode() {
        return idTransaccion != null ? idTransaccion.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TransaccionTurno{" +
                "idTransaccion=" + idTransaccion +
                ", idTurno=" + idTurno +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", montoTotal=" + montoTotal +
                ", fechaTransaccion=" + fechaTransaccion +
                ", version=" + version +
                '}';
    }
} 