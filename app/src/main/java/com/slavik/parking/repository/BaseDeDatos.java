package com.slavik.parking.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.slavik.parking.model.TipoVehiculo;

/**
 * Clase que permite el acceso a la base de datos.
 */
@Database(entities = {TipoVehiculo.class}, version = 1)
public abstract class BaseDeDatos extends RoomDatabase {

    private static BaseDeDatos instance;

    public static BaseDeDatos getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, BaseDeDatos.class, "parking_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract TipoVehiculoDAO tipoVehiculoDAO();
}
