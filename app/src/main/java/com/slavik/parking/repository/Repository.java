package com.slavik.parking.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.slavik.parking.model.TipoVehiculo;
import com.slavik.parking.util.Constantes;

import java.util.List;
import java.util.Objects;

public class Repository {

    private BaseDeDatos db;
    private MutableLiveData<TipoVehiculo> auto, camioneta, moto;

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
        auto = new MutableLiveData<>(db.tipoVehiculoDAO().get(Constantes.NOMBRE_AUTO));
        camioneta = new MutableLiveData<>(db.tipoVehiculoDAO().get(Constantes.NOMBRE_CAMIONETA));
        moto = new MutableLiveData<>(db.tipoVehiculoDAO().get(Constantes.NOMBRE_MOTO));
    }

    public LiveData<TipoVehiculo> getAuto() {
        return auto;
    }

    public LiveData<TipoVehiculo> getCamioneta() {
        return camioneta;
    }

    public LiveData<TipoVehiculo> getMoto() {
        return moto;
    }

    public TipoVehiculo getVehiculo(String tipoActual) {
        if (Objects.requireNonNull(auto.getValue()).getNombre().equals(tipoActual))
            return auto.getValue();
        else if (Objects.requireNonNull(camioneta.getValue()).getNombre().equals(tipoActual))
            return camioneta.getValue();
        else if (Objects.requireNonNull(moto.getValue()).getNombre().equals(tipoActual))
            return moto.getValue();

        return null;
    }
}
