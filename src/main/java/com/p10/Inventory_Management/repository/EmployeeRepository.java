package com.p10.Inventory_Management.repository;

import com.p10.Inventory_Management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  EmployeeRepository extends JpaRepository<Employee, Long> {

}
