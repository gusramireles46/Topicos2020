package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.productosDAO;
import sample.Vistas.frmProducto;

import java.util.Optional;

public class ButtonCell extends TableCell<productosDAO, String> {
    private Button _btnCelda;
    private productosDAO _objP;

    public ButtonCell(int opc){
        if (opc == 1){
            _btnCelda = new Button("Editar");
            _btnCelda.setOnAction(event ->{
                TableView<productosDAO> _tbvTemp;
                _tbvTemp = ButtonCell.this.getTableView();
                _objP = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                new frmProducto(_tbvTemp, _objP);
                //ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
            });
        } else {
            _btnCelda = new Button("Borrar");
            _btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del sistema");
                alert.setHeaderText("Confirmando acción");
                alert.setContentText("¿Seguro que deseas eliminar el producto?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    _objP = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    _objP.delProducto();

                    //Refrescar tabla
                    ButtonCell.this.getTableView().setItems((_objP).selAllProducts());
                    ButtonCell.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty)
            setGraphic(_btnCelda);
    }
}