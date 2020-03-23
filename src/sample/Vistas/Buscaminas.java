package sample.Vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Eventos.EventoBuscaminas;

public class Buscaminas extends Stage {
    private Label _lblRow, _lblCol;
    private TextField _txfRow, _txfCol;
    private Button _btnMinar, _btnReiniciar;
    private GridPane _gdpCampo;
    private ToggleButton[][] _tgbCeldas;
    private Scene _escena;
    private HBox _hbox;
    private VBox _vbox;
    private String[][] contador;
    private int nr = 0;
    private int nc = 0;
    private boolean gameOver = true;


    public Alert _objAlert = new Alert(Alert.AlertType.INFORMATION);

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

        _btnReiniciar = new Button();
        _btnReiniciar.setGraphic(new ImageView("sample/Imagenes/actualizar.png"));
        _btnReiniciar.setOnAction(event -> {
            close();
            new Buscaminas();
        });

        _btnMinar.setGraphic(new ImageView("sample/Imagenes/bomba.png"));
        _btnMinar.setOnAction(event -> {
            crearCampo();
            minarCampo();
        });

        //_btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventoBuscaminas(_txfRow, _txfCol, _arrBtnCeldas, _gdpCampo, _vbox));

        _hbox.getChildren().addAll(_lblRow, _txfRow, _lblCol, _txfCol, _btnMinar, _btnReiniciar);
        _gdpCampo = new GridPane();
        _vbox.getChildren().addAll(_hbox, _gdpCampo);
        _vbox.setPadding(new Insets(10));

        _escena = new Scene(_vbox,700,650);
        //_escena.getStylesheets().add("sample/Estilos/Bootstrap3.css");
    }

    private void crearCampo() {
        String _strR = _txfRow.getText();
        String _strC = _txfCol.getText();

        nr = Integer.parseInt(_strR);
        nc = Integer.parseInt(_strC);

        _tgbCeldas = new ToggleButton[nr][nc];
        contador = new String[nr][nc];

        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                _tgbCeldas[i][j] = new ToggleButton();
                _tgbCeldas[i][j].setPrefSize(35,35);
                _tgbCeldas[i][j].setStyle("-fx-base: #CCCCCC;");


                int x = i;
                int y = j;

                _tgbCeldas[i][j].setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY){
                        eventoBuscaminas(x,y);
                    } else if (e.getButton() == MouseButton.SECONDARY){
                        if (e.getClickCount() == 1)
                            isFlagged(x, y);
                        else if (e.getClickCount() == 2) {
                            _tgbCeldas[x][y].setGraphic(null);
                            _tgbCeldas[x][y].setDisable(false);
                        }
                    }
                });

                _gdpCampo.add(_tgbCeldas[i][j], i, j);
                _gdpCampo.setAlignment(Pos.CENTER);
                _gdpCampo.setPadding(new Insets(15));
            }
        }
    }

    private void isFlagged(int x, int y) {
        _tgbCeldas[x][y].setGraphic(new ImageView("sample/Imagenes/banderas16.png"));
    }

    private void descubrirMinas(){
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (contador[i][j].equals("*"))
                    _tgbCeldas[i][j].setText("*");
            }
        }
    }

    private void eventoBuscaminas(int a, int b) {

        if (!gameOver){
            if (_tgbCeldas[a][b].isSelected()){
                if (contador[a][a].equals("*")){
                    descubrirMinas();

                    _objAlert.setTitle("Mensaje del sistema");
                    _objAlert.setHeaderText("Perdiste :'c");
                    _objAlert.setContentText("Oh no, te has topado con una mina :/");
                    _objAlert.showAndWait();
                    _tgbCeldas[a][b].setSelected(false);
                    gameOver = true;
                    close();
                    new Buscaminas();
                } else {
                    _tgbCeldas[a][b].setText(contador[a][b]);
                    _tgbCeldas[a][b].setSelected(true);
                }
            } _tgbCeldas[a][b].setSelected(true);

            try{
                int winner = 0;
                int m = 0;
                if(_tgbCeldas[a][b].isSelected()) {
                    if (
                            contador[a][b].equals("") || contador[a][b].equals("1") ||
                            contador[a][b].equals("2") || contador[a][b].equals("3") ||
                            contador[a][b].equals("4") || contador[a][b].equals("5") ||
                            contador[a][b].equals("6") || contador[a][b].equals("7") ||
                            contador[a][b].equals("8")
                    ){
                        winner++;
                    }
                    if (winner == m) {
                        descubrirMinas();

                        _objAlert.setTitle("Mensaje del sistema");
                        _objAlert.setHeaderText("Ganaste");
                        _objAlert.setContentText("Has detectado todas las minas");
                        _objAlert.showAndWait();
                        _tgbCeldas[a][b].setSelected(false);
                        gameOver = true;
                        close();
                        new Buscaminas();
                    }
                }
            } catch (Exception e){}
        }
    }

    private void minarCampo() {
        gameOver = false;
        double minas = (nr * nc) * (0.25);
        int intMinas = (int) Math.round(minas);

        if (intMinas > 0){
            for (int i = 0; i < intMinas; i++) {
                int a = 0, b = 0;

                a = (int)(Math.random()*16)+1;
                b = (int)(Math.random()*16)+1;

                if (contador[nr][nc].equals(""))
                    contador[nr][nc] = "*";
                else
                    i--;
                
            }
        }
    }
}
