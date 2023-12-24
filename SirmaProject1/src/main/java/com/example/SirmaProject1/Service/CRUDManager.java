package com.example.SirmaProject1.Service;

import com.example.SirmaProject1.Model.Employee;

import java.util.List;

public  class CRUDManager {
    Employee employee;
    public static void RemoveEmployee(int id, List<Employee>employees) {
        for (int i=0;i<employees.size();i++) {
            if (employees.get(i).getEmplId()==id) {
                employees.remove(i);
            }
        }

        }

    public static void  updateEmployee(Employee updatedEmployee,List<Employee>employees) {

        for (Employee employee : employees) {
            if (employee.getEmplId() == updatedEmployee.getEmplId()) {
                employee.setProjectId(updatedEmployee.getProjectId());
                employee.setStartDate(updatedEmployee.getStartDate());
                employee.setEndDate(updatedEmployee.getEndDate());
                break;
            }

        }


    }

    public static void addEmployee(Employee employee,List<Employee>employees) {
        employees.add(employee);
    }


    }


