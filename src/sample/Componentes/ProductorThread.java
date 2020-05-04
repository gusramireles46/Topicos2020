package sample.Componentes;

import sample.Vistas.RecursoCompartido;

public class ProductorThread extends Thread {

    RecursoCompartido _objR;

    public ProductorThread(RecursoCompartido _objRec){
        this._objR = _objRec;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 1; i <= 50 ; i++) {
            _objR.llenarRecurso(i);
        }
    }
}
