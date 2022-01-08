package com.slavik.parking.util;

import static org.junit.Assert.assertEquals;

import com.slavik.parking.model.TipoVehiculo;

import org.junit.Test;

import java.util.Calendar;

public class CobroFechasIntegrationTest {

    @Test
    public void cobro65Minutos50Segundos() {

        TipoVehiculo tipo = new TipoVehiculo("Auto", 100);

        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();

        inicio.set(Calendar.HOUR_OF_DAY, 8);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);

        fin.set(Calendar.HOUR_OF_DAY, 8);
        fin.set(Calendar.MINUTE, 5);
        fin.set(Calendar.SECOND, 50);

        long duracion = Fechas.calcularMinutosDuracion(inicio, fin);

        assertEquals(100, CalculadorCobro.calcularCobro(tipo, duracion), 0);
    }

    @Test
    public void cobro66Minutos() {

        TipoVehiculo tipo = new TipoVehiculo("Auto", 100);

        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();

        inicio.set(Calendar.HOUR_OF_DAY, 8);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);

        fin.set(Calendar.HOUR_OF_DAY, 9);
        fin.set(Calendar.MINUTE, 6);
        fin.set(Calendar.SECOND, 0);

        long duracion = Fechas.calcularMinutosDuracion(inicio, fin);

        assertEquals(150, CalculadorCobro.calcularCobro(tipo, duracion), 0);
    }
}
