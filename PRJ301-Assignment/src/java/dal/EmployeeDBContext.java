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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.District;
import model.Employee;
import model.Job;
import model.EmployeeContact;
import model.Province;
import model.Ward;
import common.Generator;
import common.Mailler;
import model.Account;

/**
 *
 * @author PhuongNH
 */
public class EmployeeDBContext extends DBContext {

    public ArrayList<Employee> getEmployeesByPageIndex(String message, String order_by, int page_index, int page_size) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "with sampledata AS (SELECT a.[e_id]\n"
                    + "                    ,a.[e_first_name] \n"
                    + "                    ,a.[e_last_name] \n"
                    + "                    ,a.[e_email] \n"
                    + "                    ,a.[e_salary] \n"
                    + "                    ,a.[e_join_date] \n"
                    + "                    ,b.[department_name] \n"
                    + "                    ,c.[job_title]\n"
                    + "                    ,g.[province_name] \n"
                    + "                    ,ROW_NUMBER() OVER (" + order_by + ") AS row_index\n"
                    + "                     FROM \n"
                    + "                     [Employees] AS a\n"
                    + "                    		LEFT JOIN\n"
                    + "                     [Departments] AS b ON a.[department_id] = b.[department_id]\n"
                    + "                    		LEFT JOIN\n"
                    + "                     [Jobs] AS c ON a.[job_id] = c.[job_id]\n"
                    + "                    		LEFT JOIN\n"
                    + "                     [EmployeeContacts] AS d ON a.[contact_id] = d.[contact_id] \n"
                    + "                    		LEFT JOIN\n"
                    + "                     [Wards] AS e ON d.[ward_id] = e.[ward_id]\n"
                    + "                    		LEFT JOIN\n"
                    + "                     [Districts] AS f ON f.[district_id] = e.[district_id]\n"
                    + "                    		LEFT JOIN\n"
                    + "                     [Provinces] AS g ON f.[province_id] = g.[province_id]\n"
                    + "                          WHERE (1 = 1) AND (a.[e_first_name] LIKE '%'+?+'%'\n"
                    + "                                        OR a.[e_last_name] LIKE '%'+?+'%'\n"
                    + "                                        OR b.[department_name] LIKE '%'+?+'%'\n"
                    + "                                        OR g.[province_name] LIKE N'%'+?+'%'\n"
                    + "                                        OR d.[phone] LIKE '%'+?+'%'))\n"
                    + "SELECT * FROM sampledata\n"
                    + "WHERE row_index >= (? - 1) * ? + 1 AND row_index <= ? * ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, message);
            stm.setString(2, message);
            stm.setString(3, message);
            stm.setString(4, message);
            stm.setString(5, message);
            stm.setInt(6, page_index);
            stm.setInt(7, page_size);
            stm.setInt(8, page_index);
            stm.setInt(9, page_size);
            ResultSet rs = stm.executeQuery();

            //Assign the information of all employees to list students
            while (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                EmployeeContact c = new EmployeeContact();
                Ward w = new Ward();
                District dist = new District();
                Province p = new Province();

                e.setE_id(rs.getInt(1)); //[e_id]
                e.setE_first_name(rs.getString(2)); //[e_first_name]
                e.setE_last_name(rs.getString(3)); //[e_last_name]
                e.setE_email(rs.getString(4)); //[e_email]
                e.setE_salary(rs.getDouble(5));
//                c.setE(rs.getString(5));
//                e.setContact(c); //[e_phone]
                e.setE_join_date(rs.getDate(6)); //[e_join_date]

                d.setDepartment_name(rs.getString(7)); //[dapartment_name]
                e.setDepartment(d);

                j.setJob_title(rs.getString(8)); //[job_title]
                e.setJob(j);

                p.setProvince_name(rs.getString(9)); //[province_name]
                dist.setProvince(p);
                w.setDistrict(dist);
                c.setWard(w);
                e.setContact(c);

                //Add info off the employee to list
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            String sql = "SELECT a.[e_id]\n" //1
                    + "      ,a.[e_first_name]\n" //2
                    + "      ,a.[e_last_name]\n" //3
                    + "      ,a.[e_gender]\n" //4
                    + "      ,a.[e_dob]\n" //5
                    + "      ,a.[e_email]\n" //6
                    + "      ,a.[e_join_date]\n" //7
                    + "      ,a.[e_salary]\n" //8
                    + "      ,a.[manager_id]\n" //9
                    + "      ,b.[department_id]\n" //10
                    + "      ,b.[department_name]\n" //11
                    + "      ,b.[manager_id]\n" //12
                    + "      ,c.[job_id]\n" //13
                    + "      ,c.[job_title]\n" //14
                    + "      ,c.[min_salary]\n" //15
                    + "      ,c.[max_salary]\n" //16
                    + "      ,d.[contact_id]\n" //17
                    + "      ,d.[email]\n" //18
                    + "      ,d.[phone]\n" //19
                    + "      ,d.[StreetAddress]\n" //20
                    + "      ,e.[ward_id]\n" //21
                    + "      ,e.[ward_name]\n" //22
                    + "      ,e.[ward_type]\n" //23
                    + "      ,f.[district_id]\n" //24
                    + "      ,f.[district_name]\n" //25
                    + "      ,f.[district_type]\n" //26
                    + "      ,g.[province_id]\n" //27
                    + "      ,g.[province_name]\n" //28
                    + "      ,g.[province_type]\n" //29
                    + "       FROM \n"
                    + "       [Employees] AS a\n"
                    + "       		LEFT JOIN\n"
                    + "       [Departments] AS b ON a.[department_id] = b.[department_id]\n"
                    + "       		LEFT JOIN\n"
                    + "       [Jobs] AS c ON a.[job_id] = c.[job_id]\n"
                    + "       		LEFT JOIN\n"
                    + "       [EmployeeContacts] AS d ON a.[contact_id] = d.[contact_id] \n"
                    + "			LEFT JOIN\n"
                    + "	[Wards] AS e ON d.[ward_id] = e.[ward_id]\n"
                    + "			LEFT JOIN\n"
                    + "	[Districts] AS f ON f.[district_id] = e.[district_id]\n"
                    + "			LEFT JOIN\n"
                    + "	 [Provinces] AS g ON f.[province_id] = g.[province_id]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            //Assign the information of all employees to list students
            while (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                EmployeeContact l = new EmployeeContact();
                Ward w = new Ward();
                District dist = new District();
                Province p = new Province();

                e.setE_id(rs.getInt(1));
                e.setE_first_name(rs.getString(2));
                e.setE_last_name(rs.getString(3));
                e.setE_gender(rs.getBoolean(4));
                e.setE_dob(rs.getDate(5));
                e.setE_email(rs.getString(6));
                e.setE_join_date(rs.getDate(7));
                e.setE_salary(rs.getDouble(8));
                Employee manager = new Employee();
                manager.setE_id(rs.getInt(9));
                e.setManager(manager);

                d.setDepartment_id(rs.getInt(10));
                d.setDepartment_name(rs.getString(11));
                d.setManager(manager);
                e.setDepartment(d);

                j.setJob_id(rs.getInt(13));
                j.setJob_title(rs.getString(14));
                j.setMin_salary(rs.getDouble(15));
                j.setMax_salary(rs.getDouble(16));
                e.setJob(j);

                l.setContact_id(rs.getInt(17));
                l.setEmail(rs.getString(18));
                l.setPhone(rs.getString(19));
                l.setStreet(rs.getString(20));
                w.setWard_id(rs.getString(21));
                w.setWard_name(rs.getString(22));
                w.setWard_type(rs.getString(23));
                dist.setDistrict_id(rs.getString(24));
                dist.setDistrict_name(rs.getString(25));
                dist.setDistrict_type(rs.getString(26));
                p.setProvince_id(rs.getString(27));
                p.setProvince_name(rs.getString(28));
                p.setProvince_type(rs.getString(29));
                dist.setProvince(p);
                w.setDistrict(dist);
                l.setWard(w);
                e.setContact(l);

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
            String sql = "SELECT a.[e_id]\n" //1
                    + "      ,a.[e_first_name]\n" //2
                    + "      ,a.[e_last_name]\n" //3
                    + "      ,a.[e_gender]\n" //4
                    + "      ,a.[e_dob]\n" //5
                    + "      ,a.[e_email]\n" //6
                    + "      ,a.[e_join_date]\n" //7
                    + "      ,a.[e_salary]\n" //8
                    + "      ,a.[manager_id]\n" //9
                    + "      ,b.[department_id]\n" //10
                    + "      ,b.[department_name]\n" //11
                    + "      ,b.[manager_id]\n" //12
                    + "      ,c.[job_id]\n" //13
                    + "      ,c.[job_title]\n" //14
                    + "      ,c.[min_salary]\n" //15
                    + "      ,c.[max_salary]\n" //16
                    + "      ,d.[contact_id]\n" //17
                    + "      ,d.[email]\n" //18
                    + "      ,d.[phone]\n" //19
                    + "      ,d.[StreetAddress]\n" //20
                    + "      ,e.[ward_id]\n" //21
                    + "      ,e.[ward_name]\n" //22
                    + "      ,e.[ward_type]\n" //23
                    + "      ,f.[district_id]\n" //24
                    + "      ,f.[district_name]\n" //25
                    + "      ,f.[district_type]\n" //26
                    + "      ,g.[province_id]\n" //27
                    + "      ,g.[province_name]\n" //28
                    + "      ,g.[province_type]\n" //29
                    + "       FROM \n"
                    + "       [Employees] AS a\n"
                    + "       		LEFT JOIN\n"
                    + "       [Departments] AS b ON a.[department_id] = b.[department_id]\n"
                    + "       		LEFT JOIN\n"
                    + "       [Jobs] AS c ON a.[job_id] = c.[job_id]\n"
                    + "       		LEFT JOIN\n"
                    + "       [EmployeeContacts] AS d ON a.[contact_id] = d.[contact_id] \n"
                    + "			LEFT JOIN\n"
                    + "	[Wards] AS e ON d.[ward_id] = e.[ward_id]\n"
                    + "			LEFT JOIN\n"
                    + "	[Districts] AS f ON f.[district_id] = e.[district_id]\n"
                    + "			LEFT JOIN\n"
                    + "	 [Provinces] AS g ON f.[province_id] = g.[province_id]\n"
                    + "       WHERE a.[e_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            // assign id to the condition in the sql statement
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            //Assign the information of all employees to list students
            if (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                EmployeeContact l = new EmployeeContact();
                Ward w = new Ward();
                District dist = new District();
                Province p = new Province();

                e.setE_id(rs.getInt(1));
                e.setE_first_name(rs.getString(2));
                e.setE_last_name(rs.getString(3));
                e.setE_gender(rs.getBoolean(4));
                e.setE_dob(rs.getDate(5));
                e.setE_email(rs.getString(6));
                e.setE_join_date(rs.getDate(7));
                e.setE_salary(rs.getDouble(8));
                Employee manager = new Employee();
                manager.setE_id(rs.getInt(9));
                e.setManager(manager);

                d.setDepartment_id(rs.getInt(10));
                d.setDepartment_name(rs.getString(11));
                d.setManager(manager);
                e.setDepartment(d);

                j.setJob_id(rs.getInt(13));
                j.setJob_title(rs.getString(14));
                j.setMin_salary(rs.getDouble(15));
                j.setMax_salary(rs.getDouble(16));
                e.setJob(j);

                l.setContact_id(rs.getInt(17));
                l.setEmail(rs.getString(18));
                l.setPhone(rs.getString(19));
                l.setStreet(rs.getString(20));
                w.setWard_id(rs.getString(21));
                w.setWard_name(rs.getString(22));
                w.setWard_type(rs.getString(23));
                dist.setDistrict_id(rs.getString(24));
                dist.setDistrict_name(rs.getString(25));
                dist.setDistrict_type(rs.getString(26));
                p.setProvince_id(rs.getString(27));
                p.setProvince_name(rs.getString(28));
                p.setProvince_type(rs.getString(29));
                dist.setProvince(p);
                w.setDistrict(dist);
                l.setWard(w);
                e.setContact(l);
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
            String sql_insert_contact = "INSERT INTO [EmployeeContacts]\n"
                    + "           ([StreetAddress]\n"
                    + "           ,[Ward_id]\n"
                    + "           ,[phone]\n"
                    + "           ,[email])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_insert_contact = connection.prepareStatement(sql_insert_contact);
            stm_insert_contact.setString(1, employee.getContact().getStreet());
            stm_insert_contact.setString(2, employee.getContact().getWard().getWard_id());
            stm_insert_contact.setString(3, employee.getContact().getPhone());
            stm_insert_contact.setString(4, employee.getContact().getEmail());
            stm_insert_contact.executeUpdate();

            // Get location_id of the employee has been inserted 
            String sql_get_lid = "Select @@Identity as cid";
            PreparedStatement stm_get_lid = connection.prepareStatement(sql_get_lid);
            ResultSet rs = stm_get_lid.executeQuery();
            if (rs.next()) {
                // set location_id for the location of the employee
                employee.getContact().setContact_id(rs.getInt("cid"));
            }

            String sql = "INSERT INTO [Employees]\n"
                    + "           ([e_first_name]\n" //1
                    + "           ,[e_last_name]\n" //2
                    + "           ,[e_gender]\n" //3
                    + "           ,[e_dob]\n" //4
                    + "           ,[e_email]\n" //5
                    + "           ,[job_id]\n" //6
                    + "           ,[e_salary]\n" //7
                    + "           ,[department_id]\n" //8
                    + "           ,[e_join_date]\n" //9
                    + "           ,[manager_id]\n" //10
                    + "           ,[contact_id])\n" //11
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            // Insert information of the employee
            stm.setString(1, employee.getE_first_name());
            stm.setString(2, employee.getE_last_name());
            stm.setBoolean(3, employee.isE_gender());
            stm.setDate(4, employee.getE_dob());
            String e_email = Generator.generateEmailPrivacy(employee.getE_last_name().trim(), employee.getE_first_name().trim());
            stm.setString(5, e_email);
            stm.setInt(6, employee.getJob().getJob_id());
            stm.setDouble(7, employee.getE_salary());
            stm.setInt(8, employee.getDepartment().getDepartment_id());
            java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
            stm.setDate(9, currentDate);
            // Check if the department have manager or not
            if (employee.getDepartment().getManager().getE_id() > 0) {
                stm.setInt(10, employee.getDepartment().getManager().getE_id());
            } else {
                stm.setNull(10, Types.INTEGER);
            }
            stm.setInt(11, employee.getContact().getContact_id());
            stm.executeUpdate();

            // Get e_id 
            String sql_get_e_id = "Select @@Identity as eid";
            PreparedStatement stm_get_e_id = connection.prepareStatement(sql_get_e_id);
            ResultSet rs_stm_get_e_id = stm_get_e_id.executeQuery();
            if (rs_stm_get_e_id.next()) {
                // set location_id for the location of the employee
                employee.setE_id(rs_stm_get_e_id.getInt("eid"));
            }

            //Create Account
            String sql_create_account = "INSERT INTO [Accounts]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[e_id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_create_account = connection.prepareStatement(sql_create_account);
            stm_create_account.setString(1, e_email);
            String password = Generator.generateRandomPassword();
            stm_create_account.setString(2, password);
            stm_create_account.setInt(3, employee.getE_id());
            stm_create_account.executeUpdate();
            Mailler.sendAccount(new Account(e_email, password), employee.getContact().getEmail());

            //Set GroupEmployee
            int department_id = employee.getDepartment().getDepartment_id();
            String sql_set_group_employee = "INSERT INTO [Group_Employees]\n"
                    + "           ([e_id]\n"
                    + "           ,[group_id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm_set_group_employee = connection.prepareStatement(sql_set_group_employee);
            stm_set_group_employee.setInt(1, employee.getE_id());
            if (department_id == 1) {
                stm_set_group_employee.setInt(2, 1);
            } else if (department_id == 2) {
                stm_set_group_employee.setInt(2, 2);
            } else if (department_id == 5) {
                stm_set_group_employee.setInt(2, 3);
            } else {
                stm_set_group_employee.setInt(2, 4);
            }
            stm_set_group_employee.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteEmployeeById(int id) {
        try {
            connection.setAutoCommit(false);

            //Update department if employee is a manager 
            String sql_update_department = "UPDATE [Departments]\n"
                    + "                  SET [manager_id] = ?\n"
                    + "                  WHERE [manager_id] = ?";
            PreparedStatement stm_update_department = connection.prepareStatement(sql_update_department);
            stm_update_department.setNull(1, Types.INTEGER);
            stm_update_department.setInt(2, id);
            stm_update_department.executeLargeUpdate();

            //Update department if employee is a manager 
            String sql_update_employee = "UPDATE [Employees]\n"
                    + "                  SET [manager_id] = ?\n"
                    + "                  WHERE [manager_id] = ?";
            PreparedStatement stm_update_employee = connection.prepareStatement(sql_update_employee);
            stm_update_employee.setNull(1, Types.INTEGER);
            stm_update_employee.setInt(2, id);
            stm_update_employee.executeLargeUpdate();

            EmployeeContact contact = new EmployeeContact();
            String sql_get_contact_id = "SELECT [contact_id]\n"
                    + "  FROM [Employees]\n"
                    + "  WHERE [e_id] = ?";
            PreparedStatement stm_get_contact_id = connection.prepareStatement(sql_get_contact_id);
            stm_get_contact_id.setInt(1, id);
            ResultSet rs_get_contact_id = stm_get_contact_id.executeQuery();
            if (rs_get_contact_id.next()) {
                contact.setContact_id(rs_get_contact_id.getInt(1));
            }

            String sql_delete_account = "DELETE FROM [Accounts]\n"
                    + "      WHERE e_id = ?";
            PreparedStatement stm_delete_account = connection.prepareStatement(sql_delete_account);
            stm_delete_account.setInt(1, id);
            stm_delete_account.executeUpdate();

            String sql_delete_in_group_employee = "DELETE FROM [Group_Employees]\n"
                    + "      WHERE e_id = ?";
            PreparedStatement stm_delete_in_group_employee = connection.prepareStatement(sql_delete_in_group_employee);
            stm_delete_in_group_employee.setInt(1, id);
            stm_delete_in_group_employee.executeUpdate();

            String sql = "DELETE FROM [Employees]\n"
                    + "      WHERE [e_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            // Assign id to the condition in the sql statement
            stm.setInt(1, id);
            stm.executeUpdate();

            String sql_delete_contact = "DELETE FROM [EmployeeContacts]\n"
                    + "      WHERE [contact_id] = ?";
            PreparedStatement stm_delete_location = connection.prepareStatement(sql_delete_contact);
            stm_delete_location.setInt(1, contact.getContact_id());
            stm_delete_location.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateEmployeeById(Employee employee) {
        try {
            connection.setAutoCommit(false);

            String sql_get_employee_before_udpate = "SELECT [department_id]\n"
                    + "                                 FROM [Employees]\n"
                    + "                                 WHERE [e_id] = ?";
            PreparedStatement stm_get_employee_before_udpate = connection.prepareStatement(sql_get_employee_before_udpate);
            stm_get_employee_before_udpate.setInt(1, employee.getE_id());
            ResultSet rs_get_employee_before_udpate = stm_get_employee_before_udpate.executeQuery();
            Employee e = new Employee();
            Department d = new Department();
            if (rs_get_employee_before_udpate.next()) {
                d.setDepartment_id(rs_get_employee_before_udpate.getInt(1));
                e.setDepartment(d);
            }

            // Update information of the employee
            String sql = "UPDATE [Employees]\n"
                    + "   SET [e_first_name] = ?\n" //1
                    + "           ,[e_last_name] = ?\n" //2
                    + "           ,[e_gender]  = ?\n" //3
                    + "           ,[e_dob]  = ?\n" //4
                    + "           ,[e_email] = ?\n" //5
                    + "           ,[job_id] = ?\n" //6
                    + "           ,[e_salary] = ?\n" //7
                    + "           ,[department_id] = ?\n" //8
                    + "           ,[e_join_date] = ?\n" //9
                    + "           ,[manager_id] = ?\n" //10
                    + " WHERE [e_id] = ?"; //12

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, employee.getE_first_name());
            stm.setString(2, employee.getE_last_name());
            stm.setBoolean(3, employee.isE_gender());
            stm.setDate(4, employee.getE_dob());
            stm.setString(5, employee.getE_email());
            // Check if the employee is choosed a job or not
            if (employee.getJob().getJob_id() > 0) {
                stm.setInt(6, employee.getJob().getJob_id());
            } else {
                stm.setNull(6, Types.INTEGER);
            }
            stm.setDouble(7, employee.getE_salary());
            // Check if the employee is choosed a job or not
            if (employee.getDepartment().getDepartment_id() > 0) {
                stm.setInt(8, employee.getDepartment().getDepartment_id());
            } else {
                stm.setNull(8, Types.INTEGER);
            }
            java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
            stm.setDate(9, currentDate);
            // Check if the department have manager or not
            if (employee.getDepartment().getManager().getE_id() > 0) {
                stm.setInt(10, employee.getDepartment().getManager().getE_id());
            } else {
                stm.setNull(10, Types.INTEGER);
            }
            stm.setInt(11, employee.getE_id());
            stm.executeUpdate();

            // Update address of the employee
            String sql_update_location = "UPDATE [EmployeeContacts]\n"
                    + "   SET [StreetAddress] = ?\n"
                    + "      ,[Ward_id] = ?\n"
                    + "      ,[phone] = ?"
                    + " WHERE [contact_id] = ?";
            PreparedStatement stm_update_location = connection.prepareStatement(sql_update_location);
            stm_update_location.setString(1, employee.getContact().getStreet());
            stm_update_location.setString(2, employee.getContact().getWard().getWard_id());
            stm_update_location.setString(3, employee.getContact().getPhone());
            stm_update_location.setInt(4, employee.getContact().getContact_id());
            stm_update_location.executeUpdate();

            //Delete old authorize
            if (e.getDepartment().getDepartment_id() == 1 || e.getDepartment().getDepartment_id() == 2 || e.getDepartment().getDepartment_id() == 5) {
                String sql_delete_authorize_of_manager = "DELETE FROM [Group_Employees]\n"
                        + "      WHERE e_id = ? AND group_id = ?";
                PreparedStatement stm_delete_authorize_of_manager = connection.prepareStatement(sql_delete_authorize_of_manager);
                stm_delete_authorize_of_manager.setInt(1, employee.getE_id());
                if (e.getDepartment().getDepartment_id() == 1) {
                    stm_delete_authorize_of_manager.setInt(2, 1);
                } else if (e.getDepartment().getDepartment_id() == 2) {
                    stm_delete_authorize_of_manager.setInt(2, 2);
                } else if (e.getDepartment().getDepartment_id() == 5) {
                    stm_delete_authorize_of_manager.setInt(2, 3);
                } else {
                    stm_delete_authorize_of_manager.setInt(2, 4);

                }
                stm_delete_authorize_of_manager.executeUpdate();
            }

            //Set GroupEmployee
            int department_id = employee.getDepartment().getDepartment_id();
            String sql_set_group_employee = "INSERT INTO [Group_Employees]\n"
                    + "           ([e_id]\n"
                    + "           ,[group_id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm_set_group_employee = connection.prepareStatement(sql_set_group_employee);
            stm_set_group_employee.setInt(1, employee.getE_id());
            if (department_id == 1) {
                stm_set_group_employee.setInt(2, 1);
            } else if (department_id == 2) {
                stm_set_group_employee.setInt(2, 2);
            } else if (department_id == 5) {
                stm_set_group_employee.setInt(2, 3);
            } else {
                stm_set_group_employee.setInt(2, 4);
            }
            stm_set_group_employee.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
            String sql = "SELECT a.[e_id]\n" //1
                    + "      ,a.[e_first_name]\n" //2
                    + "      ,a.[e_last_name]\n" //3
                    + "      ,a.[e_gender]\n" //4
                    + "      ,a.[e_dob]\n" //5
                    + "      ,a.[e_email]\n" //6
                    + "      ,a.[e_join_date]\n" //7
                    + "      ,a.[e_salary]\n" //8
                    + "      ,a.[manager_id]\n" //9
                    + "      ,b.[department_id]\n" //10
                    + "      ,b.[department_name]\n" //11
                    + "      ,b.[manager_id]\n" //12
                    + "      ,c.[job_id]\n" //13
                    + "      ,c.[job_title]\n" //14
                    + "      ,c.[min_salary]\n" //15
                    + "      ,c.[max_salary]\n" //16
                    + "      ,d.[contact_id]\n" //17
                    + "      ,d.[email]\n" //18
                    + "      ,d.[phone]\n" //19
                    + "      ,d.[StreetAddress]\n" //20
                    + "      ,e.[ward_id]\n" //21
                    + "      ,e.[ward_name]\n" //22
                    + "      ,e.[ward_type]\n" //23
                    + "      ,f.[district_id]\n" //24
                    + "      ,f.[district_name]\n" //25
                    + "      ,f.[district_type]\n" //26
                    + "      ,g.[province_id]\n" //27
                    + "      ,g.[province_name]\n" //28
                    + "      ,g.[province_type]\n" //29
                    + "       FROM \n"
                    + "       [Employees] AS a\n"
                    + "       		LEFT JOIN\n"
                    + "       [Departments] AS b ON a.[department_id] = b.[department_id]\n"
                    + "       		LEFT JOIN\n"
                    + "       [Jobs] AS c ON a.[job_id] = c.[job_id]\n"
                    + "       		LEFT JOIN\n"
                    + "       [EmployeeContacts] AS d ON a.[contact_id] = d.[contact_id] \n"
                    + "			LEFT JOIN\n"
                    + "	[Wards] AS e ON d.[ward_id] = e.[ward_id]\n"
                    + "			LEFT JOIN\n"
                    + "	[Districts] AS f ON f.[district_id] = e.[district_id]\n"
                    + "			LEFT JOIN\n"
                    + "	 [Provinces] AS g ON f.[province_id] = g.[province_id]\n"
                    + "         WHERE b.[department_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, department_id);
            ResultSet rs = stm.executeQuery();

            //Assign the information of all employees to list students
            while (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                EmployeeContact l = new EmployeeContact();
                Ward w = new Ward();
                District dist = new District();
                Province p = new Province();

                e.setE_id(rs.getInt(1));
                e.setE_first_name(rs.getString(2));
                e.setE_last_name(rs.getString(3));
                e.setE_gender(rs.getBoolean(4));
                e.setE_dob(rs.getDate(5));
                e.setE_email(rs.getString(6));
                e.setE_join_date(rs.getDate(7));
                e.setE_salary(rs.getDouble(8));
                Employee manager = new Employee();
                manager.setE_id(rs.getInt(9));
                e.setManager(manager);

                d.setDepartment_id(rs.getInt(10));
                d.setDepartment_name(rs.getString(11));
                d.setManager(manager);
                e.setDepartment(d);

                j.setJob_id(rs.getInt(13));
                j.setJob_title(rs.getString(14));
                j.setMin_salary(rs.getDouble(15));
                j.setMax_salary(rs.getDouble(16));
                e.setJob(j);

                l.setContact_id(rs.getInt(17));
                l.setEmail(rs.getString(18));
                l.setPhone(rs.getString(19));
                l.setStreet(rs.getString(20));
                w.setWard_id(rs.getString(21));
                w.setWard_name(rs.getString(22));
                w.setWard_type(rs.getString(23));
                dist.setDistrict_id(rs.getString(24));
                dist.setDistrict_name(rs.getString(25));
                dist.setDistrict_type(rs.getString(26));
                p.setProvince_id(rs.getString(27));
                p.setProvince_name(rs.getString(28));
                p.setProvince_type(rs.getString(29));
                dist.setProvince(p);
                w.setDistrict(dist);
                l.setWard(w);
                e.setContact(l);

                //Add info off the employee to list
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public int countEmployeesSearchByMessage(String message) {
        try {
            String sql = "SELECT COUNT(*) AS [Amount]\n"
                    + "       FROM\n"
                    + "           	   [Employees] AS a\n"
                    + "        				LEFT JOIN\n"
                    + "           	   [Departments] AS b ON a.[department_id] = b.[department_id]\n"
                    + "        				LEFT JOIN\n"
                    + "           	   [Jobs] AS c ON a.[job_id] = c.[job_id]\n"
                    + "        				LEFT JOIN\n"
                    + "           	   [EmployeeContacts] AS d ON a.[contact_id] = d.[contact_id] \n"
                    + "           			LEFT JOIN\n"
                    + "           	   [Wards] AS e ON d.[ward_id] = e.[ward_id]\n"
                    + "           			LEFT JOIN\n"
                    + "           	   [Districts] AS f ON f.[district_id] = e.[district_id]\n"
                    + "           			LEFT JOIN\n"
                    + "           	   [Provinces] AS g ON f.[province_id] = g.[province_id]\n"
                    + "                     WHERE (1 = 1) AND (a.[e_first_name] LIKE '%'+?+'%'\n"
                    + "                                   OR a.[e_last_name] LIKE '%'+?+'%'\n"
                    + "                                   OR b.[department_name] LIKE '%'+?+'%'\n"
                    + "                                   OR g.[province_name] LIKE '%'+?+'%')";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, message);
            stm.setString(2, message);
            stm.setString(3, message);
            stm.setString(4, message);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Amount");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int countAll() {
        try {
            String sql = "SELECT COUNT(*) FROM [Employees]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Employee getEmployeeByEmail(String username) {
        
        try {
            String sql = "SELECT a.[e_id]\n" //1
                    + "      ,a.[e_first_name]\n" //2
                    + "      ,a.[e_last_name]\n" //3
                    + "      ,a.[e_gender]\n" //4
                    + "      ,a.[e_dob]\n" //5
                    + "      ,a.[e_email]\n" //6
                    + "      ,a.[e_join_date]\n" //7
                    + "      ,a.[e_salary]\n" //8
                    + "      ,a.[manager_id]\n" //9
                    + "      ,b.[department_id]\n" //10
                    + "      ,b.[department_name]\n" //11
                    + "      ,b.[manager_id]\n" //12
                    + "      ,c.[job_id]\n" //13
                    + "      ,c.[job_title]\n" //14
                    + "      ,c.[min_salary]\n" //15
                    + "      ,c.[max_salary]\n" //16
                    + "      ,d.[contact_id]\n" //17
                    + "      ,d.[email]\n" //18
                    + "      ,d.[phone]\n" //19
                    + "      ,d.[StreetAddress]\n" //20
                    + "      ,e.[ward_id]\n" //21
                    + "      ,e.[ward_name]\n" //22
                    + "      ,e.[ward_type]\n" //23
                    + "      ,f.[district_id]\n" //24
                    + "      ,f.[district_name]\n" //25
                    + "      ,f.[district_type]\n" //26
                    + "      ,g.[province_id]\n" //27
                    + "      ,g.[province_name]\n" //28
                    + "      ,g.[province_type]\n" //29
                    + "       FROM \n"
                    + "       [Employees] AS a\n"
                    + "       		LEFT JOIN\n"
                    + "       [Departments] AS b ON a.[department_id] = b.[department_id]\n"
                    + "       		LEFT JOIN\n"
                    + "       [Jobs] AS c ON a.[job_id] = c.[job_id]\n"
                    + "       		LEFT JOIN\n"
                    + "       [EmployeeContacts] AS d ON a.[contact_id] = d.[contact_id] \n"
                    + "			LEFT JOIN\n"
                    + "	[Wards] AS e ON d.[ward_id] = e.[ward_id]\n"
                    + "			LEFT JOIN\n"
                    + "	[Districts] AS f ON f.[district_id] = e.[district_id]\n"
                    + "			LEFT JOIN\n"
                    + "	 [Provinces] AS g ON f.[province_id] = g.[province_id]\n"
                    + "       WHERE a.[e_email] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            // assign id to the condition in the sql statement
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();

            //Assign the information of all employees to list students
            if (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                EmployeeContact l = new EmployeeContact();
                Ward w = new Ward();
                District dist = new District();
                Province p = new Province();

                e.setE_id(rs.getInt(1));
                e.setE_first_name(rs.getString(2));
                e.setE_last_name(rs.getString(3));
                e.setE_gender(rs.getBoolean(4));
                e.setE_dob(rs.getDate(5));
                e.setE_email(rs.getString(6));
                e.setE_join_date(rs.getDate(7));
                e.setE_salary(rs.getDouble(8));
                Employee manager = new Employee();
                manager.setE_id(rs.getInt(9));
                e.setManager(manager);

                d.setDepartment_id(rs.getInt(10));
                d.setDepartment_name(rs.getString(11));
                d.setManager(manager);
                e.setDepartment(d);

                j.setJob_id(rs.getInt(13));
                j.setJob_title(rs.getString(14));
                j.setMin_salary(rs.getDouble(15));
                j.setMax_salary(rs.getDouble(16));
                e.setJob(j);

                l.setContact_id(rs.getInt(17));
                l.setEmail(rs.getString(18));
                l.setPhone(rs.getString(19));
                l.setStreet(rs.getString(20));
                w.setWard_id(rs.getString(21));
                w.setWard_name(rs.getString(22));
                w.setWard_type(rs.getString(23));
                dist.setDistrict_id(rs.getString(24));
                dist.setDistrict_name(rs.getString(25));
                dist.setDistrict_type(rs.getString(26));
                p.setProvince_id(rs.getString(27));
                p.setProvince_name(rs.getString(28));
                p.setProvince_type(rs.getString(29));
                dist.setProvince(p);
                w.setDistrict(dist);
                l.setWard(w);
                e.setContact(l);
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
