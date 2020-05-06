package sample.Componentes;

public class Hilo extends Thread{

    @Override
    public void run() {
        System.out.println("Inicia corredor: "+getName());
        for (int i = 0; i < 5; i++) {
            try {
                sleep((long)(Math.random()*5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Corredor: "+getName()+" termino el km: "+i);
        }
        System.out.println("Corredor "+getName()+" llego a la meta");
    }
}

//Sonic - Hilo1
//Rubencin - Hilo2
//Hulk - Hilo3
//El prisas - Hilo4
//El Favela - Hilo5
