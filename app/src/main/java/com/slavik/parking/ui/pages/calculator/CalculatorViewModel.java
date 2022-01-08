package com.slavik.parking.ui.pages.calculator;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.slavik.parking.model.TipoVehiculo;
import com.slavik.parking.repository.Repository;
import com.slavik.parking.util.CalculadorCobro;

import java.time.Duration;
import java.util.Calendar;

public class CalculatorViewModel extends ViewModel {
    private Calendar horaIngreso, horaSalida;
    private MutableLiveData<String> cobro, tiempoEstadia, ingreso, salida;
    private MutableLiveData<Boolean> salidaAhora;
    private MutableLiveData<Integer> tipoId;
    private TipoVehiculo tipoActual;

    private Repository repository;

    private boolean iniciado = false;

    public LiveData<String> getCobro() {
        return cobro;
    }

    public LiveData<String> getTiempoEstadia() {
        return tiempoEstadia;
    }

    public LiveData<String> getIngreso() {
        return ingreso;
    }

    public LiveData<String> getSalida() {
        return salida;
    }

    public LiveData<Boolean> getSalidaAhora() {
        return salidaAhora;
    }

    public MutableLiveData<Integer> getTipoId() {
        return tipoId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setIngreso(int hora, int minuto) {
        horaIngreso.set(Calendar.HOUR_OF_DAY, hora);
        horaIngreso.set(Calendar.MINUTE, minuto);

        ingreso.postValue(formatoFecha(hora, minuto));

        calcularCosto();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void calcularCosto() {
        if (salidaAhora.getValue()) {
            horaSalida = Calendar.getInstance();
            salida.postValue("Ahora");
        }
        Duration estadia = calcularTiempoEstadia(horaIngreso, horaSalida);
        cobro.postValue(
                String.valueOf(CalculadorCobro.calcularCobro(tipoActual, estadia.toMinutes()))
        );
        tiempoEstadia.postValue(String.valueOf(estadia.toHours()) + " horas y " + String.valueOf(estadia.toMinutes() / 60) + " minutos.");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setTipo(int tvid) {
        tipoActual = repository.getTipoVehiculo(tvid);
        tipoId.postValue(tvid);
        calcularCosto();
    }

    private String formatoFecha(Calendar fecha) {
        return formatoFecha(fecha.get(Calendar.HOUR_OF_DAY), fecha.get(Calendar.MINUTE));
    }

    private String formatoFecha(int hora, int minuto) {
        String horaStr, minStr;
        horaStr = String.valueOf(hora);
        minStr = String.valueOf(minuto);

        if (horaStr.length() == 1) {
            horaStr = "0" + horaStr;
        }
        if (minStr.length() == 1) {
            minStr = "0" + minStr;
        }

        return horaStr + ":" + minStr + " hs";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init(Context context) {
        if (iniciado) return;
        iniciado = true;

        repository = Repository.getInstance(context);
        tipoActual = repository.getTipoVehiculo(0);
        tipoId = new MutableLiveData<>(0);

        horaIngreso = Calendar.getInstance();
        horaIngreso.add(Calendar.HOUR_OF_DAY, -1);
        horaSalida = Calendar.getInstance();

        ingreso = new MutableLiveData<>(formatoFecha(horaIngreso));

        Duration estadia = calcularTiempoEstadia(horaIngreso, horaSalida);
        cobro = new MutableLiveData<>(
                String.valueOf(CalculadorCobro.calcularCobro(tipoActual, estadia.toMinutes()))
        );
        tiempoEstadia = new MutableLiveData<>(
                String.valueOf(estadia.toHours()) + " horas y " + String.valueOf(estadia.toMinutes() / 60) + " minutos."
        );
        salida = new MutableLiveData<>("Ahora");
        salidaAhora = new MutableLiveData<>(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Duration calcularTiempoEstadia(Calendar ingreso, Calendar salida) {
        return Duration.between(ingreso.toInstant(), salida.toInstant());
    }
}