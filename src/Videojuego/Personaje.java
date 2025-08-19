package Videojuego;

public abstract class Personaje {

    public double Vida;
    public double Ataque;
    public double Defensa;
    public double Velocidad;
    public double danoIngfligido = 0;
    public String nombre;
    public boolean Nerffeado=false;
    public boolean Buffeado=false;

    public static final int VIDA_BASE_GUERRERO=100;
    public static final int VIDA_BASE_MAGO=75;
    public static final int VIDA_BASE_ARQUERO=85;
    public static final int VIDA_BASE_CABALLERO=90;
    public static final int VIDA_BASE_GUARDIAN=125;
    public static final int VIDA_BASE_VALKYRIA=80;
    public static final int VIDA_BASE_ESCUDERO=110;
    public static final int VIDA_BASE_NINJA=70;
    public static final int VIDA_BASE_SAMURAI=95;
    public static final int VIDA_BASE_PALADIN=120;

    public static final int ATAQUE_GUERRERO=25;
    public static final int ATAQUE_ARQUERO=20;
    public static final int ATAQUE_MAGO=30;
    public static final int ATAQUE_CABALLERO=24;
    public static final int ATAQUE_GUARDIAN=17;
    public static final int ATAQUE_VALKYRIA=27;
    public static final int ATAQUE_ESCUDERO=15;
    public static final int ATAQUE_NINJA=28;
    public static final int ATAQUE_SAMURAI=22;
    public static final int ATAQUE_PALADIN=29;

    public abstract void aplicarNerffeo();

    public abstract void aplicarBuffeo();


    public Personaje() {
    }


}
