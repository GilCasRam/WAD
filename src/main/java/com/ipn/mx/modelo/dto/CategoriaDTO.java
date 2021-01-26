/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.entidades.Categoria;
import lombok.Data;
/**
 *
 * @author Juan
 */
@Data

public class CategoriaDTO {
   
    private Categoria entidad;
    
    public CategoriaDTO(){
        
        entidad = new Categoria();
    }
    
}
