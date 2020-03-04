package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class frmProducto extends Stage {

    private VBox _vbox;
    private TextField _txfDesc, _txfCosto, _txfPrecio, _txfExistemcia;
    private ComboBox _cbxVigente;
    private ComboBox _cbxProveedor;
    private Button _btnGuardar;
    private Scene _escena;

    public frmProducto(){
        CrearGUI();
        this.setTitle("Gestion de Productos :3");
        this.setScene(_escena);
        this.show();
    }

    private void CrearGUI() {
    }
}
