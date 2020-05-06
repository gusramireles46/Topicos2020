package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Buscaminas1 extends Stage {

    private Label lblMinas;
    private TextField txtNoMinas;
    private Button btnGenerar,btnReiniciar;
    private VBox vBox;
    private HBox hBox;
    private GridPane grpCampo;
    private Scene escena;
    private ToggleButton[][] arCeldas = new ToggleButton[18][18];
    private String[][] numeros  = new String[18][18];

    private boolean endgame;

    public Alert objAlert = new Alert(Alert.AlertType.WARNING);

    public Buscaminas1(){
        CrearGUI();

        this.setTitle("Buscaminas :V");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI(){
        endgame = true;
        vBox = new VBox();
        hBox = new HBox();
        lblMinas = new Label("No. Minas");
        txtNoMinas = new TextField();
        btnGenerar = new Button("GENERAR");
        btnGenerar.setOnAction(event ->{
            crearTabla();
            AgregarBombas();
        });
        btnReiniciar = new Button("",new ImageView("/Images/facesmile.png"));
        btnReiniciar.setOnAction(event -> {
            close();
            new Buscaminas1();
        });
        hBox.getChildren().addAll(lblMinas,txtNoMinas,btnGenerar,btnReiniciar);

        grpCampo = new GridPane();

        vBox.getChildren().addAll(hBox,grpCampo);
        escena = new Scene(vBox,490,520);
    }

    private void crearTabla(){

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                arCeldas[i][j] = new ToggleButton("");
                arCeldas[i][j].setPrefSize(30,30);
                arCeldas[i][j].setStyle("-fx-base: #FFFF");
                arCeldas[0][j].setVisible(false);
                arCeldas[i][0].setVisible(false);
                try { arCeldas[17][j].setVisible(false); } catch (Exception e) {  }
                try{ arCeldas[i][17].setVisible(false); }catch (Exception e) {  }
                int finalI = i;
                int finalJ = j;
                arCeldas[i][j].setOnMouseClicked((event -> {
                    if(event.getButton() == MouseButton.SECONDARY){
                        if(event.getClickCount() ==1){
                            colocarbandera(finalI, finalJ);

                        }else if(event.getClickCount()==2){
                            arCeldas[finalI][finalJ].setGraphic(null);

                            this.arCeldas[finalI][finalJ].setDisable(false);
                        }
                    }else if(event.getButton() == MouseButton.PRIMARY) {
                        EventoMinas(finalI, finalJ);
                    }
                }));

                grpCampo.add(arCeldas[i][j], i, j);
            }
        }
    }

    private void colocarbandera(int i, int j) {
        try{ arCeldas[i][j].setGraphic(new ImageView("/Images/Banderita.png")); }catch (Exception e){ }
    }

    private void mostrarbombas() {
        for (int i = 0; i < 18 ; i++) {
            for (int j = 0; j < 18 ; j++) {
                if(numeros[i][j].equals("B")){
                    arCeldas[i][j].setText("B");
                }else{
                }
            }

        }
    }

    private void EventoMinas(int i, int j) {

        if(!endgame) {
            if (arCeldas[i][j].isSelected()) {
                if (numeros[i][j].equals("B")) {

                    mostrarbombas();

                    objAlert .setTitle("JUEGO TERMINADO");
                    objAlert.setHeaderText("MINAS");
                    objAlert.setContentText("A PERDIDO SIGA INTENTANDOLO");
                    objAlert.showAndWait();
                    arCeldas[i][j].setSelected(false);
                    endgame = true;
                    close();
                    new Buscaminas1();
                } else {

                    arCeldas[i][j].setText(numeros[i][j]);
                    arCeldas[i][j].setSelected(true);

                }
            } arCeldas[i][j].setSelected(true);
            try{
                int ganador=0;
                int m=0;

                for(i=0;i<18;i++){
                    for(j=0;j<18;j++){
                        int n=Integer.parseInt(txtNoMinas.getText());
                        m=256 - n;
                        if(arCeldas[i][j].isSelected()){
                            if(numeros[i][j].equals("")||numeros[i][j].equals("1")||
                                    numeros[i][j].equals("2")||numeros[i][j].equals("3")||
                                    numeros[i][j].equals("4")|| numeros[i][j].equals("5")||
                                    numeros[i][j].equals("6")||numeros[i][j].equals("7")||
                                    numeros[i][j].equals("8")){
                                ganador++;

                            }
                            if(ganador ==m){
                                mostrarbombas();

                                objAlert .setTitle("JUEGO TERMINADO");
                                objAlert.setHeaderText("GANADOR");
                                objAlert.setContentText("HAS GANADO CAMPEON");
                                objAlert.showAndWait();
                                arCeldas[i][j].setSelected(false);
                                endgame = true;
                                close();
                                new Buscaminas1();
                            }
                        }

                    }
                }
            }catch (Exception e){

            }

        }else{
            objAlert .setTitle("Error");
            objAlert.setHeaderText("ERROR EN SU SINTAXIS");
            objAlert.setContentText("Ingresa un valor valido de 1 a 255 bombas");
            objAlert.showAndWait();
            txtNoMinas.clear();
            Limpiar();
        }
    }

    public void AgregarBombas() {

        endgame = false;
        int minas;
        if(esNumero(txtNoMinas.getText())){
            minas = Integer.parseInt(txtNoMinas.getText());
            txtNoMinas.setVisible(false);
            Limpiar();
            if(minas > 0 && minas < 256) {
                for (int i = 0; i < minas; i++) {

                    int a, b;

                    a = (int) (Math.random() * 16)+1;
                    b = (int) (Math.random() * 16)+1;
                    if (numeros[a][b].equals("")) {
                        numeros[a][b] = ("B");
                        // arCeldas[a][b].setText("B");
                    } else {
                        i--;
                    }
                }
                txtNoMinas.setVisible(false);
                lblMinas.setVisible(false);
                btnGenerar.setVisible(false);
                contarMinas();

            }else{
                objAlert .setTitle("ERROR");
                objAlert.setHeaderText("VALOR INVALIDO");
                objAlert.setContentText("INGRESE UN VALOR DE 1 A 255");
                objAlert.showAndWait();
                txtNoMinas.clear();
            }
        }
        else{
            objAlert .setTitle("ERROR");
            objAlert.setHeaderText("VALOR INVALIDO");
            objAlert.setContentText("INGRESE UN NUMERO");
            objAlert.showAndWait();
            close();
            new Buscaminas1();
        }

    }

    public void Limpiar(){
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                lblMinas.setVisible(true);
                txtNoMinas.setVisible(true);
                btnGenerar.setVisible(true);
                numeros[i][j] = "";
                arCeldas[i][j].setText("");
                arCeldas[i][j].setSelected(false);
            }
        }
    }

    public boolean esNumero(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public void contarMinas(){
        for (int a = 0; a < 18 ; a++) {
            for (int b = 0; b < 18 ; b++) {
                //  try{
                int comp=0;
                if(!(numeros[a][b].equals("B"))){
                    if(b !=0 && a !=0 && b != 17 && a != 17){
                        if(numeros[a+1][b+1].equals("B")){
                            comp++;
                        }
                        if(numeros[a][b+1].equals("B")){
                            comp++;
                        }
                        if (numeros[a+1][b].equals("B")){
                            comp++;
                        }
                        if(numeros[a-1][b+1].equals("B")){
                            comp++;
                        }
                        if(numeros[a][b-1].equals("B")){
                            comp++;
                        }
                        if(numeros[a-1][b-1].equals("B")){
                            comp++;
                        }
                        if(numeros[a+1][b-1].equals("B")){
                            comp++;
                        }
                        if (numeros[a-1][b].equals("B")){
                            comp++;
                        }

                        if(comp!=0){
                            numeros[a][b] = (Integer.toString(comp));
                            // arCeldas[a][b].setText(Integer.toString(comp));

                        }
                    }
                }

            }
        }
    }

}