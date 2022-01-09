package com.slavik.parking.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "tipo_vehiculo")
public class TipoVehiculo {

    @PrimaryKey(autoGenerate = true)
    private int tvid;

    private String nombre;

    @ColumnInfo(name = "precio_hora")
    private double precioHora;

    public TipoVehiculo() {
    }

    public TipoVehiculo(String nombre, double precioHora) {
        this.nombre = nombre;
        this.precioHora = precioHora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(double precioHora) {
        this.precioHora = precioHora;
    }

    public int getTvid() {
        return tvid;
    }

    public void setTvid(int tvid) {
        this.tvid = tvid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoVehiculo that = (TipoVehiculo) o;
        return tvid == that.tvid && nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tvid, nombre);
    }
}
