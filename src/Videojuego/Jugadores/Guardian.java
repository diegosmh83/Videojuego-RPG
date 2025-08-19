package Videojuego.Jugadores;

import Videojuego.Interfaces.AccionesJugador;
import Videojuego.Interfaces.ColoresConsola;

import static Videojuego.Juego.*;

public class Guardian extends Jugador implements AccionesJugador {

    public Guardian(){
        Vida=VIDA_BASE_GUARDIAN;
        Ataque=ATAQUE_GUARDIAN;
        Defensa=15;
        Velocidad=19;
        Estamina=5;
        nombre="Guardian";
        Nerffeado=false;
        Buffeado=false;
    }

    int apuntar;

    public static final double VIDA_BASE=VIDA_BASE_GUARDIAN;
    public static final double DEFENSA_BASE=15;
    public static final double ATAQUE_BASE=ATAQUE_GUARDIAN;
    public static final double VELOCIDAD_BASE=19;

    double defense=Math.random();

    double ataqueCargado=ATAQUE_BASE*2.2;


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

                System.out.println("Elige un enemigo al que atacar: (1) "+enemigoActual[0].nombre+
                        " (("+enemigoActual[0].Vida+")) || (2) "+enemigoActual[1].nombre+
                        " (("+enemigoActual[1].Vida+"))");
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



        //Sub-metodo para que el enemigo se defienda
        if(enemigoActual[apuntar].defenderse){


            System.out.println((enemigoActual[apuntar].nombre+ "se esta defendiendo... \n "));

            if(defense < 0.2){
                System.out.println(ColoresConsola.enAmarillo("La defensa de " +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
            }else if (defense <= 0.8){
                Ataque-=Ataque*0.25;
                System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " se ha defendido \n ") );
            }else{
                System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " ha realizado una defensa perfecta y ha bloqueado tu ataque \n "));
                return;
            }

        }

        double attack;
        attack=Math.random();

        double buffeo=Math.random();
        double nerffeo=Math.random();

        if(Furia){

            if(turnosFurioso > 0){

                Ataque=ATAQUE_BASE*1.5;

                Velocidad=VELOCIDAD_BASE*1.5;

                --turnosFurioso;

            }else{

                Furia=false;

                System.out.println(ColoresConsola.enRojo(nombre+ " ha salido del estado de furia"));

                turnosFurioso=4;

            }

        }

        switch (dificultad){

            case 1:{

                if(attack < 0.1){
                    double ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo) "));
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP+ "\n"));
                    Estamina++;
                }else if(attack <= 0.7){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    if(Ataque <= 0){
                        Ataque=3;
                    }
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque normal  "));
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+Ataque+ "\n"));
                    Estamina+=5;
                }else {
                    double ataqueC = Ataque*1.75;
                    System.out.println(ColoresConsola.enVerde("¡Has realizado un ataque critico! (Suerte)  "));
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                        Estamina+=15;
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
                    double ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque penoso (fallo)  "+ColoresConsola.RESET);
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueP + ColoresConsola.RESET);
                    Estamina++;
                }else if(attack <= 0.8){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    if(Ataque <= 0){
                        Ataque=3;
                    }
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal "+ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: " +Ataque+ColoresConsola.RESET);
                    Estamina+=5;
                }else {
                    double ataqueC = Ataque*1.75;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) "+ColoresConsola.RESET);
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: "+ataqueC+ "\n"+ColoresConsola.RESET);
                        Estamina+=15;
                    if(nerffeo > 0.8){
                        enemigoActual[apuntar].Nerffeado=true;
                        EnemigoNerffeado=apuntar;
                    }
                    if(buffeo > 0.8){
                        Buffeado=true;
                    }
                }
                break;
            }

            case 3:{

                if(attack < 0.3){
                    double ataqueP = Ataque/2;
                    System.out.println(ColoresConsola.enAzul("Has realizado un ataque penoso (fallo)") );
                    danoIngfligido+=ataqueP;
                    enemigoActual[apuntar].Vida-=ataqueP;
                    System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueP));
                    Estamina++;
                }else if(attack <= 0.9){
                    Ataque-=enemigoActual[apuntar].Defensa;
                    if(Ataque <= 0){
                        Ataque=3;
                    }
                    System.out.println(ColoresConsola.AZUL+"Has realizado un ataque normal \n" +ColoresConsola.RESET);
                    danoIngfligido+=Ataque;
                    enemigoActual[apuntar].Vida-=Ataque;
                    System.out.println(ColoresConsola.VERDE+"Daño infligido: "+Ataque+"\n"+ColoresConsola.RESET);
                    Estamina+=5;
                }else {
                    double ataqueC = Ataque*1.75;
                    System.out.println(ColoresConsola.VERDE+"¡Has realizado un ataque critico! (Suerte) ");
                        danoIngfligido+=ataqueC;
                        enemigoActual[apuntar].Vida-=ataqueC;
                        System.out.println(ColoresConsola.VERDE+"Daño infligido: " +ataqueC+"\n"+ColoresConsola.RESET);
                        Estamina+=15;
                    if(nerffeo > 0.9){
                        enemigoActual[apuntar].Nerffeado=true;
                        EnemigoNerffeado=apuntar;
                    }
                    if(buffeo > 0.9){
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

        esquivando=false;

    }



    @Override
    public void Curarse(){

        System.out.println("Has escogido curarte \n ");

        Estamina-=20;

        double Curation=Math.random();


        int input;

        if(!jugador0muerto && !jugador1muerto){

            do{

                System.out.println("Elige a que jugador curar: (1) "+ Jugadores[0].nombre + Jugadores[0].Vida+ " || (2) "+ Jugadores[1].nombre + Jugadores[1].Vida);

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

        esquivando=false;
    }

    @Override
    public void SuperAtaque(){

        int input;

        if(!enemigo0muerto && !enemigo1muerto){

            do{

                System.out.println("Elige un enemigo al que atacar: (1)"+enemigoActual[0].nombre+
                        "(("+enemigoActual[0].Vida+")) || (2) "+enemigoActual[1].nombre+
                        "(("+enemigoActual[1].Vida+"))");
                input=sc.nextInt();
                apuntar=input-1;

            }while(input!=1 && input!=2);

        }else if(enemigo1muerto){
            apuntar=0;
            System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 1 "+enemigoActual[0].nombre));
        }else{
            apuntar=1;
            System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 2 "+enemigoActual[1].nombre));
        }


        System.out.println(("¡Has usado la Justicia de Acero!"));

        Estamina-=75;

        double SuperA = Math.random();

        if(SuperA > 0.85){
            double SuperC = ATAQUE_GUARDIAN*5;
            SuperC-=enemigoActual[apuntar].Defensa;
            System.out.println(ColoresConsola.enVerde("¡¡Y ademas has realizado un ataque critico!!"));
            enemigoActual[apuntar].Vida-=SuperC;
            System.out.println(ColoresConsola.enVerde("Daño: "+SuperC+"\n"));
            danoIngfligido+=SuperC;
        }else{
            double Super = ATAQUE_GUARDIAN*3;
            Super-=enemigoActual[apuntar].Defensa;
            enemigoActual[apuntar].Vida-=Super;
            System.out.println(ColoresConsola.enVerde("Daño: "+Super+"\n"));
            danoIngfligido+=Super;

        }

        defenderse=false;

        esquivando=false;
    }

    @Override
    public void golpeDevastador(){

        System.out.println("Has escogido el golpe devastador \n");

        Ataque=ATAQUE_BASE*2;

        int input;

        if(!enemigo0muerto && !enemigo1muerto){

            do{

                System.out.println("Elige un enemigo al que atacar: (1)"+enemigoActual[0].nombre+
                        enemigoActual[0].Vida+" || (2) "+enemigoActual[1].nombre+
                        enemigoActual[1].Vida);
                input=sc.nextInt();
                apuntar=input-1;

            }while(input!=1 && input!=2);

        }else if(enemigoActual[0].Vida > 0){
            apuntar=0;
            System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 1 "+enemigoActual[0].nombre));
        }else{
            apuntar=1;
            System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 2 "+enemigoActual[1].nombre));
        }

        double defense=Math.random();

        if(enemigoActual[apuntar].defenderse){


            System.out.println((enemigoActual[apuntar].nombre+ " se esta defendiendo... \n "));

            if(defense < 0.2){
                System.out.println(ColoresConsola.enAmarillo("La defensa de " +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
                enemigoActual[apuntar].Vida-=Ataque;
                danoIngfligido+=Ataque;
                System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
            }else if (defense <= 0.8){
                Ataque-=Ataque*0.25;
                System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " se ha defendido del golpe devastador\n ") );
                enemigoActual[apuntar].Vida-=Ataque;
                danoIngfligido+=Ataque;
                System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
            }else{
                Ataque=ATAQUE_BASE;
                System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " ha realizado una defensa perfecta y ha mermado tu golpe devastador \n "));
                enemigoActual[apuntar].Vida-=Ataque;
                danoIngfligido+=Ataque;
                System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
            }

        }else{
            enemigoActual[apuntar].Vida-=Ataque;
            danoIngfligido+=Ataque;
            System.out.println(ColoresConsola.enVerde("Daño inligido: "+Ataque));
        }


        Ataque=ATAQUE_BASE;

        defenderse=false;

        esquivando=false;

    }

    @Override
    public void invocarEspiritu(){

        System.out.println(ColoresConsola.enMorado("Has invocado al Espiritu Ancestral"));

        espirituInvocado=true;

        defenderse=false;

        esquivando=false;

    }

    @Override
    public void atacarEspiritu(){

        int punt;
        double elegir=Math.random();

        int ataqueEspiritu=7;

        if(!enemigo0muerto && !enemigo1muerto){
            if(elegir > 0.5){
                punt=1;
                System.out.println(ColoresConsola.enMorado("El espiritu ancestral va a por el enemigo 2 "+ enemigoActual[punt].nombre + " ("+enemigoActual[punt].Vida+")"));
            }else{
                punt=0;
                System.out.println(ColoresConsola.enMorado("El espiritu ancestral va a por el enemigo 1 "+ enemigoActual[punt].nombre + " ("+enemigoActual[punt].Vida+")"));
            }
        }else{

            if(enemigo1muerto){
                punt=0;
                System.out.println(ColoresConsola.enMorado("El espiritu ancestral va a por el enemigo 1 "+ enemigoActual[punt].nombre + " ("+enemigoActual[punt].Vida+")"));
            }else{
                punt=1;
                System.out.println(ColoresConsola.enMorado("El espiritu ancestral va a por el enemigo 2 "+ enemigoActual[punt].nombre + " ("+enemigoActual[punt].Vida+")"));
            }

        }

        enemigoActual[punt].Vida-=ataqueEspiritu;

        System.out.println(ColoresConsola.enMorado("El espiritu ha infligido "+ataqueEspiritu+ " puntos de daño \n "));

    }

    @Override
    public void regeneracionRapida(){

        int input;

        if(!jugador0muerto && !jugador1muerto){

            do{

                System.out.println("Elige a que jugador curar: (1) "+ Jugadores[0].nombre + Jugadores[0].Vida+ " || (2) "+ Jugadores[1].nombre + Jugadores[1].Vida);

                input=sc.nextInt();
                apuntar=input-1;

            }while(input!=1 && input!=2);

            String Clase = Jugadores[apuntar].getClass().getSimpleName();

            switch (Clase){
                case "Arquero":
                    Jugadores[apuntar].Vida+=VIDA_BASE_ARQUERO*0.25;
                    break;
                case "Caballero":
                    Jugadores[apuntar].Vida+=VIDA_BASE_CABALLERO*25;
                    break;
                case "Escudero":
                    Jugadores[apuntar].Vida+=VIDA_BASE_ESCUDERO*0.25;
                    break;
                case "Guardian":
                    Jugadores[apuntar].Vida+=VIDA_BASE_GUARDIAN*0.25;
                    break;
                case "Guerrero":
                    Jugadores[apuntar].Vida+=VIDA_BASE_GUERRERO*0.25;
                    break;
                case "Mago":
                    Jugadores[apuntar].Vida+=VIDA_BASE_MAGO*0.25;
                    break;
                case "Ninja":
                    Jugadores[apuntar].Vida+=VIDA_BASE_NINJA*0.25;
                    break;
                case "Paladin":
                    Jugadores[apuntar].Vida+=VIDA_BASE_PALADIN*0.25;
                    break;
                case "Samurai":
                    Jugadores[apuntar].Vida+=VIDA_BASE_SAMURAI*0.25;
                    break;
                case "Valkyria":
                    Jugadores[apuntar].Vida+=VIDA_BASE_VALKYRIA*0.25;
                    break;
            }

            System.out.println(ColoresConsola.enVerde(Clase+" ha regenerado el 25% de su vida"));

        }else{

            Vida+=VIDA_BASE*0.25;
            System.out.println(ColoresConsola.enVerde(nombre+" ha regenerado el 25% de su vida"));

        }

        defenderse=false;

        esquivando=false;

    }

    @Override
    public void danoColateral(){


        int danoPocion=20;
        int input;

        if(!enemigo0muerto && !enemigo1muerto){

            do {

                System.out.println("Elige un enemigo al que atacar: (1)" + enemigoActual[0].nombre +
                        enemigoActual[0].Vida + " || (2) " + enemigoActual[1].nombre +
                        enemigoActual[1].Vida);
                input = sc.nextInt();
                apuntar = input - 1;

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

        enemigoActual[apuntar].Vida-=danoPocion;
        danoIngfligido+=danoPocion;
        System.out.println(ColoresConsola.enVerde("Daño infligido: "+danoPocion));

        defenderse=false;

        esquivando=false;

    }

    @Override
    public void cargarAtaque() {

        if(!cargandoAtaque){

            System.out.println("Has escogido cargar el ataque");

            System.out.println(ColoresConsola.enAzul("Cargando...."));

            cargandoAtaque=true;

        }else{

            System.out.println(ColoresConsola.enVerde("Ataque Cargado"));

            if(!enemigo0muerto && !enemigo1muerto){
                int input=0;

                do{

                    System.out.println("Elige un enemigo al que atacar: (1) "+enemigoActual[0].nombre+
                            " (("+enemigoActual[0].Vida+")) || (2) "+enemigoActual[1].nombre+
                            " (("+enemigoActual[1].Vida+"))");
                    input=sc.nextInt();
                    apuntar=input-1;


                }while(input!=1 && input!=2);

            }else{

                if(enemigo1muerto){
                    apuntar=0;
                    System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 1 "+enemigoActual[0].nombre));
                }else{
                    apuntar=1;
                    System.out.println(ColoresConsola.enAzul("Vas a por el enemigo 2 "+enemigoActual[1].nombre));
                }

            }

            if(enemigoActual[apuntar].defenderse){
                System.out.println(("El" +enemigoActual[apuntar].nombre+ " se esta defendiendo... \n "));

                if(defense < 0.2){
                    System.out.println(ColoresConsola.enAmarillo("La Defensa de" +enemigoActual[apuntar].nombre+ " ha resultado fallida \n "));
                }else if (defense <= 0.8){
                    ataqueCargado=ataqueCargado*0.9;
                    System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " se ha defendido \n ") );
                }else{
                    System.out.println(ColoresConsola.enAmarillo(enemigoActual[apuntar].nombre+ " ha realizado una defensa genial y ha jodido tu ataque cargado \n "));
                    ataqueCargado=ataqueCargado*0.7;
                }
            }

            enemigoActual[apuntar].Vida-=ataqueCargado;
            danoIngfligido+=ataqueCargado;
            System.out.println(ColoresConsola.enVerde("Daño infligido: "+ataqueCargado));

            ataqueCargado=ATAQUE_BASE*2.2;

            cargandoAtaque=false;

        }

        defenderse=false;

        esquivando=false;

    }

    @Override
    public void accionEspecial() {

        Curandero=true;

        System.out.println(ColoresConsola.enVerde("Has invocado al Curandero"));

        defenderse=false;

        esquivando=false;

    }

    @Override
    public void esquivar(){

        System.out.println("Has decidido esquivar al enemigo");

        esquivando=true;

        defenderse=false;

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

        if(!Furia){
            return ColoresConsola.AZUL+ " (( GUARDIAN: " +
                    " Vida=" + Vida +
                    " || Ataque=" + Ataque +
                    " || Estamina=" + Estamina +
                    " || Daño total: " +danoIngfligido+" )) \n"+ColoresConsola.RESET;
        }else{
            return ColoresConsola.AZUL+ " (( GUARDIAN: " +
                    " Vida=" + Vida +
                    " || Ataque=" + Ataque +
                    " || Estamina=" + Estamina +
                    " || Daño total: " +danoIngfligido+" )) "+ColoresConsola.ROJO+" (FURIOSO) \n "+ColoresConsola.RESET;
        }

    }

}
