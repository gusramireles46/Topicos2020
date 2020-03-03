package sample.Vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Eventos.EventoTaquimecanografo;
import sample.Eventos.EventoVentanaTaqui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Taquimecanografo extends Stage {
    private Scene _escena;
    private ToolBar _tlbMenu;
    private Button _btnAbrir;
    private FileChooser _flcArchivo;
    private File _objFile;
    private TextArea _txaTexto, _txaEscritura;
    private Button[] _btnTeclas1, _btnTeclas2, _btnTeclas3, _btnTeclas4, _btnTeclas5, _btnTeclas6;
    private VBox _vPrincipal, _vTeclado;
    private HBox _hTeclas1, _hTeclas2, _hTeclas3, _hTeclas4, _hTeclas5, _hTeclas6;
    private String[] _sTeclas1 = {"esc","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","imp pnt", "supr"};
    private String[] _sTeclas2 = {"|","1","2","3","4","5","6","7","8","9","0","'","¿","<-"};
    private String[] _sTeclas3 = {"↹","Q","W","E","R","T","Y","U","I","O","P","´","+","}"};
    private String[] _sTeclas4 = {"bloq mayus","A","S","D","F","G","H","J","K","L","Ñ","{","↲"};
    private String[] _sTeclas5 = {"⇧","Z","X","C","V","B","N","M",",",".","-","↑","⇧"};
    private String[] _sTeclas6 = {"ctrl","fn","⊞","alt","_","alt gr","<","←","↓","→"};


    public Taquimecanografo(){
        CrearGUI();

        this.setMaximized(true);
        this.setTitle("Taquimecanógrafo :v");
        this.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventoVentanaTaqui());
        this.setScene(_escena);
        this.show();
    }

    private void CrearGUI() {
        _tlbMenu = new ToolBar();
        _btnAbrir = new Button();
        _btnAbrir.setGraphic(new ImageView("sample/Imagenes/open.png"));
        _btnAbrir.setOnAction(event -> AbrirExplorador());
        _tlbMenu.getItems().addAll(_btnAbrir);

        _txaTexto = new TextArea();
        _txaTexto.setEditable(false);
        _txaTexto.setPrefRowCount(5);
        _txaTexto.setPadding(new Insets(10));

        _txaEscritura = new TextArea();
        _txaEscritura.setPrefRowCount(5);
        _txaEscritura.setPadding(new Insets(10));


        _hTeclas1 = new HBox();
        _hTeclas2 = new HBox();
        _hTeclas3 = new HBox();
        _hTeclas4 = new HBox();
        _hTeclas5 = new HBox();
        _hTeclas6 = new HBox();

        _btnTeclas1 = new Button[15];
        for (int i = 0; i < 15; i++) {
            _btnTeclas1[i] = new Button(_sTeclas1[i]);
            _hTeclas1.getChildren().addAll(_btnTeclas1[i]);
            _btnTeclas1[i].setPrefWidth(100);
            _btnTeclas1[i].setId("button1");
        }

        _btnTeclas2 = new Button[14];
        for (int i = 0; i < 14; i++) {
            _btnTeclas2[i] = new Button(_sTeclas2[i]);
            _hTeclas2.getChildren().addAll(_btnTeclas2[i]);
            _btnTeclas2[i].setPrefWidth(100);
            _btnTeclas2[i].setPrefHeight(50);
            _btnTeclas2[i].setId("button2");
        }
        _btnTeclas2[13].setPrefWidth(150);

        _btnTeclas3 = new Button[14];
        for (int i = 0; i < 14; i++) {
            _btnTeclas3[i] = new Button(_sTeclas3[i]);
            _hTeclas3.getChildren().addAll(_btnTeclas3[i]);
            _btnTeclas3[i].setPrefWidth(100);
            _btnTeclas3[i].setPrefHeight(50);
            _btnTeclas3[i].setId("button3");
        }
        _btnTeclas3[0].setPrefWidth(120);
        _btnTeclas3[13].setPrefWidth(110);


        _btnTeclas4 = new Button[13];
        for (int i = 0; i < 13; i++) {
            _btnTeclas4[i] = new Button(_sTeclas4[i]);
            _hTeclas4.getChildren().addAll(_btnTeclas4[i]);
            _btnTeclas4[i].setPrefWidth(100);
            _btnTeclas4[i].setPrefHeight(50);
            _btnTeclas4[i].setId("button4");
        }
        _btnTeclas4[0].setPrefWidth(135);
        _btnTeclas4[12].setPrefWidth(160);

        _btnTeclas5 = new Button[13];
        for (int i = 0; i < 13; i++) {
            _btnTeclas5[i] = new Button(_sTeclas5[i]);
            _hTeclas5.getChildren().addAll(_btnTeclas5[i]);
            _btnTeclas5[i].setPrefWidth(100);
            _btnTeclas5[i].setPrefHeight(50);
            _btnTeclas5[i].setId("button5");
        }
        _btnTeclas5[0].setPrefWidth(190);

        _btnTeclas6 = new Button[10];
        for (int i = 0; i < 10; i++) {
            _btnTeclas6[i] = new Button(_sTeclas6[i]);
            _hTeclas6.getChildren().addAll(_btnTeclas6[i]);
            _btnTeclas6[i].setPrefWidth(100);
            _btnTeclas6[i].setPrefHeight(50);
            _btnTeclas6[i].setId("button6");
        }
        _btnTeclas6[4].setPrefWidth(500);

        _hTeclas1.setSpacing(5);
        _hTeclas2.setSpacing(5);
        _hTeclas3.setSpacing(5);
        _hTeclas4.setSpacing(5);
        _hTeclas5.setSpacing(5);
        _hTeclas6.setSpacing(5);
        _hTeclas1.setPadding(new Insets(5));
        _hTeclas2.setPadding(new Insets(5));
        _hTeclas3.setPadding(new Insets(5));
        _hTeclas4.setPadding(new Insets(5));
        _hTeclas5.setPadding(new Insets(5));
        _hTeclas6.setPadding(new Insets(5));

        EventoTaquimecanografo objEvento = new EventoTaquimecanografo(_btnTeclas1, _btnTeclas2, _btnTeclas3, _btnTeclas4, _btnTeclas5, _btnTeclas6);

        _txaEscritura.setOnKeyPressed(objEvento);
        _txaEscritura.setOnKeyReleased(objEvento);

        _vTeclado = new VBox();
        _vTeclado.getChildren().addAll(_hTeclas1, _hTeclas2, _hTeclas3, _hTeclas4, _hTeclas5, _hTeclas6);
        _vTeclado.setPadding(new Insets(10));
        _vTeclado.setId("teclado");

        _vPrincipal = new VBox();
        _vPrincipal.getChildren().addAll(_tlbMenu, _txaTexto, _txaEscritura, _vTeclado);
        _vPrincipal.setPadding(new Insets(5));

        _escena = new Scene(_vPrincipal, 1280, 650);
        _escena.getStylesheets().add("sample/Estilos/estilos_taquimecanografo.css");
    }

    private void AbrirExplorador() {
        _flcArchivo = new FileChooser();
        _flcArchivo.setTitle("Seleccione el archivo :3");
        _objFile = _flcArchivo.showOpenDialog(this);

        if (_objFile != null){
            FileReader _frLectura = null;
            BufferedReader _brLeer = null;
            String _sTexto = "";

            try {
                _frLectura = new FileReader(_objFile);
                _brLeer = new BufferedReader(_frLectura);
                String _sTextoLinea = _brLeer.readLine();

                while (_sTextoLinea != null){
                    _sTexto = _sTexto + _sTextoLinea + "\n";
                    _sTextoLinea = _brLeer.readLine();
                }

            } catch (Exception e){

                _txaTexto.appendText(e.toString());

            } finally {

                try {

                    _frLectura.close();

                } catch (Exception e2) {

                    _txaTexto.appendText(e2.toString());

                }
            }
            _txaTexto.appendText(_sTexto);
        }
    }
}