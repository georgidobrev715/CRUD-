package com.example.SirmaProject1.Controller;

import com.example.SirmaProject1.Model.Employee;
import com.example.SirmaProject1.Model.SecondFormValidation;
import com.example.SirmaProject1.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String showEmployeeList(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "index.html"; // Return the name of your Thymeleaf template file
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("newEmployee", new Employee());
        return "AddEmployee.html"; // Name of your Thymeleaf template for adding employees
    }

    @PostMapping("/added")
    public String addEmployee(@ModelAttribute("newEmployee") @Valid Employee newEmployee,
                              BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
           redirectAttributes.addFlashAttribute("hasError", true);

            return "redirect:/add";
        }
        else if(!employeeService.Validation(newEmployee)){
            redirectAttributes.addFlashAttribute("hasError",true);
            return "redirect:/add";
        }
        else {

            employeeService.addEmployee(newEmployee);
            return "redirect:/"; // Redirect to a success page or another appropriate view
        }
    }
    @GetMapping("/update")
    public String showUpdateEmployeeForm(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("updatedEmployee", new Employee());
        return "update.html";

    }


    @PostMapping("/updated")
    public String updateEmployee(@ModelAttribute("Employee") @Valid Employee updatedEmployee,
                                 BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hasError", true);
            return "redirect:/update";
        }
        else if(!employeeService.UpdateDeleteValidation(updatedEmployee)){
            redirectAttributes.addFlashAttribute("hasError",true);
            return "redirect:/update";
        }
        else {
            employeeService.updateEmployee(updatedEmployee);
            return "redirect:/";
        }
    }
    @GetMapping("/delete")
    public  String deleteEmployee(Model model)
    {
        List<Employee> employees = employeeService.getEmployees();
       model.addAttribute("deletedEmployee",new Employee());
       return "delete.html";
    }

    @PostMapping("/deleted")
    public String deleteAndRedirect(@ModelAttribute("Employee") @Validated(SecondFormValidation.class) Employee deletedEmployee,
                                    BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hasError", true);
            return "redirect:/delete";
        }else if(!employeeService.UpdateDeleteValidation(deletedEmployee)){
            redirectAttributes.addFlashAttribute("hasError",true);
            return "redirect:/delete";
        }
        else {
            employeeService.RemoveEmployee(deletedEmployee.getEmplId());
            return "redirect:/";
        }
    }



    @GetMapping("/duoEmployee")
    public  String duoEmployee(Model model)
    {
        List<Employee> employees = employeeService.getEmployees();
        HashMap<Integer,List<Employee>> employeeDuo = employeeService.longestDuo();
        model.addAttribute("days", employeeDuo.keySet().iterator().next());
        model.addAttribute("employees", employeeDuo.values().iterator().next());
        return "duoEmployee.html";
    }


}
