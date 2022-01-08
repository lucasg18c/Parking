package com.slavik.parking.util;

import android.annotation.SuppressLint;

import com.slavik.parking.R;

public class VehiculoIDParser {

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
