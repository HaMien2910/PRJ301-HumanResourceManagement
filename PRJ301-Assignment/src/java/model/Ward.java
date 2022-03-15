/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author PhuongNH
 */
public class Ward {
    private String ward_id;
    private String ward_name;
    private String ward_type;
    private District district;
    private ArrayList<EmployeeContact> locations = new ArrayList<>();

    public String getWard_id() {
        return ward_id;
    }

    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getWard_type() {
        return ward_type;
    }

    public void setWard_type(String ward_type) {
        this.ward_type = ward_type;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public ArrayList<EmployeeContact> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<EmployeeContact> locations) {
        this.locations = locations;
    }
}
