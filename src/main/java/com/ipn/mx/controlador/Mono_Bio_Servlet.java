/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.Mono_BioDAO;
import com.ipn.mx.modelo.dto.Mono_BioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "Mono_Bio_Servlet", urlPatterns = {"/Mono_Bio_Servlet"})
public class Mono_Bio_Servlet extends HttpServlet {

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

        if (accion.equals("listaDeMono")) {

            listaDeMono(request, response);

        } else if (accion.equals("nuevo")) {

            agregarMono(request, response);

        } else if (accion.equals("eliminar")) {

            eliminarMono(request, response);

        } else if (accion.equals("actualizar")) {

            actualizarMono(request, response);

        } else if (accion.equals("guardar")) {

            almacenarMono(request, response);

        } else if (accion.equals("ver")) {

            mostrarMono(request, response);

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

    private void listaDeMono(HttpServletRequest request, HttpServletResponse response) {
        try {
         
            RequestDispatcher vista = request.getRequestDispatcher("listaMono_Bio.jsp");
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Mono_Bio_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarMono(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher vista = request.getRequestDispatcher("monoForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Mono_Bio_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarMono(HttpServletRequest request, HttpServletResponse response) {
        Mono_BioDAO dao = new Mono_BioDAO();
        Mono_BioDTO dto = new Mono_BioDTO();
        dto.getEntidad().setIdmon_bio(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            RequestDispatcher vista = request.getRequestDispatcher("Mono_Bio_Servlet?accion=listaDeMono");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Mono_Bio_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarMono(HttpServletRequest request, HttpServletResponse response) {
        Mono_BioDAO dao = new Mono_BioDAO();
        Mono_BioDTO dto = new Mono_BioDTO();
        dto.getEntidad().setIdmon_bio(Integer.parseInt(request.getParameter("id")));
        try {
            dao.read(dto);
            request.setAttribute("mono", dto);
            RequestDispatcher vista = request.getRequestDispatcher("monoForm.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Mono_Bio_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarMono(HttpServletRequest request, HttpServletResponse response) {
        Mono_BioDAO dao = new Mono_BioDAO();
        Mono_BioDTO dto = new Mono_BioDTO();
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            dto.getEntidad().setNombre(request.getParameter("nombre"));
            dto.getEntidad().setClave(request.getParameter("claveMono"));
            dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("precio")));
            dto.getEntidad().getIdCategoria().setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
            try {
                dao.create(dto);
                RequestDispatcher vista = request.getRequestDispatcher("Mono_Bio_Servlet?accion=listaDeMono");
                vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(Mono_Bio_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            dto.getEntidad().setIdmon_bio(Integer.parseInt(request.getParameter("id")));
            dto.getEntidad().setNombre(request.getParameter("nombre"));
            dto.getEntidad().setClave(request.getParameter("claveMono"));
            dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("precio")));
            dto.getEntidad().getIdCategoria().setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
            try {
                dao.update(dto);
                RequestDispatcher vista = request.getRequestDispatcher("Mono_Bio_Servlet?accion=listaDeMono");
                vista.forward(request, response);
            } catch (SQLException | ServletException | IOException ex) {
                Logger.getLogger(Mono_Bio_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private void mostrarMono(HttpServletRequest request, HttpServletResponse response) {
        Mono_BioDAO dao = new Mono_BioDAO();
        Mono_BioDTO dto = new Mono_BioDTO();
        dto.getEntidad().setIdmon_bio(Integer.parseInt(request.getParameter("id")));
        try {
            dao.read(dto);
            request.setAttribute("mono", dto);
            RequestDispatcher vista = request.getRequestDispatcher("mostarMono.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(Mono_Bio_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
