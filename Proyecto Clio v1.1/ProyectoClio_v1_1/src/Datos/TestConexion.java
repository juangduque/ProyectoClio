package Datos;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author juan
 */
public class TestConexion {
    private String puerto = "";
    private String bd = "";
    private String usuariobd = "";
    private String contraseñabd = "";    
    private boolean conexion = false;
    
    public TestConexion(String pu, String bd, String usbd, String conbd){
        this.puerto = pu;
        this.bd = bd;
        this.usuariobd = usbd;
        this.contraseñabd = conbd;
    }
    
    public boolean  testearConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection ("jdbc:mysql://localhost:"+this.puerto+"/"+this.bd, this.usuariobd, this.contraseñabd);
            if (cn != null) {
                conexion = true;
            }            
        }
        catch (ClassNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "ERROR No encuentro el driver de la BD:"+e1.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
        }    
        catch (SQLException e2) {
            JOptionPane.showMessageDialog(null, "ERROR Fallo en SQL: "+e2.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
        }           
        return conexion;
    }
}
