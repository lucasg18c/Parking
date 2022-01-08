package com.slavik.parking.util;

import com.slavik.parking.model.TipoVehiculo;

public class CalculadorCobro {

    public static double calcularCobro(TipoVehiculo tipoVehiculo, long minutosEstadia) {
        if (minutosEstadia < 66) return tipoVehiculo.getPrecioHora();
        return (0.5 * Math.floor((minutosEstadia - 36)/30d) + 1) * tipoVehiculo.getPrecioHora();
    }
}
