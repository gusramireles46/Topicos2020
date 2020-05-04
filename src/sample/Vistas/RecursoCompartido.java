package sample.Vistas;

public class RecursoCompartido {

    public int[] _arreglo = new int[5];
    public int _tope = 0;
    public boolean detenerHilo = false;

    //Método llamado por el hilo productor
    public synchronized void llenarRecurso(int valor){
        try {
            while (detenerHilo){
                System.out.println("Se detuvo el hilo productor");
                wait();
                System.out.println("Se reanudó el hilo productor");
            }
            System.out.println("Recurso ["+_tope+"] = "+valor);
            _arreglo[_tope] = valor;
            _tope++;

            if (_tope == _arreglo.length){
                detenerHilo = true;
                _tope--;
                notifyAll();
            }

        } catch(Exception e){e.printStackTrace();}
    }

    //LLamado por el hilo consumidor
    public synchronized void vaciarRecurso(){
        try {
            while(!detenerHilo){
                System.out.println("Se detuvo el hilo consumidor");
                wait();
                System.out.println("Se reanudó el hilo consumidor");
            }

            System.out.println("Valor = Recurso ["+_tope+"] : "+_arreglo[_tope]);
            _tope--;
            if (_tope < 0){
                _tope++;
                detenerHilo = false;
                notify();
            }
        }catch (Exception e){e.printStackTrace();}

    }
}
