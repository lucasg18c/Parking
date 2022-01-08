package com.slavik.parking.util;

import static org.junit.Assert.*;

import com.slavik.parking.model.TipoVehiculo;

import org.junit.Test;

public class CalculadorCobroTest {

    @Test
    public void cobroUnaHoraValorMedio() {

        TipoVehiculo tipo = new TipoVehiculo("auto", 100);
        long estadia = 30;

        assertEquals(100, CalculadorCobro.calcularCobro(tipo, estadia), 0);
    }

    @Test
    public void cobroUnaHoraValorLimiteSup() {

        TipoVehiculo tipo = new TipoVehiculo("auto", 100);
        long estadia = 65;

        assertEquals(100, CalculadorCobro.calcularCobro(tipo, estadia), 0);
    }

    @Test
    public void cobroHoraYMediaValorMedio() {

        TipoVehiculo tipo = new TipoVehiculo("auto", 100);
        long estadia = 80;

        assertEquals(150, CalculadorCobro.calcularCobro(tipo, estadia), 0);
    }

    @Test
    public void cobroHoraYMediaValorLimiteInf() {

        TipoVehiculo tipo = new TipoVehiculo("auto", 100);
        long estadia = 66;

        assertEquals(150, CalculadorCobro.calcularCobro(tipo, estadia), 0);
    }

    @Test
    public void cobroHoraYMediaValorLimiteSup() {

        TipoVehiculo tipo = new TipoVehiculo("auto", 100);
        long estadia = 95;

        assertEquals(150, CalculadorCobro.calcularCobro(tipo, estadia), 0);
    }

    @Test
    public void cobroDosHorasValorLimiteInf() {

        TipoVehiculo tipo = new TipoVehiculo("auto", 100);
        long estadia = 96;

        assertEquals(200, CalculadorCobro.calcularCobro(tipo, estadia), 0);
    }
}