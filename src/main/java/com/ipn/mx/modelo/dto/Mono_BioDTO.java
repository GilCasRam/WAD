/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.entidades.Mono_Bio;
import lombok.Data;

/**
 *
 * @author Juan
 */
@Data
public class Mono_BioDTO {
    
    private Mono_Bio entidad;
    
    public Mono_BioDTO(){
        entidad = new Mono_Bio();
    }
}
