/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

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

    
    
}
