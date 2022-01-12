package com.slavik.parking.repository;

import android.content.Context;

import com.slavik.parking.model.TipoVehiculo;
import com.slavik.parking.util.Constantes;
import com.slavik.parking.util.NumberFormat;

/**
 * Clase que sirve como origen único de verdad para acceso y manimulación de datos.
 */
public class Repository {

    private static Repository instance;
    private BaseDeDatos db;
    private TipoVehiculo auto, camioneta, moto;
    private boolean iniciado = false;

    private Repository() {

    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    /**
     * Obtiene la instancia de un tipo de vehículo.
     *
     * @param nombreTipo Nombre del tipo de vehículo.
     * @return Instancia del tipo de vehículo.
     */
    public TipoVehiculo getVehiculo(String nombreTipo) {
        if (auto.getNombre().equals(nombreTipo))
            return auto;

        else if (camioneta.getNombre().equals(nombreTipo))
            return camioneta;

        else if (moto.getNombre().equals(nombreTipo))
            return moto;

        return null;
    }

    /**
     * Actualiza el precio de un tipo de vehículo.
     *
     * @param nombreTipo  Nombre del tipo de vehículo.
     * @param nuevoPrecio Nuevo precio.
     */
    public void updatePrecioVehiculo(String nombreTipo, String nuevoPrecio) {
        TipoVehiculo modificado = getVehiculo(nombreTipo);

        if (!NumberFormat.esDouble(nuevoPrecio)) throw new RuntimeException(
                "No se pudo actualizar el precio de " + nombreTipo + ".");

        double precio = Double.parseDouble(nuevoPrecio);

        if (modificado.getPrecioHora() == precio) return;

        modificado.setPrecioHora(precio);
        db.tipoVehiculoDAO().updateTipoVehiculo(modificado);
    }

    /**
     * Ejecutar una única vez al iniciar la ejecución de la app, previo a requerir acceder a datos.
     * Inicializa el acceso a los datos, y crea los datos por defecto requeridos para el
     * funcionamiento de la app.
     *
     * @param context Contexto.
     */
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
        } else {
            auto = db.tipoVehiculoDAO().get(Constantes.NOMBRE_AUTO);
            camioneta = db.tipoVehiculoDAO().get(Constantes.NOMBRE_CAMIONETA);
            moto = db.tipoVehiculoDAO().get(Constantes.NOMBRE_MOTO);
        }
    }
}
