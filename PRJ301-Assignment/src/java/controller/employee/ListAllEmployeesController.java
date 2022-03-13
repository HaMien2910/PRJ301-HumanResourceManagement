/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.employee;

import controller.login_security.BaseAuthenticationController;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;

/**
 *
 * @author PhuongNH
 */
public class ListAllEmployeesController extends BaseAuthenticationController {

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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDBContext employeeDBContext = new EmployeeDBContext();
        String raw_page = request.getParameter("page");
        String message = request.getParameter("message");
        if (message == null) {
            message = "";
        }else{
            message = message.trim();
        }
        if (raw_page == null || raw_page.trim().length() == 0) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 10;
        // Get all employee from DB
        ArrayList<Employee> employees = employeeDBContext.getEmployeesByPageIndex(message, page_index, page_size);
        int total_records_search_by_message = employeeDBContext.countEmployeesSearchByMessage(message);
        int all_records = employeeDBContext.countAll();
        int total_pages = ((total_records_search_by_message % page_size) == 0) ? (total_records_search_by_message / page_size) : (total_records_search_by_message / page_size + 1);

        request.setAttribute("employees", employees);
        request.setAttribute("total_pages", total_pages);
        request.setAttribute("page_index", page_index);
        request.setAttribute("all_records", all_records);
        request.setAttribute("total_records_search_by_message", total_records_search_by_message);
        request.setAttribute("message", message);
        request.setAttribute("index", 0);
        request.getRequestDispatcher("../view/management/employee/all-employees.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
