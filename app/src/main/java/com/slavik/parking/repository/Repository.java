package com.slavik.parking.repository;

import android.content.Context;

import com.slavik.parking.model.TipoVehiculo;

import java.util.List;

public class Repository {

    private BaseDeDatos db;
    private List<TipoVehiculo> tiposVehiculo;

    private Repository() {

    }

    private static Repository instance;

    public static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository();
            instance.db = BaseDeDatos.getInstance(context);
            instance.populate();
        }
        return instance;
    }

    private void populate() {
        tiposVehiculo = db.tipoVehiculoDAO().getAll();
    }

    public TipoVehiculo getTipoVehiculo(int tvid) {
        if (tvid >= tiposVehiculo.size()) return null;

        return tiposVehiculo.get(tvid);
    }
}
