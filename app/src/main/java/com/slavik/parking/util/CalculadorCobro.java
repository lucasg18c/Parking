package com.slavik.parking.util;

import com.slavik.parking.model.TipoVehiculo;

/**
 * Clase con métodos relacionados a cobrar una estadía.
 */
public class CalculadorCobro {

    /**
     * Calcula el monto a cobrarle al dueño de un vehículo por su estadía.
     *
     * @param tipoVehiculo   Tipo de vehículo estacionado.
     * @param minutosEstadia Cantidad de minutos de estadía.
     * @return Monto en pesos a cobrar.
     */
    public static double calcularCobro(TipoVehiculo tipoVehiculo, long minutosEstadia) {
        if (minutosEstadia < 66) return tipoVehiculo.getPrecioHora();
        return (0.5 * Math.floor((minutosEstadia - 36) / 30d) + 1) * tipoVehiculo.getPrecioHora();
    }
}
