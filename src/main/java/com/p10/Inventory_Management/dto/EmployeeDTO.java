package com.p10.Inventory_Management.dto;

import com.p10.Inventory_Management.dao.EmployeeDAO;

public class EmployeeDTO {
    private Long empId;
    private String empName;
    private String teamName;

    public EmployeeDTO(){}

    public EmployeeDTO(Long empId, String empName, String teamName) {
        this.empId = empId;
        this.empName = empName;
        this.teamName = teamName;
    }
    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}


