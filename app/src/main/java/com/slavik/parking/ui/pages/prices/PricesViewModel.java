package com.slavik.parking.ui.pages.prices;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.slavik.parking.model.TipoVehiculo;
import com.slavik.parking.repository.Repository;
import com.slavik.parking.repository.TipoVehiculoDAO;

public class PricesViewModel extends ViewModel {

    private Repository repository;

    private MutableLiveData<Double> auto, camioneta, moto;

    public void init(Context context) {
        repository = Repository.getInstance(context);
        auto = new MutableLiveData<>(repository.getAuto().getValue().getPrecioHora());
        camioneta = new MutableLiveData<>(repository.getCamioneta().getValue().getPrecioHora());
        moto = new MutableLiveData<>(repository.getMoto().getValue().getPrecioHora());
    }

    public LiveData<Double> getAuto() {
        return auto;
    }

    public LiveData<Double> getCamioneta() {
        return camioneta;
    }

    public LiveData<Double> getMoto() {
        return moto;
    }

    public void setAuto(CharSequence nuevoTexto) {
    }

    public void setCamioneta(CharSequence nuevoTexto) {

    }

    public void setMoto(CharSequence nuevoTexto) {

    }
}