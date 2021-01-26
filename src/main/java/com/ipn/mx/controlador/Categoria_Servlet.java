/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
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


@WebServlet(name = "Categoria_Servlet", urlPatterns = {"/Categoria_Servlet"})
public class Categoria_Servlet extends HttpServlet {

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
        
        String accion = request.getParameter("accion");
        
        if (accion.equals("listaDeCategorias")) {

            listaDeCategorias(request, response);

        } else if (accion.equals("nuevo")) {

            agregarCategoria(request, response);

        } else if (accion.equals("eliminar")) {

            eliminarCategoria(request, response);

        } else if (accion.equals("actualizar")) {

            actualizarCategoria(request, response);

        } else if (accion.equals("guardar")) {

            almacenarCategoria(request, response);

        } else if (accion.equals("ver")) {

            mostrarCategoria(request, response);
            
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

    private void listaDeCategorias(HttpServletRequest request, HttpServletResponse response) {

        try {
            
            RequestDispatcher vista = request.getRequestDispatcher("listaCategorias.jsp");
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Categoria_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("categoriaForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Categoria_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            RequestDispatcher vista = request.getRequestDispatcher("Categoria_Servlet?accion=listaDeCategorias");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Categoria_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("categoria", dto);
            RequestDispatcher vista = request.getRequestDispatcher("categoriaForm.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Categoria_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        
        if(request.getParameter("id") == null || request.getParameter("id").isEmpty()){
            dto.getEntidad().setNombreCategoria(request.getParameter("nombre"));
            dto.getEntidad().setDescripcionCategoria(request.getParameter("descripcion"));
            try {
                dao.create(dto);
                RequestDispatcher vista = request.getRequestDispatcher("Categoria_Servlet?accion=listaDeCategorias");
                vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(Categoria_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setNombreCategoria(request.getParameter("nombre"));
            dto.getEntidad().setDescripcionCategoria(request.getParameter("descripcion"));
            try {
                dao.update(dto);
                RequestDispatcher vista = request.getRequestDispatcher("Categoria_Servlet?accion=listaDeCategorias");
                vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(Categoria_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        RequestDispatcher vista = request.getRequestDispatcher("mostrarCategoria.jsp");
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        
        try {
            dto = dao.read(dto);
            request.setAttribute("categoria", dto);
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Categoria_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
