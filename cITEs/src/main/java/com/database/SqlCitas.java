/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mycompany.cites.Cita;
import com.mycompany.cites.Coordinador;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author Usuario
 */
public class SqlCitas extends SqlConector {
    public void crearCita(Cita cita){ }


    
    public Cita[] consultarCitasPorAlumno(int idAlumno){
        Cita citaAlumno = new Cita();
        String sqlSelect = "SELECT * FROM Alumnos WHERE correo = (?) AND contrasenia = (?)";

        
        try {
            PreparedStatement PS = this.getConnection().prepareStatement(sqlSelect);
            RS = PS.executeQuery();
            
            // Agregado para la base de datos 19/02/2024
            citaAlumno.setId_cita(RS.getString(1)); 
            citaAlumno.setFecha( RS.getString(2));
            citaAlumno.setHora( Integer.parseInt(RS.getString(3)));
            citaAlumno.setCitadorId(RS.getString(4));
            citaAlumno.setCitadorDocente(RS.getString(5));
            citaAlumno.setMotivo(RS.getString(4));
            citaAlumno.setEstado(RS.getString(5));
            
            //Desconosco si esto se rompera por que el metodo esta dise;ado para agregar a listas
        } catch(Exception e){
            System.out.println("Error al modificar datos de alumno: "+e.getMessage());
            
        } finally{
            this.desconectar();
            this.close();
            return alumno;
        }
    }
        return 
    }
    
    
    public Cita[] consultarCitasPorCoordinador(int idCoordinador){
        
        return
    }
    
    public void cambiarEstadoCita(char estadoNuevo){}
}
