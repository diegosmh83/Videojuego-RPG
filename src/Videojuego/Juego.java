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
    
    Jugador player; //Preparamos el personaje de tipo jugador que aun no sabemos cual de los 3 posibles sera
    Enemigo NPC; //Preparamos el personaje tipo enemigo que aun no sabemos cual de los dos posibles sera sera

    public static int dificultad;

    public void controldelJuego(){

        //Creamos Personajes y les asignamos su rival correspondiente
        CrearPersonaje();
        CrearEnemigo();
        player.asignarEnemigo(NPC);
        NPC.asignarJugador(player);

        escogerDificultad();//Elegimos la dificultad del juego

        /*Hacemos que el juego se ejecute en bucle de turnos mientras ambos personajes tengan vida y
        comprobamos despues de cada turno si hemos matado al enemigo si es asi mostramos mensaje de
        fin de juego antes de terminar el bucle de turnos
         */
        while(player.Vida > 0 && NPC.Vida > 0){

            System.out.println(player);
            turnoJugador();

            if(NPC.Vida <= 0){
                System.out.println("Has matado al enemigo \n");
                System.out.println("¡Has ganado!");
                break;
            }

            System.out.println(NPC);
            turnoEnemigo();

            if(player.Vida <= 0){
                System.out.println("El enemigo te ha derrotado \n");
                System.out.println("Has perdido...");
                break;
            }

            if(dificultad==1){
                player.Estamina+=15;
            }else if(dificultad==2){
                player.Estamina+=10;
            }else{
                player.Estamina+=5;
            }


            //Limitamos siempre la estamina a 100
            while(player.Estamina > 100){
                --player.Estamina;
            }

        }

    }

    public void escogerDificultad(){

        System.out.println("Elige el nivel de " +
                "dificultad: \n 1-Facil \n 2-Normal \n 3-Dificil \n ");

        dificultad=sc.nextInt();

        switch (dificultad){
            case 1: { //Nivel Facil
                System.out.println("Has elegido el modo Facil \n ");
                NPC.Vida-=15;
                NPC.Ataque-=3;
                player.Vida+=10;
                player.Ataque +=5;
                break;
            }

            case 2:{ //Nivel Normal
                //Vida y ataques predeterminados
                System.out.println("Has elegido el modo Normal \n ");
                break;
            }

            case 3: { //Nivel Dificil
                System.out.println("Has elegido el modo Dificil");
                NPC.Vida+=10;
                NPC.Ataque+=5;
                player.Vida-=10;
                player.Ataque-=5;
                break;
            }

            default:{
                System.out.println("Escoge una dificultad valida \n ");
                escogerDificultad();
            }

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
            System.out.println("Tu rival es un Duende (Vida inicial: 150) \n");
        }else {
            NPC = new Bruja();
            System.out.println("Tu rival es una Bruja (Vida inicial: 125) \n");
        }

        return NPC;
    }

    public void turnoJugador(){
        int jugar;

        switch (dificultad){
            case 1:{
                System.out.println("¿Que quieres hacer? \n 1-Ataque \n 2-Defensa \n 3-Curacion (20 Estamina) " +
                        "\n 4-Habilidad Especial (65 Estamina)");
                break;
            }
            case 2:{
                System.out.println("¿Que quieres hacer? \n 1-Ataque \n 2-Defensa \n 3-Curacion (20 Estamina)" +
                        " \n 4-Habilidad Especial (75 Estamina)");
                break;
            }
            case 3:{
                System.out.println("¿Que quieres hacer? \n 1-Ataque \n 2-Defensa \n 3-Curacion (20 Estamina)" +
                        " \n 4-Habilidad Especial (85 Estamina)");
                break;
            }

        }



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
                    break;
                }else{
                    System.out.println("No tienes suficiente Estamina \n ");
                    turnoJugador();
                }
            case 4:{
                switch(dificultad){
                    case 1:{
                        if(player.Estamina >= 65){
                            player.SuperAtaque();
                            break;
                        }else{
                            System.out.println("No tienes suficiente Estamina \n ");
                            turnoJugador();
                        }
                        break;
                    }
                    case 2:{
                        if(player.Estamina >= 75){
                            player.SuperAtaque();
                            break;
                        }else{
                            System.out.println("No tienes suficiente Estamina \n ");
                            turnoJugador();
                        }
                        break;
                    }
                    case 3:{
                        if(player.Estamina >= 85){
                            player.SuperAtaque();
                            break;
                        }else{
                            System.out.println("No tienes suficiente Estamina \n ");
                            turnoJugador();
                        }
                        break;
                    }

                }
                break;
            }

            default:
                System.out.println("No puedes huir en este juego, elige una accion valida \n ");
                turnoJugador();
        }

    }

    public void turnoEnemigo(){
        double iadeUltimaGeneracion=Math.random();

        if(iadeUltimaGeneracion > 0.25){
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
