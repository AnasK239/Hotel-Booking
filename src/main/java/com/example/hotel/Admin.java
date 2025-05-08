package com.example.hotel;

import java.util.List;

public class Admin extends User {
    private String role;
    private String department;

    Admin (String name, String userName, String email, String password, String phoneNumber, String role, String department) {
        super(name, userName, email, password, phoneNumber);
        this.role = role;
        this.department = department;
    }

    void updateRole(String role) {
        this.role = role;
    }
    void updateDepartment(String department) {
        this.department = department;
    }
    String getRole() {
        return role;
    }
    String getDepartment() {
        return department;
    }


}
