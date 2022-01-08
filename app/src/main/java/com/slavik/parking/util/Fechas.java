package com.slavik.parking.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.util.Calendar;

public class Fechas {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Duration calcularDuracion(Calendar momentoInicio, Calendar momentoFin) {
        return Duration.between(momentoInicio.toInstant(), momentoFin.toInstant());
    }
}
