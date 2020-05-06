package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class productosDAO {
    private int idProducto;
    private String nomProducto;
    private float precio;
    private int existencia;
    private boolean vigencia;
    private float costo;
    private int idProveedor;

    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public String getNomProducto() {
        return nomProducto;
    }
    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public int getExistencia() {
        return existencia;
    }
    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    public boolean isVigencia() {
        return vigencia;
    }
    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }
    public float getCosto() {
        return costo;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
    public int getIdProveedor() {
        return idProveedor;
    }
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    private Connection con;
    public productosDAO(){
        con = Conexion.con;
    }

    public void insProducto(){
        String query = "INSERT INTO tbl_productos (nomProducto, precio, existencia, vigente, costo, idProveedor) VALUES ('"+nomProducto+"',"+precio+","+existencia+","+vigencia+","+costo+","+idProveedor+");";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updProducto(){
        String query = "UPDATE tbl_productos SET nomProducto='"+nomProducto+"', precio="+precio+", existencia="+existencia+", vigente="+vigencia+", idProveedor="+idProveedor+", costo="+costo+" WHERE idProducto="+idProducto;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {}
    }

    public void delProducto(){
        String query = "DELETE from tbl_productos WHERE idProducto="+idProducto;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e){}
    }

    public ObservableList<productosDAO> selAllProducts(){

        ObservableList<productosDAO> listaP = FXCollections.observableArrayList();
        productosDAO objP = null;
        String query = "SELECT * FROM tbl_productos ORDER BY nomProducto";
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                objP = new productosDAO();
                objP.setIdProducto(res.getInt("idProducto"));
                objP.setNomProducto(res.getString("nomProducto"));
                objP.setPrecio(res.getFloat("precio"));
                objP.setExistencia(res.getInt("existencia"));
                objP.setVigencia(res.getBoolean("vigente"));
                objP.setIdProveedor(res.getInt("idProveedor"));
                objP.setCosto(res.getFloat("costo"));
                listaP.add(objP);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return listaP;
    }

    public void selProdByID(){}
}
