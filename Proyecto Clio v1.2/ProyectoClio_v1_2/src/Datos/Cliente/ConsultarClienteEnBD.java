package Datos.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;

public class ConsultarClienteEnBD {
    private String puerto = "";
    private String bd = "";
    private String usuariobd = "";
    private String contraseñabd = "";
    
    public ConsultarClienteEnBD(String pu, String bd, String usbd, String conbd){
        this.puerto = pu;
        this.bd = bd;
        this.usuariobd = usbd;
        this.contraseñabd = conbd;
    }
    
    public ResultSet consultarCliente(String consultaNum){
        int opcion = 5;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:"+this.puerto+"/"+this.bd, this.usuariobd, this.contraseñabd);
            Statement s = conexion.createStatement();
            rs = s.executeQuery ("SELECT * FROM `cliente` WHERE `Numero_Contacto` = " + consultaNum);
        }
        catch (ClassNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "ERROR No encuentro el driver de la BD:"+e1.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e2) {
            JOptionPane.showMessageDialog(null, "ERROR Fallo en SQL: "+e2.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
        }       
        return rs;
    }
}

  
