/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;
import model.Department;
import model.District;
import model.Employee;
import model.Job;
import model.Location;
import model.Province;
import model.Ward;

/**
 *
 * @author PhuongNH
 */
public class EmployeeDBContext extends DBContext {

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        
        try {
            String sql = "SELECT a.[e_id]\n" + //1
                        "      ,a.[e_first_name]\n" + //2
                        "      ,a.[e_last_name]\n" + //3
                        "      ,a.[e_gender]\n" + //4
                        "      ,a.[e_dob]\n" + //5
                        "      ,a.[e_email]\n" + //6
                        "      ,a.[e_phone]\n" +  //7
                        "      ,a.[e_join_date]\n" + //8
                        "      ,a.[e_salary]\n" + //9
                        "      ,a.[manager_id]\n" + //10
                        "      ,b.[department_id]\n" + //11
                        "      ,b.[dapartment_name]\n" + //12
                        "      ,b.[manager_id]\n" + //13
                        "      ,c.[job_id]\n" + //14
                        "      ,c.[job_title]\n" + //15
                        "      ,c.[min_salary]\n" + //16
                        "      ,c.[max_salary]\n" + //17
                        "      ,d.[location_id]\n" + //18
                        "      ,d.[StreetAddress]\n" + //19
                        "      ,e.[ward_id]\n" + //20
                        "      ,e.[ward_name]\n" + //21
                        "      ,e.[ward_type]\n" + //22
                        "      ,f.[district_id]\n" + //23
                        "      ,f.[district_name]\n" + //24
                        "      ,f.[district_type]\n" + //25
                        "      ,g.[province_id]\n" + //26
                        "      ,g.[province_name]\n" + //27
                        "      ,g.[province_type]\n" + //28
                        "       FROM \n" +
                        "       [Employees] AS a\n" +
                        "       		LEFT JOIN\n" +
                        "       [Departments] AS b ON a.[department_id] = b.[department_id]\n" +
                        "       		LEFT JOIN\n" +
                        "       [Jobs] AS c ON a.[job_id] = c.[job_id]\n" +
                        "       		LEFT JOIN\n" +
                        "       [Locations] AS d ON a.[location_id] = d.[location_id] \n" +
                        "			LEFT JOIN\n" +
                        "	[Wards] AS e ON d.[ward_id] = e.[ward_id]\n" +
                        "			LEFT JOIN\n" +
                        "	[Districts] AS f ON f.[district_id] = e.[district_id]\n" +
                        "			LEFT JOIN\n" +
                        "	 [Provinces] AS g ON f.[province_id] = g.[province_id]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            
            //Assign the information of all employees to list students
            while (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                Location l = new Location();
                Ward w = new Ward();
                District dist = new District();
                Province p = new Province();
                
                
                e.setE_id(rs.getInt(1));
                e.setE_first_name(rs.getString(2));
                e.setE_last_name(rs.getString(3));
                e.setE_gender(rs.getBoolean(4));
                e.setE_dob(rs.getDate(5));
                e.setE_email(rs.getString(6));
                e.setE_phone(rs.getString(7));
                e.setE_join_date(rs.getDate(8));
                e.setE_salary(rs.getDouble(9));
                Employee manager = new Employee();
                manager.setE_id(rs.getInt(10));
                e.setManager(manager);
                
                d.setDepartment_id(rs.getInt(11));
                d.setDepartment_name(rs.getString(12));
                d.setManager(manager);
                e.setDepartment(d);
                
                j.setJob_id(rs.getInt(14));
                j.setJob_title(rs.getString(15));
                j.setMin_salary(rs.getDouble(16));
                j.setMax_salary(rs.getDouble(17));
                e.setJob(j);
                
                l.setLocation_id(rs.getInt(18));
                l.setStreet(rs.getString(19));
                w.setWard_id(rs.getString(20));
                w.setWard_name(rs.getString(21));
                w.setWard_type(rs.getString(22));
                dist.setDistrict_id(rs.getString(23));
                dist.setDistrict_name(rs.getString(24));
                dist.setDistrict_type(rs.getString(25));
                p.setProvince_id(rs.getString(26));
                p.setProvince_name(rs.getString(27));
                p.setProvince_type(rs.getString(28));
                dist.setProvince(p);
                w.setDistrict(dist);
                l.setWard(w);
                e.setLocation(l);
                
                //Add info off the employee to list
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public Employee getEmployeeById(int id) {
        
        try {
            String sql = "SELECT a.[e_id]\n" + //1
                        "      ,a.[e_first_name]\n" + //2
                        "      ,a.[e_last_name]\n" + //3
                        "      ,a.[e_gender]\n" + //4
                        "      ,a.[e_dob]\n" + //5
                        "      ,a.[e_email]\n" + //6
                        "      ,a.[e_phone]\n" +  //7
                        "      ,a.[e_join_date]\n" + //8
                        "      ,a.[e_salary]\n" + //9
                        "      ,a.[manager_id]\n" + //10
                        "      ,b.[department_id]\n" + //11
                        "      ,b.[dapartment_name]\n" + //12
                        "      ,b.[manager_id]\n" + //13
                        "      ,c.[job_id]\n" + //14
                        "      ,c.[job_title]\n" + //15
                        "      ,c.[min_salary]\n" + //16
                        "      ,c.[max_salary]\n" + //17
                        "      ,d.[location_id]\n" + //18
                        "      ,d.[StreetAddress]\n" + //19
                        "      ,e.[ward_id]\n" + //20
                        "      ,e.[ward_name]\n" + //21
                        "      ,e.[ward_type]\n" + //22
                        "      ,f.[district_id]\n" + //23
                        "      ,f.[district_name]\n" + //24
                        "      ,f.[district_type]\n" + //25
                        "      ,g.[province_id]\n" + //26
                        "      ,g.[province_name]\n" + //27
                        "      ,g.[province_type]\n" + //28
                        "       FROM \n" +
                        "       [Employees] AS a\n" +
                        "       		LEFT JOIN\n" +
                        "       [Departments] AS b ON a.[department_id] = b.[department_id]\n" +
                        "       		LEFT JOIN\n" +
                        "       [Jobs] AS c ON a.[job_id] = c.[job_id]\n" +
                        "       		LEFT JOIN\n" +
                        "       [Locations] AS d ON a.[location_id] = d.[location_id] \n" +
                        "			LEFT JOIN\n" +
                        "	[Wards] AS e ON d.[ward_id] = e.[ward_id]\n" +
                        "			LEFT JOIN\n" +
                        "	[Districts] AS f ON f.[district_id] = e.[district_id]\n" +
                        "			LEFT JOIN\n" +
                        "	 [Provinces] AS g ON f.[province_id] = g.[province_id]\n" +
                        "       WHERE a.[e_id] = ?" ;
            PreparedStatement stm = connection.prepareStatement(sql);
            // assign id to the condition in the sql statement
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            
            //Assign the information of all employees to list students
            if (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                Location l = new Location();
                Ward w = new Ward();
                District dist = new District();
                Province p = new Province();
                
                
                e.setE_id(rs.getInt(1));
                e.setE_first_name(rs.getString(2));
                e.setE_last_name(rs.getString(3));
                e.setE_gender(rs.getBoolean(4));
                e.setE_dob(rs.getDate(5));
                e.setE_email(rs.getString(6));
                e.setE_phone(rs.getString(7));
                e.setE_join_date(rs.getDate(8));
                e.setE_salary(rs.getDouble(9));
                Employee manager = new Employee();
                manager.setE_id(rs.getInt(10));
                e.setManager(manager);
                
                d.setDepartment_id(rs.getInt(11));
                d.setDepartment_name(rs.getString(12));
                d.setManager(manager);
                e.setDepartment(d);
                
                j.setJob_id(rs.getInt(14));
                j.setJob_title(rs.getString(15));
                j.setMin_salary(rs.getDouble(16));
                j.setMax_salary(rs.getDouble(17));
                e.setJob(j);
                
                l.setLocation_id(rs.getInt(18));
                l.setStreet(rs.getString(19));
                w.setWard_id(rs.getString(20));
                w.setWard_name(rs.getString(21));
                w.setWard_type(rs.getString(22));
                dist.setDistrict_id(rs.getString(23));
                dist.setDistrict_name(rs.getString(24));
                dist.setDistrict_type(rs.getString(25));
                p.setProvince_id(rs.getString(26));
                p.setProvince_name(rs.getString(27));
                p.setProvince_type(rs.getString(28));
                dist.setProvince(p);
                w.setDistrict(dist);
                l.setWard(w);
                e.setLocation(l);
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addEmployee(Employee employee) {
        try {
            connection.setAutoCommit(false);
            
            // Insert address of the employee into location table
            String sql_insert_location = "INSERT INTO [Locations]\n" +
                                        "           ([StreetAddress]\n" +
                                        "           ,[Ward_id])\n" +
                                        "     VALUES\n" +
                                        "           (?\n" +
                                        "           ,?)";
            PreparedStatement stm_insert_location = connection.prepareStatement(sql_insert_location);
            stm_insert_location.setString(1, employee.getLocation().getStreet());
            stm_insert_location.setString(2, employee.getLocation().getWard().getWard_id());
            stm_insert_location.executeUpdate();
            
            // Get location_id of the employee have been inserted 
            String sql_get_lid = "Select @@Identity as lid";
            PreparedStatement stm_get_lid = connection.prepareStatement(sql_get_lid);
            ResultSet rs = stm_get_lid.executeQuery();
            if (rs.next()) {
                // set location_id for the location of the employee
                employee.getLocation().setLocation_id(rs.getInt("lid"));
            }
            
            String sql = "INSERT INTO [Employees]\n" +
            "           ([e_first_name]\n" + //1
            "           ,[e_last_name]\n" + //2
            "           ,[e_gender]\n" + //3
            "           ,[e_dob]\n" + //4
            "           ,[e_email]\n" + //5 
            "           ,[e_phone]\n" + //6
            "           ,[job_id]\n" + //7
            "           ,[e_salary]\n" + //8
            "           ,[department_id]\n" + //9
            "           ,[e_join_date]\n" + //10
            "           ,[manager_id]\n" + //11
            "           ,[location_id])\n" + //12
            "     VALUES\n" +
            "           (?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            // Insert information of the employee
            stm.setString(1, employee.getE_first_name());
            stm.setString(2, employee.getE_last_name());
            stm.setBoolean(3, employee.isE_gender());
            stm.setDate(4, employee.getE_dob());
            stm.setString(5, employee.getE_email());
            stm.setString(6, employee.getE_phone());
            stm.setInt(7, employee.getJob().getJob_id());
            stm.setDouble(8, employee.getE_salary());
            stm.setInt(9, employee.getDepartment().getDepartment_id());
            java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
            stm.setDate(10, currentDate);
            // Check if the department have manager or not
            if(employee.getDepartment().getManager().getE_id() == employee.getDepartment().getManager().getE_id() && employee.getDepartment().getManager().getE_id() > 0){
                stm.setInt(11, employee.getDepartment().getManager().getE_id());   
            }else{
                stm.setNull(11, Types.INTEGER);   
            }
            stm.setInt(12, employee.getLocation().getLocation_id());
            stm.executeUpdate();
            
            
            connection.commit();
       } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } 
        finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteEmployeeById(int id) {
        try {
            String sql = "DELETE FROM [Employees]\n" +
                    "      WHERE [e_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            // Assign id to the condition in the sql statement
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEmployeeById(Employee employee) {
        try {
            connection.setAutoCommit(false);
            
            // Update information of the employee
            String sql = "UPDATE [Employees]\n" +
                    "   SET [e_first_name] = ?\n" + //1
                    "           ,[e_last_name] = ?\n" + //2
                    "           ,[e_gender]  = ?\n" + //3
                    "           ,[e_dob]  = ?\n" + //4
                    "           ,[e_email] = ?\n" + //5 
                    "           ,[e_phone] = ?\n" + //6
                    "           ,[job_id] = ?\n" + //7
                    "           ,[e_salary] = ?\n" + //8
                    "           ,[department_id] = ?\n" + //9
                    "           ,[e_join_date] = ?\n" + //10
                    "           ,[manager_id] = ?\n" + //11
                    " WHERE [e_id] = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, employee.getE_first_name());
            stm.setString(2, employee.getE_last_name());
            stm.setBoolean(3, employee.isE_gender());
            stm.setDate(4, employee.getE_dob());
            stm.setString(5, employee.getE_email());
            stm.setString(6, employee.getE_phone());
            stm.setInt(7, employee.getJob().getJob_id());
            stm.setDouble(8, employee.getE_salary());
            stm.setInt(9, employee.getDepartment().getDepartment_id());
            java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
            stm.setDate(10, currentDate);
            // Check if the department have manager or not
            if(employee.getDepartment().getManager().getE_id() == employee.getDepartment().getManager().getE_id() && employee.getDepartment().getManager().getE_id() > 0){
                stm.setInt(11, employee.getDepartment().getManager().getE_id());   
            }else{
                stm.setNull(11, Types.INTEGER);   
            }
            stm.setInt(12, employee.getE_id());
            stm.executeUpdate();
            
            // Update address of the employee
            String sql_update_location = "UPDATE [Locations]\n" +
                                        "   SET [StreetAddress] = ?\n" +
                                        "      ,[Ward_id] = ?\n" +
                                        " WHERE [location_id] = ?";
            PreparedStatement stm_update_location = connection.prepareStatement(sql_update_location);
            stm_update_location.setString(1, employee.getLocation().getStreet());
            stm_update_location.setString(2, employee.getLocation().getWard().getWard_id());
            stm_update_location.setInt(3, employee.getLocation().getLocation_id());
            stm_update_location.executeUpdate();
            
            
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    ArrayList<Employee> getAllEmployeesByDepartmentId(int department_id) {
        ArrayList<Employee> employees = new ArrayList<>();
        
        try {
            String sql = "SELECT a.[e_id]\n" + //1
                        "      ,a.[e_first_name]\n" + //2
                        "      ,a.[e_last_name]\n" + //3
                        "      ,a.[e_gender]\n" + //4
                        "      ,a.[e_dob]\n" + //5
                        "      ,a.[e_email]\n" + //6
                        "      ,a.[e_phone]\n" +  //7
                        "      ,a.[e_join_date]\n" + //8
                        "      ,a.[e_salary]\n" + //9
                        "      ,a.[manager_id]\n" + //10
                        "      ,b.[department_id]\n" + //11
                        "      ,b.[dapartment_name]\n" + //12
                        "      ,b.[manager_id]\n" + //13
                        "      ,c.[job_id]\n" + //14
                        "      ,c.[job_title]\n" + //15
                        "      ,c.[min_salary]\n" + //16
                        "      ,c.[max_salary]\n" + //17
                        "      ,d.[location_id]\n" + //18
                        "      ,d.[StreetAddress]\n" + //19
                        "      ,e.[ward_id]\n" + //20
                        "      ,e.[ward_name]\n" + //21
                        "      ,e.[ward_type]\n" + //22
                        "      ,f.[district_id]\n" + //23
                        "      ,f.[district_name]\n" + //24
                        "      ,f.[district_type]\n" + //25
                        "      ,g.[province_id]\n" + //26
                        "      ,g.[province_name]\n" + //27
                        "      ,g.[province_type]\n" + //28
                        "       FROM \n" +
                        "       [Employees] AS a\n" +
                        "       		LEFT JOIN\n" +
                        "       [Departments] AS b ON a.[department_id] = b.[department_id]\n" +
                        "       		LEFT JOIN\n" +
                        "       [Jobs] AS c ON a.[job_id] = c.[job_id]\n" +
                        "       		LEFT JOIN\n" +
                        "       [Locations] AS d ON a.[location_id] = d.[location_id] \n" +
                        "			LEFT JOIN\n" +
                        "	[Wards] AS e ON d.[ward_id] = e.[ward_id]\n" +
                        "			LEFT JOIN\n" +
                        "	[Districts] AS f ON f.[district_id] = e.[district_id]\n" +
                        "			LEFT JOIN\n" +
                        "	 [Provinces] AS g ON f.[province_id] = g.[province_id]\n"
                    + "         WHERE b.[department_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, department_id);
            ResultSet rs = stm.executeQuery();
            
            
            //Assign the information of all employees to list students
            while (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                Location l = new Location();
                Ward w = new Ward();
                District dist = new District();
                Province p = new Province();
                
                
                e.setE_id(rs.getInt(1));
                e.setE_first_name(rs.getString(2));
                e.setE_last_name(rs.getString(3));
                e.setE_gender(rs.getBoolean(4));
                e.setE_dob(rs.getDate(5));
                e.setE_email(rs.getString(6));
                e.setE_phone(rs.getString(7));
                e.setE_join_date(rs.getDate(8));
                e.setE_salary(rs.getDouble(9));
                Employee manager = new Employee();
                manager.setE_id(rs.getInt(10));
                e.setManager(manager);
                
                d.setDepartment_id(rs.getInt(11));
                d.setDepartment_name(rs.getString(12));
                d.setManager(manager);
                e.setDepartment(d);
                
                j.setJob_id(rs.getInt(14));
                j.setJob_title(rs.getString(15));
                j.setMin_salary(rs.getDouble(16));
                j.setMax_salary(rs.getDouble(17));
                e.setJob(j);
                
                l.setLocation_id(rs.getInt(18));
                l.setStreet(rs.getString(19));
                w.setWard_id(rs.getString(20));
                w.setWard_name(rs.getString(21));
                w.setWard_type(rs.getString(22));
                dist.setDistrict_id(rs.getString(23));
                dist.setDistrict_name(rs.getString(24));
                dist.setDistrict_type(rs.getString(25));
                p.setProvince_id(rs.getString(26));
                p.setProvince_name(rs.getString(27));
                p.setProvince_type(rs.getString(28));
                dist.setProvince(p);
                w.setDistrict(dist);
                l.setWard(w);
                e.setLocation(l);
                
                //Add info off the employee to list
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }
}
