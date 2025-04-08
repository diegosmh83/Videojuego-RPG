package Videojuego.Enemigos;

import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.dificultad;

public class Duende extends Enemigo {

    public Duende() {
        Vida=150;
        Ataque=10;
    }


    @Override
    public void Atacar(){

        //puedes perder el turno de ataque si te defiendes
        if(defenderse){
            double probabilidad=Math.random();

            switch (dificultad){

                case 1:{
                    if(probabilidad > 0.7){
                        System.out.println(("El duende perdio el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa \n"));
                        defenderse=false;
                        return;
                    }
                    break;
                }

                case 2:{
                    if(probabilidad > 0.8){
                        System.out.println(("El duende perdio el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa \n"));
                        defenderse=false;
                        return;
                    }
                    break;
                }

                case 3:{
                    if(probabilidad > 0.9){
                        System.out.println(("El duende perdio el turno de ataque por tardar en reincorporarte tras la " +
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


            //Sub-metodo para defender:
        if (jugadorActual.defenderse){

            double defense=Math.random();

            if(defense < 0.2 ){
                    System.out.println(ColoresConsola.enAzul("Tu defensa ha resultado fallida \n ") );
            }else if(defense >= 0.2 && defense <=0.8){
                    Ataque-=Ataque*0.25;
                    System.out.println(ColoresConsola.enAzul("Defensa Normal \n "));
            }else{
                    Ataque=0;
                    System.out.println(ColoresConsola.enVerde("¡Has realizado una defensa perfecta! \n "));
            }

        }


        double attack=Math.random();


        //Sub-metodo para atacar:
        if(attack < 0.7){
            System.out.println(ColoresConsola.enAmarillo("Ha realizado un ataque normal \n"));
            jugadorActual.Vida-=Ataque;
            System.out.println(ColoresConsola.enRojo("Daño recibido: "+Ataque));
            danoIngfligido+=Ataque;
        }else{
            System.out.println(ColoresConsola.enAmarillo("El enemigo ha realizado un ataque critico \n"));
            int ataqueC= Ataque*2;
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
        return ColoresConsola.ROJO+ "(( Vida Duende: " + Vida + "))\n"+ColoresConsola.RESET;
    }

}
