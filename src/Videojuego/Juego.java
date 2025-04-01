package Videojuego;
import Videojuego.Enemigos.Bruja;
import Videojuego.Enemigos.Duende;
import Videojuego.Enemigos.Enemigo;
import Videojuego.Jugadores.Arquero;
import Videojuego.Jugadores.Guerrero;
import Videojuego.Jugadores.Jugador;
import Videojuego.Jugadores.Mago;

import java.util.*;
public class Juego {
    public static Scanner sc=new Scanner(System.in);
    
    Jugador player;
    Enemigo NPC;


    public void controldelJuego(){
        //Creamos Personajes y les asignamos su rival correspondiente
        CrearPersonaje();
        CrearEnemigo();
        player.asignarEnemigo(NPC);
        NPC.asignarJugador(player);

        //Hacemos que el juego se ejecute en bucle de turnos mientras alguno de los dos personajes tenga vida
        do{
            System.out.println(player);
            turnoJugador();
            System.out.println(NPC);
            turnoEnemigo();
            player.Estamina+=10;
        }while(player.Vida > 0 || NPC.Vida > 0 );

        //Limitamos la estamina a 100
        do{
            --player.Estamina;
        }while(player.Estamina > 100);

        //Determinamos el mensaje de fin de juego

        if(player.Vida <= 0){
            System.out.println("Has perdido...");
        }else if(NPC.Vida <= 0){
            System.out.println("¡Has ganado!");
        }

    }

    int escoger;

    public Jugador CrearPersonaje(){

        System.out.println("Bienvenido a mi Videojuego, para comenzar a jugar, escoga un Personaje:" +
                " \n 1-guerrero \n 2-arquero \n 3-mago");
        escoger=sc.nextInt();
        switch (escoger){
            case 1:
              player=new Guerrero();
                System.out.println("Has escogido el Guerrero  \n ");
                break;
            case 2:
                player=new Arquero();
                System.out.println("Has escogido el Arquero \n ");
                break;
            case 3:
                player=new Mago();
                System.out.println("Has escogido el Mago \n ");
                break;
            default:
                CrearPersonaje();
        }
        return player;
    }

    public Enemigo CrearEnemigo(){
        double num;
        num=Math.random()*2;

        if(num > 1){
            NPC=new Duende();
        }else {
            NPC = new Bruja();
        }

        return NPC;
    }

    public void turnoJugador(){
        int jugar;

        System.out.println("¿Que quieres hacer? Estamina: "+player.Estamina+" \n 1-Ataque \n 2-Defensa \n 3-Curacion (20 Estamina) \n 4-Habilidad Especial (75 Estamina)");

        jugar=sc.nextInt();

        switch (jugar){
            case 1:
                player.Atacar();
                break;
            case 2:
                player.Defender();
                break;
            case 3:
                if(player.Estamina >= 20){
                    player.Curarse();
                }else{
                    System.out.println("No tienes suficiente Estamina \n ");
                    turnoJugador();
                }
            case 4:
                if(player.Estamina >= 75){
                    player.SuperAtaque();
                }else{
                    System.out.println("No tienes suficiente Estamina \n ");
                    turnoJugador();
                }
            default:
                System.out.println("No puedes huir en este juego, elige una accion valida \n ");
                turnoJugador();
        }

    }

    public void turnoEnemigo(){
        double contarla=Math.random();

        if(contarla > 0.25){
            NPC.Atacar();
        }else{
            NPC.Defender();
        }
    }


    public static void main(String[] args) {
        Juego ejecutable = new Juego();
        ejecutable.controldelJuego();
    }

}
