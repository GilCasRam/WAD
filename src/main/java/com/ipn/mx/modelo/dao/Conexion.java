/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Juan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Conexion {
    
    private Connection con;
    
     
    public void obtenerConexion_PostgreSQL() throws SQLException {
       
        String usr = "USERNAME";
        String pwd = "PASSWORD";
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://HOST:5432/DATABASE?sslmode=require";
         
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, usr, pwd);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
