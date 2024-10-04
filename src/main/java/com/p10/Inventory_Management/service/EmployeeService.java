package com.p10.Inventory_Management.service;

import com.p10.Inventory_Management.dao.EmployeeDAO;
import com.p10.Inventory_Management.dto.EmployeeDTO;
import com.p10.Inventory_Management.entity.Employee;
import com.p10.Inventory_Management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDAO employeeDAO;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapToEntity(employeeDTO);
        Employee savedEmployee = employeeDAO.save(employee);
        return mapToDTO(savedEmployee);
    }

    public Employee mapToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmpId(employeeDTO.getEmpId());
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setTeamName(employeeDTO.getTeamName());
        return employee;
    }

    public EmployeeDTO mapToDTO(Employee employee) {
        return new EmployeeDTO(employee.getEmpId(), employee.getEmpName(), employee.getTeamName(), employee.getRole());
    }

}
