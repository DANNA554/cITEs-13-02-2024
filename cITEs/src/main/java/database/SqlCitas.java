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

    // Modificado 20/02/2024    
    public Cita[] consultarCitasPorCoordinador(int idCoordinador) {
    // Crear una instancia de Cita para almacenar los datos recuperados de la base de datos
    Cita[] citasCoordinador = new Cita[MAX_NUM_CITAS]; // Ajusta MAX_NUM_CITAS al máximo número esperado de citas por coordinador
    int index = 0; // Índice para rastrear la posición actual al agregar citas al arreglo

    // Consulta SQL para recuperar citas por coordinador
    String sqlSelect = "SELECT * FROM Citas WHERE coordinador_id = ?";

    try {
        // Preparar la consulta SQL
        PreparedStatement PS = this.getConnection().prepareStatement(sqlSelect);
        PS.setInt(1, idCoordinador); // Establecer el parámetro de coordinador_id en la consulta SQL
        RS = PS.executeQuery();

        // Iterar sobre los resultados de la consulta
        while (RS.next()) {
            // Crear una nueva instancia de Cita para almacenar los datos de cada cita
            Cita cita = new Cita();

            // Asignar valores de las columnas de la tabla a los campos de la instancia de Cita
            cita.setId_cita(RS.getString("id_cita"));
            cita.setFecha(RS.getString("fecha"));
            cita.setHora(Integer.parseInt(RS.getString("hora")));
            cita.setCitadorId(RS.getString("citador_id"));
            cita.setCitadorDocente(RS.getString("citador_docente"));
            cita.setMotivo(RS.getString("motivo"));
            cita.setEstado(RS.getString("estado"));

            // Agregar la cita al arreglo de citas
            citasCoordinador[index] = cita;
            index++; // Incrementar el índice para la siguiente cita
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL
        System.out.println("Error al consultar citas por coordinador: " + e.getMessage());
    } finally {
        // Cerrar la conexión y liberar recursos
        this.desconectar();
        this.close();
    }

    // Devolver el arreglo de citas
    return citasCoordinador;
}
    
    
    public void cambiarEstadoCita(char estadoNuevo){}
}
