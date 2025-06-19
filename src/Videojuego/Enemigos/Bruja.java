package Videojuego.Enemigos;

import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.dificultad;

public class Bruja extends Enemigo {

    int apuntado;

    public Bruja() {
        Vida=125;
        Ataque=20;
        Defensa=8;
        nombre="Bruja";
        Nerffeado=false;
    }

    double defensa_Base=DEFENSA_BRUJA_BASE;

    @Override
    public void Atacar(){

        double defense=Math.random();

        //puedes perder el turno de ataque si te defiendes
        if(defenderse){
            double probabilidad=Math.random();

            switch (dificultad){

                case 1:{
                    if(probabilidad > 0.7){
                        System.out.println(("La bruja perdio el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa \n "));
                        defenderse=false;
                        return;
                    }
                    break;
                }

                case 2:{
                    if(probabilidad > 0.8){
                        System.out.println(("La bruja perdio el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa \n"));
                        defenderse=false;
                        return;
                    }
                    break;
                }

                case 3:{
                    if(probabilidad > 0.9){
                        System.out.println(("La bruja perdio el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa \n"));
                        defenderse=false;
                        return;
                    }
                    break;
                }

            }


        }

        System.out.println(("La bruja va a atacar \n "));

        double input;

        input=Math.random();

        if(input > 0.5){
            apuntado=1;
            System.out.println(ColoresConsola.enAmarillo("va a por el jugador 2 (("+jugadorActual[1].Vida+"))"));
        }else{
            apuntado=0;
            System.out.println(ColoresConsola.enAmarillo("va a por el jugador 1 (("+jugadorActual[0].Vida+"))"));
        }

        double ataqueBase = Ataque;

        if(jugadorActual[apuntado].defenderse){

            if(defense < 0.2 ){
                System.out.println(ColoresConsola.enAzul("Tu defensa ha resultado fallida \n ") );
            }else if(defense <=0.8){
                Ataque-=Ataque*0.25;
                System.out.println(ColoresConsola.enAzul("Has realizado una defensa normal \n "));
            }else{
                Ataque=0;
                System.out.println(ColoresConsola.enVerde("¡Has realizado una defensa Perfecta! \n "));
            }
        }

        double attack;

        attack=Math.random();

        double estado=Math.random();
        double elegir=Math.random();

        switch (dificultad){

            case 1:{
                if(estado > 0.95){
                    if(elegir < 0.5){
                        jugadorActual[apuntado].envenenado=true;
                    }else if(elegir < 0.9){
                        jugadorActual[apuntado].paralizado=true;
                    }else{
                        jugadorActual[apuntado].envenenado=true;
                        jugadorActual[apuntado].paralizado=true;
                    }
                }
            }

            case 2:{
                if(estado > 0.9){
                    if(elegir < 0.5){
                        jugadorActual[apuntado].envenenado=true;
                    }else if(elegir < 0.9){
                        jugadorActual[apuntado].paralizado=true;
                    }else{
                        jugadorActual[apuntado].envenenado=true;
                        jugadorActual[apuntado].paralizado=true;
                    }

                }
            }

            case 3:{
                if(estado > 0.85){
                    if(elegir < 0.45){
                        jugadorActual[apuntado].envenenado=true;
                    }else if(elegir < 0.9){
                        jugadorActual[apuntado].paralizado=true;
                    }else{
                        jugadorActual[apuntado].envenenado=true;
                        jugadorActual[apuntado].paralizado=true;
                    }
                }
            }

        }

        double buffeo=Math.random();
        double nerffeo=Math.random();

        if(attack < 0.7){
            System.out.println(ColoresConsola.enAmarillo("Ha realizado un ataque normal \n"));
            Ataque-=jugadorActual[apuntado].Defensa;
            jugadorActual[apuntado].Vida-=Ataque;
            System.out.println(ColoresConsola.enRojo("Daño recibido: "+Ataque));
            danoIngfligido+=Ataque;
        }else{
            System.out.println(ColoresConsola.enAmarillo("Ha realizado un ataque critico \n"));
            if(jugadorActual[apuntado].defenderse && defense > 0.8){
                System.out.println(ColoresConsola.enVerde("Pero no le ha servido de nada"));
                return;
            }
            double ataqueC=Ataque*2;
            jugadorActual[apuntado].Vida-=ataqueC;
            System.out.println(ColoresConsola.enRojo("Daño recibido: "+ataqueC));
            danoIngfligido+=Ataque;
            if(nerffeo > 0.7){
                System.out.println(ColoresConsola.enAmarillo("Se reduce temporalmente la defensa de"+jugadorActual[apuntado].nombre+" en un 20%"));
                jugadorActual[apuntado].Nerffeado=true;
                JugadorNerffeado=apuntado;
            }
            if(buffeo > 0.7){
                System.out.println(ColoresConsola.enAmarillo("Aumenta temporalmente el ataque de "+nombre+ "en un 15%"));
                Buffeado=true;
            }
        }
        Ataque = ataqueBase;

        defenderse=false;

    }



    @Override
    public void Defender(){

        defenderse=true;

    }

    public void aplicarNerffeo(){

        Defensa=DEFENSA_BRUJA_BASE*0.8;

    }

    public void aplicarBuffeo(){

        Ataque=ATAQUE_BRUJA*1.15;

    }

    @Override
    public String toString() {
        return ColoresConsola.ROJO+ "(( BRUJA: Vida:" + Vida+" || Ataque:"+Ataque+" || Defensa: "+Defensa+" || Daño total: "+danoIngfligido+"))\n"+ColoresConsola.RESET;
    }


}
