package com.example.SirmaProject1.Service;

import com.example.SirmaProject1.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

  static   List<Employee> employees = new ArrayList<>();



    public List<Employee> getEmployees() {

      return   CSVManager.getEmployeesFromCSV(employees);

    }

    public  void saveEmployees(List<Employee>employees) {
       CSVManager.saveEmployeesToCSV(employees);

    }
    public void addEmployee(Employee employee) {
     CRUDManager.addEmployee(employee,employees);
      saveEmployees(employees);
    }


    public void RemoveEmployee(int id) {
        CRUDManager.RemoveEmployee(id,employees);
        saveEmployees(employees);
    }

    public  void updateEmployee(Employee updatedEmployee) {

      CRUDManager.updateEmployee(updatedEmployee,employees);
      saveEmployees(employees);


    }

   public HashMap< Integer,List<Employee>> longestDuo()
   {
     return   Duo.longestDuo(employees);
}

   public   boolean Validation(Employee employee){

        return   Validation.AddValidation(employee,employees);
    }
    public boolean UpdateDeleteValidation(Employee employee)
    {
       return  Validation.UpdateDeleteValidation(employee,employees);
    }



}
