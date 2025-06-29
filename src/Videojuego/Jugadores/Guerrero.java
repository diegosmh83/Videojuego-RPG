package Videojuego.Jugadores;

import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.*;
import static Videojuego.Juego.enemigo1muerto;

public class Guerrero extends Jugador {


    public Guerrero() {
        Vida=VIDA_BASE_GUERRERO;
        Ataque=ATAQUE_GUERRERO;
        Defensa=5;
        Estamina=0;
        nombre="Guerrero";
        Nerffeado=false;
    }

    int apuntar;
    final double DEFENSA_BASE=5;
    final double ATAQUE_BASE=25;


    @Override
    public void Atacar(){

        //puedes perder el turno de ataque si te has defendido previamente
        if(defenderse){

            double probabilidad=Math.random();

            switch (dificultad){

                case 1:{
                    if(probabilidad > 0.9){
                        System.out.println("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n");
                        defenderse=false;
                        return;
                    }
                }

                case 2:{
                    if(probabilidad > 0.8){
                        System.out.println("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n");
                        defenderse=false;
                        return;
                    }
                }

                case 3:{
                    if(probabilidad > 0.7){
                        System.out.println("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n");
                        defenderse=false;
                        return;
                    }
                }

            }



        }

        System.out.println(("Has escogido Atacar \n "));

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
                System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 1 "+enemigoActual[0].nombre));
            }else{
                System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 2 "+enemigoActual[1].nombre));
            }

        }

        double defense=Math.random();

        if(enemigoActual[apuntar].defenderse){
            System.out.println(enemigoActual[apuntar].nombre+ " se esta defendiendo... \n ");


            if(defense < 0.2){
                System.out.println(ColoresConsola.enAmarillo("La defensa del" +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
            }else if (defense <= 0.8){
                Ataque-=Ataque*0.25;
                System.out.println(ColoresConsola.enAmarillo("El "+enemigoActual[apuntar].nombre+ " se ha defendido \n ") );
            }else{
                Ataque=0;
                System.out.println(ColoresConsola.enAmarillo("El "+enemigoActual[apuntar].nombre+ " ha realizado una defensa perfecta y ha bloqueado tu ataque \n "));
            }
        }

        double attack=Math.random();

        double buffeo=Math.random();
        double nerffeo=Math.random();

        switch (dificultad){

            case 1:{

                if(attack <= 0.1){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    double ataqueP = Ataque/3;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo) \n"+ColoresConsola.RESET);
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueP+ "\n"+ColoresConsola.RESET);
                }else if( attack <= 0.7){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal \n"+ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+Ataque+ "\n"+ColoresConsola.RESET);
                }else {
                   double ataqueC = Ataque*2;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) \n"+ColoresConsola.VERDE);
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                        System.out.println((ColoresConsola.enAmarillo("Pero no te ha servido de nada")));
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);

                    }
                    if(nerffeo > 0.7){
                        System.out.println(ColoresConsola.enVerde("Se reduce temporalmente la defensa de "+enemigoActual[apuntar].nombre+" en un 20%"));
                        enemigoActual[apuntar].Nerffeado=true;
                        EnemigoNerffeado=apuntar;
                    }
                    if(buffeo > 0.7){
                        System.out.println(ColoresConsola.enVerde("Aumenta el ataque de "+nombre+" en un 15%"));
                        Buffeado=true;
                    }

                }
                break;
            }

            case 2:{

                if(attack <= 0.2){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    double ataqueP = Ataque/3;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo) \n"+ColoresConsola.RESET);
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueP+ "\n"+ColoresConsola.RESET);
                }else if( attack <= 0.8){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal \n"+ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+Ataque+ "\n"+ColoresConsola.RESET);
                }else {
                    double ataqueC = Ataque*2;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) \n"+ColoresConsola.RESET);
                    if(enemigoActual[apuntar].defenderse && defense >0.8){
                        System.out.println(ColoresConsola.enAmarillo("Pero no te ha servido de nada"));
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                    }
                    if(nerffeo > 0.7){
                        System.out.println(ColoresConsola.enVerde("Se reduce temporalmente la defensa de "+enemigoActual[apuntar].nombre+" en un 20%"));
                        enemigoActual[apuntar].Nerffeado=true;
                        EnemigoNerffeado=apuntar;
                    }
                    if(buffeo > 0.7){
                        System.out.println(ColoresConsola.enVerde("Aumenta el ataque de "+nombre+" en un 15%"));
                        Buffeado=true;
                    }
                }
                break;
            }

            case 3:{

                if(attack <= 0.3){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    double ataqueP = Ataque/3;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo) " );
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP));
                }else if(attack <= 0.9){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque normal "));
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque));
                }else {
                    double ataqueC = Ataque*2;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) \n"+ColoresConsola.RESET);
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                        System.out.println(ColoresConsola.enAmarillo("Pero no te ha servido de nada"));
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                    }
                    if(nerffeo > 0.7){
                        System.out.println(ColoresConsola.enVerde("Se reduce temporalmente la defensa de "+enemigoActual[apuntar].nombre+" en un 20%"));
                        enemigoActual[apuntar].Nerffeado=true;
                        EnemigoNerffeado=apuntar;
                    }
                    if(buffeo > 0.7){
                        System.out.println(ColoresConsola.enVerde("Aumenta el ataque de "+nombre+" en un 15%"));
                        Buffeado=true;
                    }
                }
                break;
            }

        }

        Ataque = ATAQUE_GUERRERO;

        defenderse=false;

    }


    @Override
    public void Defender(){

        System.out.println("Has escogido defenderte \n ");

        defenderse=true;

    }


    @Override
    public void Curarse(){

        System.out.println("Has escogido curarte \n");

        Estamina-=20;

        double Curation=Math.random();

        int input=0;

        if(!jugador0muerto && !jugador1muerto){

            do{
                System.out.println("Elige un jugador al que curar: (1) "+Jugadores[0].nombre + Jugadores[0].Vida+ " || (2) "+Jugadores[1].nombre + Jugadores[1].Vida);

                input=sc.nextInt();
                apuntar=input-1;

            }while(input!=1 && input!=2);

            if(Curation < 0.8){
                double Curacion=VIDA_BASE_GUERRERO*0.2;
                Jugadores[apuntar].Vida+=Curacion;
                System.out.println(ColoresConsola.enVerde("Has aumentado la vida en: "+Curacion+" puntos"));
            }else {
                double Kuracion=VIDA_BASE_GUERRERO*0.45;
                Jugadores[apuntar].Vida+=Kuracion;
                System.out.println(ColoresConsola.enVerde("Has aumentado la vida en "+Kuracion+" puntos"));
            }

        }else{
            if(Curation < 0.8){
                double Curacion=VIDA_BASE_GUERRERO*0.2;
                Vida+=Curacion;
                System.out.println(ColoresConsola.enVerde("Has aumentado la vida en: "+Curacion+" puntos"));
            }else {
                double Kuracion=VIDA_BASE_GUERRERO*0.45;
                Vida+=Kuracion;
                System.out.println(ColoresConsola.enVerde("Has aumentado la vida en "+Kuracion+" puntos"));
            }
        }

        defenderse=false;
    }

    @Override
    public void SuperAtaque(){

        int input;

        do{

            System.out.println("Elige un enemigo al que atacar: \n (1) "+enemigoActual[0].nombre+
                     enemigoActual[0].Vida+" || (2) "+enemigoActual[1].nombre+
                     enemigoActual[1].Vida);
            input=sc.nextInt();
            apuntar=input-1;

        }while(input!=1 && input!=2);

        System.out.println("¡Has usado el SuperCorte! \n");

        switch (dificultad) {
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

        double SuperA = Math.random();

        if(SuperA > 0.85){
            int SuperC = ATAQUE_GUERRERO*5;
            SuperC-=enemigoActual[apuntar].Defensa;
            System.out.println(ColoresConsola.enVerde("¡¡Y ademas has realizado un ataque critico!!"));
            enemigoActual[apuntar].Vida-=SuperC;
            System.out.println(ColoresConsola.enVerde("Daño: "+SuperC+"\n"));
            danoIngfligido+=SuperC;
        }else {
            int Super = ATAQUE_GUERRERO*3;
            Super-=enemigoActual[apuntar].Defensa;
            enemigoActual[apuntar].Vida-=Super;
            System.out.println(ColoresConsola.enVerde("Daño: "+Super+"\n"));
            danoIngfligido+=Super;
        }

        defenderse=false;
    }

    @Override
    public void golpeDevastador(){

        System.out.println("Has escogido el golpe devastador \n");

        Ataque=ATAQUE_BASE*2;

        int input;

        double defense=Math.random();

        if(!enemigo0muerto && !enemigo1muerto){

            do{

                System.out.println("Elige un enemigo al que atacar: \n (1) "+enemigoActual[0].nombre+
                        " " +enemigoActual[0].Vida+" || (2) "+enemigoActual[1].nombre+
                        " " +enemigoActual[1].Vida);
                input=sc.nextInt();
                apuntar=input-1;

            }while(input!=1 && input!=2);

            if(enemigoActual[apuntar].defenderse){


                System.out.println((enemigoActual[apuntar].nombre+ "se esta defendiendo... \n "));

                if(defense < 0.2){
                    System.out.println(ColoresConsola.enAmarillo("La defensa de " +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                }else if (defense <= 0.8){
                    Ataque-=Ataque*0.25;
                    System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " se ha defendido del golpe devastador\n "));
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                }else{
                    Ataque=ATAQUE_BASE;
                    System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " ha realizado una defensa perfecta y ha mermado tu golpe devastador \n "));
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                }

            }else{
                enemigoActual[apuntar].Vida-=Ataque;
                System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
            }

        }else{

            if(enemigo1muerto){
                apuntar=0;

                System.out.println("Vas a por el enemigo 1 "+enemigoActual[apuntar].nombre);

                if(enemigoActual[apuntar].defenderse){

                    System.out.println((enemigoActual[apuntar].nombre+ "se esta defendiendo... \n "));

                    if(defense < 0.2){
                        System.out.println(ColoresConsola.enAmarillo("La defensa de " +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
                        enemigoActual[apuntar].Vida-=Ataque;
                        System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                    }else if (defense <= 0.8){
                        Ataque-=Ataque*0.25;
                        System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " se ha defendido del golpe devastador\n "));
                        enemigoActual[apuntar].Vida-=Ataque;
                        System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                    }else{
                        Ataque=ATAQUE_BASE;
                        System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " ha realizado una defensa perfecta y ha mermado tu golpe devastador \n "));
                        enemigoActual[apuntar].Vida-=Ataque;
                        System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                    }

                }else{
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                }

            }else{
                apuntar=1;

                System.out.println("Vas a por el enemigo 2 "+enemigoActual[apuntar].nombre);

                if(enemigoActual[apuntar].defenderse){

                    System.out.println((enemigoActual[apuntar].nombre+ "se esta defendiendo... \n "));

                    if(defense < 0.2){
                        System.out.println(ColoresConsola.enAmarillo("La defensa de " +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
                        enemigoActual[apuntar].Vida-=Ataque;
                        System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                    }else if (defense <= 0.8){
                        Ataque-=Ataque*0.25;
                        System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " se ha defendido del golpe devastador\n "));
                        enemigoActual[apuntar].Vida-=Ataque;
                        System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                    }else{
                        Ataque=ATAQUE_BASE;
                        System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " ha realizado una defensa perfecta y ha mermado tu golpe devastador \n "));
                        enemigoActual[apuntar].Vida-=Ataque;
                        System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                    }

                }else{
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
                }

            }

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

    public void aplicarNerffeo(){

        Defensa=DEFENSA_BASE*0.8;

    }

    public void aplicarBuffeo(){

        Ataque=ATAQUE_BASE*1.15;

    }

    @Override
    public String toString() {
        return ColoresConsola.AZUL+ " (( GUERRERO: " +
                " Vida=" + Vida +
                " || Ataque=" + Ataque +
                " || Estamina=" + Estamina +
        " || Daño total: " +danoIngfligido+" )) \n"+ColoresConsola.RESET;
    }


}
