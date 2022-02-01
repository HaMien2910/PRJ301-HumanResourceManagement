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
import model.District;
import model.Province;
import model.Ward;

/**
 *
 * @author PhuongNH
 */
public class AddressDBContext extends DBContext {

    public ArrayList<Province> getAllProvinces() {
        ArrayList<Province> provinces = new ArrayList<>();
        try {
            String sql = "SELECT [province_id]\n"
                    + "      ,[province_name]\n"
                    + "      ,[province_type]\n"
                    + "  FROM [Provinces]";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Province p = new Province();
                p.setProvince_id(rs.getString(1));
                p.setProvince_name(rs.getString(2));
                p.setProvince_type(rs.getString(3));
                provinces.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return provinces;
    }

    public ArrayList<District> getAllProvincesByProvinceID(String province_id) {
        ArrayList<District> districts = new ArrayList<>();
        try {
            String sql = "SELECT [district_id]\n"
                    + "      ,[district_name]\n"
                    + "      ,[district_type]\n"
                    + "      ,[province_id]\n"
                    + "  FROM [Districts] \n"
                    + "  Where [province_id] = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, province_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                District d = new District();
                d.setDistrict_id(rs.getString(1));
                d.setDistrict_name(rs.getString(2));
                d.setDistrict_type(rs.getString(3));
                Province p = new Province();
                p.setProvince_id(province_id);
                d.setProvince(p);
                districts.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return districts;
    }

    public ArrayList<Ward> getAllWardsByDistrictID(String district_id) {
        ArrayList<Ward> wards = new ArrayList<>();
        try {
            String sql = "SELECT [ward_id]\n"
                    + "      ,[ward_name]\n"
                    + "      ,[ward_type]\n"
                    + "      ,[district_id]\n"
                    + "  FROM [Wards]\n"
                    + "  WHERE [district_id] = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, district_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Ward w = new Ward();
                w.setWard_id(rs.getString(1));
                w.setWard_name(rs.getString(2));
                w.setWard_type(rs.getString(3));
                District d = new District();
                d.setDistrict_id(district_id);
                w.setDistrict(d);
                wards.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wards;
    }

}
