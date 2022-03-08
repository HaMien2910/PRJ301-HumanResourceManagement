/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.job;

import dal.JobDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Job;

/**
 *
 * @author PhuongNH
 */
public class ListAllJobsController extends HttpServlet {

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
        JobDBContext jobDBContext = new JobDBContext();

        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.trim().length() == 0) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 10;
        ArrayList<Job> jobs = jobDBContext.getJobsByPage(page_index, page_size);
        int total_records = jobDBContext.countAll();
        int total_pages = ((total_records % page_size) == 0) ? (total_records / page_size) : (total_records / page_size + 1);

        request.setAttribute("jobs", jobs);
        request.setAttribute("total_pages", total_pages);
        request.setAttribute("page_index", page_index);
        request.setAttribute("index", 0);
        request.getRequestDispatcher("../view/management/job/all-jobs.jsp").forward(request, response);
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

}
