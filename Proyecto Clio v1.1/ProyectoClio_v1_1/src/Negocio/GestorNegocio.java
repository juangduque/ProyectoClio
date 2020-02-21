package Negocio;

import javax.swing.JOptionPane;
import Datos.IngresarClienteEnBD;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

/* La presente clase es la que contiene toda la lógica del programa. Se encarga de el puente
entre las clases que hacen las operaciones a las bases de datos y pasar la información a las
correspondientes clases que se encargan de manejar la interfaz.
 */
public class GestorNegocio {
    
    private static String puerto = "3306";
    private static String bd = "revoluciondelsabor";
    private static String usuariobd = "admin";
    private static String contraseñabd = "Revolucion12";
    
    /*La siguiente función se encarga de hacer la inserciones de la información de un cliente.
    Toma los valores que se le pasan por parámetro, verificando en los condicionales que dichos
    parámetros no estén vacíos, porque de no ser así no se podra hacer la inserción, o mejor, no
    vale la pena ingresar campos vacíos.  
    Si los parámetros no son vacíos procederá a ingresarlos a la bd.*/
    public String ingresarDatos(String nombre, String apellido, String direccion, String piso, String numero){
        boolean hayNombre = false;
        boolean hayApellido = false;
        boolean hayDireccion = false;
        boolean hayNumero = false;
        String mensajeRespuesta = "";
        Datos.IngresarClienteEnBD ingreso = new Datos.IngresarClienteEnBD(puerto, bd, usuariobd, contraseñabd);
        
        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay nombre que guardar. Debe ponerlo para ingresar cliente", "Operación interrumpida", JOptionPane.ERROR_MESSAGE);
        }
        else{
            hayNombre = true;
        }
        
        if(apellido.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay apellido que guardar. Debe ponerlo para ingresar cliente", "Operación interrumpida", JOptionPane.ERROR_MESSAGE);
        }
        else{
            hayApellido = true;
        }
        
        if(direccion.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay dirección que guardar. Debe ponerlo para ingresar cliente", "Operación interrumpida", JOptionPane.ERROR_MESSAGE);
        }
        else{
            hayDireccion = true;
        }
        
        if(numero.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay número que guardar. Debe ponerlo para ingresar cliente", "Operación interrumpida", JOptionPane.ERROR_MESSAGE);
        }
        else{
            hayNumero = true;
        }
        
        if(hayNombre && hayApellido && hayDireccion && hayNumero){
            try{
                ResultSet rs;
                Datos.ConsultarClienteEnBD consulta = new Datos.ConsultarClienteEnBD(puerto, bd, usuariobd, contraseñabd);
                rs = consulta.consultarCliente(numero);
                if (! rs.next()) {               
                    /*Mensaje de respuesta sirve para verificar si algún cliente si fue ingresado a la bd,
                    pero también puede ser información sobre algún error que se halla generado durante el 
                    proceso. Dicho mensaje se retornara para que sea puesto en la interfaz y el usuario
                    la pueda visualizar.   */
                    mensajeRespuesta = ingreso.ingresoCliente(nombre, apellido, direccion, piso, numero);    
                }else{
                    JOptionPane.showMessageDialog(null, "Revise el número a guardar porque ya existe en la base de datos.", "Número ya en base de datos", JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException e2) {
                JOptionPane.showMessageDialog(null, "ERROR:Fallo en SQL: "+e2.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
            }     
        }                    
        return mensajeRespuesta;
    } 
    
    /*La siguiente función se encarga de consultar el número que se la pasa por parámetro.
    Lo primero que hace es verificar si dicho número es de 10 caracteres, ya que sólo existen
    números telefónicos de 10 dígitos. 
    Luego crea el objeto que se encarga de traer la información de la bd.
    El método que usa el objetoretorna un result set (rs). Si tal rs está vacío mostrara un 
    panel diciendo que el número no existe en la bd y ofrece la posibilidad de guardarlo en la bd posteriormente.  */
    public void consultarDatos(String numeroConsulta){    
        int opcion = 3;
        ResultSet rs;
        if(numeroConsulta.length() == 10){
            Datos.ConsultarClienteEnBD consulta = new Datos.ConsultarClienteEnBD(puerto, bd, usuariobd, contraseñabd);
            rs = consulta.consultarCliente(numeroConsulta);
            try{
                //Si rs.next() es "true" es porque si existe el cliente en la bd.
                if(rs.next()){                    
                    rs.beforeFirst();//Se debe regresar el puntero al inicio.
                    while (rs.next()){ //En el presente bucle se imprimen los valores.                       
                        JOptionPane.showMessageDialog(null,"ID del cliente:  "+rs.getString("ID_Cliente")+"\nNombre(s):  "+rs.getString("Nombre_Cliente")+
                                "\nApellido(s):  "+rs.getString("Apellido_Cliente")+"\nDirección:  "+rs.getString("Direccion")+"\nPiso en el que vive:  "+rs.getString("Piso"));
                    }
                }else{
                    opcion = JOptionPane.showConfirmDialog(null, "El número consultado en la base de datos no existe\n¿Desea guardarlo?", "Cliente no existe en BD", JOptionPane.WARNING_MESSAGE);
                }
                if (opcion == 0){
                    Interfaz.IngresoDeCliente ingreso = new Interfaz.IngresoDeCliente(numeroConsulta);
                    ingreso.setVisible(true);
                }
            }
            catch (SQLException e2) {
                JOptionPane.showMessageDialog(null, "ERROR:Fallo en SQL: "+e2.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
            }  
        }else{
            JOptionPane.showMessageDialog(null, "El número a consultar debe ser de 10 dígitos", "Revise número a consultar", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    public String[] traerDatosDeCliente(String numeroConsulta){
        String[] datosCliente = new String[5];
        ResultSet rs;
        int opcion = 3;
        if(numeroConsulta.length() == 10){
            Datos.ConsultarClienteEnBD consulta = new Datos.ConsultarClienteEnBD(puerto, bd, usuariobd, contraseñabd);
            rs = consulta.consultarCliente(numeroConsulta);
            try{
                //Si rs.next() es "true" es porque si existe el cliente en la bd.
                if(rs.next()){                    
                    rs.beforeFirst();//Se debe regresar el puntero al inicio.
                    while(rs.next()){
                        datosCliente[0] = rs.getString("Nombre_Cliente");
                        datosCliente[1] = rs.getString("Apellido_Cliente");
                        datosCliente[2] = rs.getString("Direccion");
                        datosCliente[3] = rs.getString("Piso");
                        datosCliente[4] = rs.getString("Numero_Contacto");
                    }                    
                }else{
                    
//                    JOptionPane.showMessageDialog(null, "El número consultado en la base de datos no existe", "Cliente no existe en BD", JOptionPane.INFORMATION_MESSAGE);
                    opcion = JOptionPane.showConfirmDialog(null, "El número consultado en la base de datos no existe\n¿Desea guardarlo?", "Cliente no existe en BD", JOptionPane.WARNING_MESSAGE);
                }
                if (opcion == 0){
                    Interfaz.IngresoDeCliente ingreso = new Interfaz.IngresoDeCliente(numeroConsulta);
                    ingreso.setVisible(true);
                }
            }
            catch (SQLException e2) {
                JOptionPane.showMessageDialog(null, "ERROR:Fallo en SQL: "+e2.getMessage(), "Fallo de conexión", JOptionPane.ERROR_MESSAGE);
            }  
        
        }else{
            JOptionPane.showMessageDialog(null, "El número a consultar debe ser de 10 dígitos", "Revise número a consultar", JOptionPane.ERROR_MESSAGE);
        }  
        return datosCliente;
    }
    
    public void modificarDatos(String numConsulta, String nombre, String apellido, String direccion, String piso, String nuevoNum){
        Datos.ModificarClienteEnBD modificador = new Datos.ModificarClienteEnBD(puerto, bd, usuariobd, contraseñabd);
        modificador.modificarDatosCliente(numConsulta, nombre, apellido, direccion, piso, nuevoNum);
    }
    
    /*La siguiente función se usa para asignar los valores que llegan por parámetro a las 
    variables correspondientes de la base de datos.  */
    public void confConexionBD(String pu, String bd, String us, String con){
        this.puerto = pu;
        this.bd = bd;
        this.usuariobd = us;
        this.contraseñabd = con;
    }
    
    /*La siguiente función se encarga de encapsular los valores correspondientes de la base de
    datos en un arreglo, para retornarlos posteriormente.  */
    public String[] setValoresBD(){
        String[] valoresBD = new String[4];
        valoresBD[0] = this.puerto;
        valoresBD[1] = this.bd;
        valoresBD[2] = this.usuariobd;
        valoresBD[3] = this.contraseñabd;
        return valoresBD;
    }
    
    /*La siguiente función se usa para hacer un testeo de conexión con la base de datos. Crea 
    un objeto de la clase TestConexion la cual devuelve un boolean el cual si es true es porque 
    la conexión está establecida y lo contrario si no.   */
    public boolean testearConexion(){
        boolean conexion = false;
        Datos.TestConexion test =new  Datos.TestConexion(this.puerto, this.bd, this.usuariobd, this.contraseñabd);
        conexion = test.testearConexion();
        return conexion;
    }
    
    public void imprimirDomicilio(String nombre, String apellido, String direccion, String piso, String numero){
        try {
            FileWriter file = new FileWriter("c:\\ImpresionDatos\\domicilios.txt");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter archivo = new BufferedWriter(file);
            archivo.write("Nombre: "+nombre+" "+apellido+"\nDireccion: "+direccion
            +"\nPiso: "+piso+"\nNumero: "+numero+"\n\n\n\n\n\n\n\n\n");
            archivo.close();
            Impresion.Impresion impresora = new Impresion.Impresion();
            impresora.imprimir("c:\\ImpresionDatos\\domicilios.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fallo en gestión de archivo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*El método main del programa lo único que se encarga de hacer es llamar la página inicial
    de la interfaz gráfica.   */
    public static void main(String[] args) {
        Interfaz.LandingPage paginaPrincipal = new Interfaz.LandingPage();
        paginaPrincipal.setVisible(true);
    }    
}
