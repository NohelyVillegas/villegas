package com.examen.villegas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Document(collection = "transacciones_turno")
public class TransaccionTurno {
    @Id
    private String id;
    private String codigoTurno; 
    private String codigoCaja;
    private String codigoCajero;
    private String tipoTransaccion; 
    private BigDecimal montoTotal;
    private LocalDateTime fecha;
    private List<Denominacion> denominaciones;

    @Version
    private Long version;

    public TransaccionTurno() {
    }

    public TransaccionTurno(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoTurno() {
        return codigoTurno;
    }

    public void setCodigoTurno(String codigoTurno) {
        this.codigoTurno = codigoTurno;
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

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Denominacion> getDenominaciones() {
        return denominaciones;
    }

    public void setDenominaciones(List<Denominacion> denominaciones) {
        this.denominaciones = denominaciones;
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
        TransaccionTurno that = (TransaccionTurno) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TransaccionTurno{" +
                "id='" + id + '\'' +
                ", codigoTurno='" + codigoTurno + '\'' +
                ", codigoCaja='" + codigoCaja + '\'' +
                ", codigoCajero='" + codigoCajero + '\'' +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", montoTotal=" + montoTotal +
                ", fecha=" + fecha +
                ", denominaciones=" + denominaciones +
                ", version=" + version +
                '}';
    }

    // Clase interna para Denominacion
    public static class Denominacion {
        private int billete;
        private int cantidad;
        private BigDecimal monto;

        public Denominacion() {
        }

        public Denominacion(int billete, int cantidad, BigDecimal monto) {
            this.billete = billete;
            this.cantidad = cantidad;
            this.monto = monto;
        }

        public int getBillete() {
            return billete;
        }

        public void setBillete(int billete) {
            this.billete = billete;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public BigDecimal getMonto() {
            return monto;
        }

        public void setMonto(BigDecimal monto) {
            this.monto = monto;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Denominacion that = (Denominacion) o;
            return billete == that.billete && cantidad == that.cantidad && Objects.equals(monto, that.monto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(billete, cantidad, monto);
        }

        @Override
        public String toString() {
            return "Denominacion{" +
                    "billete=" + billete +
                    ", cantidad=" + cantidad +
                    ", monto=" + monto +
                    '}';
        }
    }
} 