package sample.Vistas;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Campo extends StackPane {
    private Button _btnCelda;
    private int x, y = 0;
    private int numMinas;
    private boolean _isMined;
    private boolean _tieneBandera;
    private Color _colorCelda;
    private ArrayList<Campo> _vecinos = new ArrayList<>();
    private boolean _isActive = true;

    public Campo(int _x, int _y, boolean _mined){
        this.x = _x;
        this.y = _y;
        this._isMined = _mined;

        if (_isMined)
            Minesweeper._noMinas++;

        _btnCelda.setPrefSize(40,40);
        _btnCelda.setOnMouseClicked(e -> EventoMouse(e));
        getChildren().addAll(_btnCelda);

        setTranslateX(x);
        setTranslateY(y);
    }

    private Parent crearContenido(){
        Minesweeper._noMinas = 0;
        Minesweeper._minasEncontradas = 0;

        Pane _pnRoot = new Pane();
        _pnRoot.setPrefSize(Minesweeper.nr * 40, Minesweeper.nc * 40);

        for (int i = 0; i < Minesweeper.nr; i++) {
            for (int j = 0; j < Minesweeper.nc; j++) {
                Campo _cmpCeldas = new Campo(x, y, Math.random() < (double) Minesweeper._porcentajeBombas / 100);
                Minesweeper._cmpPrincipal[x][y] = _cmpCeldas;

                _pnRoot.getChildren().addAll(_cmpCeldas);
            }
            
        }

        for (int i = 0; i < Minesweeper.nr; i++) {
            for (int j = 0; j < Minesweeper.nc; j++) {
                int numBombasVecinas = 0;
                ArrayList<Campo> vecinos = new ArrayList<>();

                int[] locacionVecinos = new int[]{-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1};

                for (int k = 0; k < locacionVecinos.length; k++) {
                    int vx = locacionVecinos[k];
                    int vy = locacionVecinos[++k];

                    int nuevaX = i + vx;
                    int nuevaY = j + vy;

                    if (nuevaX >= 0 && nuevaX < Minesweeper.nr && nuevaY >= 0 && nuevaY < Minesweeper.nc){
                        vecinos.add(Minesweeper._cmpPrincipal[nuevaX][nuevaY]);
                        if (Minesweeper._cmpPrincipal[nuevaX][nuevaY]._isMined)
                            numBombasVecinas++;
                    }
                }

                Minesweeper._cmpPrincipal[i][j].numMinas = numBombasVecinas;
                Minesweeper._cmpPrincipal[i][j]._vecinos = vecinos;

                Color[] colors = {
                        null, Color.BLUE, Color.GREEN, Color.RED, Color.DARKBLUE, Color.DARKRED, Color.CYAN, Color.BLACK, Color.DARKGRAY
                };

                Minesweeper._cmpPrincipal[i][j]._colorCelda = colors[Minesweeper._cmpPrincipal[i][j].numMinas];
            }
        }
        
        return _pnRoot;
    }

    private void EventoMouse(MouseEvent e){
        if(e.getButton() == MouseButton.PRIMARY){
            if (!_tieneBandera) {
                _btnCelda.setBackground(null);
                _btnCelda.setDisable(true);
                _isActive = false;
                if (_isMined)
                    finJuego();
                else{
                    if (this.numMinas == 0)
                        btnVacio(this);
                    else{
                        _btnCelda.setText(Integer.toString(numMinas));
                        _btnCelda.setTextFill(_colorCelda);
                    }
                }
            }
        }
    }

    private void btnVacio(Campo _cmpVacio){

    }

    public void finJuego() {
        Alert _alFin = new Alert(AlertType.ERROR);
        _alFin.setTitle("Mensaje del sistema");
        _alFin.setHeaderText("Fin del juego T-T");
        _alFin.setContentText("Chale, te has topado con una mina :/");
        _alFin.showAndWait();
    }
}
