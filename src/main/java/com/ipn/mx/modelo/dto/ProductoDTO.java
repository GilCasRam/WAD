/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;
import com.ipn.mx.entidades.Producto;
import lombok.Data;

/**
 *
 * @author Juan
 */
@Data
public class ProductoDTO {
    
    private Producto entidad;
    
    public ProductoDTO(){
        entidad = new Producto();
    }
}
