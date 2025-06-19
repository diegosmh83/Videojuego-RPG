package Videojuego;

public abstract class Personaje  {

    public double Vida;
    public double Ataque;
    public double Defensa;
    public double danoIngfligido = 0;
    public String nombre;
    public boolean Nerffeado=false;
    public boolean Buffeado=false;
    public static final int VIDA_BASE_GUERRERO=100;
    public static final int VIDA_BASE_MAGO=75;
    public static final int VIDA_BASE_ARQUERO=85;
    public static final int ATAQUE_GUERRERO=25;
    public static final int ATAQUE_ARQUERO=20;
    public static final int ATAQUE_MAGO=30;

    public abstract void aplicarNerffeo();

    public abstract void aplicarBuffeo();


    public Personaje() {
    }


}
