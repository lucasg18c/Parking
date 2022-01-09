package com.slavik.parking.repository;

import android.content.Context;

import com.slavik.parking.model.TipoVehiculo;
import com.slavik.parking.util.Constantes;
import com.slavik.parking.util.NumberFormat;

public class Repository {

    private BaseDeDatos db;
    private TipoVehiculo auto, camioneta, moto;
    private boolean iniciado = false;

    private Repository() {

    }

    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public TipoVehiculo getVehiculo(String nombreTipo) {
        if (auto.getNombre().equals(nombreTipo))
            return auto;

        else if (camioneta.getNombre().equals(nombreTipo))
            return camioneta;

        else if (moto.getNombre().equals(nombreTipo))
            return moto;

        return null;
    }

    public void updatePrecioVehiculo(String nombreTipo, String nuevoPrecio) {
        TipoVehiculo modificado = getVehiculo(nombreTipo);

        if (!NumberFormat.esDouble(nuevoPrecio)) throw new RuntimeException(
                "No se pudo actualizar el precio de " + nombreTipo + ".");

        double precio = Double.parseDouble(nuevoPrecio);

        if (modificado.getPrecioHora() == precio) return;

        modificado.setPrecioHora(precio);
        db.tipoVehiculoDAO().updateTipoVehiculo(modificado);
    }

    public void init(Context context) {
        if (iniciado) return;
        iniciado = true;

        db = BaseDeDatos.getInstance(context);
        TipoVehiculoDAO dao = db.tipoVehiculoDAO();

        if (dao.get(Constantes.NOMBRE_AUTO) == null) {

            auto = new TipoVehiculo(Constantes.NOMBRE_AUTO, 120);
            camioneta = new TipoVehiculo(Constantes.NOMBRE_CAMIONETA, 160);
            moto = new TipoVehiculo(Constantes.NOMBRE_MOTO, 80);

            dao.insertTipoVehiculo(auto);
            dao.insertTipoVehiculo(camioneta);
            dao.insertTipoVehiculo(moto);
        }
        else {
            auto = db.tipoVehiculoDAO().get(Constantes.NOMBRE_AUTO);
            camioneta = db.tipoVehiculoDAO().get(Constantes.NOMBRE_CAMIONETA);
            moto = db.tipoVehiculoDAO().get(Constantes.NOMBRE_MOTO);
        }
    }
}
