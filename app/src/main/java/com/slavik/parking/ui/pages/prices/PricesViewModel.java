package com.slavik.parking.ui.pages.prices;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.slavik.parking.repository.Repository;
import com.slavik.parking.util.Constantes;
import com.slavik.parking.util.NumberFormat;

public class PricesViewModel extends ViewModel {

    private Repository repository;

    private MutableLiveData<String> precioAuto, precioCamioneta, precioMoto, error;

    public void init() {
        repository = Repository.getInstance();

        error = new MutableLiveData<>("");

        precioAuto = new MutableLiveData<>(
                NumberFormat.sinDecimal(
                        repository.getVehiculo(Constantes.NOMBRE_AUTO).getPrecioHora())
        );
        precioCamioneta = new MutableLiveData<>(
                NumberFormat.sinDecimal(
                        repository.getVehiculo(Constantes.NOMBRE_CAMIONETA).getPrecioHora())
        );
        precioMoto = new MutableLiveData<>(
                NumberFormat.sinDecimal(
                        repository.getVehiculo(Constantes.NOMBRE_MOTO).getPrecioHora())
        );
    }

    public LiveData<String> getAuto() {
        return precioAuto;
    }

    public LiveData<String> getCamioneta() {
        return precioCamioneta;
    }

    public LiveData<String> getMoto() {
        return precioMoto;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void setPrecioAuto(String precioAuto) {
        this.precioAuto.postValue(precioAuto);
    }

    public void setPrecioCamioneta(String precioCamioneta) {
        this.precioCamioneta.postValue(precioCamioneta);
    }

    public void setPrecioMoto(String precioMoto) {
        this.precioMoto.postValue(precioMoto);
    }

    public void updatePrecios() {
        try {
            repository.updatePrecioVehiculo(Constantes.NOMBRE_AUTO, precioAuto.getValue());
            repository.updatePrecioVehiculo(Constantes.NOMBRE_CAMIONETA, precioCamioneta.getValue());
            repository.updatePrecioVehiculo(Constantes.NOMBRE_MOTO, precioMoto.getValue());
        }
        catch (RuntimeException ex) {
            error.postValue(ex.getMessage());
        }
    }
}