package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.productosDAO;
import sample.Modelos.proveedoresDAO;

public class frmProducto extends Stage {

    private VBox _vbox;
    private TextField _txfDesc, _txfCosto, _txfPrecio, _txfExistencia;
    private ComboBox<String> _cbxVigente;
    private ComboBox<proveedoresDAO> _cbxProveedor;
    private Button _btnGuardar;
    private Scene _escena;
    private productosDAO _objP;
    private TableView<productosDAO> _tbvProductos;

    public frmProducto(TableView<productosDAO> _tbvP, productosDAO _obj){

        if (_obj != null)
            _objP = _obj;
        else
            _objP = new productosDAO();

        this._tbvProductos = _tbvP;
        CrearGUI();
        this.setTitle("Gestion de Productos :3");
        this.setScene(_escena);
        this.show();
    }

    private void CrearGUI() {
        _vbox = new VBox();
        _vbox.setSpacing(10);
        _vbox.setAlignment(Pos.CENTER);
        _txfDesc = new TextField();
        _txfDesc.setMaxWidth(150);
        _txfDesc.setText(_objP.getNomProducto());
        _txfDesc.setPromptText("Descripción");
        _txfCosto = new TextField();
        _txfCosto.setText(_objP.getCosto()+"");
        _txfCosto.setMaxWidth(150);
        _txfCosto.setPromptText("Costo");
        _txfPrecio = new TextField();
        _txfPrecio.setText(_objP.getPrecio()+"");
        _txfPrecio.setMaxWidth(150);
        _txfPrecio.setPromptText("Precio");
        _txfExistencia = new TextField();
        _txfExistencia.setText(_objP.getExistencia()+"");
        _txfExistencia.setMaxWidth(150);
        _txfExistencia.setPromptText("Existencia");

        ObservableList<String> listaV = FXCollections.observableArrayList();
        listaV.addAll("Vigente", "Descontinuado");
        _cbxVigente = new ComboBox();
        String val = _objP.isVigencia() == true ? "Vigente" : "Descontinuado";
        _cbxVigente.setItems(listaV);
        _cbxVigente.setValue(val);

        _cbxProveedor = new ComboBox();
        _cbxProveedor.setItems(new proveedoresDAO().selAllProveedores());
        proveedoresDAO _objPr = new proveedoresDAO();
        _objPr.setIdProveedor(_objP.getIdProveedor());
        _objPr.getProvById();
        _cbxProveedor.setValue(_objPr);

        _btnGuardar = new Button("Guardar");
        _btnGuardar.setOnAction(event -> guardarDatos());
        _vbox.getChildren().addAll(_txfDesc, _txfCosto, _txfPrecio, _txfExistencia, _cbxVigente, _cbxProveedor, _btnGuardar);

        _escena = new Scene(_vbox, 250, 300);
    }

    private void guardarDatos() {
        _objP.setNomProducto(_txfDesc.getText());
        _objP.setCosto(Float.parseFloat(_txfCosto.getText()));
        _objP.setPrecio(Float.parseFloat(_txfPrecio.getText()));
        _objP.setExistencia(Integer.parseInt(_txfExistencia.getText()));

        Boolean _ban = (_cbxVigente.getValue() == "Vigente") ? true : false;
        _objP.setVigencia(_ban);

        //proveedoresDAO objTemp = _cbxProveedor.getItems().get(_cbxProveedor.getSelectionModel().getSelectedIndex());
        proveedoresDAO objTemp = _cbxProveedor.getValue();

        _objP.setIdProveedor(objTemp.getIdProveedor());

        if (_objP.getIdProducto() >= 1)
            _objP.updProducto();
        _objP.insProducto();

        _tbvProductos.setItems(_objP.selAllProducts());
        _tbvProductos.refresh();

        this.close();
    }
}
