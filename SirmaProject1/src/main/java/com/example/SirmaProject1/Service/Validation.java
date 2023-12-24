package com.example.SirmaProject1.Service;

import com.example.SirmaProject1.Model.Employee;

import java.util.List;

public class Validation {

    protected  static   boolean AddValidation(Employee employee, List<Employee>employees){

        for (Employee employee1:employees)
        {
            if (employee1.getEmplId()==employee.getEmplId())
            {
                return false;
            }
            if (employee.getStartDate()==null)
            {
                return false;
            }

        }
        return true;
    }

    protected static boolean UpdateDeleteValidation(Employee employee,List<Employee> employees)
    {
        for (Employee employee1:employees){
            if (employee1.getEmplId()==employee.getEmplId())
            {
                return true;
            }

        }

        return false;

    }

}
