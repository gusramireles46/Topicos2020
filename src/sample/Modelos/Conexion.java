package sample.Modelos;

import java.sql.*;

public class Conexion {
    private static final String host = "127.0.0.1";
    private static final String user = "hazahart";
    private static final String pwd = "darkzero46";
    private static final String db = "oxxo";
    public static Connection con;

    public static void crearConexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            // Se abre el socket de conexión hacia el DBMS
            con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+db+"?useSSL=false",user,pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
