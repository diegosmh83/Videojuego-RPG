package Videojuego.Jugadores;

import static Videojuego.Juego.dificultad;

public class Mago extends Jugador {


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
                        System.out.println("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n");
                        defenderse=false;
                        return;
                    }
                    break;
                }

                case 2:{

                    if(probabilidad > 0.8){
                        System.out.println("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n");
                        defenderse=false;
                        return;
                    }
                    break;
                }

                case 3:{

                    if(probabilidad > 0.7){
                        System.out.println("Perdiste el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa (lento)\n");
                        defenderse=false;
                        return;
                    }
                    break;
                }

            }
        }




        System.out.println("Has escogido atacar \n");

        double defense=Math.random();

        if(enemigoActual.defenderse){

            System.out.println("El enemigo se esta defendiendo... \n ");

            if(defense < 0.2){
                System.out.println("La defensa del enemigo ha resultado fallida \n ");
            }else if (defense >= 0.2 && defense <= 0.8){
                Ataque-=Ataque*0.25;
                System.out.println("El enemigo se ha defendido \n " );
            }else{
                Ataque=0;
                System.out.println("El enemigo ha realizado una defensa impecable \n ");
            }

        }

        double attack;

        attack=Math.random();

        switch (dificultad){

            case 1:{

                if(attack < 0.1){
                    int ataqueP = Ataque/2;
                    System.out.println("Has realizado un ataque penoso (fallo) \n ");
                    danoIngfligido+=ataqueP;
                    enemigoActual.Vida-=ataqueP;
                    System.out.println("Daño infligido: "+ataqueP+ "\n");
                    Estamina++;
                }else if(attack >= 0.1 && attack <= 0.7){
                    System.out.println("Has realizado un ataque normal \n ");
                    danoIngfligido+=Ataque;
                    enemigoActual.Vida-=Ataque;
                    System.out.println("Daño infligido: "+Ataque+ "\n");
                    Estamina+=5;
                }else if (attack > 0.7){
                    double ataqueC = Ataque*1.5;
                    System.out.println("¡Has realizado un ataque critico! (Suerte) \n ");
                    if(enemigoActual.defenderse && defense >0.8) {
                        System.out.println("Pero no te ha servido para nada \n ");
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual.Vida-=ataqueC;
                        System.out.println("Daño infligido: "+ataqueC+ "\n");
                        Estamina+=15;
                    }

                }
                break;
            }

            case 2:{

                if(attack < 0.2){
                    int ataqueP = Ataque/2;
                    System.out.println("Has realizado un ataque penoso (fallo)  ");
                    danoIngfligido+=ataqueP;
                    enemigoActual.Vida-=ataqueP;
                    System.out.println("Daño infligido: "+ataqueP);
                    Estamina++;
                }else if(attack >= 0.2 && attack <= 0.8){
                    System.out.println("Has realizado un ataque normal ");
                    danoIngfligido+=Ataque;
                    enemigoActual.Vida-=Ataque;
                    System.out.println("Daño infligido: " +Ataque);
                    Estamina+=5;
                }else if (attack > 0.8){
                    double ataqueC = Ataque*1.5;
                    System.out.println("¡Has realizado un ataque critico! (Suerte) ");
                    if(enemigoActual.defenderse && defense > 0.8){
                        System.out.println("Pero no te ha servido para nada \n ");
                        return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual.Vida-=ataqueC;
                        System.out.println("Daño infligido: "+ataqueC+ "\n");
                        Estamina+=15;
                    }

                }
                break;
            }

            case 3:{

                if(attack < 0.3){
                    int ataqueP = Ataque/2;
                    System.out.println("Has realizado un ataque penoso (fallo) \n Daño infligido: " +ataqueP +"\n");
                    danoIngfligido+=ataqueP;
                    enemigoActual.Vida-=ataqueP;
                    Estamina++;
                }else if(attack >= 0.3 && attack <= 0.9){
                    System.out.println("Has realizado un ataque normal \n" );
                    danoIngfligido+=Ataque;
                    enemigoActual.Vida-=Ataque;
                    System.out.println("Daño infligido: "+Ataque+"\n");
                    Estamina+=5;
                }else if (attack > 0.9){
                    double ataqueC = Ataque*1.5;
                    System.out.println("¡Has realizado un ataque critico! (Suerte) ");
                    if(enemigoActual.defenderse && defense > 0.8){
                            System.out.println("Pero no te ha servido para nada \n ");
                            return;
                    }else{
                        danoIngfligido+=ataqueC;
                        enemigoActual.Vida-=ataqueC;
                        System.out.println("Daño infligido: " +ataqueC+"\n");
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
            System.out.println("Has aumentado la vida en: "+Curacion+ "puntos \n ");
        }else {
            double Kuracion=VIDA_BASE_MAGO*0.45;
            Vida+=Kuracion;
            System.out.println("Has aumentado la vida en "+Kuracion+" puntos \n ");
        }

        defenderse=false;
    }

    @Override
    public void SuperAtaque(){

        System.out.println("¡Has escogido el SuperAtaque! \n  ");

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
            System.out.println("¡¡Y ademas has realizado un ataque critico!! \n  ");
            enemigoActual.Vida-=SuperC;
            System.out.println("Daño infligido: "+SuperC+"\n");
            danoIngfligido+=SuperC;
        }else {
            int Super = ATAQUE_MAGO*3;
            enemigoActual.Vida-=Super;
            System.out.println("Daño infligido: "+Super+"\n");
            danoIngfligido+=Super;
        }

        defenderse=false;
    }

    @Override
    public String toString() {
        return " (( Mago: " +
                " Vida=" + Vida +
                " || Ataque=" + Ataque +
                " || Estamina=" + Estamina +
                " || Daño total: " +danoIngfligido+" )) \n";
    }
}
