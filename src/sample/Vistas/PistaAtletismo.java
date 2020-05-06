package sample.Vistas;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Componentes.Corredor;

public class PistaAtletismo extends Stage implements EventHandler {

    private Scene _escena;
    private VBox _vbox;

    private Label[] _lblCorredores = new Label[5];
    private String[] _strCorredores = {"El Favela", "El prisas", "Sonic", "Rubensin", "Hulk"};
    private HBox[] _hbox = new HBox[5];

    private ProgressBar[] _pgbCarriles = new ProgressBar[5];
    private Corredor[] _crdCorredores = new Corredor[5];
    private Button _btnInicio;

    public PistaAtletismo(){
        CrearGUI();
        this.setTitle("Pista de Atletismo >w<");
        this.setScene(_escena);
        this.show();
    }

    private void CrearGUI() {
        _vbox = new VBox();

        for (int i = 0; i < _pgbCarriles.length; i++) {
            _hbox[i] = new HBox();
            _lblCorredores[i] = new Label(_strCorredores[i]);
            _pgbCarriles[i] = new ProgressBar(0);
            _hbox[i].getChildren().addAll(_lblCorredores[i], _pgbCarriles[i]);
            _crdCorredores[i] = new Corredor(_pgbCarriles[i]);
            _vbox.getChildren().add(_hbox[i]);
        }
        _btnInicio = new Button("Inicio");
        _btnInicio.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
        _vbox.getChildren().add(_btnInicio);
        _escena = new Scene(_vbox, 200, 300);
    }


    @Override
    public void handle(Event event) {
        for (int i = 0; i < _pgbCarriles.length; i++) {
            _crdCorredores[i].start();
        }
    }
}
