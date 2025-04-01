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

       if (jugadorActual.defenderse){

           double defense=Math.random();

           if(defense < 0.2 ){
               System.out.println("Tu defensa ha resultado fallida \n " );
           }else if(defense >= 0.2 && defense <=0.8){
               Ataque-=Ataque*0.25;
               System.out.println("Defensa Normal \n ");
           }else{
               Ataque=0;
               System.out.println("Has realizado una defensa impecable \n ");
           }
       }

        double attack=Math.random();

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
        return "Duende: " +
                " Vida=" + Vida;
    }

}
