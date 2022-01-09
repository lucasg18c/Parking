package com.slavik.parking.util;

public class NumberFormat {

    public static String sinDecimal(double numero) {
        return String.valueOf(numero).split("\\.")[0];
    }

    public static boolean esDouble(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        }
        catch (NumberFormatException ignored) {
            return false;
        }
    }
}
