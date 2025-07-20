package Videojuego.Jugadores;

import Videojuego.Interfaces.AccionesJugador;
import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.*;

public class Guardian extends Jugador implements AccionesJugador {

    public Guardian(){
        Vida=VIDA_BASE_GUARDIAN;
        Ataque=ATAQUE_GUARDIAN;
        Defensa=15;
        Estamina=5;
        nombre="Guardian";
        Nerffeado=false;
    }

    int apuntar;

    final double VIDA_BASE=VIDA_BASE_GUARDIAN;
    final double DEFENSA_BASE=15;
    final double ATAQUE_BASE=ATAQUE_GUARDIAN;


    @Override
    public void Atacar(){

        //puedes perder el turno de ataque si te defiendes previamente
        if(defenderse){
            double probabilidad=Math.random();

            switch (dificultad){

                case 1:{

                    if(probabilidad > 0.9){
                        System.out.println(("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n"));
                        defenderse=false;
                        return;
                    }
                    break;
                }

                case 2:{

                    if(probabilidad > 0.8){
                        System.out.println(("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n"));
                        defenderse=false;
                        return;
                    }
                    break;
                }

                case 3:{

                    if(probabilidad > 0.7){
                        System.out.println(("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n"));
                        defenderse=false;
                        return;
                    }
                    break;
                }

            }
        }

        System.out.println("Has escogido atacar \n");

        if(!enemigo0muerto && !enemigo1muerto){
            int input=0;

            do{

                System.out.println("Elige un enemigo al que atacar: "+enemigoActual[0].nombre+
                        enemigoActual[0].Vida+" || (2) "+enemigoActual[1].nombre+
                        enemigoActual[1].Vida);
                input=sc.nextInt();
                apuntar=input-1;


            }while(input!=1 && input!=2);

        }else{

            if(enemigo1muerto){
                apuntar=0;
                System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 1 "+enemigoActual[apuntar].nombre));
            }else{
                apuntar=1;
                System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 2 "+enemigoActual[apuntar].nombre));
            }

        }

        double defense=Math.random();

        if(enemigoActual[apuntar].defenderse){


            System.out.println((enemigoActual[apuntar].nombre+ "se esta defendiendo... \n "));

            if(defense < 0.2){
                System.out.println(ColoresConsola.enAmarillo("La defensa de " +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
            }else if (defense <= 0.8){
                Ataque-=Ataque*0.25;
                System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " se ha defendido \n ") );
            }else{
                Ataque=0;
                System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " ha realizado una defensa perfecta y ha bloqueado tu ataque \n "));
            }

        }

        double attack;
        attack=Math.random();

        double buffeo=Math.random();
        double nerffeo=Math.random();

        switch (dificultad){

            case 1:{

                if(attack < 0.1){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    double ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo) "));
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP+ "\n"));
                    Estamina++;
                }else if(attack <= 0.7){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque normal  "));
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque+ "\n"));
                    Estamina+=5;
                }else {

                    double ataqueC = Ataque*1.5;
                    System.out.println(ColoresConsola.enVerde("¡Has realizado un ataque critico! (Suerte)  "));
                    if(enemigoActual[apuntar].defenderse && defense > 0.8) {
                        System.out.println(ColoresConsola.enAmarillo("Pero no te ha servido para nada \n "));
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                        Estamina+=15;
                    }
                    if(nerffeo > 0.7){
                        enemigoActual[apuntar].Nerffeado=true;
                        EnemigoNerffeado=apuntar;
                    }
                    if(buffeo > 0.7){
                        Buffeado=true;
                    }

                }
                break;
            }

            case 2:{

                if(attack < 0.2){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    double ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo)  "+ColoresConsola.RESET);
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueP + ColoresConsola.RESET);
                    Estamina++;
                }else if(attack <= 0.8){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal "+ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: " +Ataque+ColoresConsola.RESET);
                    Estamina+=5;
                }else {
                    double ataqueC = Ataque*1.5;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) "+ColoresConsola.RESET);
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                        System.out.println(ColoresConsola.enAmarillo("Pero no te ha servido para nada \n "));
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                        Estamina+=15;
                    }
                    if(nerffeo > 0.7){
                        enemigoActual[apuntar].Nerffeado=true;
                        EnemigoNerffeado=apuntar;
                    }
                    if(buffeo > 0.7){
                        Buffeado=true;
                    }
                }
                break;
            }

            case 3:{

                if(attack < 0.3){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    double ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo)") );
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP));
                    Estamina++;
                }else if(attack <= 0.9){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal \n" +ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+Ataque+"\n"+ColoresConsola.RESET);
                    Estamina+=5;
                }else {
                    double ataqueC = Ataque*1.5;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) ");
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                        System.out.println(ColoresConsola.enAmarillo("Pero no te ha servido para nada " ));
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: " +ataqueC+"\n"+ColoresConsola.RESET);
                        Estamina+=15;
                    }
                    if(nerffeo > 0.7){
                        enemigoActual[apuntar].Nerffeado=true;
                        EnemigoNerffeado=apuntar;
                    }
                    if(buffeo > 0.7){
                        Buffeado=true;
                    }
                }
                break;
            }
        }


        Ataque = ATAQUE_GUARDIAN;

        defenderse=false;

    }



    @Override
    public void Defender(){

        defenderse=true;

        System.out.println("Has escogido defenderte \n ");

    }



    @Override
    public void Curarse(){

        System.out.println("Has escogido curarte \n ");

        Estamina-=20;

        double Curation=Math.random();


        int input;

        if(!jugador0muerto && !jugador1muerto){

            do{

                System.out.println("Elige a que jugador curar: (1) "+ Jugadores[0].nombre + Jugadores[0].Vida+ " || (2) "+ Jugadores[1].nombre + Jugadores[0].Vida);

                input=sc.nextInt();
                apuntar=input-1;

            }while(input!=1 && input!=2);

            if(Curation < 0.8){
                double Curacion=VIDA_BASE_GUARDIAN*0.2;
                Jugadores[apuntar].Vida+=Curacion;
                System.out.println(ColoresConsola.VERDE+"Has aumentado la vida en: "+Curacion+ "puntos \n ");
            }else{
                double Kuracion=VIDA_BASE_GUARDIAN*0.45;
                Jugadores[apuntar].Vida+=Kuracion;
                System.out.println(ColoresConsola.enVerde("Has aumentado la vida en "+Kuracion+" puntos \n "));
            }

        }else{

            if(Curation < 0.8){
                double Curacion=VIDA_BASE_GUARDIAN*0.2;
                Vida+=Curacion;
                System.out.println(ColoresConsola.VERDE+"Has aumentado la vida en: "+Curacion+ "puntos \n ");
            }else{
                double Kuracion=VIDA_BASE_GUARDIAN*0.45;
                Vida+=Kuracion;
                System.out.println(ColoresConsola.enVerde("Has aumentado la vida en "+Kuracion+" puntos \n "));
            }

        }


        defenderse=false;
    }

    @Override
    public void SuperAtaque(){

        System.out.println("¡Has usado la Justicia de Acero! \n  ");

        int input;

        double SuperA = Math.random();

        if(!enemigo0muerto && !enemigo1muerto){

            do {

                System.out.println("Elige un enemigo al que atacar: " + enemigoActual[0].nombre +
                        enemigoActual[0].Vida + " || (2) " + enemigoActual[1].nombre +
                        enemigoActual[1].Vida);
                input = sc.nextInt();
                apuntar = input - 1;

            }while(input!=1 && input!=2);


            if(SuperA > 0.85){
                double SuperC = ATAQUE_GUARDIAN*5;
                SuperC-=enemigoActual[apuntar].Defensa;
                System.out.println(ColoresConsola.VERDE+"¡¡Y ademas has realizado un ataque critico!! \n  ");
                enemigoActual[apuntar].Vida-=SuperC;
                System.out.println(ColoresConsola.enVerde("Daño infligido: "+SuperC+"\n"));
                danoIngfligido+=SuperC;
            }else {
                double Super = ATAQUE_GUARDIAN*3;
                Super-=enemigoActual[apuntar].Defensa;
                enemigoActual[apuntar].Vida-=Super;
                System.out.println(ColoresConsola.enVerde("Daño infligido: "+Super+"\n"));
                danoIngfligido+=Super;
            }

        }else{

            if(enemigo1muerto){
                apuntar=0;
                System.out.println("Vas a por el enemigo 1 "+enemigoActual[apuntar].nombre);

                if(SuperA > 0.85){
                    double SuperC = ATAQUE_GUARDIAN*5;
                    SuperC-=enemigoActual[apuntar].Defensa;
                    System.out.println(ColoresConsola.VERDE+"¡¡Y ademas has realizado un ataque critico!! \n  ");
                    enemigoActual[apuntar].Vida-=SuperC;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+SuperC+"\n"));
                    danoIngfligido+=SuperC;
                }else {
                    double Super = ATAQUE_GUARDIAN*3;
                    Super-=enemigoActual[apuntar].Defensa;
                    enemigoActual[apuntar].Vida-=Super;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Super+"\n"));
                    danoIngfligido+=Super;
                }

            }else{
                apuntar=1;

                System.out.println("Vas a por el enemigo 2 "+enemigoActual[apuntar].nombre);

                if(SuperA > 0.85){
                    double SuperC = ATAQUE_GUARDIAN*5;
                    SuperC-=enemigoActual[apuntar].Defensa;
                    System.out.println(ColoresConsola.VERDE+"¡¡Y ademas has realizado un ataque critico!! \n  ");
                    enemigoActual[apuntar].Vida-=SuperC;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+SuperC+"\n"));
                    danoIngfligido+=SuperC;
                }else {
                    double Super = ATAQUE_GUARDIAN*3;
                    Super-=enemigoActual[apuntar].Defensa;
                    enemigoActual[apuntar].Vida-=Super;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Super+"\n"));
                    danoIngfligido+=Super;
                }
            }

        }

        switch (dificultad){
            case 1:{
                Estamina-=65;
                break;
            }
            case 2:{
                Estamina-=75;
                break;
            }
            case 3:{
                Estamina-=85;
                break;
            }
        }


        defenderse=false;
    }

    @Override
    public void golpeDevastador(){

        System.out.println("Has escogido el golpe devastador \n");

        Ataque=ATAQUE_BASE*2;

        int input;

        do{

            if(enemigoActual[0].Vida > 0 && enemigoActual[1].Vida > 0){
                System.out.println("Elige un enemigo al que atacar: "+enemigoActual[0].nombre+
                        enemigoActual[0].Vida+" || (2) "+enemigoActual[1].nombre+
                        enemigoActual[1].Vida);
                input=sc.nextInt();
                apuntar=input-1;
            }else if(enemigoActual[0].Vida > 0){
                System.out.println("Elige un enemigo al que atacar: "+enemigoActual[0].nombre+
                        enemigoActual[0].Vida);
                input=sc.nextInt();
                apuntar=input-1;
            }else{
                System.out.println("Elige un enemigo al que atacar: (2) "+enemigoActual[1].nombre+
                        enemigoActual[1].Vida);
                input=sc.nextInt();
                apuntar=input-1;
            }

        }while(input!=1 && input!=2);


        double defense=Math.random();

        if(enemigoActual[apuntar].defenderse){

            System.out.println((enemigoActual[apuntar].nombre+ " se esta defendiendo... \n "));

            if(defense < 0.2){
                System.out.println(ColoresConsola.enAmarillo("La defensa de " +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
                enemigoActual[apuntar].Vida-=Ataque;
                System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque));
            }else if (defense <= 0.8){
                Ataque=Ataque*0.75;
                System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " se ha defendido del golpe devastador\n ") );
                enemigoActual[apuntar].Vida-=Ataque;
                System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque));
            }else{
                Ataque=ATAQUE_BASE;
                System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " ha realizado una defensa perfecta y ha mermado tu golpe devastador \n "));
                enemigoActual[apuntar].Vida-=Ataque;
                System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque));
            }

        }else{
            System.out.println(ColoresConsola.enAzul(enemigoActual[apuntar].nombre+ " ha recibido golpe devastador "));
            enemigoActual[apuntar].Vida-=Ataque;
            System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque));
        }

        Ataque=ATAQUE_BASE;

        defenderse=false;

    }

    @Override
    public void invocarEspiritu(){

        System.out.println(ColoresConsola.enMorado("Has invocado al Espiritu Ancestral"));

        espirituInvocado=true;

    }

    @Override
    public void atacarEspiritu(){

        int punt;
        double elegir=Math.random();

        int ataqueEspiritu=7;

        if(elegir > 0.5){
            punt=1;
            System.out.println(ColoresConsola.enMorado("El espiritu ancestral va a por el enemigo 2 "+ enemigoActual[punt].nombre + enemigoActual[punt].Vida));
        }else{
            punt=0;
            System.out.println(ColoresConsola.enMorado("El espiritu ancestral va a por el enemigo 1 "+ enemigoActual[punt].nombre + enemigoActual[punt].Vida));
        }

        enemigoActual[punt].Vida-=ataqueEspiritu;

        System.out.println(ColoresConsola.enMorado("El espiritu ha infligido "+ataqueEspiritu+ " puntos de daño"));

    }

    @Override
    public void regeneracionRapida(){

        Vida+=VIDA_BASE*0.25;
        System.out.println(ColoresConsola.enVerde("Has regenerado el 25% de tu vida"));

    }

    @Override
    public void danoColateral(){


        int danoPocion=20;
        int input;

        if(!enemigo0muerto && !enemigo1muerto){

            do {

                System.out.println("Elige un enemigo al que atacar: " + enemigoActual[0].nombre +
                        enemigoActual[0].Vida + " || (2) " + enemigoActual[1].nombre +
                        enemigoActual[1].Vida);
                input = sc.nextInt();
                apuntar = input - 1;

            }while(input!=1 && input!=2);


        }else{

            if(enemigo1muerto){
                apuntar=0;
                System.out.println("Vas a por el enemigo 1 "+enemigoActual[apuntar].nombre);


            }else{
                apuntar=1;

                System.out.println("Vas a por el enemigo 2 "+enemigoActual[apuntar].nombre);

            }

        }

        enemigoActual[apuntar].Vida-=danoPocion;
        System.out.println("Daño infligido: "+danoPocion);

    }

    @Override
    public void aplicarNerffeo(){

        Defensa=DEFENSA_BASE*0.8;
        System.out.println(ColoresConsola.enAmarillo("Se reduce temporalmente la defensa de "+nombre+" en un 20%"));

    }

    @Override
    public void aplicarBuffeo(){

        Ataque=ATAQUE_BASE*1.15;
        System.out.println("Aumenta temporalmente el ataque de " +nombre+ " en un 15%");

    }

    @Override
    public String toString() {
        return ColoresConsola.AZUL+" (( GUARDIAN: " +
                " Vida=" + Vida +
                " || Ataque=" + Ataque +
                " || Estamina=" + Estamina +
                " || Daño total: " +danoIngfligido+" )) \n"+ColoresConsola.RESET;
    }

}
