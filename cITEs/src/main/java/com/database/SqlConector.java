/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

     
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
    
    protected ResultSet RS;
    
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
    
    
    
   
    
}