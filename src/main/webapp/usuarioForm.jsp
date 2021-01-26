<%-- 
    Document   : usuarioForm
    Created on : 24-ene-2021, 0:51:17
    Author     : Juan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Usuario</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
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
                    <a class="nav-link active" aria-current="page" href="index.html">Home</a>
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
                    <a class="nav-link" href="Mono_Bio_Servlet?accion=listaDeMono">Monografías y Biografías</a>
                  </li>
                </ul>
              </div>
            </div>
        </nav>
        
       <div class="container p-5">
            <h1>Nuevo Usuario</h1>
        </div>
        
        <div class="container col-6 p-5">
            
            <form action="Usuario_Servlet?accion=guardar" method="post" id="Formulario">
                 
                <div class="form-group">
                    <label for="sdf">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" required>
                </div>

                <div class="form-group">
                    <label for="sdf">Email</label>
                    <input type="email" class="form-control" name="email" id="email" required>
                </div>
                
                <div class="form-group">
                    <label for="sdf">Username</label>
                    <input type="text" class="form-control" name="nombreUsuario" id="nombreUsuario" required>
                </div>
                
                <div class="form-group">
                    <label for="sdf">Clave de Usuario</label>
                    <input type="password" class="form-control" name="claveUsuario" id="claveUsuario" required>
                </div>
                
                <div class="form-group">
                    <label for="sdf">Rol</label>
                    <input type="text" class="form-control" name="rol" id="rol" required>
                </div>
                
                <button type="submit" class="btn btn-primary mb-2 col-12" href="Usuario_Servlet?accion=guardar">Enviar</button>
            </form>
            
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>  
    </body>
</html>
