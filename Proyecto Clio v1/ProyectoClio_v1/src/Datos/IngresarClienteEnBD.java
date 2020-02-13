package Datos;
import java.sql.*;
import javax.swing.JOptionPane;

public class IngresarClienteEnBD {
    private String nombre = "";
    private String apellido = "";
    private String direccion = "";
    private String piso = "";
    private String numero = "";    
    private Connection conexion;
    private Statement statement;
    
    private String puerto = "";
    private String bd = "";
    private String usuariobd = "";
    private String contraseñabd = "";
    
    public IngresarClienteEnBD(String pu, String bd, String usbd, String conbd){
        this.puerto = pu;
        this.bd = bd;
        this.usuariobd = usbd;
        this.contraseñabd = conbd;
    }

    public String ingresoCliente(String nom, String ap, String dir, String pi, String num){
        String mensajeRespuesta = "";
        this.nombre = nom;
        this.apellido = ap;
        this.direccion = dir;
        this.piso = pi;
        this.numero = num;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection ("jdbc:mysql://localhost:"+this.puerto+"/"+this.bd, this.usuariobd, this.contraseñabd);
            statement = conexion.createStatement();
            statement.executeUpdate("INSERT INTO `cliente` (`ID_Cliente`, `Nombre_Cliente`, `Apellido_Cliente`, `Direccion`,"
                    + " `Piso`, `Numero_Contacto`, `Fecha_Inscripcion`) VALUES (NULL, \'"+nombre+"\', \'"+apellido+""
                            + "\', \'"+direccion+"\', \'"+piso+"\', \'"+numero+"\', current_timestamp())");
            mensajeRespuesta = "Cliente ingresado correctamente";
            cierreconexion();
        }
        catch (ClassNotFoundException e1) {
            mensajeRespuesta = "ERROR No encuentro el driver de la BD: " + e1.getMessage();
        }
        catch (SQLException e2) {
            mensajeRespuesta = "ERROR Fallo en SQL: "+e2.getMessage();
        }
        return mensajeRespuesta;
    }
    
    public void cierreconexion() throws SQLException{
        conexion.close();
    }  
}