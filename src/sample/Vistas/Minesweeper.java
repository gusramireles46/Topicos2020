package sample.Vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;

public class Minesweeper extends Stage {
    static int _porcentajeBombas = 25;
    static Campo[][] _cmpPrincipal;
    private VBox _vbox;
    private HBox _hbox;
    private Button _btnMinar;
    private Scene _escena;
    static int _noMinas, _minasEncontradas;
    private int _tiempoTranscurrido = 0;
    private Timer time;
    private Label _lblR, _lblC;
    private TextField _txfR, _txfC;
    private String _strR, _strC;
    static int nr = 0, nc = 0;

    public Minesweeper(){
        CrearGUI();
        setTitle("Buscaminas :v");
        setScene(_escena);
        this.show();
    }

    private void CrearGUI() {
        _lblR = new Label("No. Renglones");
        _txfR = new TextField();
        _txfR.setMaxWidth(50);
        _lblC = new Label("No. Columnas");
        _txfC = new TextField();
        _txfC.setMaxWidth(50);
        _btnMinar = new Button("Minar Campo");
        _btnMinar.setOnMouseClicked(e -> EventoMinesweeper());

        _hbox = new HBox();
        _hbox.setAlignment(Pos.CENTER);
        _hbox.setSpacing(10);
        _hbox.getChildren().addAll(_lblR, _txfR, _lblC, _txfC, _btnMinar);
        _vbox = new VBox();
        _vbox.getChildren().addAll(_hbox);
        _vbox.setPadding(new Insets(10));

        _escena = new Scene(_vbox, 480, 480);
    }

    private void EventoMinesweeper() {
        _strR = _txfR.getText();
        _strC = _txfC.getText();
        nr = Integer.parseInt(_strR);
        nc = Integer.parseInt(_strC);

        _cmpPrincipal = new Campo[nr][nc];
    }
}
