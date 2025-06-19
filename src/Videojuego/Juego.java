package Videojuego;
import Videojuego.Enemigos.Bruja;
import Videojuego.Enemigos.Duende;
import Videojuego.Enemigos.Enemigo;
import Videojuego.Interfaces.ColoresConsola;
import Videojuego.Jugadores.Arquero;
import Videojuego.Jugadores.Guerrero;
import Videojuego.Jugadores.Jugador;
import Videojuego.Jugadores.Mago;

import java.util.*;

public class Juego {
    public static Scanner sc=new Scanner(System.in);

    //Capacidad de cada grupo que se va a enfrentar (Jugadores y Enemigos)
    public static final int EQUIPO=2;

    //Cementerio Objetos version anterior
    Jugador player;
    Enemigo NPC;

    //Equipos que se enfrentaran
    Jugador[] Jugadores;
    Enemigo[] NPCs;

    //variable que recoge el nivel de dificultad del juego
    public static int dificultad;

    //variable que indica la posicion del jugador/enemigo de cada equipo que le toque ejecutar su turno (0/1)
    public static int n=0;

    public static boolean enemigo0muerto=false;
    public static boolean enemigo1muerto=false;

    public static boolean jugador0muerto=false;
    public static boolean jugador1muerto=false;

    public boolean turno1Hecho=false;
    public boolean turno2Hecho=false;
    public boolean turnoJ1Hecho=false;
    public boolean turnoJ2Hecho=false;

    public boolean Bturno1Hecho=false;
    public boolean Bturno2Hecho=false;
    public boolean BturnoJ1Hecho=false;
    public boolean BturnoJ2Hecho=false;

    int N1=3;
    int N2=3;
    int N3=3;
    int N4=3;

    int N5=3;
    int N6=3;
    int N7=3;
    int N8=3;

    public void controldelJuego(){

        //Creamos Personajes
        CrearPersonajes();
        CrearEnemigos();

        //Les asignamos sus rivales correspondientes
        for(Jugador j: Jugadores){
            j.asignarEnemigo(NPCs);
        }

        for(Enemigo e: NPCs){
            e.asignarJugador(Jugadores);
        }

        escogerDificultad(); //Elegimos la dificultad del juego


        while ((Jugadores[0].Vida > 0 || Jugadores[1].Vida > 0) && (NPCs[0].Vida > 0 || NPCs[1].Vida > 0)) {

            //Turno del primer jugador solo se ejecuta si esta vivo y no esta paralizado
            if (Jugadores[n].Vida > 0 && !Jugadores[n].paralizado){
                System.out.println(Jugadores[n]);
                turnoJugador();
                if(NPCs[Jugadores[n].EnemigoNerffeado].Nerffeado){
                    gestionarNerffeoEnemigo();
                }
                if(Jugadores[n].Buffeado){
                    gestionarBuffeoJugador();
                }
            }else if (Jugadores[n].paralizado)
                aplicarParalizacion();
            n++;

            limitarEstamina();
            limitarVida();

            //Comprobamos si enemigos mueren
            comprobarMuertesEnemigo();

            if(NPCs[0].Vida <= 0 && NPCs[1].Vida <= 0){
                System.out.println("Has matado a todos los enemigos \n");
                System.out.println(ColoresConsola.enVerde("¡Has ganado!"));
                break;
            }

            //Turno del segundo jugador
            if (Jugadores[n].Vida > 0 && !Jugadores[n].paralizado){
                System.out.println(Jugadores[n]);
                turnoJugador();
                if(NPCs[Jugadores[n].EnemigoNerffeado].Nerffeado){
                    gestionarNerffeoEnemigo();
                }
                if(Jugadores[n].Buffeado){
                    gestionarBuffeoJugador();
                }
            }else if (Jugadores[n].paralizado)
                aplicarParalizacion();
            n--;

            limitarEstamina();
            limitarVida();

            //Coprobamos si enemigos mueren
            comprobarMuertesEnemigo();

            if(NPCs[0].Vida <= 0 && NPCs[1].Vida <= 0){
                System.out.println("Has matado a todos los enemigos \n");
                System.out.println(ColoresConsola.enVerde("¡Has ganado!"));
                break;
            }

            //Turno primer enemigo
            if(NPCs[n].Vida > 0){
                System.out.println(NPCs[n]);
                turnoEnemigo();
                if(Jugadores[NPCs[n].JugadorNerffeado].Nerffeado){
                    gestionarNerffeoJugador();
                }
                if(NPCs[n].Buffeado){
                    gestionarBuffeoEnemigo();
                }
            }else if(enemigo0muerto)
                System.out.println();
            n++;


            //Comprobamos si jugadores mueren
            comprobarMuertesJugador();

            //Turno segundo enemigo
            if(NPCs[n].Vida > 0){
                System.out.println(NPCs[n]);
                turnoEnemigo();
                if(Jugadores[NPCs[n].JugadorNerffeado].Nerffeado){
                    gestionarNerffeoJugador();
                }
                if(NPCs[n].Buffeado){
                    gestionarBuffeoEnemigo();
                }
            }else if (enemigo1muerto){
                System.out.println();
            }
            n--;

            //Comprobamos si jugadores mueren
            comprobarMuertesJugador();

            if(Jugadores[0].Vida <= 0 && Jugadores[1].Vida <= 0){
                System.out.println("Los enemigos te han derrotado \n");
                System.out.println(ColoresConsola.enRojo("Has perdido..."));
                break;
            }

            //Dependiendo del nivel de dificultad recargamos mas o menos estamina al final de cada turno
            recargarEstamina();

            aplicarSangrado();
            aplicarVeneno();

            //Limitamos siempre la estamina a 100
            limitarEstamina();

        }

    }

    public void escogerDificultad(){

        System.out.println("Elige el nivel de " +
                "dificultad: \n 1-Facil \n 2-Normal \n 3-Dificil \n ");

        dificultad=sc.nextInt();

        switch (dificultad){
            case 1: { //Nivel Facil
                System.out.println(ColoresConsola.enVerde("Has elegido el modo Facil \n "));

                //Mejoramos atributos de los jugadores y reducimos los de los enemigos
                for(int n=0; n<NPCs.length; n++){
                    NPCs[n].Vida-=15;
                    NPCs[n].Ataque-=3;
                    Jugadores[n].Vida+=10;
                    Jugadores[n].Ataque+=5;
                }
                break;
            }

            case 2:{ //Nivel Normal
                //Vida y ataque predeterminado (no cambios)
                System.out.println(ColoresConsola.enAmarillo("Has elegido el modo Normal \n "));
                break;
            }

            case 3: { //Nivel Dificil
                System.out.println(ColoresConsola.enRojo("Has elegido el modo Dificil"));

                //Mejoramos atributos de los enemigos y reducimos los de los jugadores
                for(int i=0; i<Jugadores.length; i++){
                    NPCs[i].Vida+=10;
                    NPCs[i].Ataque+=5;
                    Jugadores[i].Vida-=10;
                    Jugadores[i].Ataque-=5;
                }
                break;
            }

            default:{
                System.out.println("Escoge una dificultad valida \n ");
                escogerDificultad();
                break;
            }

        }

    }

    int escoger;

    public Jugador[] CrearPersonajes(){

        System.out.println("Bienvenido a mi Videojuego, para comenzar a jugar, elige un Personaje:" +
                " \n 1-guerrero (Vida: 100, Ataque:25, Defensa: 5) \n 2-arquero \n 3-mago");

        Jugadores=new Jugador[EQUIPO];

        escoger=sc.nextInt();

        switch (escoger){
            case 1:
                Jugadores[0]=new Guerrero();
                System.out.println("Has escogido el Guerrero  \n ");
                break;
            case 2:
                Jugadores[0]=new Arquero();
                System.out.println("Has escogido el Arquero \n ");
                break;
            case 3:
                Jugadores[0]=new Mago();
                System.out.println("Has escogido el Mago \n ");
                break;
            default:
                CrearPersonajes();
                break;
        }

        int escoger2;
        System.out.println("Ahora elige un segundo personaje:  \n 1-guerrero \n 2-arquero \n 3-mago");

        do{
            escoger2=sc.nextInt();
            switch (escoger2){
                case 1:
                    Jugadores[1]=new Guerrero();
                    System.out.println("Has escogido el Guerrero  \n ");
                    break;
                case 2:
                    Jugadores[1]=new Arquero();
                    System.out.println("Has escogido el Arquero \n ");
                    break;
                case 3:
                    Jugadores[1]=new Mago();
                    System.out.println("Has escogido el Mago \n ");
                    break;
                default:
                    System.out.println("Elige una opcion valida.");
            }
        }while(escoger2 > 3);


        return Jugadores;
    }

    public Enemigo[] CrearEnemigos(){
        NPCs=new Enemigo[EQUIPO];

        double num=Math.random()*2;

        if(num > 1){
            NPCs[0]=new Duende();
            System.out.println("Tu 1º rival es un Duende (Vida inicial: 150) \n");
        }else {
            NPCs[0]=new Bruja();
            System.out.println("Tu 1º rival es una Bruja (Vida inicial: 125) \n");
        }

        double mum=Math.random()*2;

        if(mum > 1){
            NPCs[1]=new Duende();
            System.out.println("Tu 2º rival es un Duende (Vida inicial: 150) \n");
        }else {
            NPCs[1]=new Bruja();
            System.out.println("Tu 2º rival es una Bruja (Vida inicial: 125) \n");
        }

        return NPCs;
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
                Jugadores[n].Atacar();
                break;
            case 2:
                Jugadores[n].Defender();
                break;
            case 3:
                if(Jugadores[n].Estamina >= 20){
                    Jugadores[n].Curarse();
                    break;
                }else{
                    System.out.println(ColoresConsola.enAmarillo("No tienes suficiente Estamina \n "));
                    turnoJugador();
                    break;
                }
            case 4:{
                switch(dificultad){
                    case 1:{
                        if(Jugadores[n].Estamina >= 65){
                            Jugadores[n].SuperAtaque();
                            break;
                        }else{
                            System.out.println(ColoresConsola.enAmarillo("No tienes suficiente Estamina \n "));
                            turnoJugador();
                        }
                        break;
                    }
                    case 2:{
                        if(Jugadores[n].Estamina >= 75){
                            Jugadores[n].SuperAtaque();
                            break;
                        }else{
                            System.out.println(ColoresConsola.enAmarillo("No tienes suficiente Estamina \n "));
                            turnoJugador();
                        }
                        break;
                    }
                    case 3:{
                        if(Jugadores[n].Estamina >= 85){
                            Jugadores[n].SuperAtaque();
                            break;
                        }else{
                            System.out.println(ColoresConsola.enAmarillo("No tienes suficiente Estamina \n "));
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
                break;
        }

    }


    public void turnoEnemigo(){
        double iadeUltimaGeneracion=Math.random();

        if(iadeUltimaGeneracion > 0.25){
            NPCs[n].Atacar();
        }else{
            NPCs[n].Defender();
        }
    }

    public void comprobarMuertesJugador(){

        if(Jugadores[0].Vida <= 0 && !jugador0muerto){
            System.out.println(ColoresConsola.enRojo("El "+Jugadores[0].nombre+" ha sido eliminado"));
            jugador0muerto=true;
        }
        if(Jugadores[1].Vida <= 0 && !jugador1muerto){
            System.out.println(ColoresConsola.enRojo("El "+Jugadores[1].nombre+" ha sido eliminado"));
            jugador1muerto=true;
        }

    }

    public void comprobarMuertesEnemigo(){

        if(NPCs[0].Vida <= 0 && !enemigo0muerto){
            System.out.println(ColoresConsola.enVerde("Has matado al "+NPCs[0].nombre));
            enemigo0muerto=true;
        }

        if(NPCs[1].Vida <= 0 && !enemigo1muerto){
            System.out.println(ColoresConsola.enVerde("Has matado al "+NPCs[1].nombre));
            enemigo1muerto=true;
        }

    }

    public void recargarEstamina(){
        if(dificultad==1){
            Jugadores[0].Estamina+=15;
            Jugadores[1].Estamina+=15;
        }else if(dificultad==2){
            Jugadores[0].Estamina+=10;
            Jugadores[1].Estamina+=10;
        }else{
            Jugadores[0].Estamina+=5;
            Jugadores[1].Estamina+=5;
        }
    }

    public void limitarEstamina(){

        while(Jugadores[0].Estamina > 100 ){
            --Jugadores[0].Estamina;
        }
        while (Jugadores[1].Estamina > 100){
            --Jugadores[1].Estamina;
        }

    }

    public void limitarVida(){

        while(Jugadores[0].Vida > 100){
            --Jugadores[0].Vida;
        }
        while(Jugadores[1].Vida > 100){
            --Jugadores[1].Vida;
        }

    }

    int turnoJ1des=2;
    int turnoJ2des=2;
    int sangrado=10;

    public void aplicarSangrado(){

        if(Jugadores[0].desangrado){

            if(turnoJ1des > 0){
                System.out.println(ColoresConsola.enAmarillo("El "+Jugadores[0].nombre+" esta desangrado"));
                Jugadores[0].Vida-=sangrado;
                System.out.println(ColoresConsola.enRojo("Se reduce la vida en "+sangrado+"puntos"));
                --turnoJ1des;
            }else{
                System.out.println(ColoresConsola.enVerde("El "+Jugadores[0].nombre+" se ha curado del sangrado"));
                Jugadores[0].desangrado=false;
                turnoJ1des=2;
            }

        }else{
            System.out.println();
        }

        if(Jugadores[1].desangrado){

            if(turnoJ2des > 0){
                System.out.println(ColoresConsola.enAmarillo("El "+Jugadores[1].nombre+" esta desangrado"));
                Jugadores[1].Vida-=sangrado;
                System.out.println(ColoresConsola.enRojo("Se reduce la vida en "+sangrado+" puntos"));
                --turnoJ2des;
            }else{
                System.out.println(ColoresConsola.enVerde("El "+Jugadores[1].nombre+" se ha curado del sangrado"));
                Jugadores[1].desangrado=false;
                turnoJ2des=2;
            }

        }else{
            System.out.println();
        }

    }

    int turnoJ1ven=6;
    int turnoJ2ven=6;
    int veneno=2;

    public void aplicarVeneno(){

        if(Jugadores[0].envenenado){

            if(turnoJ1ven > 0){
                System.out.println(ColoresConsola.enAmarillo("El "+Jugadores[0].nombre+" esta envenenado"));
                Jugadores[0].Vida-=veneno;
                System.out.println(ColoresConsola.enRojo("Se reduce la vida en "+veneno+" puntos"));
                --turnoJ1ven;
            }else{
                System.out.println(ColoresConsola.enVerde("El "+Jugadores[0].nombre+ "se ha des-envenenado"));
                Jugadores[0].envenenado=false;
                turnoJ1ven=6;
            }

        }else{
            System.out.println();
        }

        if(Jugadores[1].envenenado){
            if(turnoJ2ven > 0){
                System.out.println(ColoresConsola.enAmarillo("El "+Jugadores[1].nombre+ "esta envenenado"));
                Jugadores[1].Vida-=veneno;
                System.out.println(ColoresConsola.enRojo("Se reduce la vida en "+veneno+" puntos"));
                --turnoJ2ven;
            }else{
                System.out.println(ColoresConsola.enVerde("El "+Jugadores[1].nombre+ "se ha des-envenenado"));
                Jugadores[1].envenenado=false;
                turnoJ2ven=6;
            }

        }else{
            System.out.println();
        }

    }

    int turnosJ1par=2;
    int turnosJ2par=2;

    public void aplicarParalizacion(){

        if(Jugadores[0].paralizado){

            if(turnosJ1par > 0){
                System.out.println(ColoresConsola.AMARILLO+"El "+Jugadores[0].nombre+ "esta paralizado "+ColoresConsola.ROJO+" (pierde el turno)"+ColoresConsola.RESET);
                turnosJ1par-=1;
            }else{
                System.out.println(ColoresConsola.enVerde("El "+Jugadores[0].nombre+ "se ha descongelado"));
                Jugadores[0].paralizado=false;
                turnosJ1par=2;
            }

        }

        if(Jugadores[1].paralizado){

            if(turnosJ2par > 0){
                System.out.println(ColoresConsola.AMARILLO+"EL "+Jugadores[1].nombre+ "esta paralizado "+ColoresConsola.ROJO+"(pierde el turno)"+ColoresConsola.RESET);
                turnosJ2par--;
            }else{
                System.out.println(ColoresConsola.enVerde("El "+Jugadores[1].nombre+ "se ha descongelado"));
                Jugadores[1].paralizado=false;
                turnosJ2par=2;
            }

        }

    }

    public void gestionarNerffeoEnemigo(){

        if(Jugadores[n].EnemigoNerffeado==0){
            NPCs[Jugadores[n].EnemigoNerffeado].aplicarNerffeo();
            if(turno1Hecho){
                turno1Hecho=false;
            }else{
                --N1;
                turno1Hecho=true;
            }
            if(N1==0){
                NPCs[Jugadores[n].EnemigoNerffeado].Nerffeado=false;
                if(NPCs[Jugadores[n].EnemigoNerffeado].nombre.equals("Duende")){
                    NPCs[Jugadores[n].EnemigoNerffeado].Defensa=Enemigo.DEFENSA_DUENDE_BASE;
                }else{
                    NPCs[Jugadores[n].EnemigoNerffeado].Defensa=Enemigo.DEFENSA_BRUJA_BASE;
                }
                N1=3;
            }
        }else if(Jugadores[n].EnemigoNerffeado==1){
            NPCs[Jugadores[n].EnemigoNerffeado].aplicarNerffeo();
            if(turno2Hecho){
                turno2Hecho=false;
            }else{
                --N2;
                turno2Hecho=true;
            }
            if(N2==0){
                NPCs[Jugadores[n].EnemigoNerffeado].Nerffeado=false;
                if(NPCs[Jugadores[n].EnemigoNerffeado].nombre.equals("Duende")){
                    NPCs[Jugadores[n].EnemigoNerffeado].Defensa=Enemigo.DEFENSA_DUENDE_BASE;
                }else{
                    NPCs[Jugadores[n].EnemigoNerffeado].Defensa=Enemigo.DEFENSA_BRUJA_BASE;
                }
                N2=3;
            }

        }

    }

    public void gestionarNerffeoJugador(){

        if(NPCs[n].JugadorNerffeado==0){
            Jugadores[NPCs[n].JugadorNerffeado].aplicarNerffeo();
            if(turnoJ1Hecho){
                turnoJ1Hecho=false;
            }else{
                --N3;
                turnoJ1Hecho=true;
            }
            if(N3==0){
                Jugadores[NPCs[n].JugadorNerffeado].Nerffeado=false;
                if(Jugadores[NPCs[n].JugadorNerffeado].nombre.equals("Mago")){
                    Jugadores[NPCs[n].JugadorNerffeado].Defensa=Jugador.DEFENSA_MAGO_BASE;
                }else if(Jugadores[NPCs[n].JugadorNerffeado].nombre.equals("Arquero")){
                    Jugadores[NPCs[n].JugadorNerffeado].Defensa=Jugador.DEFENSA_ARQUERO_BASE;
                }else{
                    Jugadores[NPCs[n].JugadorNerffeado].Defensa=Jugador.DEFENSA_GUERRERO_BASE;
                }
                N3=3;
            }
        }else if(NPCs[n].JugadorNerffeado==1){
            Jugadores[NPCs[n].JugadorNerffeado].aplicarNerffeo();
            if(turnoJ2Hecho){
                turnoJ2Hecho=false;
            }else{
                --N4;
                turnoJ2Hecho=true;
            }
            if(N4==0){
                Jugadores[NPCs[n].JugadorNerffeado].Nerffeado=false;
                if(Jugadores[NPCs[n].JugadorNerffeado].nombre.equals("Mago")){
                    Jugadores[NPCs[n].JugadorNerffeado].Defensa=Jugador.DEFENSA_MAGO_BASE;
                }else if(Jugadores[NPCs[n].JugadorNerffeado].nombre.equals("Arquero")){
                    Jugadores[NPCs[n].JugadorNerffeado].Defensa=Jugador.DEFENSA_ARQUERO_BASE;
                }else{
                    Jugadores[NPCs[n].JugadorNerffeado].Defensa=Jugador.DEFENSA_GUERRERO_BASE;
                }
                N4=3;
            }
        }

    }

    public void gestionarBuffeoJugador(){

        if(Jugadores[0].Buffeado){
            Jugadores[0].aplicarBuffeo();
            if(BturnoJ1Hecho){
                BturnoJ1Hecho=false;
            }else{
                --N5;
                BturnoJ1Hecho=true;
            }
            if(N5==0){
                Jugadores[0].Buffeado=false;
                if(Jugadores[0].nombre.equals("Mago")){
                    Jugadores[0].Ataque=Jugador.ATAQUE_MAGO;
                }else if(Jugadores[0].nombre.equals("Guerrero")){
                    Jugadores[0].Ataque=Jugador.ATAQUE_GUERRERO;
                }else{
                    Jugadores[0].Ataque=Jugador.ATAQUE_ARQUERO;
                }
            }
        }else if(Jugadores[1].Buffeado){
            Jugadores[1].aplicarBuffeo();
            if(BturnoJ2Hecho){
                BturnoJ2Hecho=false;
            }else{
                --N6;
                BturnoJ2Hecho=true;
            }
            if(N6==0){
                Jugadores[1].Buffeado=false;
                if(Jugadores[1].nombre.equals("Mago")){
                    Jugadores[1].Ataque=Jugador.ATAQUE_MAGO;
                }else if(Jugadores[1].nombre.equals("Guerrero")){
                    Jugadores[1].Ataque=Jugador.ATAQUE_GUERRERO;
                }else{
                    Jugadores[1].Ataque=Jugador.ATAQUE_ARQUERO;
                }
            }
        }

    }

    public void gestionarBuffeoEnemigo(){

        if(NPCs[0].Buffeado){
            NPCs[0].aplicarBuffeo();
            if(Bturno1Hecho){
                Bturno1Hecho=false;
            }else{
                --N7;
                Bturno1Hecho=true;
            }
            if(N7==0){
                if(NPCs[0].nombre.equals("Duende")){
                    NPCs[0].Ataque=Enemigo.ATAQUE_DUENDE;
                }else{
                    NPCs[0].Ataque=Enemigo.ATAQUE_BRUJA;
                }
            }
        }else if(NPCs[1].Buffeado){
            NPCs[1].aplicarBuffeo();
            if(Bturno2Hecho){
                Bturno2Hecho=false;
            }else{
                --N8;
                Bturno2Hecho=true;
            }
            if(N8==0){
                if(NPCs[1].nombre.equals("Duende")){
                    NPCs[1].Ataque=Enemigo.ATAQUE_DUENDE;
                }else{
                    NPCs[1].Ataque=Enemigo.ATAQUE_BRUJA;
                }
            }
        }

    }


    public static void main(String[] args) {
        Juego ejecutable = new Juego();
        ejecutable.controldelJuego();
    }

}
