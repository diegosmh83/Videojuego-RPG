package Videojuego.Jugadores;

import Videojuego.Enemigos.Enemigo;
import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.dificultad;
import static Videojuego.Juego.sc;

public class Arquero extends Jugador {

    int apuntar;

    public Arquero() {
        Vida=VIDA_BASE_ARQUERO;
        Ataque=ATAQUE_ARQUERO;
        Estamina=15;
    }


    @Override
    public void Atacar(){

        //puedes perder el turno de ataque si te defiendes
        if(defenderse){
            double probabilidad=Math.random();

            switch (dificultad){

                case 1:{

                    if(probabilidad > 0.9){
                        System.out.println(ColoresConsola.enAmarillo("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento) \n "));
                        defenderse=false;
                        return;
                    }
                }

                case 2:{

                    if(probabilidad > 0.8){
                        System.out.println(ColoresConsola.enAmarillo("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento) \n "));
                        defenderse=false;
                        return;
                    }
                }

                case 3:{

                    if(probabilidad > 0.7){
                        System.out.println(ColoresConsola.enAmarillo("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento) \n "));
                        defenderse=false;
                        return;
                    }
                }

            }

        }

        System.out.println(ColoresConsola.enAmarillo("Has escogido atacar \n "));

        int input;

        do{

            System.out.println("Elige un enemigo al que atacar: (1)  "+enemigoActual[0]+" (2)  "+enemigoActual[1]);
            input=sc.nextInt();
            apuntar=input-1;

        }while(input!=1 && input!=2);

        double defense=Math.random();

        if(enemigoActual[apuntar].defenderse){
            System.out.println(("El enemigo se esta defendiendo... \n "));

            if(defense < 0.2){
                System.out.println(ColoresConsola.enAmarillo("La defensa del enemigo ha resultado fallida \n "));
            }else if (defense <= 0.8){
                Ataque-=Ataque*0.25;
                System.out.println(ColoresConsola.enAmarillo("El enemigo se ha defendido \n ") );
            }else{
                Ataque=0;
                System.out.println(ColoresConsola.enAmarillo("El enemigo ha realizado una defensa impecable \n "));
            }
        }

        double attack;

        attack=Math.random();

        switch(dificultad){

            case 1:{
                if(attack <= 0.1){
                    double ataqueP = Ataque/2.5;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo) \n"));
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP+ "\n"));
                }else if( attack <= 0.7){
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque normal \n"));
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque+ "\n"));
                }else {
                    int ataqueC = Ataque*3;
                    System.out.println(ColoresConsola.enVerde("¡Has realizado un ataque critico! (suerte) \n"));
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                        System.out.println(ColoresConsola.enAzul("Pero no te ha servido de nada \n "));
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueC+ "\n"));
                    }

                }
                break;
            }

            case 2:{
                if(attack <= 0.2){
                    double ataqueP = Ataque/2.5;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo) \n"));
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP+ "\n"));
                }else if(attack <= 0.8){
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque normal \n"));
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque+ "\n"));
                }else {
                    int ataqueC = Ataque*3;
                    System.out.println(ColoresConsola.enVerde("¡Has realizado un ataque critico! (suerte) \n"));
                    if(defense > 0.8 ){
                        System.out.println(ColoresConsola.enAzul("Pero no te ha servido de nada \n "));
                        return;
                    }
                    danoIngfligido+=ataqueC;
                    enemigoActual[apuntar].Vida-=ataqueC;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueC+ "\n"));
                }
                break;
            }

            case 3:{
                if(attack <= 0.3){
                    double ataqueP = Ataque/2.5;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo) \n"));
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP+ "\n"));
                }else if(attack <= 0.7){
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque normal \n"));
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque+ "\n"));
                }else {
                    int ataqueC = Ataque*3;
                    System.out.println(ColoresConsola.enVerde("¡Has realizado un ataque critico! (suerte) \n"));
                    if(defense > 0.8 ){
                        System.out.println(ColoresConsola.enAzul("Pero no te ha servido de nada \n "));
                        return;
                    }
                    danoIngfligido+=ataqueC;
                    enemigoActual[apuntar].Vida-=ataqueC;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueC+ "\n"));

                }
                break;
            }

        }



        Ataque = ATAQUE_ARQUERO;

        defenderse=false;

    }


    @Override
    public void Defender(){

        System.out.println(("Has escogido defenderte "));

        defenderse=true;

    }

    @Override
    public void Curarse(){

        System.out.println(("Has escogido curarte \n "));

        Estamina-=20;

        double Curation=Math.random();

        if(Curation > 0.8){
            double Curacion=VIDA_BASE_ARQUERO*0.25;
            Vida+=Curacion;
            System.out.println(ColoresConsola.enVerde("Has aumentado la vida en: "+Curacion+" puntos"));
        }else {
            double Kuracion=VIDA_BASE_ARQUERO*0.45;
            Vida+=Kuracion;
            System.out.println(ColoresConsola.enVerde("Has aumentado la vida en "+Kuracion+" puntos"));
        }

        defenderse=false;
    }


    @Override
    public void SuperAtaque(){

        int input;

        do{

            System.out.println("Elige un enemigo al que atacar: (1) "+enemigoActual[0]+" | (2) "+enemigoActual[1]);
            input=sc.nextInt();
            apuntar=input-1;

        }while(input!=1 && input!=2);

        System.out.println(("¡Has usado la lluvia de flechas!"));

        switch (dificultad){
            case 1:{
                Estamina-=65;
            }
            case 2:{
                Estamina-=75;
            }
            case 3:{
                Estamina-=85;
            }
        }

        double SuperA = Math.random();

        if(SuperA > 0.85){
            int SuperC = ATAQUE_ARQUERO;
            System.out.println(ColoresConsola.enVerde("¡¡Y ademas has realizado un ataque ciritico!!"));
            enemigoActual[apuntar].Vida-=SuperC;
            System.out.println(ColoresConsola.enVerde("Daño: "+SuperC+"\n"));
            danoIngfligido+=SuperC;
        }else{
            int Super = ATAQUE_ARQUERO*3;
            enemigoActual[apuntar].Vida-=Super;
            System.out.println(ColoresConsola.enVerde("Daño: "+Super+"\n"));
            danoIngfligido+=Super;

        }

        defenderse=false;
    }

    @Override
    public String toString() {
        return ColoresConsola.AZUL+ " (( Arquero: " +
                " Vida=" + Vida +
                " || Ataque=" + Ataque +
                " || Estamina=" + Estamina+
                " || Daño total: " +danoIngfligido+" )) \n"+ColoresConsola.RESET;
    }

}
