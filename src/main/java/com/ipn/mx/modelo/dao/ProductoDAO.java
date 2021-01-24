/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class ProductoDAO {
    
     
    private static final String SQL_INSERTAR = "insert into Producto (nombreproducto, precio, existencia, idcategoria) values(?,?,?,?)";
    private static final String SQL_ACTUALIZAR = "update Producto set nombreproducto = ?, precio = ?, existencia = ?, idcategoria = ? where idproducto = ?";
    private static final String SQL_ELIMINAR = "delete from Producto where idproducto = ?";
    private static final String SQL_VER = "select * from Producto where idproducto = ?";
    private static final String SQL_VER_TODO = "select * from Producto";
    
    private final Conexion conexion = new Conexion();
    
    
    public void create(ProductoDTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_INSERTAR);
            cs.setString(1, dto.getEntidad().getNombreProducto());
            cs.setFloat(2, dto.getEntidad().getPrecio());
            cs.setInt(3, dto.getEntidad().getExistencia());
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
    
    public void update(ProductoDTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_ACTUALIZAR);
            cs.setString(1, dto.getEntidad().getNombreProducto());
            cs.setFloat(2, dto.getEntidad().getPrecio());
            cs.setInt(3, dto.getEntidad().getExistencia());
            cs.setInt(4, dto.getEntidad().getIdCategoria().getIdCategoria());
            cs.setInt(5, dto.getEntidad().getIdProducto());
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
    
    public void delete(ProductoDTO dto) throws SQLException {
       
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        
        try{
            cs = con.prepareCall(SQL_ELIMINAR);
            cs.setInt(1, dto.getEntidad().getIdProducto());
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
            ProductoDTO dto = new ProductoDTO();
            
            dto.getEntidad().setIdProducto(rs.getInt("idproducto"));
            dto.getEntidad().setNombreProducto(rs.getString("nombreproducto"));
            dto.getEntidad().setPrecio(rs.getFloat("precio"));
            dto.getEntidad().setExistencia(rs.getInt("existencia"));
            
            CategoriaDTO cat = new CategoriaDTO();
            cat.getEntidad().setIdCategoria(rs.getInt("idcategoria"));
            dto.getEntidad().setIdCategoria(cat.getEntidad());
             
            resultados.add(dto);
        }
        
        return resultados; 
    }
    
    
    public ProductoDTO read(ProductoDTO dto) throws SQLException {
        
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try {
            cs = con.prepareCall(SQL_VER);
            cs.setInt(1, dto.getEntidad().getIdProducto());
            rs = cs.executeQuery();
            List Resultados = obtenerResultados(rs);
            if(Resultados.size() > 0){
                
                ProductoDTO resultado = (ProductoDTO) Resultados.get(0);
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
            cs = con.prepareCall(SQL_VER_TODO);
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
    
    
    
    
    public static void main(String[] args) {
        
        
        ProductoDTO dto = new ProductoDTO();
        ProductoDAO dao = new ProductoDAO();
        
        CategoriaDTO cat = new CategoriaDTO();
        
        dto.getEntidad().setNombreProducto("Hojas");
        dto.getEntidad().setExistencia(100);
        dto.getEntidad().setPrecio((float) 2.50);
        
        cat.getEntidad().setIdCategoria(2);
            
        dto.getEntidad().setIdCategoria(cat.getEntidad());
       
        
        try {
            dao.create(dto);
            
            System.out.print(dto.toString());
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
