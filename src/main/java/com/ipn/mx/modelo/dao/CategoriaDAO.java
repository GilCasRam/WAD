/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class CategoriaDAO {
    
    private static final String SQL_INSERT = "insert into Categoria (nombrecategoria, descripcioncategoria) values (?,?)";
    private static final String SQL_UPDATE = "update Categoria set nombrecategoria = ?, descripcioncategoria = ? where idcategoria = ?";
    private static final String SQL_DELETE = "delete from Categoria where idcategoria = ?";
    private static final String SQL_READ = "select * from Categoria where idcategoria = ?";
    private static final String SQL_READ_ALL = "select * from Categoria";
        
    private final Conexion conexion = new Conexion();

    
    public void create(CategoriaDTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
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
    
    public void delete(CategoriaDTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
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
            CategoriaDTO dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            dto.getEntidad().setNombreCategoria(rs.getString("nombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(rs.getString("descripcionCategoria"));
            resultados.add(dto);
        }
        
        return resultados;
        
    }
     
    public CategoriaDTO read(CategoriaDTO dto) throws SQLException {
        
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            cs = con.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if(Resultados.size() > 0){
                
                CategoriaDTO resultado = (CategoriaDTO) Resultados.get(0);
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
