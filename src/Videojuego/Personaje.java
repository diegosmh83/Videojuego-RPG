package Videojuego;

public abstract class Personaje  {

    public int Vida;
    public int Ataque;
    public int Defensa;
    public int danoIngfligido = 0;
    public String nombre;
    public static final int VIDA_BASE_GUERRERO=100;
    public static final int VIDA_BASE_MAGO=75;
    public static final int VIDA_BASE_ARQUERO=85;
    public static final int ATAQUE_GUERRERO=25;
    public static final int ATAQUE_ARQUERO=20;
    public static final int ATAQUE_MAGO=30;


    public Personaje() {
    }


}
