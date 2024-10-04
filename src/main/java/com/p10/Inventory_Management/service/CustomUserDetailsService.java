package com.p10.Inventory_Management.service;

import com.p10.Inventory_Management.entity.Employee;
import com.p10.Inventory_Management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException{
        Employee employee = employeeRepository.findById(Long.parseLong(empId))
                .orElseThrow(()->new UsernameNotFoundException("User not found with id: "+empId));

        GrantedAuthority authority = new SimpleGrantedAuthority(employee.getRole());
        System.out.println(employee.getRole());
        System.out.println(authority);

        return new User(employee.getEmpId().toString(), "", Collections.singletonList(authority));
    }
}
