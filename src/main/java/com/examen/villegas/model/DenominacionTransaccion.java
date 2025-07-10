package com.examen.villegas.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "denominacion_transaccion")
public class DenominacionTransaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_denominacion_transaccion")
    private Integer idDenominacionTransaccion;

    @Column(name = "id_transaccion", nullable = false)
    private Integer idTransaccion;

    @Column(name = "valor", nullable = false, precision = 6, scale = 2)
    private BigDecimal valor;

    @Column(name = "cantidad_billetes", nullable = false)
    private Integer cantidadBilletes;

    @Column(name = "monto", nullable = false, precision = 18, scale = 2)
    private BigDecimal monto;

    @Version
    @Column(name = "version")
    private Long version;

    // Relación hijo a padre
    // @ManyToOne
    // @JoinColumn(name = "id_transaccion", referencedColumnName = "id_transaccion", insertable = false, updatable = false)
    // private TransaccionTurno transaccionTurno;
    // @ManyToOne
    // @JoinColumn(name = "valor", referencedColumnName = "valor", insertable = false, updatable = false)
    // private Denominacion denominacion;

    public DenominacionTransaccion() {}

    public DenominacionTransaccion(Integer idDenominacionTransaccion) {
        this.idDenominacionTransaccion = idDenominacionTransaccion;
    }

    public Integer getIdDenominacionTransaccion() { return idDenominacionTransaccion; }
    public void setIdDenominacionTransaccion(Integer idDenominacionTransaccion) { this.idDenominacionTransaccion = idDenominacionTransaccion; }
    public Integer getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(Integer idTransaccion) { this.idTransaccion = idTransaccion; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public Integer getCantidadBilletes() { return cantidadBilletes; }
    public void setCantidadBilletes(Integer cantidadBilletes) { this.cantidadBilletes = cantidadBilletes; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DenominacionTransaccion that = (DenominacionTransaccion) o;
        return idDenominacionTransaccion != null && idDenominacionTransaccion.equals(that.idDenominacionTransaccion);
    }

    @Override
    public int hashCode() {
        return idDenominacionTransaccion != null ? idDenominacionTransaccion.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DenominacionTransaccion{" +
                "idDenominacionTransaccion=" + idDenominacionTransaccion +
                ", idTransaccion=" + idTransaccion +
                ", valor=" + valor +
                ", cantidadBilletes=" + cantidadBilletes +
                ", monto=" + monto +
                ", version=" + version +
                '}';
    }
} 