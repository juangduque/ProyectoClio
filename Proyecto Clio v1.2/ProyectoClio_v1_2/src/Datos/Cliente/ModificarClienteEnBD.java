package Datos.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;

public class ModificarClienteEnBD {
    
private String puerto = "";
private String bd = "";
private String usuariobd = "";
private String contraseñabd = "";
    
    public ModificarClienteEnBD(String pu, String bd, String usbd, String conbd){
        this.puerto = pu;
        this.bd = bd;
        this.usuariobd = usbd;
        this.contraseñabd = conbd;
    }

    public void modificarDatosCliente(String numeroConsulta, String nombre, String apellido, String direccion, String piso, String nuevoNum){
        try{
            Class.forName("com.mysql.jdbc.Driver");            
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:"+this.puerto+"/"+this.bd, this.usuariobd, this.contraseñabd);
            String query = "UPDATE cliente SET `Nombre_Cliente`=?,`Apellido_Cliente`=?,`Direccion`=?,`Piso`=?, `Numero_Contacto`=? WHERE `Numero_Contacto`=?";
            PreparedStatement prest = conexion.prepareStatement(query);
            prest.setString(1, nombre);
            prest.setString(2, apellido);
            prest.setString(3, direccion);
            prest.setString(4, piso);
            prest.setString(5, nuevoNum);
            prest.setString(6, numeroConsulta);
            int res = prest.executeUpdate();
            if(res > 0){        
                JOptionPane.showMessageDialog(null, "Los datos han sido modificados con éxito", "Operación Exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);
            
             }else{        
                JOptionPane.showMessageDialog(null, "No se ha podido realizar la actualización de los datos\n"
                    + "Revise la conexión a la base de datos", "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
            conexion.close();
        }
        catch (ClassNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "ERROR:No encuentro el driver de la BD:"+e1.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
        }
        catch (SQLException e2) {
            JOptionPane.showMessageDialog(null, "ERROR:Fallo en SQL: "+e2.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
        }   
    }
}
