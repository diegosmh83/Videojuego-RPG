package Videojuego.Jugadores;

import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.dificultad;
import static Videojuego.Juego.sc;

public class Guerrero extends Jugador {


    public Guerrero() {
        Vida=VIDA_BASE_GUERRERO;
        Ataque=ATAQUE_GUERRERO;
        Estamina=0;
        nombre="Guerrero";
    }

    int apuntar;


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

        int input;

        do{

            System.out.println("Elige un enemigo al que atacar: \n (1)  "+enemigoActual[0]+" | (2)  "+enemigoActual[1]);
            input=sc.nextInt();
            apuntar=input-1;

        }while(input!=1 && input!=2);

        double defense=Math.random();

        if(enemigoActual[apuntar].defenderse){
            System.out.println("El "+enemigoActual[apuntar].nombre+ "se esta defendiendo... \n ");


            if(defense < 0.2){
                System.out.println(ColoresConsola.enAmarillo("La defensa del" +enemigoActual[apuntar].nombre+ "ha resultado fallida \n "));
            }else if (defense <= 0.8){
                Ataque-=Ataque*0.25;
                System.out.println(ColoresConsola.enAmarillo("El "+enemigoActual[apuntar].nombre+ " se ha defendido \n ") );
            }else{
                Ataque=0;
                System.out.println(ColoresConsola.enAmarillo("El "+enemigoActual[apuntar].nombre+ "ha realizado una defensa impecable \n "));
            }
        }

        double attack=Math.random();

        switch (dificultad){

            case 1:{

                if(attack <= 0.1){
                    int ataqueP = Ataque/3;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo) \n"+ColoresConsola.RESET);
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueP+ "\n"+ColoresConsola.RESET);
                }else if( attack <= 0.7){
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal \n"+ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+Ataque+ "\n"+ColoresConsola.RESET);
                }else {
                    int ataqueC = Ataque*2;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) \n"+ColoresConsola.VERDE);
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                        System.out.println("Pero no te ha servido de nada");
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);

                    }

                }
                break;
            }

            case 2:{

                if(attack <= 0.2){
                    int ataqueP = Ataque/3;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo) \n"+ColoresConsola.RESET);
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueP+ "\n"+ColoresConsola.RESET);
                }else if( attack <= 0.8){
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal \n"+ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+Ataque+ "\n"+ColoresConsola.RESET);
                }else {
                    int ataqueC = Ataque*2;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) \n"+ColoresConsola.RESET);
                    if(enemigoActual[apuntar].defenderse && defense >0.8){
                        System.out.println("Pero no te ha servido de nada");
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                    }

                }
                break;
            }

            case 3:{

                if(attack <= 0.3){
                    int ataqueP = Ataque/3;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo) " );
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP));
                }else if(attack <= 0.9){
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque normal "));
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque));
                }else {
                    int ataqueC = Ataque*2;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) \n"+ColoresConsola.RESET);
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                        System.out.println("Pero no te ha servido de nada");
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
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

        if(Curation < 0.8){
            double Curacion=VIDA_BASE_GUERRERO*0.2;
            Vida+=Curacion;
            System.out.println(ColoresConsola.enVerde("Has aumentado la vida en: "+Curacion+" puntos"));
        }else {
            double Kuracion=VIDA_BASE_GUERRERO*0.45;
            Vida+=Kuracion;
            System.out.println(ColoresConsola.enVerde("Has aumentado la vida en "+Kuracion+" puntos"));
        }

        defenderse=false;
    }

    @Override
    public void SuperAtaque(){

        int input;

        do{

            System.out.println("Elige un enemigo al que atacar: \n (1) "+enemigoActual[0]+" | (2) "+enemigoActual[1]);
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
            System.out.println(ColoresConsola.enVerde("¡¡Y ademas has realizado un ataque critico!!"));
            enemigoActual[apuntar].Vida-=SuperC;
            System.out.println(ColoresConsola.enVerde("Daño: "+SuperC+"\n"));
            danoIngfligido+=SuperC;
        }else {
            int Super = ATAQUE_GUERRERO*3;
            enemigoActual[apuntar].Vida-=Super;
            System.out.println(ColoresConsola.enVerde("Daño: "+Super+"\n"));
            danoIngfligido+=Super;
        }

        defenderse=false;
    }

    @Override
    public String toString() {
        return ColoresConsola.AZUL+ " (( Guerrero: " +
                " Vida=" + Vida +
                " || Ataque=" + Ataque +
                " || Estamina=" + Estamina +
        " || Daño total: " +danoIngfligido+" )) \n"+ColoresConsola.RESET;
    }


}
