package Videojuego.Jugadores;

import Videojuego.Interfaces.AccionesJugador;
import Videojuego.Enemigos.Enemigo;
import Videojuego.Juego;
import Videojuego.Personaje;

public abstract class Jugador extends Personaje implements AccionesJugador {

    public int Estamina;
    public Enemigo enemigoActual;


    public Jugador() {
    }

    public void asignarEnemigo(Enemigo enemigo){
        this.enemigoActual=enemigo;
    }

    public boolean defenderse;

    public abstract void Atacar();

    public abstract void Defender();

    public abstract void Curarse();

    public abstract void SuperAtaque();



}
