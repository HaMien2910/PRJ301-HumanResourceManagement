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
        
        String field = request.getParameter("field");
        String status = request.getParameter("sortIs");
        
        // Sorting the list employees by title
        String order_by = "";
        // If user clicks on a tilte for the first time when the list unsorted or sorted DESC, then list will be sorted ASC
        if (field != null && (status == null || status.length() == 0 || status.equals("DESC"))) {
            status = "ASC";
            order_by += "ORDER BY [" + field + "] " + status;
        } else if(field != null && status.equals("ASC")){ // If user clicks on a tilte when the list sorted ASC, then list will be sorted DESC
            status = "DESC";
            order_by += "ORDER BY [" + field + "] " + status;
        } else{ // Showing the list employees sort by e_id when the page loaded 
            order_by += "ORDER BY [e_id] ASC";
        }

        // Searching employees by e_first_name, e_last_name or department_name
        message = (message == null) ? "" : message.trim();
        if (raw_page == null || raw_page.trim().length() == 0) {
            raw_page = "1";
        }
        int page_index = Integer.parseInt(raw_page);
        int page_size = 10;
        
        // Getting all employee from DB
        ArrayList<Employee> employees = employeeDBContext.getEmployeesByPageIndex(message, order_by,page_index, page_size);
        // Counting all of the employees is searched by message by e_first_name, e_last_name or department_name
        int total_records_search_by_message = employeeDBContext.countEmployeesSearchByMessage(message);
        // Counting all employees in DB
        int all_records = employeeDBContext.countAll();
        // Calculating total page
        int total_pages = ((total_records_search_by_message % page_size) == 0) ? (total_records_search_by_message / page_size) : (total_records_search_by_message / page_size + 1);

        request.setAttribute("employees", employees);
        request.setAttribute("total_pages", total_pages);
        request.setAttribute("page_index", page_index);
        request.setAttribute("all_records", all_records);
        request.setAttribute("total_records_search_by_message", total_records_search_by_message);
        request.setAttribute("message", message);
        request.setAttribute("status", status);
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
