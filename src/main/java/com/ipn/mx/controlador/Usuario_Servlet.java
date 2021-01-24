/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
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
 * @author Juan
 */
@WebServlet(name = "Usuario_Servlet", urlPatterns = {"/Usuario_Servlet"})
public class Usuario_Servlet extends HttpServlet {

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

        if (accion.equals("listaDeUsuarios")) {

            listaDeUsuarios(request, response);

        } else if (accion.equals("nuevo")) {

            agregarUsuario(request, response);

        } else if (accion.equals("eliminar")) {

            eliminarUsuario(request, response);

        } else if (accion.equals("actualizar")) {

            actualizarUsuario(request, response);

        } else if (accion.equals("guardar")) {

            almacenarUsuario(request, response);

        } else if (accion.equals("ver")) {

            mostrarUsuario(request, response);

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

    private void listaDeUsuarios(HttpServletRequest request, HttpServletResponse response) {

        try {
            RequestDispatcher vista;
            vista = request.getRequestDispatcher("listaUsuarios.jsp");
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Usuario_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("usuarioForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Usuario_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            RequestDispatcher vista = request.getRequestDispatcher("Usuario_Servlet?accion=listaDeUsuarios");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Usuario_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);
            RequestDispatcher vista = request.getRequestDispatcher("usuarioForm.jsp");
            vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Usuario_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        if(request.getParameter("id")==null || request.getParameter("id").isEmpty()){
            dto.getEntidad().setNombre(request.getParameter("nombre"));
            dto.getEntidad().setEmail(request.getParameter("email"));
            dto.getEntidad().setNombreUsuario(request.getParameter("nombreUsuario"));
            dto.getEntidad().setClaveUsuario(request.getParameter("claveUsuario"));
            dto.getEntidad().setRol(request.getParameter("rol"));
            try {
                dao.create(dto);
                RequestDispatcher vista = request.getRequestDispatcher("Usuario_Servlet?accion=listaDeUsuarios");
                vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(Usuario_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setNombre(request.getParameter("nombre"));
            dto.getEntidad().setEmail(request.getParameter("email"));
            dto.getEntidad().setNombreUsuario(request.getParameter("nombreUsuario"));
            dto.getEntidad().setClaveUsuario(request.getParameter("claveUsuario"));
            dto.getEntidad().setRol(request.getParameter("rol"));
            
            try {
                dao.update(dto);
                RequestDispatcher vista = request.getRequestDispatcher("Usuario_Servlet?accion=listaDeUsuarios");
                vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(Usuario_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void mostrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);
            RequestDispatcher vista = request.getRequestDispatcher("mostrarUsuario.jsp");
            vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Usuario_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
