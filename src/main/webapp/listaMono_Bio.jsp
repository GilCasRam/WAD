<%-- 
    Document   : listaMono_Bio
    Created on : 23-ene-2021, 16:35:53
    Author     : Juan
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="com.ipn.mx.modelo.dto.Mono_BioDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.ipn.mx.modelo.dao.Mono_BioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <title>Lista de Monografías y Biografías</title>
        
         
        <style>
            
            body {
                background-image: url("imagenes/fondo.jpg");
                background-repeat: repeat-y;
                background-position: right top;
            }
            
        </style>
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
                    <a class="nav-link" href="Usuario_Servlet?accion=listaDeUsuarios">Usuarios</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="Categoria_Servlet?accion=listaDeCategorias">Categorias</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="Producto_Servlet?accion=listaDeProductos">Productos</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" href="Mono_Bio_Servlet?accion=listaDeMono">Monografías y Biografías</a>
                  </li>
                </ul>
              </div>
            </div>
        </nav>
    


     
            
            <div class="container title p-5">
          
                <h1 class="m-2">Monografías y Biografías</h1>
                
                <div class="container">
                    
                    <a class="btn btn-primary m-2 col-6" href="Mono_Bio_Servlet?accion=nuevo">Agregar nuevo</a>
                
                    <a class="btn btn-info m-2 col-6" href="Mono_Bio_Servlet?accion=verPDF">Ver Reporte</a>
                </div> 
                
            </div>
        
        <div class="container container-fluid">
            <%
                int idmon_bio;
                String clave;
                String nombre;
                float precio;
                int idCategoria;
                

                Mono_BioDAO dao = new Mono_BioDAO();
                
                try {
                    
                    List lista = dao.readAll();
            %>

            <table class="table table-responsive table-dark table-hover table-striped">    
                <tr>
                    <th scope="col">id de artículo</th>
                    <th scope="col">Clave</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Precio</th>
                    <th scope="col">clave de Categoría</th>
                    <th scope="col" colspan="2"> Acciones</th>
                </tr>
                
            <%
                
                for(int i=0; i<lista.size(); i++){
                    
                    
                    Mono_BioDTO dto = (Mono_BioDTO) lista.get(i);
                    
                   idmon_bio = dto.getEntidad().getIdmon_bio();
                   clave = dto.getEntidad().getClave();
                   nombre = dto.getEntidad().getNombre();
                   precio = dto.getEntidad().getPrecio();
                   idCategoria = dto.getEntidad().getIdCategoria().getIdCategoria();
            %>
                    
                    <tr>
                        <td scope="row"> <a class="btn btn-primary btn-xs" href="Mono_Bio_Servlet?accion=ver&id=<%= idmon_bio %>"><%= idmon_bio %></a></td>
                        <td><%= clave %></td>
                        <td><%= nombre %></td>
                        <td><%= precio %></td>
                        <td><%= idCategoria %></td>

                        <td>
                            <a class="btn btn-success btn-xs" href="Mono_Bio_Servlet?accion=actualizar&id=<%= idmon_bio %>">Actualizar</a>
                        </td>
                        <td>
                            <a class="btn btn-danger btn-xs" href="Mono_Bio_Servlet?accion=eliminar&id=<%= idmon_bio %>">Eliminar</a>
                        </td>
                    </tr>
            <%        
                    
                }
            
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            
            %>

            </table>
        <div>
            
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>  
    </body>
</html>
