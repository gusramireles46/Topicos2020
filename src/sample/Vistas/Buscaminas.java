package sample.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Buscaminas extends Stage {
    private Label _lblRow, _lblCol;
    private TextField _txfRow, _txfCol;
    private Button _btnMinar;
    private Button[][] _arrBtnCeldas;
    private GridPane _gdpCampo;
    private Scene _escena;
    private HBox _hbox;
    private VBox _vbox;

    public Buscaminas(){
        CrearGUI();
        this.setTitle("Buscaminas >:)");
        this.setScene(_escena);
        this.show();
    }

    private void CrearGUI() {
        _vbox = new VBox();
        _hbox = new HBox();

        _hbox.setSpacing(10);
        _hbox.setAlignment(Pos.CENTER);

        _lblRow = new Label("No. Renglones");
        _lblCol = new Label("No. Columnas");
        _txfRow = new TextField();
        _txfRow.setMaxWidth(50);
        _txfCol = new TextField();
        _txfCol.setMaxWidth(50);
        _btnMinar = new Button("Minar Campo");

        _hbox.getChildren().addAll(_lblRow, _txfRow, _lblCol, _txfCol, _btnMinar);
        _vbox.getChildren().addAll(_hbox);

        _escena = new Scene(_vbox,600,500);
        _escena.getStylesheets().add("sample/Estilos/Bootstrap3.css");
    }
}
