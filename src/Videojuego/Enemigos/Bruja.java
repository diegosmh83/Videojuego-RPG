package Videojuego.Enemigos;

import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.dificultad;

public class Bruja extends Enemigo {


    public Bruja() {
        Vida=125;
        Ataque=15;
    }


    @Override
    public void Atacar(){

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

        System.out.println(("El enemigo va a atacar \n "));

        int ataqueBase = Ataque;

        if(jugadorActual.defenderse){
            double defense;

            defense=Math.random();

            if(defense < 0.2 ){
                System.out.println(ColoresConsola.enAzul("Tu defensa ha resultado fallida \n ") );
            }else if(defense >= 0.2 && defense <=0.8){
                Ataque-=Ataque*0.25;
                System.out.println(ColoresConsola.enAzul("Has realizado una defensa normal \n "));
            }else{
                Ataque=0;
                System.out.println(ColoresConsola.enVerde("¡Has realizado una defensa Perfecta! \n "));
            }
        }

        double attack;

        attack=Math.random();

        if(attack < 0.7){
            System.out.println(ColoresConsola.enAmarillo("Ha realizado un ataque normal \n"));
            jugadorActual.Vida-=Ataque;
            System.out.println(ColoresConsola.enRojo("Daño recibido: "+Ataque));
            danoIngfligido+=Ataque;
        }else{
            System.out.println(ColoresConsola.enAmarillo("El enemigo ha realizado un ataque critico \n"));
            int ataqueC=Ataque*2;
            jugadorActual.Vida-=ataqueC;
            System.out.println(ColoresConsola.enRojo("Daño recibido: "+ataqueC));
            danoIngfligido+=Ataque;
        }
        Ataque = ataqueBase;

        defenderse=false;


    }



    @Override
    public void Defender(){

        defenderse=true;

    }

    @Override
    public String toString() {
        return ColoresConsola.ROJO+ "(( Vida Bruja:" + Vida+" ))\n"+ColoresConsola.RESET;
    }


}
