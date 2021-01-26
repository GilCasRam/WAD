/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.Dulce_DTO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Dulce_DAO {
    
    private static final String SQL_INSERT = "insert into mon_bio (clave, nombre, precio, idcategoria) values (?,?,?,?)";
    private static final String SQL_UPDATE = "update mon_bio set clave = ?, nombre = ?, precio = ?, idcategoria = ? where idmon_bio = ?";
    private static final String SQL_DELETE = "delete from mon_bio where idmon_bio = ?";
    private static final String SQL_READ = "select * from mon_bio where idmon_bio = ?";
    private static final String SQL_READ_ALL = "select * from mon_bio";
        
    private final Conexion conexion = new Conexion();

    public void create(Dulce_DTO dto) throws SQLException {
       
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
    
    public void update(Dulce_DTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getClave());
            cs.setString(2, dto.getEntidad().getNombre());
            cs.setDouble(3, dto.getEntidad().getPrecio());
            cs.setInt(4, dto.getEntidad().getIdCategoria().getIdCategoria());
            cs.setInt(5, dto.getEntidad().getIdmon_bio());
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
    
    public void delete(Dulce_DTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdmon_bio());
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
    
    private List obtenerResultados(ResultSet rs) throws SQLException {
        
        List resultados = new ArrayList();
        
        while(rs.next()) {
            Dulce_DTO dto = new Dulce_DTO();
            dto.getEntidad().setIdmon_bio(rs.getInt("idmon_bio"));
            dto.getEntidad().setClave(rs.getString("clave"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setPrecio(rs.getFloat("precio"));
            
            CategoriaDTO cat = new CategoriaDTO();
            cat.getEntidad().setIdCategoria(rs.getInt("idcategoria"));
            
            dto.getEntidad().setIdCategoria(cat.getEntidad());
            resultados.add(dto);
        }
        
        return resultados;   
    }
    
    public Dulce_DTO read(Dulce_DTO dto) throws SQLException {
        
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            cs = con.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdmon_bio());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if(Resultados.size() > 0){
                
                Dulce_DTO resultado = (Dulce_DTO) Resultados.get(0);
                return resultado;
            }else {
                return null;
            }
        }
        
        finally{
            
            if(rs != null){
                rs.close();
            }
            if(cs != null){
                cs.close();
            }
            if(con != null){
                con.close();
            }
        }
    } 
    
    public List readAll() throws SQLException {
        
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            cs = con.prepareCall(SQL_READ_ALL);
            rs = cs.executeQuery();
            List Resultados = (List) obtenerResultados(rs);
            if(Resultados.size() > 0){
                return Resultados;
            }else {
                return null;
            }
        }
        
        finally{
            
            if(rs != null){
                rs.close();
            }
            if(cs != null){
                cs.close();
            }
            if(con != null){
                con.close();
            }
        }
    }
    
     
}
