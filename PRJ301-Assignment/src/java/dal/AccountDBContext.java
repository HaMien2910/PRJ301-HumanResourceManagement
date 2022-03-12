/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author PhuongNH
 */
public class AccountDBContext extends DBContext {

    public Account getAccount(String raw_user, String raw_pass) {
        try {
            String sql = "SELECT [username]\n"
                    + "         ,[password]\n"
                    + "   FROM [Accounts]\n"
                    + "   WHERE [username] = ?\n"
                    + "   AND [password] = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, raw_user);
            stm.setString(2, raw_pass);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString(1), rs.getString(2));
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
                    + "			JOIN\n"
                    + "	   [Departments] AS b ON a.department_id = b.department_id\n"
                    + "			JOIN\n"
                    + "	   [DepartmentFeature] AS c ON b.department_id = c.department_id\n"
                    + "			JOIN\n"
                    + "	   [Feature] AS d ON c.feature_id = d.feature_id\n"
                    + "  WHERE a.[username] = ? AND d.url = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
