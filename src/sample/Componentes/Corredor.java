package sample.Componentes;

import javafx.scene.control.ProgressBar;

public class Corredor extends Thread{
    private ProgressBar _pgbPista;

    public Corredor(ProgressBar _pgbPista){
        this._pgbPista = _pgbPista;
    }

    @Override
    public void run() {
        super.run();
        double _avance = 0;
        while(_avance < 1){
            _avance += Math.random()/10;
            this._pgbPista.setProgress(_avance);

            try {
                sleep((long)(Math.random()*2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
