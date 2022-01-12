package com.slavik.parking.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Clase útil para métodos relacionadas a fechas.
 */
public class Fechas {

    /**
     * Calcula la cantidad de minutos transcurridos entre dos momentos.
     *
     * @param momentoInicio Momento de inicio.
     * @param momentoFin    Momento de fin.
     * @return Cantidad de minutos transcurridos.
     */
    public static long calcularMinutosDuracion(Calendar momentoInicio, Calendar momentoFin) {
        return TimeUnit.MINUTES.convert(
                momentoFin.getTimeInMillis() - momentoInicio.getTimeInMillis(),
                TimeUnit.MILLISECONDS);
    }

    /**
     * Le da a un horario el formato "hh:mm hs".
     *
     * @param hora   Hora.
     * @param minuto Minuto.
     * @return Texto con formato.
     */
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

    /**
     * Le da a un horario el formato "hh:mm" hs.
     *
     * @param horario Horario.
     * @return Texto con formato.
     */
    public static String formatearHora(Calendar horario) {
        return formatearHora(horario.get(Calendar.HOUR_OF_DAY), horario.get(Calendar.MINUTE));
    }

    /**
     * Expresa una cantidad de tiempo en lenguaje natural.
     *
     * @param minutosDuracion Duración de un evento en minutos.
     * @return Duración en lenguaje natural.
     */
    public static String descripcionDuracion(long minutosDuracion) {
        // todo Mejorar descripción

        String textoHora, textoMinutos, res;
        int horas, minutos;

        horas = (int) Math.floor(minutosDuracion / 60d);
        minutos = (int) minutosDuracion % 60;

        if (horas == 0) textoHora = "";
        else if (horas == 1) textoHora = "1 hora";
        else textoHora = horas + " horas";

        if (minutos == 0) textoMinutos = "";
        else if (minutos == 1) textoMinutos = "1 minuto";
        else textoMinutos = minutos + " minutos";

        if (horas == 0) {
            if (minutos == 0) {
                res = "0 minutos";
            } else {
                res = textoMinutos;
            }
        } else {
            if (minutos == 0) {
                res = textoHora;
            } else {
                res = textoHora + " y " + textoMinutos;
            }
        }
        return res;
    }
}
