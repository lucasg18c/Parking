package com.slavik.parking.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.slavik.parking.model.TipoVehiculo;

@Database(entities = {TipoVehiculo.class}, version = 1)
public abstract class BaseDeDatos extends RoomDatabase {

    private static BaseDeDatos instance;

    public static BaseDeDatos getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, BaseDeDatos.class, "parking_db")
                    .allowMainThreadQueries()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InsertarTiposBase(instance).execute();
        }
    };

    private static class InsertarTiposBase extends AsyncTask<Void, Void, Void> {
        TipoVehiculoDAO dao;

        public InsertarTiposBase(BaseDeDatos db) {
            dao = db.tipoVehiculoDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insertTipoVehiculo(new TipoVehiculo(0, "Auto", 120));
            dao.insertTipoVehiculo(new TipoVehiculo(1, "Camioneta", 160));
            dao.insertTipoVehiculo(new TipoVehiculo(2, "Moto", 80));
            return null;
        }
    }

    public abstract TipoVehiculoDAO tipoVehiculoDAO();
}
