<%-- 
    Document   : listaProductos
    Created on : 23-ene-2021, 16:35:33
    Author     : Juan
--%>

<%@page import="com.ipn.mx.modelo.dao.ProductoDAO"%>
<%@page import="com.ipn.mx.modelo.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Productos</title>
        
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
                    <a class="nav-link" href="Usuario_Servlet?accion=listaDeUsuarios">Usuarios</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="Categoria_Servlet?accion=listaDeCategorias">Categorias</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link active" href="Producto_Servlet?accion=listaDeProductos">Productos</a>
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
          
                <h1 class="m-2">Productos</h1>
                
                <a class="btn btn-primary m-2" href="Producto_Servlet?accion=nuevo">Agregar Producto</a>
            </div>

            
            <%
                int idProducto;
                String nombreProducto;
                float precio;
                int existencia;
                int idCategoria;
                

                ProductoDAO dao = new ProductoDAO();
                
                try {
                    
                    List lista = dao.readAll();
            %>

            <table class="table table-responsive table-hover table-striped">    
                <tr class="thead-dark">
                    <th>Clave Producto</th>
                    <th>Nombre Producto</th>
                    <th>Precio</th>
                    <th>Existencia</th>
                    <th>Clave Categoría</th>
                    <th colspan="2"> Acciones</th>
                </tr>
                
            <%
                
                for(int i=0; i<lista.size(); i++){
                    
                    
                    ProductoDTO dto = (ProductoDTO) lista.get(i);
                    
                    idProducto = dto.getEntidad().getIdProducto();
                    nombreProducto = dto.getEntidad().getNombreProducto();
           
                    precio = dto.getEntidad().getPrecio();
                    existencia = dto.getEntidad().getExistencia();
                    idCategoria = dto.getEntidad().getIdCategoria().getIdCategoria();      
            %>
                    
                    <tr>
                        <td> <a class="btn btn-primary btn-xs" href="ProductoServlet?accion=ver&id=<%=idProducto%>"><%=idProducto%></a></td>
                        <td><%= nombreProducto %></td>
                        <td><%= precio %></td>
                        <td><%= existencia %></td>
                        <td><%= idCategoria %></td>
                        <td>
                            <a class="btn btn-success btn-xs" href="Producto_Servlet?accion=actualizar&id=<%=idProducto%>">Actualizar</a>
                        </td>
                        <td>
                            <a class="btn btn-danger btn-xs" href="Producto_Servlet?accion=eliminar&id=<%=idProducto%>">Eliminar</a>
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
              
        </div>
    </body>
</html>
