/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mycompany.cites.Coordinador;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class SqlCoordinador extends SqlConector {
    
    public void crearCoordinador(Coordinador coordinador){ //Por areglar
        int numEmpleado = coordinador.getNumeroControl(); //Igual a matricula
        String nombreCompleto = coordinador.getNombreCompleto();
        String correo = coordinador.getCorreoInstitucional();
        String contrasenia = coordinador.getContrasena();
        String cargo = coordinador.getCargo();
        
        //FALTAba CARGO
        String sqlInsert= "INSERT INTO Coordinador (nombre, cargo, matricula, contrasenia, correo) values (?,?,?,?,?)";
        //nombre, cargo, matricula, contrasenia, correo
        try {
            this.conectar();
            PreparedStatement PS = this.getConnection().prepareStatement(sqlInsert);
            PS.setString(1,nombreCompleto);
            PS.setString(2,cargo);
            PS.setString(3,String.valueOf(numEmpleado));
            PS.setString(5,correo);
            PS.setString(4, contrasenia);
            
            int res= PS.executeUpdate();
            if(res>0){
                System.out.println("Coordinador Registrado");
            }
        } catch(Exception e){
            System.err.println("Error al crear coordinador en bd: "+e.getMessage());
        } finally{
            this.desconectar();
            this.close();
        }
    }
    
    public Coordinador consultarCoordinador(String contrasenia, String correo){ //por areglar
        //Codigo de: https://www.youtube.com/watch?v=dSn4ZORiqpY
        Coordinador coordinador = new Coordinador();
        String sqlSelect = "SELECT * FROM Coordinador WHERE correo = (?) AND contrasenia = (?)";

        
        try {
            PreparedStatement PS = this.getConnection().prepareStatement(sqlSelect);
            PS.setString(1,correo);
            PS.setString(2,contrasenia);
            RS = PS.executeQuery();
            
            coordinador.setId(String.valueOf(RS.getInt(1)));  //No creo necesario cambiar la id
            coordinador.setNombreCompleto(RS.getString(2));
            coordinador.setCargo(RS.getString(3));
            coordinador.setCorreoInstitucional(RS.getString(4));
            coordinador.setNumeroControl(Integer.valueOf(RS.getString(5)));
            coordinador.setContrasena(RS.getString(6));
            
            //Desconosco si esto se rompera por que el metodo esta dise;ado para agregar a listas
        } catch(Exception e){
            System.out.println("Error al modificar datos de alumno: "+e.getMessage());
            
        } finally{
            this.desconectar();
            this.close();
            return coordinador;
        }
    }
    
    public void modificarCoordinador(Coordinador coordinador){ //por areglar
        //Ocupa un objeto alumno con el que sobre escribir el campo de la base de datos
        //fuente: https://www.youtube.com/watch?v=UNiYU0NjiQI
        String sqlUpdate= "UPDATE Coordinador SET nombre = (?), cargo = (?), matricula = (?), contrasenia = (?), correo = (?) WHERE id_coordinador = (?)";
        String nombre = coordinador.getNombreCompleto();
        String cargo = coordinador.getCargo();
        int matricula = coordinador.getNumeroControl();
        String contrasenia = coordinador.getContrasena();
        String correo = coordinador.getCorreoInstitucional();
        String id_usuario = coordinador.getId();
        
        
        try {
            PreparedStatement PS = this.getConnection().prepareStatement(sqlUpdate);
            PS.setString(1,nombre);
            PS.setString(2,cargo);
            PS.setString(3,String.valueOf(matricula));
            PS.setString(4,contrasenia);
            PS.setString(5,correo);
            
            PS.execute();
            
        } catch(SQLException e){
            System.out.println("Error al modificar registro de coordinador: "+e.getMessage());
        } finally {
            this.desconectar();
            this.close();
        }
    }
}
