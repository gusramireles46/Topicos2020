package sample.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.productosDAO;

public class frmProducto extends Stage {

    private VBox _vbox;
    private TextField _txfDesc, _txfCosto, _txfPrecio, _txfExistencia;
    private ComboBox _cbxVigente;
    private ComboBox _cbxProveedor;
    private Button _btnGuardar;
    private Scene _escena;
    private productosDAO _objP;

    public frmProducto(){
        CrearGUI();
        this.setTitle("Gestion de Productos :3");
        this.setScene(_escena);
        //this.setMaximized(true);
        this.show();
    }

    private void CrearGUI() {
        _vbox = new VBox();
        _vbox.setSpacing(10);
        _vbox.setAlignment(Pos.CENTER);
        _txfDesc = new TextField();
        _txfDesc.setMaxWidth(150);
        _txfDesc.setPromptText("Descripción");
        _txfCosto = new TextField();
        _txfCosto.setMaxWidth(150);
        _txfCosto.setPromptText("Costo");
        _txfPrecio = new TextField();
        _txfPrecio.setMaxWidth(150);
        _txfPrecio.setPromptText("Precio");
        _txfExistencia = new TextField();
        _txfExistencia.setMaxWidth(150);
        _txfExistencia.setPromptText("Existencia");
        _cbxVigente = new ComboBox();
        _cbxProveedor = new ComboBox();
        _btnGuardar = new Button("Guardar");
        _btnGuardar.setOnAction(event -> guardarDatos());
        _vbox.getChildren().addAll(_txfDesc, _txfCosto, _txfPrecio, _txfExistencia, _cbxVigente, _cbxProveedor, _btnGuardar);

        _escena = new Scene(_vbox, 250, 300);
    }

    private void guardarDatos() {
        _objP = new productosDAO();
        _objP.setNomProducto(_txfDesc.getText());
        _objP.setCosto(Float.parseFloat(_txfCosto.getText()));
        _objP.setPrecio(Float.parseFloat(_txfPrecio.getText()));
        _objP.setExistencia(Integer.parseInt(_txfExistencia.getText()));
        _objP.setVigencia(true);
        _objP.setIdProveedor(2);
        _objP.insProducto();
    }
}
