package com.slavik.parking.ui.pages.calculator;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.slavik.parking.repository.Repository;
import com.slavik.parking.util.CalculadorCobro;
import com.slavik.parking.util.Constantes;
import com.slavik.parking.util.Fechas;
import com.slavik.parking.util.NumberFormat;
import com.slavik.parking.util.VehiculoIDParser;

import java.util.Calendar;
import java.util.Objects;

public class CalculatorViewModel extends ViewModel {
    private Calendar calendarIngreso, calendarSalida;
    private MutableLiveData<String> montoACobrar, tiempoEstadia, horaIngreso, horaSalida;
    private MutableLiveData<Boolean> salidaAhora;
    private MutableLiveData<Integer> tipoId;

    private String tipoActual;

    private Repository repository;

    private boolean iniciado = false;

    public LiveData<String> getCobro() {
        return montoACobrar;
    }

    public LiveData<String> getTiempoEstadia() {
        return tiempoEstadia;
    }

    public LiveData<String> getHoraIngreso() {
        return horaIngreso;
    }

    public LiveData<String> getHoraSalida() {
        return horaSalida;
    }

    public LiveData<Boolean> getSalidaAhora() {
        return salidaAhora;
    }

    public MutableLiveData<Integer> getTipoId() {
        return tipoId;
    }

    public void setIngreso(int hora, int minuto) {
        calendarIngreso.set(Calendar.HOUR_OF_DAY, hora);
        calendarIngreso.set(Calendar.MINUTE, minuto);

        horaIngreso.postValue(Fechas.formatearHora(hora, minuto));

        calcularCosto();
    }

    private void calcularCosto() {
        if (Objects.requireNonNull(salidaAhora.getValue())) {
            calendarSalida = Calendar.getInstance();
            horaSalida.postValue("Ahora");
        }

        long estadia = Fechas.calcularMinutosDuracion(calendarIngreso, calendarSalida);
        montoACobrar.postValue(
                NumberFormat.sinDecimal(
                        CalculadorCobro.calcularCobro(repository.getVehiculo(tipoActual), estadia))
        );

        tiempoEstadia.postValue(
                Fechas.descripcionDuracion(calendarIngreso, calendarSalida)
        );
    }

    public void setTipo(int tvid) {
        tipoActual = VehiculoIDParser.getNombreVehiculo(tvid);
        tipoId.postValue(tvid);
        calcularCosto();
    }

    public void init() {
        if (iniciado) {
            calcularCosto();
            return;
        }
        iniciado = true;

        repository = Repository.getInstance();
        tipoActual = Constantes.NOMBRE_AUTO;
        tipoId = new MutableLiveData<>(VehiculoIDParser.getRadioButtonId(tipoActual));

        calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(Calendar.HOUR_OF_DAY, 12);
        calendarIngreso.set(Calendar.MINUTE, 0);
        calendarSalida = Calendar.getInstance();

        horaIngreso = new MutableLiveData<>(Fechas.formatearHora(calendarIngreso));

        montoACobrar = new MutableLiveData<>();
        tiempoEstadia = new MutableLiveData<>();

        horaSalida = new MutableLiveData<>();
        salidaAhora = new MutableLiveData<>(true);

        calcularCosto();
    }
}