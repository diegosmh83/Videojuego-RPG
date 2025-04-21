package Videojuego.Jugadores;

import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.dificultad;
import static Videojuego.Juego.sc;

public class Mago extends Jugador {

    int apuntar;

    public Mago() {
        Vida=VIDA_BASE_MAGO;
        Ataque=ATAQUE_MAGO;
        Estamina=25;
    }


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

        int input;

        do{

            System.out.println("Elige un enemigo al que atacar: (1) "+enemigoActual[0]+" | (2) "+enemigoActual[1]);
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

        switch (dificultad){

            case 1:{

                if(attack < 0.1){
                    int ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo) \n "));
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP+ "\n"));
                    Estamina++;
                }else if(attack <= 0.7){
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque normal \n "));
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque+ "\n"));
                    Estamina+=5;
                }else {
                    double ataqueC = Ataque*1.5;
                    System.out.println(ColoresConsola.enVerde("¡Has realizado un ataque critico! (Suerte) \n "));
                    if(enemigoActual[apuntar].defenderse && defense >0.8) {
                        System.out.println("Pero no te ha servido para nada \n ");
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                        Estamina+=15;
                    }

                }
                break;
            }

            case 2:{

                if(attack < 0.2){
                    int ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo)  "+ColoresConsola.RESET);
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueP + ColoresConsola.RESET);
                    Estamina++;
                }else if(attack <= 0.8){
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal "+ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: " +Ataque+ColoresConsola.RESET);
                    Estamina+=5;
                }else {
                    double ataqueC = Ataque*1.5;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) "+ColoresConsola.RESET);
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                        System.out.println("Pero no te ha servido para nada \n ");
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                        Estamina+=15;
                    }

                }
                break;
            }

            case 3:{

                if(attack < 0.3){
                    int ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo)") );
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP));
                    Estamina++;
                }else if(attack <= 0.9){
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal \n" +ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+Ataque+"\n"+ColoresConsola.RESET);
                    Estamina+=5;
                }else {
                    double ataqueC = Ataque*1.5;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) ");
                    if(enemigoActual[apuntar].defenderse && defense > 0.8){
                            System.out.println("Pero no te ha servido para nada \n ");
                            return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: " +ataqueC+"\n"+ColoresConsola.RESET);
                        Estamina+=15;
                    }

                }
                break;
            }
        }


        Ataque = ATAQUE_MAGO;

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

        if(Curation < 0.8){
            double Curacion=VIDA_BASE_MAGO*0.2;
            Vida+=Curacion;
            System.out.println(ColoresConsola.VERDE+"Has aumentado la vida en: "+Curacion+ "puntos \n ");
        }else {
            double Kuracion=VIDA_BASE_MAGO*0.45;
            Vida+=Kuracion;
            System.out.println(ColoresConsola.enVerde("Has aumentado la vida en "+Kuracion+" puntos \n "));
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

        System.out.println("¡Has usado la Bola de Fuego! \n  ");

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



        double SuperA = Math.random();

        if(SuperA > 0.85){
            int SuperC = ATAQUE_MAGO*5;
            System.out.println(ColoresConsola.VERDE+"¡¡Y ademas has realizado un ataque critico!! \n  ");
            enemigoActual[apuntar].Vida-=SuperC;
            System.out.println(ColoresConsola.enVerde("Daño infligido: "+SuperC+"\n"));
            danoIngfligido+=SuperC;
        }else {
            int Super = ATAQUE_MAGO*3;
            enemigoActual[apuntar].Vida-=Super;
            System.out.println(ColoresConsola.enVerde("Daño infligido: "+Super+"\n"));
            danoIngfligido+=Super;
        }

        defenderse=false;
    }

    @Override
    public String toString() {
        return ColoresConsola.AZUL+" (( Mago: " +
                " Vida=" + Vida +
                " || Ataque=" + Ataque +
                " || Estamina=" + Estamina +
                " || Daño total: " +danoIngfligido+" )) \n"+ColoresConsola.RESET;
    }
}
