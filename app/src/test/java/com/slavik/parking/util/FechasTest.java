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

        assertEquals(170, Fechas.calcularDuracion(inicio, fin).toMinutes());
    }

}