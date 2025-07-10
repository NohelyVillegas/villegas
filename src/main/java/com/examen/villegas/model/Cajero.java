package com.examen.villegas.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cajero")
public class Cajero implements Serializable {

    @Id
    @Column(name = "codigo_cajero", length = 20)
    private String codigoCajero;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Version
    @Column(name = "version")
    private Long version;

    public Cajero() {}

    public Cajero(String codigoCajero) {
        this.codigoCajero = codigoCajero;
    }

    public String getCodigoCajero() { return codigoCajero; }
    public void setCodigoCajero(String codigoCajero) { this.codigoCajero = codigoCajero; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cajero cajero = (Cajero) o;
        return codigoCajero != null && codigoCajero.equals(cajero.codigoCajero);
    }

    @Override
    public int hashCode() {
        return codigoCajero != null ? codigoCajero.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Cajero{" +
                "codigoCajero='" + codigoCajero + '\'' +
                ", nombre='" + nombre + '\'' +
                ", version=" + version +
                '}';
    }
} 