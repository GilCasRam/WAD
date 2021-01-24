/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.UsuarioDTO;
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
public class UsuarioDAO {
    
    private static final String SQL_INSERT = "INSERT INTO Usuario(nombreusuario, username, claveusuario, correo, rol) VALUES"
            + "(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE Usuario SET nombreusuario = ?, username = ?, claveusuario = ?, correo = ?"
            + "rol = ?  WHERE idusuario = ? ";
    private static final String SQL_DELETE = "DELETE FROM Usuario WHERE idusuario = ?";
    private static final String SQL_READ = "SELECT * FROM Usuario WHERE idusuario = ?";
    private static final String SQL_READ_ALL = "select * from usuario";
    
    private final Conexion conexion = new Conexion();
    int contador = 0;
    
    public void create(UsuarioDTO dto) throws SQLException {
        
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        
        try (CallableStatement cs = con.prepareCall(SQL_INSERT)) {
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getNombreUsuario());
            cs.setString(3, dto.getEntidad().getClaveUsuario());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getRol());
            cs.executeUpdate();
            
        } finally {
            
            if (con != null) {
                con.close();
            }

        }
    }
    
    public void update(UsuarioDTO dto) throws SQLException {
        
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        
        try (CallableStatement cs = con.prepareCall(SQL_UPDATE)) {
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getNombreUsuario());
            cs.setString(3, dto.getEntidad().getClaveUsuario());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getRol());
            cs.setInt(6, dto.getEntidad().getIdUsuario());
            
        } finally {
            
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void delete(UsuarioDTO dto) throws SQLException {
        
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        
        try (CallableStatement cs = con.prepareCall(SQL_DELETE)) {
            cs.setInt(1, dto.getEntidad().getIdUsuario());
            cs.executeUpdate();
        } finally {
            if (con != null) {
                con.close();
            }

        }
    }
    
    private List obtenerResultados(ResultSet rs) throws SQLException {
        
        List resultados = new ArrayList();
        while (rs.next()) {
            
            UsuarioDTO dto = new UsuarioDTO();
            dto.getEntidad().setIdUsuario(rs.getInt("idusuario"));
            dto.getEntidad().setNombre(rs.getString("nombreusuario"));
            dto.getEntidad().setNombreUsuario(rs.getString("username"));
            dto.getEntidad().setClaveUsuario(rs.getString("claveusuario"));
            dto.getEntidad().setEmail(rs.getString("correo"));
            dto.getEntidad().setRol(rs.getString("rol"));
            resultados.add(dto);
        }
        return resultados;
    }
    
    public UsuarioDTO read(UsuarioDTO dto) throws SQLException {
        
        conexion.obtenerConexion_PostgreSQL();
        Connection con = conexion.getCon();
        CallableStatement cs = null;
        ResultSet rs = null;
       
        try {
            cs = con.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (UsuarioDTO) resultados.get(0);
            } else {
                return null;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
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
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return resultados;
            } else {
                return null;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    

}
