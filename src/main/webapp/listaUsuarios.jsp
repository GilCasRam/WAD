<%-- 
    Document   : listaUsuarios
    Created on : 23-ene-2021, 16:35:00
    Author     : Juan
--%>

<%@page import="com.ipn.mx.modelo.dao.UsuarioDAO"%>
<%@page import="com.ipn.mx.modelo.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Usuarios</title>
        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link" href="index.html">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" href="Usuario_Servlet?accion=listaDeUsuarios">Usuarios</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="Categoria_Servlet?accion=listaDeCategorias">Categorias</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="Producto_Servlet?accion=listaDeProductos">Productos</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="Mono_Bio_Servlet?accion=listaDeMono">Monografías y Biografías</a>
                  </li>
                </ul>
              </div>
            </div>
        </nav>
        
        
        <div class="container">
            
            <div class="container title p-5">
          
                <h1 class="m-2">Usuarios</h1>
                
                <a class="btn btn-primary m-2" href="Usuario_Servlet?accion=nuevo">Agregar Usuario</a>
            </div>

            <%
                 int idUsuario;
                 String nombre;
                 String nombreUsuario;
                 String claveUsuario;
                 String email;
                 String rol;
                 
                 UsuarioDAO dao = new UsuarioDAO();
                 
                 try {
                     
                    List lista = dao.readAll();
            %>
            
            <table class="table table-responsive table-hover table-striped">    
                <tr class="thead-dark">
                    <th>Clave Usuario</th>
                    <th>Nombre</th>
                    <th>Nombre Usuario</th>
                    <th>Contraseña</th>
                    <th>Email</th>
                    <th>Rol</th>
                   
                    <th colspan="2"> Acciones</th>
                </tr>
                
            <%
                
                for(int i=0; i<lista.size(); i++){
                    
                    
                    UsuarioDTO dto = (UsuarioDTO) lista.get(i);
                    
                    idUsuario = dto.getEntidad().getIdUsuario();
                    nombre = dto.getEntidad().getNombre();
                    nombreUsuario = dto.getEntidad().getNombreUsuario();
                    claveUsuario = dto.getEntidad().getClaveUsuario();
                    email = dto.getEntidad().getEmail();
                    rol = dto.getEntidad().getRol();
            %>
                    
                    <tr>
                        <td><a class="btn btn-primary btn-xs" href="Usuario_Servlet?accion=ver&id=<%= idUsuario %>"><%=idUsuario%></a></td>
                        <td><%= nombre %></td>
                        <td><%= nombreUsuario %></td>
                        <td><%= claveUsuario %></td>
                        <td><%= email %></td>
                        <td><%= rol %></td>
                        <td>
                            <a class="btn btn-success btn-xs" href="Usuario_Servlet?accion=actualizar&id=<%= idUsuario %>">Actualizar</a>
                        </td>
                        <td>
                            <a class="btn btn-danger btn-xs" href="Usuario_Servlet?accion=eliminar&id=<%= idUsuario %>">Eliminar</a>
                        </td>
                    </tr>
            <%        
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            %>
            
            </table>
        </div>  
            
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>  
    </body>
</html>