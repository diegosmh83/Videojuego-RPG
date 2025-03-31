package Videojuego.Jugadores;

public class Mago extends Jugador {


    public Mago() {
        Vida=VIDA_BASE_MAGO;
        Ataque=ATAQUE_MAGO;
        Estamina=25;
    }


    @Override
    public boolean Atacar(){

        System.out.println("Has escogido atacar /n");

        double defense=Math.random();

        if(enemigoActual.Defender()){
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

        if(attack < 0.2){
            int ataqueP = Ataque/2;
            System.out.println("Has realizado un ataque penoso (fallo) \n Daño: " +ataqueP);
            danoIngfligido+=ataqueP;
            enemigoActual.Vida-=ataqueP;
            Estamina++;
        }else if(attack >= 0.2 && attack <= 0.8){
            System.out.println("Has realizado un ataque normal \n Daño: " +Ataque);
            danoIngfligido+=Ataque;
            enemigoActual.Vida-=Ataque;
            Estamina+=5;
        }else if (attack > 0.8){
            double ataqueC = Ataque*1.5;
            System.out.println("¡Has realizado un ataque critico! (Suerte) \n Daño: " +ataqueC);
            danoIngfligido+=ataqueC;
            enemigoActual.Vida-=ataqueC;
            Estamina+=15;
            if(defense > 0.8){
                System.out.println("Pero no te ha servido para nada \n ");
            }
        }
        Ataque = ATAQUE_MAGO;

        return true;
    }

    @Override
    public boolean Defender(){

        return true;
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
    }

    @Override
    public void SuperAtaque(){

        System.out.println("¡Has escogido el SuperAtaque! \n  ");

        Estamina-=75;

        double SuperA = Math.random();

        if(SuperA > 0.85){
            int SuperC = Ataque*5;
            System.out.println("¡¡Y ademas has realizado un ataque ciritico!! \n  ");
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
        return " Mago: " +
                " Vida=" + Vida +
                " || Ataque=" + Ataque +
                " || Estamina=" + Estamina +
                " || Daño total: " +danoIngfligido+"\n";
    }
}
