package com.slavik.parking.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Fechas {
    public static long calcularMinutosDuracion(Calendar momentoInicio, Calendar momentoFin) {

        return TimeUnit.MINUTES.convert(momentoFin.getTimeInMillis() - momentoInicio.getTimeInMillis(), TimeUnit.MILLISECONDS);
    }

    public static String formatearHora(int hora, int minuto) {
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

    public static String formatearHora(Calendar fecha) {
        return formatearHora(fecha.get(Calendar.HOUR_OF_DAY), fecha.get(Calendar.MINUTE));
    }

    public static String descripcionDuracion(Calendar momentoInicio, Calendar momentoFin) {
        long duracion = calcularMinutosDuracion(momentoInicio, momentoFin);

        return String.valueOf(Math.floor(duracion / 60d)).split("\\.")[0] + " horas y " + (duracion % 60) + " minutos";
    }
}
