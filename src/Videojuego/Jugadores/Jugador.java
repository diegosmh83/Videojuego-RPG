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

    public static final int TAM_INVENTARIO=3;

    public boolean paralizado;
    public boolean desangrado;
    public boolean envenenado;

    public Jugador() {
    }

    public void asignarEnemigo(Enemigo[] enemigo){
        this.enemigoActual=enemigo;

    }

    public boolean defenderse;







}
