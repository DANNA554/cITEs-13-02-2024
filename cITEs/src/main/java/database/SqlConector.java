/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import com.mycompany.cites.Alumno;
import com.mycompany.cites.Coordinador;
import com.mycompany.cites.Cita;

import java.sql.SQLException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Savepoint;
import com.mycompany.cites.Alumno;
import com.mycompany.cites.Cita;
import com.mycompany.cites.Coordinador;
import javax.swing.table.DefaultTableModel;

     
/**
 *
 * @author Usuario
 */
public class SqlConector {
    //Codigo de: https://www.youtube.com/watch?v=nbAYB6HyTQI
    //y de: https://www.youtube.com/watch?v=Xy_lcPCymiE
    private static Connection con;
    
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass= "password123!"; //contrasenia
    private static final String url = "jdbc:mysql://localhost:3306/cITEsBD";
    
    private DefaultTableModel DT;
    private ResultSet RS;
    
    public void conectar() {
        // Reseteamos a null la conexion a la bd
        con=null;
        try{
            Class.forName(driver);
            // Nos conectamos a la bd
            con= (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con!=null){
                System.out.println("conexion exitosa");
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e){
            System.out.println("error en conexion");
        }
    }
    
    public Connection getConnection(){
        return con;
    }
    
    public void desconectar(){
        con = null;
        if(con==null){
            System.out.println("conexion Terminada");
        } else{
            System.out.println("Fallo al intentar salir de la conexion");
        }
    }
    
    public void close(){
        try{
            con.close();
        } catch(SQLException ex) {
            System.out.println("Error al cerrar base de datos");
        }
    } //Desconosco la diferencia entre este metodo y desconectar
    
    public void crearAlumno(Alumno alumno){
        int numControl = alumno.getNumeroControl(); //Igual a matricula
        String nombreCompleto = alumno.getNombreCompleto();
        String correo = alumno.getCorreoInstitucional();
        String contrasenia = alumno.getContrasena();
        
        
        String sqlInsert= "INSERT INTO Alumnos(nombre, matricula, contrasenia, correo) values (?,?,?,?)";
        //"+nombreCompleto+","+numControl+","+correo+","+contrasenia+"
        try {
            this.conectar();
            PreparedStatement PS = this.getConnection().prepareStatement(sqlInsert);
            PS.setString(1,String.valueOf(numControl));
            PS.setString(2,nombreCompleto);
            PS.setString(3,correo);
            PS.setString(4, contrasenia);
            
            int res= PS.executeUpdate();
            if(res>0){
                System.out.println("Alumno Registrado");
            }
        } catch(Exception e){
            System.err.println("Error al crear alumno en bd: "+e.getMessage());
        } finally{
            this.desconectar();
            this.close();
        }
    }
    
    public Alumno consultarAlumno(String contrasenia, String correo){
        //Codigo de: https://www.youtube.com/watch?v=dSn4ZORiqpY
        Alumno alumno = new Alumno();
        String sqlSelect = "SELECT * FROM Alumnos WHERE correo = (?) AND contrasenia = (?)";
        
        DT = new DefaultTableModel();
        DT.addColumn("id_usuario");
        DT.addColumn("nombre");
        DT.addColumn("matricula");
        DT.addColumn("contrasenia");
        DT.addColumn("correo");
        
        try {
            PreparedStatement PS = this.getConnection().prepareStatement(sqlSelect);
            PS.setString(1,correo);
            PS.setString(2,contrasenia);
            RS = PS.executeQuery();
            
            //alumno.ID = RS.getInt(1); 
            alumno.nombreCompleto = RS.getString(2);
            alumno.numeroControl = RS.getString(3);
            alumno.contrasena = RS.getString(4);
            alumno.correoInstitucional = RS.getString(5);
            
            

        } catch(Exception e){
            System.out.println("Error al listar alumnos: "+e.getMessage());
            
        } finally{
            this.desconectar();
            this.close();
            return alumno;
        }
    }
    
}

//Cosas que cambiar de alumno:
//Agregar constructor vacio
//Agregar campo para ID
//Set o alguna o hacer privado sus valores