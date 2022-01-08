package com.slavik.parking.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.slavik.parking.model.TipoVehiculo;

import java.util.List;

@Dao
public interface TipoVehiculoDAO {

    @Query("SELECT * FROM tipo_vehiculo")
    List<TipoVehiculo> getAll();

    @Update
    void updateTipoVehiculo(TipoVehiculo tipoVehiculo);

    @Insert
    void insertTipoVehiculo(TipoVehiculo tipoVehiculo);
}
