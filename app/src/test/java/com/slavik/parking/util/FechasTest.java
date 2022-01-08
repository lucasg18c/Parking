package com.slavik.parking.util;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Calendar;

public class FechasTest {

    @Test
    public void minutosEntreHoras() {

        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();

        inicio.set(Calendar.HOUR_OF_DAY, 8);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);

        fin.set(Calendar.HOUR_OF_DAY, 10);
        fin.set(Calendar.MINUTE, 50);
        fin.set(Calendar.SECOND, 0);

        assertEquals(170, Fechas.calcularMinutosDuracion(inicio, fin));
    }

    @Test
    public void descripcionDuracion2horas50min() {
        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();

        inicio.set(Calendar.HOUR_OF_DAY, 8);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);

        fin.set(Calendar.HOUR_OF_DAY, 10);
        fin.set(Calendar.MINUTE, 50);
        fin.set(Calendar.SECOND, 0);

        assertEquals("2 horas y 50 minutos", Fechas.descripcionDuracion(inicio, fin));
    }

    @Test
    public void descripcionDuracion30min() {
        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();

        inicio.set(Calendar.HOUR_OF_DAY, 8);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);

        fin.set(Calendar.HOUR_OF_DAY, 8);
        fin.set(Calendar.MINUTE, 30);
        fin.set(Calendar.SECOND, 0);

        assertEquals("0 horas y 30 minutos", Fechas.descripcionDuracion(inicio, fin));
    }

}