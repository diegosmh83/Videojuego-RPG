package Videojuego.Enemigos;

public class Bruja extends Enemigo {


    public Bruja() {
        Vida=125;
        Ataque=15;
    }


    @Override
    public boolean Atacar(){

        System.out.println("El enemigo ha atacado \n ");

        int ataqueBase = Ataque;

        if(jugadorActual.defenderse){
            double defense;

            defense=Math.random();

            if(defense < 0.2 ){
                System.out.println("Defensa fallida \n " );
            }else if(defense >= 0.2 && defense <=0.8){
                Ataque-=Ataque*0.25;
                System.out.println("Defensa Normal \n ");
            }else{
                Ataque=0;
                System.out.println("¡Defensa Perfecta! \n ");
            }
        }

        double attack;

        attack=Math.random();

        if(attack < 0.7){
            jugadorActual.Vida-=Ataque;
            System.out.println("Daño: "+Ataque);
            danoIngfligido+=Ataque;
        }else{
            jugadorActual.Vida-=Ataque*1.5;
            System.out.println("Daño: "+Ataque);
            danoIngfligido+=Ataque;
        }
        Ataque = ataqueBase;

        defenderse=false;

        return true;
    }



    @Override
    public boolean Defender(){

        defenderse=true;

        return true;
    }

    @Override
    public String toString() {
        return "Bruja: " +
                " Vida=" + Vida;
    }


}
