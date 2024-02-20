/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mycompany.cites.Alumno;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class SqlAlumno extends SqlConector {
    
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

        
        try {
            PreparedStatement PS = this.getConnection().prepareStatement(sqlSelect);
            PS.setString(1,correo);
            PS.setString(2,contrasenia);
            RS = PS.executeQuery();
            
            alumno.setId(RS.getString(1));  //No creo necesario cambiar la id
            alumno.setNombreCompleto( RS.getString(2));
            alumno.setNumeroControl( Integer.parseInt(RS.getString(3)));
            alumno.setContrasena(RS.getString(4));
            alumno.setCorreoInstitucional(RS.getString(5));
            
            //Desconosco si esto se rompera por que el metodo esta dise;ado para agregar a listas
        } catch(Exception e){
            System.out.println("Error al modificar datos de alumno: "+e.getMessage());
            
        } finally{
            this.desconectar();
            this.close();
            return alumno;
        }
    }
    
    public void modificarAlumno(Alumno alumno){
        //Ocupa un objeto alumno con el que sobre escribir el campo de la base de datos
        //fuente: https://www.youtube.com/watch?v=UNiYU0NjiQI
        String sqlUpdate= "UPDATE Alumnos SET nombre = (?), matricula = (?), contrasenia = (?), correo = (?) WHERE id_usuario = (?)";
        String nombre = alumno.getNombreCompleto();
        int matricula = alumno.getNumeroControl();
        String contrasenia = alumno.getContrasena();
        String correo = alumno.getCorreoInstitucional();
        String id_usuario = alumno.getId();
        
        
        try {
            PreparedStatement PS = this.getConnection().prepareStatement(sqlUpdate);
            PS.setString(1,nombre);
            PS.setString(2,String.valueOf(matricula));
            PS.setString(3,contrasenia);
            PS.setString(4,correo);
            PS.setString(5,id_usuario);
            
            PS.execute();
            
        } catch(SQLException e){
            System.out.println("Error al modificar registro de alumno: "+e.getMessage());
        } finally {
            this.desconectar();
            this.close();
        }
    }
}
