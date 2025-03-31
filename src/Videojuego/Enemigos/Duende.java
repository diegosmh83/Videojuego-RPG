package Videojuego.Enemigos;

public class Duende extends Enemigo {


    public Duende() {
        Vida=150;
        Ataque=10;
    }



    @Override
    public boolean Atacar(){

        System.out.println("El enemigo ha atacado \n ");

        int ataqueBase = Ataque;

       if(jugadorActual.Defender()){
           double defense;

           defense=Math.random();

           if(defense < 0.2 ){
               System.out.println("Defensa fallida \n " );
           }else if(defense >= 0.2 && defense <=0.8){
               Ataque-=Ataque*0.25;
               System.out.println("Defensa Normal \n ");
           }else{
               Ataque=0;
               System.out.println("Defensa impecable \n ");
           }
       }

        double attack;

        attack=Math.random();

        if(attack < 0.7){
            jugadorActual.Vida-=Ataque;
            System.out.println("Daño: "+Ataque);
            danoIngfligido+=Ataque;
        }else{
            jugadorActual.Vida-=Ataque*1.75;
            System.out.println("Daño: "+Ataque);
            danoIngfligido+=Ataque;
        }
        Ataque = ataqueBase;

        return true;
    }

    @Override
    public boolean Defender(){

        return true;
    }

    @Override
    public String toString() {
        return "Estadisticas Mago: " +
                " Vida=" + Vida +
                " Ataque=" + Ataque+
                " Daño total: " +danoIngfligido+" \n";
    }

}
