<%-- 
    Document   : mostrarUsuario
    Created on : 24 ene. 2021, 22:27:20
    Author     : Jesus
--%>

<%@page import="com.ipn.mx.modelo.dto.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                            <a class="nav-link active" href="Categoria_Servlet?accion=listaDeCategorias">Categorias</a>
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
        <%
            UsuarioDTO dto = (UsuarioDTO) request.getAttribute("usuario");

        %>


        <div class="container">
            <div class="container title p-5">

                <h1 class="m-2">Usuario</h1>

            </div>
            <div class="card">
                <div class="card-body">
                    <h4 class="header-title text-center"> INFORMACIÓN DEL USUARIO</h4> 

                    <div class="row mt-3">
                        <div class="col-2 mr-3">
                            NOMBRE:
                        </div>

                        <div class="col-5 bmr-5">
                            <%=dto.getEntidad().getNombre()%>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="col-2 mr-3">
                            USERNAME:
                        </div>

                        <div class="col-5 bmr-5">
                            <%=dto.getEntidad().getNombreUsuario()%>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-2 mr-3">
                            CORREO:
                        </div>

                        <div class="col-5 bmr-5">
                            <%=dto.getEntidad().getEmail()%>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="col-2 mr-3">
                            ROL:
                        </div>
                        <div class="col-5 bmr-5">
                            <%=dto.getEntidad().getRol()%>
                        </div>
                    </div>
                    <div>
                        <a class="btn btn-secondary mt-4 pr-4 pl-4" 
                           href="Usuario_Servlet?accion=listaDeUsuarios">REGRESAR</a>
                    </div>
                </div>
            </div>          
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
