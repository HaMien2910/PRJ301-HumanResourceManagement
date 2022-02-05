/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Job;

/**
 *
 * @author PhuongNH
 */
public class JobDBContext extends DBContext {

    public ArrayList<Job> getAllJobs() {
        ArrayList<Job> jobs = new ArrayList<>();
        try {
            String sql = "SELECT [job_id]\n"
                    + "      ,[job_title]\n"
                    + "      ,[min_salary]\n"
                    + "      ,[max_salary]\n"
                    + "  FROM [Jobs]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            //Loop to add all information in list            
            while (rs.next()) {
                Job j = new Job();
                j.setJob_id(rs.getInt(1));
                j.setJob_title(rs.getString(2));
                j.setMin_salary(rs.getDouble(3));
                j.setMax_salary(rs.getDouble(4));
                jobs.add(j);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jobs;
    }
}
