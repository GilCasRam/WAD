<%-- 
    Document   : Grafica
    Created on : 12-dic-2020, 12:15:17
    Author     : Juan
--%>

<%@page import="com.ipn.mx.modelo.dao.ProductoDAO"%>
<%@page import="com.ipn.mx.modelo.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafica</title>
        
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
        
        
        <div class="container-fluid mt-4"> 
            <div class="row">

                <div class="col-6">
                    <button id="btn" class="btn btn-primary" style="width: 100%;">Graficar</button>
                <%
                    ProductoDAO dao = new ProductoDAO();
                    List lista = new ArrayList();
                    
                    String nombreProducto;
                    int cant;
                    
                    try {
                        lista = dao.grafica();
                
                %>
                <div class="table-responsive">
                    <table id="myTable" class="table table-hover table-striped">
                        <tr class="thead-dark">
                            <th>Producto</th>
                            <th>Cantidad</th>
                        </tr>
                    
                    <%
                            
                            for(int i=0; i<lista.size(); i++){
                                
                                ProductoDTO dto = (ProductoDTO) lista.get(i);
                                nombreProducto = dto.getEntidad().getNombreProducto();
                                cant = dto.getEntidad().getExistencia();
                    %>
                        <tr>
                            <td class="producto"><%= nombreProducto %></td>
                            <td class="cant"><%= cant %></td>
                        </tr>
                    <%
                            }
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    
                    %>
                    </table>
                </div>
                </div>
                
                <div class="col-6">
                    <canvas id="myChart"></canvas>
                </div>
            </div>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
        <script src="grafica.js"></script>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>  
    </body>
</html>
