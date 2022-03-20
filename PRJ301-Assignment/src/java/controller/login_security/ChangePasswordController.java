/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login_security;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author PhuongNH
 */
public class ChangePasswordController extends HttpServlet {

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
        request.setAttribute("isValidNewPassword", true);
        request.setAttribute("isValidCurrentPassword", true);
        request.setAttribute("isSuccessful", false);
        request.getRequestDispatcher("../view/authentication/change-password.jsp").forward(request, response);
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
        AccountDBContext accountDBContext = new AccountDBContext();
        String raw_current_password = request.getParameter("current_password");
        Account account = (Account) request.getSession().getAttribute("account");
        if (account.getPassword().equals(raw_current_password)) {
            String raw_new_password1 = request.getParameter("new_password1");
            String raw_new_password2 = request.getParameter("new_password2");
            if (raw_new_password1.equals(raw_new_password2)) {
                accountDBContext.changePassword(account.getUsername(), raw_new_password1);
                request.setAttribute("isSuccessful", true);
                request.setAttribute("isValidNewPassword", true);
                request.setAttribute("isValidCurrentPassword", true);
                request.getRequestDispatcher("../view/authentication/change-password.jsp").forward(request, response);
            } else {
                request.setAttribute("isValidCurrentPassword", true);
                request.setAttribute("isValidNewPassword", false);
                request.getRequestDispatcher("../view/authentication/change-password.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("isValidCurrentPassword", false);
            request.setAttribute("isValidNewPassword", true);
            request.getRequestDispatcher("../view/authentication/change-password.jsp").forward(request, response);
        }
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
