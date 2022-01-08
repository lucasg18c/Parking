package com.slavik.parking.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tipo_vehiculo")
public class TipoVehiculo {

    @PrimaryKey()
    private int tvid;
    private String nombre;

    @ColumnInfo(name = "precio_hora")
    private double precioHora;

    public TipoVehiculo() {
    }

    public TipoVehiculo(int tvid, String nombre, double precioHora) {
        this.tvid = tvid;
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
}
