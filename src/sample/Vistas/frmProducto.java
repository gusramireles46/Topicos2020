package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class frmProducto extends Stage {

    private VBox _vbox;
    private TextField _txfDesc, _txfCosto, _txfPrecio, _txfExistencia;
    private ComboBox _cbxVigente;
    private ComboBox _cbxProveedor;
    private Button _btnGuardar;
    private Scene _escena;

    public frmProducto(){
        CrearGUI();
        this.setTitle("Gestion de Productos :3");
        this.setScene(_escena);
        this.setMaximized(true);
        this.show();
    }

    private void CrearGUI() {
        _vbox = new VBox();
        _txfDesc = new TextField();
        _txfDesc.setPromptText("Descripción");
        _txfCosto = new TextField();
        _txfCosto.setPromptText("Costo");
        _txfPrecio = new TextField();
        _txfPrecio.setPromptText("Precio");
        _txfExistencia = new TextField();
        _txfExistencia.setPromptText("Existencia");
        _cbxVigente = new ComboBox();
        _cbxProveedor = new ComboBox();
        _btnGuardar = new Button("Guardar");
        _vbox.getChildren().addAll(_txfDesc, _txfCosto, _txfPrecio, _txfExistencia, _cbxVigente, _cbxProveedor, _btnGuardar);

        _escena = new Scene(_vbox, 560, 560);
    }
}
