package Videojuego.Enemigos;

public class Duende extends Enemigo {

    public Duende() {
        Vida=150;
        Ataque=10;
    }


    @Override
    public boolean Atacar(){

        do{

            System.out.println("El enemigo va a atacar \n ");

            int ataqueBase = Ataque;


            //Sub-metodo para defender:
            if (jugadorActual.defenderse){

                double defense=Math.random();

                if(defense < 0.2 ){
                    System.out.println("Tu defensa ha resultado fallida \n " );
                }else if(defense >= 0.2 && defense <=0.8){
                    Ataque-=Ataque*0.25;
                    System.out.println("Defensa Normal \n ");
                }else{
                    Ataque=0;
                    System.out.println("¡Has realizado una defensa perfecta! \n ");
                }

            }



            double attack=Math.random();


            //Sub-metodo para atacar:
            if(attack < 0.7){
                System.out.println("Ha realizado un ataque normal \n");
                jugadorActual.Vida-=Ataque;
                System.out.println("Daño recibido: "+Ataque);
                danoIngfligido+=Ataque;
            }else{
                System.out.println("El enemigo ha realizado un ataque critico \n");
                int ataqueC= Ataque*2;
                jugadorActual.Vida-=ataqueC;
                System.out.println("Daño recibido: "+ataqueC);
                danoIngfligido+=Ataque;
            }

            Ataque = ataqueBase;

            defenderse=false;

            return true;

        }while (Vida > 0);

    }


    @Override
    public boolean Defender(){

        defenderse=true;

        return true;
    }

    @Override
    public String toString() {
        return " Vida Duende: " + Vida + "\n";
    }

}
