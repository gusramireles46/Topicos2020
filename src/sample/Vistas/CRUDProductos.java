package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCell;
import sample.Modelos.productosDAO;

public class CRUDProductos extends Stage {
    private Scene _escena;
    private VBox _vbox;
    private TableView<productosDAO> _tbvProductos;
    private Button _btnAgregar;
    private productosDAO _objP;

    public CRUDProductos(){
        _objP = new productosDAO();
        CrearGUI();
        this.setTitle("CRUD Productos");
        this.setScene(_escena);
        this.show();
    }

    private void CrearGUI() {
        _vbox = new VBox();
        _tbvProductos = new TableView<>();
        CrearTabla();
        _btnAgregar = new Button("Agregar Producto");
        _vbox.getChildren().addAll(_tbvProductos, _btnAgregar);
        _btnAgregar.setOnAction(event -> AgregarProducto(1));
        _escena = new Scene(_vbox, 840, 450);
    }

    private void CrearTabla() {
        TableColumn<productosDAO, Integer> tbcIdProducto = new TableColumn<>("ID");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        TableColumn<productosDAO, String> tbcNomProducto = new TableColumn<>("DESCRIPCIÓN");
        tbcNomProducto.setCellValueFactory(new PropertyValueFactory<>("nomProducto"));

        TableColumn<productosDAO, Float> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<productosDAO, Float> tbcCosto = new TableColumn<>("COSTO");
        tbcCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        TableColumn<productosDAO, Integer> tbcExistencia = new TableColumn<>("EXISTENCIA");
        tbcExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));

        TableColumn<productosDAO, Boolean> tbcVigente = new TableColumn<>("VIGENTE");
        tbcVigente.setCellValueFactory(new PropertyValueFactory<>("vigencia"));

        TableColumn<productosDAO, Integer> tbcIdProveedor = new TableColumn<>("PROVEEDOR");
        tbcIdProveedor.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));

        TableColumn<productosDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<productosDAO, String>, TableCell<productosDAO, String>>() {
            @Override
            public TableCell<productosDAO, String> call(TableColumn<productosDAO, String> param) {
                return new ButtonCell(2);
            }
        });

        TableColumn<productosDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<productosDAO, String>, TableCell<productosDAO, String>>() {
            @Override
            public TableCell<productosDAO, String> call(TableColumn<productosDAO, String> param) {
                return new ButtonCell(1);
            }
        });

        _tbvProductos.getColumns().addAll(tbcIdProducto, tbcNomProducto, tbcCosto, tbcPrecio, tbcExistencia, tbcVigente, tbcIdProveedor, tbcEditar, tbcBorrar);
        _tbvProductos.setItems(_objP.selAllProducts());

    }

    private void AgregarProducto(int opc) {
        switch (opc){
            case 1:
                new frmProducto();
                break;
        }
    }


}
