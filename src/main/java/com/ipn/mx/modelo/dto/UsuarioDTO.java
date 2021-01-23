
package com.ipn.mx.modelo.dto;


import com.ipn.mx.entidades.Usuario;
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

public class UsuarioDTO {
    
    private Usuario entidad;
    
    public void UsuarioDTO(){
        entidad = new Usuario();
    }
    
    
}
