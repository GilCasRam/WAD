/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.entidades;

import com.ipn.mx.utilerias.Imagen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Juan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String email;
    private String nombreUsuario;
    private String claveUsuario;
    private String rol;
    private Imagen img;
    
}
