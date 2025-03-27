package Videojuego.Enemigos;

import Videojuego.Interfaces.AccionesBasicas;
import Videojuego.Jugadores.Jugador;
import Videojuego.Personaje;

public  abstract class Enemigo extends Personaje implements AccionesBasicas {

    public Jugador jugadorActual;

    public Enemigo(int vida, int ataque) {
        super(vida, ataque);
    }

    public Enemigo() {
    }

    public void asignarJugador(Jugador jugador){
        this.jugadorActual=jugador;
    }

    public abstract boolean Atacar();

    public abstract boolean Defender();





}
