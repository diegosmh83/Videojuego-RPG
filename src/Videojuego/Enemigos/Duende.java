package Videojuego.Enemigos;

import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.dificultad;

public class Duende extends Enemigo {

    int apuntado;

    public Duende() {
        Vida = 150;
        Ataque = 15;
        Defensa=5;
        nombre = "Duende";
        Nerffeado=false;
    }

    double defensa_base=DEFENSA_DUENDE_BASE;


    @Override
    public void Atacar() {

        double defense = Math.random();

        //El enemigo puede perder el turno de ataque si se defiende
        if (defenderse) {
            double probabilidad = Math.random();

            switch (dificultad) {

                case 1: {
                    if (probabilidad > 0.7) {
                        System.out.println(("El duende perdio el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa \n"));
                        defenderse = false;
                        return;
                    }
                    break;
                }

                case 2: {
                    if (probabilidad > 0.8) {
                        System.out.println(("El duende perdio el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa \n"));
                        defenderse = false;
                        return;
                    }
                    break;
                }

                case 3: {
                    if (probabilidad > 0.9) {
                        System.out.println(("El duende perdio el turno de ataque por tardar en reincorporarte tras la " +
                                "defensa \n"));
                        defenderse = false;
                        return;
                    }
                    break;
                }
            }


        }

        System.out.println(("El Duende va a atacar \n "));

        double ataqueBase = Ataque;

        double input;

        input = Math.random();

        if (input > 0.5) {
            apuntado = 1;
            System.out.println(ColoresConsola.enAmarillo("va a por el J2 "+jugadorActual[1].nombre+ "((" + jugadorActual[1].Vida + "))"));
        } else {
            apuntado = 0;
            System.out.println(ColoresConsola.enAmarillo("va a por el J1 "+jugadorActual[0].nombre+ "((" + jugadorActual[0].Vida + "))"));
        }


        //Sub-metodo para defender:
        if (jugadorActual[apuntado].defenderse) {

            if (defense < 0.2) {
                System.out.println(ColoresConsola.enAzul("Tu defensa ha resultado fallida \n "));
            } else if ( defense <= 0.8) {
                Ataque-=Ataque * 0.25;
                System.out.println(ColoresConsola.enAzul("Defensa Normal \n "));
            } else {
                Ataque = 0;
                System.out.println(ColoresConsola.enVerde("¡Has realizado una defensa perfecta! \n "));
            }

        }

        double attack = Math.random();

        double estado = Math.random();
        double elegir = Math.random();

        switch (dificultad) {

            case 1: {
                if (estado > 0.95) {
                    if (elegir < 0.5) {
                        jugadorActual[apuntado].desangrado = true;
                    } else if (elegir < 0.9) {
                        jugadorActual[apuntado].paralizado = true;
                    } else {
                        jugadorActual[apuntado].desangrado = true;
                        jugadorActual[apuntado].paralizado = true;
                    }
                }
            }

            case 2: {
                if (estado > 0.9) {
                    if (elegir < 0.5) {
                        jugadorActual[apuntado].desangrado = true;
                    } else if (elegir < 0.9) {
                        jugadorActual[apuntado].paralizado = true;
                    } else {
                        jugadorActual[apuntado].desangrado = true;
                        jugadorActual[apuntado].paralizado = true;
                    }

                }
            }

            case 3: {
                if (estado > 0.85) {
                    if (elegir < 0.45) {
                        jugadorActual[apuntado].desangrado = true;
                    } else if (elegir < 0.9) {
                        jugadorActual[apuntado].paralizado = true;
                    } else {
                        jugadorActual[apuntado].desangrado = true;
                        jugadorActual[apuntado].paralizado = true;
                    }
                }
            }

            double buffeo=Math.random();
            double nerffeo=Math.random();


            //Sub-metodo para atacar:
            if (attack < 0.7) {
                System.out.println(ColoresConsola.enAmarillo("Ha realizado un ataque normal \n"));
                Ataque-=jugadorActual[apuntado].Defensa;
                jugadorActual[apuntado].Vida -= Ataque;
                System.out.println(ColoresConsola.enRojo("Daño recibido: " + Ataque));
                danoIngfligido += Ataque;
            } else {
                System.out.println(ColoresConsola.enAmarillo("Ha realizado un ataque critico \n"));
                if(jugadorActual[apuntado].defenderse && defense>0.8){
                    System.out.println(ColoresConsola.enVerde("Pero no le ha servido de nada"));
                    return;
                }
                double ataqueC = Ataque * 2;
                jugadorActual[apuntado].Vida -= ataqueC;
                System.out.println(ColoresConsola.enRojo("Daño recibido: " + ataqueC));
                danoIngfligido += Ataque;
                if(nerffeo > 0.7){
                    jugadorActual[apuntado].Nerffeado=true;
                    JugadorNerffeado=apuntado;
                }
                if(buffeo > 0.7){
                    Buffeado=true;
                }
            }

            Ataque = ataqueBase;

            defenderse = false;

        }

    }


        @Override
        public void Defender () {

            defenderse = true;

        }

    public void aplicarNerffeo(){

        Defensa=DEFENSA_DUENDE_BASE*0.8;
        System.out.println(ColoresConsola.enVerde("Se reduce temporalmente la defensa de " +nombre+ " en un 20%"));

    }

    public void aplicarBuffeo(){

        Ataque=ATAQUE_DUENDE*1.15;
        System.out.println(ColoresConsola.enAmarillo("Aumenta temporalmente el ataque de "+nombre+ " en un 15%"));

    }

        @Override
        public String toString () {
            return ColoresConsola.ROJO + "(( DUENDE:  Vida:" + Vida + " || Ataque: "+Ataque+ " || Defensa: "+Defensa+ " || Daño Total: "+danoIngfligido+" ))\n" + ColoresConsola.RESET;
        }

    }


