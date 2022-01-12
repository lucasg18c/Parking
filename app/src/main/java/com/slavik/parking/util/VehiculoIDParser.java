package com.slavik.parking.util;

import android.annotation.SuppressLint;

import com.slavik.parking.R;

/**
 * Clase para hacer conversiones relacionadas a tipos de vehículo.
 */
public class VehiculoIDParser {

    /**
     * Obtiene el ID del radiobutton de un tipo de vehículo.
     *
     * @param nombreVehiculo Nombre del tipo de vehículo.
     * @return ID del radiobutton del vehículo.
     */
    public static int getRadioButtonId(String nombreVehiculo) {
        switch (nombreVehiculo) {
            case Constantes.NOMBRE_AUTO:
                return R.id.rbAuto;
            case Constantes.NOMBRE_CAMIONETA:
                return R.id.rbCamioneta;
            case Constantes.NOMBRE_MOTO:
                return R.id.rbMoto;
        }
        return -1;
    }

    /**
     * Obtiene el nombre del tipo de vehículo a partir del ID de un radiobutton.
     *
     * @param rbID ID del radiobutton.
     * @return Nombre del tipo de vehículo.
     */
    @SuppressLint("NonConstantResourceId")
    public static String getNombreVehiculo(int rbID) {
        switch (rbID) {
            case R.id.rbAuto:
                return Constantes.NOMBRE_AUTO;
            case R.id.rbCamioneta:
                return Constantes.NOMBRE_CAMIONETA;
            case R.id.rbMoto:
                return Constantes.NOMBRE_MOTO;
        }
        return "";
    }
}
