package com.example.SirmaProject1.Service;

import com.example.SirmaProject1.Model.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public  class CSVManager {

    protected static   void saveEmployeesToCSV(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/com/example/SirmaProject1/database.csv"))) {
            // Writing headers to the CSV file
            writer.write("Id,ProjectId,StartDate,EndDate");
            writer.newLine();

            // Writing employee data to the CSV file
            for (Employee employee : employees) {
                String data = String.format("%d,%d,%s,%s",
                        employee.getEmplId(),
                        employee.getProjectId(),
                        employee.getStartDate().toString(), employee.getEndDate().toString());
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static List<Employee> getEmployeesFromCSV(List<Employee> employees) {
        if (!employees.isEmpty()) {
            return employees;
        }
        try {
            String inputStream = new File("src/main/java/com/example/SirmaProject1/database.csv").getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(inputStream));

            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header line
                }
                String[] values = line.split(",");
                Employee employee = new Employee();
                employee.setEmplId(Integer.parseInt(values[0].trim()));
                employee.setProjectId(Integer.parseInt(values[1].trim()));
                employee.setStartDate(LocalDate.parse(values[2].trim()));
                employee.setEndDate(LocalDate.parse(values[3].trim()));
                employees.add(employee);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
