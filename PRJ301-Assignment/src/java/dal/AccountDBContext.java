/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import common.Generator;
import common.Mailler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Employee;
import model.EmployeeContact;

/**
 *
 * @author PhuongNH
 */
public class AccountDBContext extends DBContext {

    public Account getAccount(String raw_user, String raw_pass) {
        try {
            String sql = "SELECT a.[username]\n"
                    + "	  ,a.[password]\n"
                    + "   ,b.[e_first_name]\n"
                    + "   ,b.[e_last_name]\n"
                    + "	  ,c.[email]\n"
                    + "  FROM [Accounts] AS a\n"
                    + "			INNER JOIN \n"
                    + "	   [Employees] AS b ON a.e_id = b.e_id\n"
                    + "			INNER JOIN\n"
                    + "	   [EmployeeContacts] as c  ON b.contact_id = c.contact_id\n"
                    + "	   WHERE a.username = ? and a.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, raw_user);
            stm.setString(2, raw_pass);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                Employee employee = new Employee();
                EmployeeContact contact = new EmployeeContact();

                account.setUsername(rs.getString(1));
                account.setPassword(rs.getString(2));
                employee.setE_first_name(rs.getString(3));
                employee.setE_last_name(rs.getString(4));
                contact.setEmail(rs.getString(5));
                employee.setContact(contact);
                account.setEmployee(employee);
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkRole(String username, String url) {
        try {
            String sql = "SELECT COUNT(*)\n"
                    + "  FROM [Accounts] AS a\n"
                    + "			INNER JOIN \n"
                    + "	   [Employees] AS b ON a.e_id = b.e_id\n"
                    + "			INNER JOIN\n"
                    + "	   [Departments] AS c ON b.department_id = c.department_id\n"
                    + "	        INNER JOIN\n"
                    + "	   [Group_Employees] AS d ON b.e_id = d.e_id\n"
                    + "	        INNER JOIN\n"
                    + "	   [Groups] AS e ON d.group_id = e.group_id\n"
                    + "			INNER JOIN\n"
                    + "	   [GroupFeature] AS f ON e.group_id = f.group_id\n"
                    + "	        INNER JOIN \n"
                    + "	   [Features] AS g ON f.feature_id = g.feature_id\n"
                    + "	   WHERE a.username = ? and g.url = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void changePassword(String username, String newPassword) {
        try {
            String sql = "UPDATE [Accounts]\n"
                    + "   SET [password] = ?\n"
                    + " WHERE [username] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setString(2, username);
            stm.executeLargeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isMasterData(Account account) {
        try {
            String sql = "SELECT d.[group_id], a.[username]\n"
                    + "       FROM [Accounts] AS a\n"
                    + "            INNER JOIN \n"
                    + "       [Employees] AS b ON a.e_id = b.e_id\n"
                    + "            INNER JOIN\n"
                    + "       [Departments] AS c ON b.department_id = c.department_id\n"
                    + "            INNER JOIN\n"
                    + "       [Group_Employees] AS d ON b.e_id = d.e_id\n"
                    + "			INNER JOIN\n"
                    + "       [Groups] AS e ON d.group_id = e.group_id\n"
                    + "       WHERE a.username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return (rs.getInt(1) == 1 || rs.getInt(1) == 2 || rs.getInt(1) == 3);
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
