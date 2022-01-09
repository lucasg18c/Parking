package com.slavik.parking.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.slavik.parking.model.TipoVehiculo;

@Dao
public interface TipoVehiculoDAO {

    @Query("SELECT * FROM tipo_vehiculo WHERE nombre = :nombreTipo")
    TipoVehiculo get(String nombreTipo);

    @Update
    void updateTipoVehiculo(TipoVehiculo tipoVehiculo);

    @Insert
    void insertTipoVehiculo(TipoVehiculo tipoVehiculo);
}
