/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.job;

import dal.DepartmentDBContext;
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

/**
 *
 * @author PhuongNH
 */
public class EditJobController extends HttpServlet {

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
        int jid = Integer.parseInt(request.getParameter("jid"));

        JobDBContext jobDBContext = new JobDBContext();
        DepartmentDBContext departmentDBContext = new DepartmentDBContext();

        ArrayList<Department> departments = departmentDBContext.getAllDepartments();
        Job job = jobDBContext.getJobByJobId(jid);

        request.setAttribute("job", job);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("../view/management/job/edit-job.jsp").forward(request, response);
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
        Job job = new Job();
        job.setJob_id(Integer.parseInt(request.getParameter("job_id")));
        job.setJob_title(request.getParameter("job_title"));
        job.setMin_salary(Double.parseDouble(request.getParameter("min_salary")));
        job.setMax_salary(Double.parseDouble(request.getParameter("max_salary")));
        Department department = new Department();
        department.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
        job.setDepartment(department);
        
        JobDBContext jobDBContext = new JobDBContext();
        jobDBContext.editJob(job);
        
        response.sendRedirect("listAllJobs");
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
