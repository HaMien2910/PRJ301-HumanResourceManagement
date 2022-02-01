/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.employee;

import com.google.gson.Gson;
import dal.DepartmentDBContext;
import dal.AddressDBContext;
import dal.JobDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Department;
import model.Job;
import model.Province;

/**
 *
 * @author PhuongNH
 */
public class AddEmployeeController extends HttpServlet {

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
        DepartmentDBContext departmentDBContext = new DepartmentDBContext();
        AddressDBContext provinceDBContext = new AddressDBContext();
        JobDBContext jobDBContext = new JobDBContext();

        ArrayList<Department> departments = departmentDBContext.getAllDepartments();
        ArrayList<Province> provinces = provinceDBContext.getAllProvinces();
        ArrayList<Job> jobs = jobDBContext.getAllJobs();

        request.setAttribute("departments", departments);
        request.setAttribute("provinces", provinces);
        request.setAttribute("jobs", jobs);

        request.getRequestDispatcher("../view/management/employee/add-employee.jsp").forward(request, response);
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
