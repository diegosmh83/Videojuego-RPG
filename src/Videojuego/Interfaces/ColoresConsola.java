package Videojuego.Interfaces;

public class ColoresConsola {

    // Códigos ANSI para colores
    public static final String RESET = "\u001B[0m";  // Restablece el color
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String MORADO = "\u001B[35m";

    public static String enRojo(String texto) {
        return ROJO + texto + RESET;
    }

    public static String enVerde(String texto) {
        return VERDE + texto + RESET;
    }

    public static String enAzul(String texto) {
        return AZUL + texto + RESET;
    }

    public static String enAmarillo(String texto) {
        return AMARILLO + texto + RESET;
    }

    public static String enMorado(String texto) {
        return MORADO + texto + RESET;
    }
}
