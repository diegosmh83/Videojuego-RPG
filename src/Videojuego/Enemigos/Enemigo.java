package Videojuego.Enemigos;

import Videojuego.Interfaces.AccionesBasicas;
import Videojuego.Jugadores.Jugador;
import Videojuego.Personaje;

public  abstract class Enemigo extends Personaje implements AccionesBasicas {

    public Jugador[] jugadorActual;

    public int JugadorNerffeado;

    public static final double DEFENSA_BRUJA_BASE=8;
    public static final double DEFENSA_DUENDE_BASE=5;
    public static final double ATAQUE_DUENDE=15;
    public static final double ATAQUE_BRUJA=20;

    public Enemigo() {
    }

    public void asignarJugador(Jugador[] jugador){
        this.jugadorActual=jugador;
    }

    public boolean defenderse;

    public abstract void Atacar();

    public abstract void Defender();





}
