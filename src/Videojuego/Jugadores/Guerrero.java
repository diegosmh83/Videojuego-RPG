package Videojuego.Jugadores;

public class Guerrero extends Jugador {


    public Guerrero(int vida, int ataque, int estamina) {
        super(vida, ataque, estamina);
        Vida=VIDA_BASE_GUERRERO;
        Ataque=ATAQUE_GUERRERO;
        Estamina=0;
    }

    public Guerrero() {
        Vida=VIDA_BASE_GUERRERO;
        Ataque=ATAQUE_GUERRERO;
        Estamina=0;
    }


    @Override
    public boolean Atacar(){

        System.out.println("Has escogido Atacar \n ");

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

        if(attack <= 0.2){
            int ataqueP = Ataque/3;
            System.out.println("Has realizado un ataque penoso (fallo) \nDaño: " +ataqueP);
            danoIngfligido+=ataqueP;
            enemigoActual.Vida-=ataqueP;
        }else if(attack > 0.2 && attack <= 0.8){
            System.out.println("Has realizado un ataque normal \nDaño: " +Ataque);
            danoIngfligido+=Ataque;
            enemigoActual.Vida-=Ataque;
        }else if (attack > 0.8){
            int ataqueC = Ataque*2;
            System.out.println("¡Has realizado un ataque critico! (Suerte) \nDaño: " +ataqueC);
            danoIngfligido+=ataqueC;
            enemigoActual.Vida-=ataqueC;
            if(defense > 0.8){
                System.out.println("Pero no te ha servido de nada");
            }
        }

        Ataque = ATAQUE_GUERRERO;

        defenderse=false;

        return true;
    }


    @Override
    public boolean Defender(){

        defenderse=true;

        return true;
    }


    @Override
    public void Curarse(){

        System.out.println("Has escogido curarte \n");

        Estamina-=20;

        double Curation=Math.random();

        if(Curation < 0.8){
            double Curacion=VIDA_BASE_GUERRERO*0.2;
            Vida+=Curacion;
            System.out.println("Has aumentado la vida en: "+Curacion+" puntos");
        }else {
            double Kuracion=VIDA_BASE_GUERRERO*0.45;
            Vida+=Kuracion;
            System.out.println("Has aumentado la vida en "+Kuracion+" puntos");
        }
    }

    @Override
    public void SuperAtaque(){

        System.out.println("¡Has escogido el SuperAtaque! \n");

       Estamina-=75;

        double SuperA = Math.random();

        if(SuperA > 0.85){
            int SuperC = Ataque*5;
            System.out.println("¡¡Y ademas has realizado un ataque ciritico!!");
            enemigoActual.Vida-=SuperC;
            System.out.println("Daño: "+SuperC);
            danoIngfligido+=SuperC;
        }else {
            int Super = Ataque*3;
            enemigoActual.Vida-=Super;
            System.out.println("Daño: "+Super);
            danoIngfligido+=Super;
        }
    }

    @Override
    public String toString() {
        return " Guerrero: " +
                " || Vida=" + Vida +
                " || Ataque=" + Ataque +
                " || Estamina=" + Estamina +
        " || Daño total: " +danoIngfligido+"\n";
    }


}
