/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.entidades;

import java.io.Serializable;
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
public class Producto implements Serializable{
    
    private int idProducto;
    private String nombreProducto;
    private float precio;
    private int existencia;
    private Categoria idCategoria;
}
