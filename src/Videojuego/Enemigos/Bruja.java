package Videojuego.Enemigos;

public class Bruja extends Enemigo {


    public Bruja() {
        Vida=125;
        Ataque=15;
    }


    @Override
    public boolean Atacar(){

        System.out.println("El enemigo va a atacar \n ");

        int ataqueBase = Ataque;

        if(jugadorActual.defenderse){
            double defense;

            defense=Math.random();

            if(defense < 0.2 ){
                System.out.println("Tu defensa ha resultado fallida \n " );
            }else if(defense >= 0.2 && defense <=0.8){
                Ataque-=Ataque*0.25;
                System.out.println("Defensa Normal \n ");
            }else{
                Ataque=0;
                System.out.println("¡Has realizado una defensa Perfecta! \n ");
            }
        }

        double attack;

        attack=Math.random();

        if(attack < 0.7){
            System.out.println("Ha realizado un ataque normal \n");
            jugadorActual.Vida-=Ataque;
            System.out.println("Daño recibido: "+Ataque);
            danoIngfligido+=Ataque;
        }else{
            System.out.println("El enemigo ha realizado un ataque critico \n");
            int ataqueC=Ataque*2;
            jugadorActual.Vida-=ataqueC;
            System.out.println("Daño recibido: "+ataqueC);
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
        return " Vida Bruja:" + Vida+"\n";
    }


}
