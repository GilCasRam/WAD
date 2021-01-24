/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;


import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "Producto_Servlet", urlPatterns = {"/Producto_Servlet"})
public class Producto_Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("accion");

        if (action.equals("listaDeProductos")) {

            listaDeProductos(request, response);

        } else if (action.equals("nuevo")) {

            agregarProducto(request, response);

        } else if (action.equals("eliminar")) {

            eliminarProducto(request, response);

        } else if (action.equals("actualizar")) {

            actualizarProducto(request, response);

        } else if (action.equals("ver")) {

            mostrarProducto(request, response);
            
        } else if (action.equals("guardar")) {

            almacenarProducto(request, response);
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaDeProductos(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            
            RequestDispatcher vista = request.getRequestDispatcher("listaProductos.jsp");
            vista.forward(request, response);
            
            } catch (ServletException | IOException ex) {
            Logger.getLogger(Producto_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("productoForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Producto_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            RequestDispatcher vista = request.getRequestDispatcher("Producto_Servlet?accion=listaDeProductos");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Producto_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            RequestDispatcher vista = request.getRequestDispatcher("productoForm.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Producto_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            RequestDispatcher vista = request.getRequestDispatcher("mostarProducto.jsp");
            vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Producto_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        if(request.getParameter("id") == null || request.getParameter("id").isEmpty()){
            
            dto.getEntidad().setNombreProducto(request.getParameter("nombreProducto"));
            dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("existencia")));
            dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("precio")));
            
            CategoriaDTO categoria = new CategoriaDTO();
            categoria.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
            
            dto.getEntidad().setIdCategoria(categoria.getEntidad());
            
            try {
                dao.create(dto);
                RequestDispatcher vista = request.getRequestDispatcher("Producto_Servlet?accion=listaDeProductos");
                vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(Producto_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }           
        } else {
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setNombreProducto(request.getParameter("nombreProducto"));
            dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("existencia")));
            dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("precio")));
            dto.getEntidad().getIdCategoria().setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
            
            try {
                dao.update(dto);
                RequestDispatcher vista = request.getRequestDispatcher("Producto_Servlet?accion=listaDeProductos");
                vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(Producto_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
