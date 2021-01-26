
package com.ipn.mx.modelo.dto;


import com.ipn.mx.entidades.Usuario;
import lombok.Data;

/**
 *
 * @author Juan
 */
@Data
public class UsuarioDTO {
    
    private Usuario entidad;
    
    public UsuarioDTO(){
        
        entidad = new Usuario();
    }
    
}
