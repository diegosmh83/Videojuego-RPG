package Videojuego.Enemigos;

import Videojuego.Interfaces.AccionesBasicas;
import Videojuego.Jugadores.Jugador;
import Videojuego.Personaje;

public  abstract class Enemigo extends Personaje implements AccionesBasicas {

    public Jugador[] jugadorActual;

    public int JugadorNerffeado;

    public double contadorMuro=0;

    public static final double DEFENSA_BRUJA_BASE=8;
    public static final double DEFENSA_DUENDE_BASE=5;
    public static final double DEFENSA_ZOMBI_BASE=7;
    public static final double DEFENSA_ARAÑA_BASE=6;
    public static final double DEFENSA_GIGANTE_BASE=4;
    public static final double DEFENSA_FANTASMA_BASE=13;
    public static final double DEFENSA_ESPECTRO_BASE=9;
    public static final double DEFENSA_SOMBRA_BASE=10;
    public static final double DEFENSA_DRAGON_BASE=11;

    public static final double ATAQUE_DUENDE=15;
    public static final double ATAQUE_BRUJA=20;
    public static final double ATAQUE_ZOMBI=17;
    public static final double ATAQUE_ARAÑA=20;
    public static final double ATAQUE_GIGANTE=15;
    public static final double ATAQUE_FANTASMA=25;
    public static final double ATAQUE_ESPECTRO=22;
    public static final double ATAQUE_SOMBRA=21;
    public static final double ATAQUE_DRAGON=18;

    public Enemigo() {
    }

    public void asignarJugador(Jugador[] jugador){
        this.jugadorActual=jugador;
    }

    public boolean defenderse;

    public abstract void Atacar();

    public abstract void Defender();





}
