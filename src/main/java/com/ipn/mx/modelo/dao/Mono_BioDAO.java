/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.Mono_BioDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Juan
 */
public class Mono_BioDAO {
    
    private static final String SQL_INSERT = "insert into mon_bio (clave, nombre, precio, idcategoria) values (?,?,?,?)";
    private static final String SQL_UPDATE = "update mon_bio set clave = ?, nombre = ?, precio = ?, idcategoria = ? where idmon_bio = ?";
    private static final String SQL_DELETE = "delete from mon_bio where idmon_bio = ?";
    private static final String SQL_READ = "select * from mon_bio where idmon_bio = ?";
    private static final String SQL_READ_ALL = "select * from mon_bio";
        
    private final Conexion conexion = new Conexion();

    public void create(Mono_BioDTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getClave());
            cs.setString(2, dto.getEntidad().getNombre());
            cs.setDouble(3, dto.getEntidad().getPrecio());
            cs.setInt(4, dto.getEntidad().getIdCategoria().getIdCategoria());
            cs.executeUpdate();
        }
        finally{
            if(cs != null) {
                cs.close();  
            }
            if(con != null) {
                con.close();
            }
        }
    }
    
    public void update(CategoriaDTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
            cs.setInt(3, dto.getEntidad().getIdCategoria());
            cs.executeUpdate();
        }
        finally{
            if(cs != null) {
                cs.close();  
            }
            if(con != null) {
                con.close();
            }
        }
    }
    
    
}
