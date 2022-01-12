package com.slavik.parking.ui.pages.prices;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.slavik.parking.repository.Repository;
import com.slavik.parking.util.Constantes;
import com.slavik.parking.util.NumberFormat;

public class PricesViewModel extends ViewModel {

    private boolean iniciado = false;
    private Repository repository;
    private MutableLiveData<String> precioAuto, precioCamioneta, precioMoto, error;


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

    /**
     * Ejecutar luego de instanciar.
     * Inicia el ViewModel, y mantiene sus datos si el fragment es destruido.
     */
    public void init() {

        // Control inicio previo
        if (iniciado) return;
        iniciado = true;

        // Iniciar variables y precios
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

    public void setPrecioAuto(String precioAuto) {
        this.precioAuto.postValue(precioAuto);
    }

    public void setPrecioCamioneta(String precioCamioneta) {
        this.precioCamioneta.postValue(precioCamioneta);
    }

    public void setPrecioMoto(String precioMoto) {
        this.precioMoto.postValue(precioMoto);
    }

    /**
     * Intenta actualizar los precios de cada tipo de vehículo en la base de datos.
     *
     * @throws RuntimeException Solo se admiten números enteros como precios.
     */
    public void updatePrecios() throws RuntimeException {
        try {
            repository.updatePrecioVehiculo(Constantes.NOMBRE_AUTO, precioAuto.getValue());
            repository.updatePrecioVehiculo(Constantes.NOMBRE_CAMIONETA, precioCamioneta.getValue());
            repository.updatePrecioVehiculo(Constantes.NOMBRE_MOTO, precioMoto.getValue());
        } catch (RuntimeException ex) {
            error.postValue(ex.getMessage());
        }
    }
}