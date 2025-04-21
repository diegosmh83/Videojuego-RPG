package Videojuego.Enemigos;

import Videojuego.Interfaces.AccionesBasicas;
import Videojuego.Jugadores.Jugador;
import Videojuego.Personaje;

public  abstract class Enemigo extends Personaje implements AccionesBasicas {

    public Jugador[] jugadorActual;


    public Enemigo() {
    }

    public void asignarJugador(Jugador[] jugador){
        this.jugadorActual=jugador;
    }

    public boolean defenderse;

    public abstract void Atacar();

    public abstract void Defender();





}
