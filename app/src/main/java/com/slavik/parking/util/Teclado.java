package com.slavik.parking.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Clase con métodos últiles sobre el teclado de software.
 */
public class Teclado {

    /**
     * Cierra el teclado.
     *
     * @param view View que está en pantalla.
     */
    public static void cerrar(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view
                .getContext()
                .getSystemService(Activity.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
