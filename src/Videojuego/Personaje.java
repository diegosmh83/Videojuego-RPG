package Videojuego;

public abstract class Personaje  {

    public int Vida;
    public final int VIDA_BASE_GUERRERO=100;
    public final int VIDA_BASE_MAGO=75;
    public final int VIDA_BASE_ARQUERO=80;
    public int Ataque;
    public int danoIngfligido = 0;

    public Personaje(int vida, int ataque) {
        Vida=CalcularVida();
        Ataque = CalcularAtaque();
    }

    public Personaje() {
    }

    public abstract int CalcularVida();

   public abstract int CalcularAtaque();


}
