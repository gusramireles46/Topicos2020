package sample.Eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Random;

public class EventoBuscaminas implements EventHandler {
    private TextField _txfRows, _txfCols;
    private Button[][] _arrBtnCeldas;
    private GridPane _gdpCampo;
    private VBox _vbox;

    public EventoBuscaminas(TextField _txfR, TextField _txfC, Button[][] _btnCelda, GridPane _gdpC, VBox _vb){
        _txfRows = _txfR;
        _txfCols = _txfC;
        _arrBtnCeldas = _btnCelda;
        _gdpCampo = _gdpC;
        _vbox = _vb;
    }

    @Override
    public void handle(Event event) {
        int nr = Integer.parseInt(_txfRows.getText());
        int nc = Integer.parseInt(_txfCols.getText());

        if( _arrBtnCeldas != null )
            _vbox.getChildren().remove(_gdpCampo);


        _arrBtnCeldas = new Button[nr][nc];
        _gdpCampo = new GridPane();
        _gdpCampo.setPadding(new Insets(15));

        //double[][] minas = new double[nr][nc];
        //int[][] minasT = new int[nr][nc];

        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc ; j++) {
                //minas[i][j] = Math.random();
                //minasT[i][j] = (int)Math.round(minas[i][j]);
                _arrBtnCeldas[i][j] = new Button();
                //_arrBtnCeldas[i][j].setText(Integer.toString(minasT[i][j]));
                _arrBtnCeldas[i][j].setPrefSize(50,50);
                _arrBtnCeldas[i][j].setStyle("-fx-base: #89AEEB; -fx-background-radius: 5;");
                _gdpCampo.add(_arrBtnCeldas[i][j],j,i);
                //_arrBtnCeldas[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new descubrirBoton());
            }
        }
        _vbox.getChildren().add(_gdpCampo);
        _gdpCampo.setAlignment(Pos.CENTER);
    }
}