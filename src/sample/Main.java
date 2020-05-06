package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Componentes.ConsumidorThread;
import sample.Componentes.ProductorThread;
import sample.Modelos.Conexion;
import sample.Vistas.*;

public class Main extends Application {
    private VBox _vbox;
    private HBox _hbox;
    private MenuBar _mnbPrincipal;
    private Menu _mnCompetencia1, _mnCompetencia2, _mnSalida;
    private MenuItem _mitPractica1, _mitPractica2, _mitBye, _mitPractica3, _mitPractica4;
    private BorderPane _bdpPrincipal;
    private Scene _escenaPrincipal;

    @Override
    public void start(Stage primaryStage) throws Exception{
        _bdpPrincipal = new BorderPane();
        _mnbPrincipal = new MenuBar();
        _bdpPrincipal.setTop(_mnbPrincipal);

        _mnCompetencia1 = new Menu("Competencia 1");
        _mnCompetencia2 = new Menu("Competencia 2");
        _mnSalida = new Menu("Salir");

        _mitPractica2 = new MenuItem("Taquimecanógrafo");
        _mitPractica1 = new MenuItem("Buscaminas");
        _mitPractica1.setOnAction(event -> EventoMenu(1));
        _mitPractica2.setOnAction(event -> EventoMenu(2));

        _mnCompetencia1.getItems().addAll(_mitPractica1, _mitPractica2);

        _mitPractica3 = new MenuItem("CRUD Productos");
        _mitPractica3.setOnAction(event -> EventoMenu(3));
        _mitPractica4 = new MenuItem("Pista de Atletismo");
        _mitPractica4.setOnAction(event -> EventoMenu(4));

        _mnCompetencia2.getItems().addAll(_mitPractica3, _mitPractica4);
        _mitBye = new MenuItem("Bye T-T");
        _mitBye.setOnAction(event -> EventoMenu(99));
        _mnSalida.getItems().add(_mitBye);

        _mnbPrincipal.getMenus().addAll(_mnCompetencia1, _mnCompetencia2, _mnSalida);

        _escenaPrincipal = new Scene(_bdpPrincipal, 1080, 640);
        _escenaPrincipal.getStylesheets().add("sample/Estilos/estilos_principal.css");


        Conexion.crearConexion();
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Tópicos Avanzados de Programación");
        primaryStage.setScene(_escenaPrincipal);
        primaryStage.show();

        RecursoCompartido _objR = new RecursoCompartido();
        new ProductorThread(_objR).start();
        new ConsumidorThread(_objR).start();
    }

    private void EventoMenu(int i) {
        switch (i){
            case 1:
                new Buscaminas();
                break;
            case 2:
                new Taquimecanografo();
                break;
            case 3:
                new CRUDProductos();
                break;
            case 4: new PistaAtletismo();
                break;
            case 99:
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
