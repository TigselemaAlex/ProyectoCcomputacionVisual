package interfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {   
    Connection connect;   
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sistema_contable","root","");
            //JOptionPane.showMessageDialog(null, "Ok Conectado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos");
            System.exit(0);
        } 
        return connect;
    }    
}
