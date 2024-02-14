/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cites;

/**
 *
 * @author Delvi
 */
public class Coordinador {
    //atributos de nuestra clase
    //atributos de nuestra clase:
    int numeroControl;
    String nombreCompleto;
    String correoInstitucional;
    String contrasena;
    
    //constructor
    public Coordinador(int numeroControl,String nombreCompleto,  String correoInstitucional,String contraseña ){
        this.numeroControl= numeroControl;
        this.nombreCompleto= nombreCompleto;
        this.correoInstitucional= correoInstitucional;
        this.contrasena= contrasena;
    }
    
    //metodos getters

    public int getNumeroControl() {
        return numeroControl;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    
}
