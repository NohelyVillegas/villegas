package com.examen.villegas.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "caja")
public class Caja implements Serializable {

    @Id
    @Column(name = "codigo_caja", length = 20)
    private String codigoCaja;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Version
    @Column(name = "version")
    private Long version;

    public Caja() {}

    public Caja(String codigoCaja) {
        this.codigoCaja = codigoCaja;
    }

    public String getCodigoCaja() { return codigoCaja; }
    public void setCodigoCaja(String codigoCaja) { this.codigoCaja = codigoCaja; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caja caja = (Caja) o;
        return codigoCaja != null && codigoCaja.equals(caja.codigoCaja);
    }

    @Override
    public int hashCode() {
        return codigoCaja != null ? codigoCaja.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Caja{" +
                "codigoCaja='" + codigoCaja + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", version=" + version +
                '}';
    }
} 