package Videojuego.Jugadores;

import Videojuego.Interfaces.AccionesJugador;
import Videojuego.Enemigos.Enemigo;
import Videojuego.Juego;
import Videojuego.Personaje;

public abstract class Jugador extends Personaje implements AccionesJugador {

    public int Estamina;
    public Enemigo[] enemigoActual;

    public static int[] inventario;

    public boolean sinPoc1=false;
    public boolean sinPoc2=false;
    public boolean sinPoc3=false;

    public int EnemigoNerffeado;

    public static final int DEFENSA_MAGO_BASE=7;
    public static final int DEFENSA_GUERRERO_BASE=5;
    public static final int DEFENSA_ARQUERO_BASE=10;
    public static final int DEFENSA_CABALLERO_BASE=8;
    public static final int DEFENSA_ESCUDERO_BASE=20;
    public static final int DEFENSA_GUARDIAN_BASE=15;
    public static final int DEFENSA_NINJA_BASE=4;
    public static final int DEFENSA_PALADIN_BASE=12;
    public static final int DEFENSA_SAMURAI_BASE=12;
    public static final int DEFENSA_VALKYRIA_BASE=6;

    public static final int TAM_INVENTARIO=3;

    public boolean Invisible=false;

    public boolean cargandoAtaque=false;

    public boolean paralizado;
    public boolean desangrado;
    public boolean envenenado;

    public boolean Furia=false;



    public int turnosFurioso=0;

    public boolean tempDefensa=false;
    public boolean tempAtaque=false;
    public boolean tempVelocidad=false;

    public boolean defenderse;

    public boolean esquivando;

    public Jugador() {
    }

    public void asignarEnemigo(Enemigo[] enemigo){
        this.enemigoActual=enemigo;

    }


}
