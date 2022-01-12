package com.slavik.parking.ui.pages.calculator;

import android.os.Handler;

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

public class CalculatorViewModel extends ViewModel {
    private Calendar calendarIngreso, calendarSalida;
    private MutableLiveData<String> montoACobrar, tiempoEstadia, horaIngreso;
    private MutableLiveData<Integer> tipoId;
    private String tipoActual;

    private boolean iniciado = false;
    private Repository repository;

    public LiveData<String> getCobro() {
        return montoACobrar;
    }

    public LiveData<String> getTiempoEstadia() {
        return tiempoEstadia;
    }

    public LiveData<Integer> getTipoId() {
        return tipoId;
    }

    /**
     * Permite setear la hora de ingreso de un vehículo.
     *
     * @param hora   Hora de ingreso
     * @param minuto Minuto de ingreso
     */
    public void setIngreso(int hora, int minuto) {
        calendarIngreso.set(Calendar.HOUR_OF_DAY, hora);
        calendarIngreso.set(Calendar.MINUTE, minuto);
        calendarIngreso.set(Calendar.SECOND, 0);
        horaIngreso.postValue(Fechas.formatearHora(hora, minuto));

        updateResultado();
    }

    /**
     * Mantiene actualizados el tiempo y costo de estadía.
     */
    private void updateResultado() {
        calendarSalida = Calendar.getInstance();
        calendarSalida.set(Calendar.SECOND, 0);

        long estadia = Fechas.calcularMinutosDuracion(calendarIngreso, calendarSalida);

        if (estadia < 1) {
            montoACobrar.postValue("-");

            if (estadia < 0) {
                tiempoEstadia.postValue(
                        "Todavía no son las " + Fechas.formatearHora(calendarIngreso) + ".");
            } else {
                tiempoEstadia.postValue("Son las " + Fechas.formatearHora(calendarIngreso) + ".");
            }
        } else {
            montoACobrar.postValue(
                    NumberFormat.sinDecimal(
                            CalculadorCobro.calcularCobro(
                                    repository.getVehiculo(tipoActual),
                                    estadia)));

            tiempoEstadia.postValue(Fechas.descripcionDuracion(estadia));
        }
    }

    /**
     * Permite actualizar el tipo de vehículo que se va a retirar.
     *
     * @param tvid IDentificador de Tipo de Vehículo que se retira
     */
    public void setTipo(int tvid) {
        tipoActual = VehiculoIDParser.getNombreVehiculo(tvid);
        tipoId.postValue(tvid);
        updateResultado();
    }

    /**
     * Ejecutar luego de instanciarlo.
     * Inicializa variables y las mantiene si el fragment es destruido.
     */
    public void init() {
        // Control de inicio previo
        if (iniciado) {
            updateResultado();
            return;
        }
        iniciado = true;

        // Inicia variables y valores
        repository = Repository.getInstance();
        tipoActual = Constantes.NOMBRE_AUTO;
        tipoId = new MutableLiveData<>(VehiculoIDParser.getRadioButtonId(tipoActual));

        calendarIngreso = Calendar.getInstance();
        calendarIngreso.add(Calendar.HOUR_OF_DAY, -1);
        calendarIngreso.set(Calendar.MINUTE, 0);
        calendarIngreso.set(Calendar.SECOND, 0);
        calendarSalida = Calendar.getInstance();
        calendarSalida.set(Calendar.SECOND, 0);

        horaIngreso = new MutableLiveData<>(Fechas.formatearHora(calendarIngreso));
        montoACobrar = new MutableLiveData<>();
        tiempoEstadia = new MutableLiveData<>();

        // Inicia el refresco automático de la hora actual
        iniciarAutoUpdateHora();
    }

    /**
     * Mantiene actualizado el costo y la estadía en tiempo real.
     */
    private void iniciarAutoUpdateHora() {
        new Handler().postDelayed(() -> {
            updateResultado();
            iniciarAutoUpdateHora();
        }, Constantes.TIEMPO_UPDATE_COSTO);
    }

    /**
     * Getter para la hora de ingreso.
     *
     * @return Calendar con la hora de ingreso.
     */
    public Calendar getCalendarIngreso() {
        return calendarIngreso;
    }
}