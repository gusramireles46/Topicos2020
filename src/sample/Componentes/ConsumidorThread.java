package sample.Componentes;

import sample.Vistas.RecursoCompartido;

public class ConsumidorThread extends Thread {
    RecursoCompartido _objR;

    public ConsumidorThread(RecursoCompartido _objRec){
        this._objR = _objRec;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 1; i <= 50 ; i++) {
            _objR.vaciarRecurso();
        }
    }
}
