package com.slavik.parking.util;

/**
 * Clase con métodos útiles para formato de números.
 */
public class NumberFormat {

    /**
     * Le quita la parte decimal a un número.
     *
     * @param numero Número con decimal.
     * @return Texto sin parte decimal.
     */
    public static String sinDecimal(double numero) {
        return String.valueOf(numero).split("\\.")[0];
    }

    /**
     * Evalúa si un texto tiene formato de double.
     *
     * @param numero Cadena de texto.
     * @return Si tiene formato de double.
     */
    public static boolean esDouble(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }
}
