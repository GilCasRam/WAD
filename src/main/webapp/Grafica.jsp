<%-- 
    Document   : Grafica
    Created on : 12-dic-2020, 12:15:17
    Author     : Juan
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ipn.mx.modelo.dao.GraficaDAO"%>
<%@page import="com.ipn.mx.modelo.dto.GraficaDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grafica</title>
        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="./index.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="CategoriaServlet?accion=listaDeCategorias">Lista de Categorias</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ProductoServlet?accion=listaDeProductos">Lista de Productos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="CategoriaServlet?accion=nuevo">Agregar Categoria</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ProductoServlet?accion=nuevo">Agregar Producto</a>
                    </li>
                </ul>
            </div>
        </nav>
        
        <div class="container-fluid mt-4"> 
            <div class="row">

                <div class="col-6">
                    <button id="btn" class="btn btn-primary" style="width: 100%;">Graficar</button>
                <%
                    GraficaDAO dao = new GraficaDAO();
                    List lista = new ArrayList();
                    
                    String nombreCategoria;
                    int cant;
                    
                    try {
                        lista = dao.grafica();
                
                %>
                <div class="table-responsive">
                    <table id="myTable" class="table table-hover table-striped">
                        <tr class="thead-dark">
                            <th>Categoria</th>
                            <th>Cantidad</th>
                        </tr>
                    
                    <%
                            
                            for(int i=0; i<lista.size(); i++){
                                
                                GraficaDTO dto = (GraficaDTO) lista.get(i);
                                nombreCategoria = dto.getNombreCategoria();
                                cant = dto.getCant();
                    %>
                        <tr>
                            <td class="categoria"><%= nombreCategoria %></td>
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
    </body>
</html>
