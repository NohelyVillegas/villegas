package com.examen.villegas.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "denominacion")
public class Denominacion implements Serializable {

    @Id
    @Column(name = "valor", precision = 6, scale = 2)
    private BigDecimal valor;

    @Column(name = "descripcion", length = 20)
    private String descripcion;

    @Version
    @Column(name = "version")
    private Long version;

    public Denominacion() {}

    public Denominacion(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Denominacion that = (Denominacion) o;
        return valor != null && valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return valor != null ? valor.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Denominacion{" +
                "valor=" + valor +
                ", descripcion='" + descripcion + '\'' +
                ", version=" + version +
                '}';
    }
} 