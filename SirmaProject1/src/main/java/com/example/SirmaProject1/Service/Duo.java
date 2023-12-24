package com.example.SirmaProject1.Service;

import com.example.SirmaProject1.Model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Duo {

    private  static   Integer countDaysBetween(LocalDate startDate, LocalDate endDate) {
        Integer daysBetween = 0;
        while (!startDate.isAfter(endDate)) {
            daysBetween++;
            startDate = startDate.plusDays(1);
        }
        return daysBetween;
    }

    protected static HashMap<Integer, List<Employee>> longestDuo(List<Employee> employees) {
        HashMap<Integer, List<Employee>> map = new HashMap<>();
        int maxDaysBetween = 0;
        List<Employee> maxDuo = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            Employee employee1 = employees.get(i);
            for (int j = i + 1; j < employees.size(); j++) {
                Employee employee2 = employees.get(j);

                if (employee1.getEmplId() == employee2.getEmplId() ||
                        employee1.getProjectId() != employee2.getProjectId()) {
                    continue; // Skip if same employee or different projects
                }

                LocalDate overlapStartDate = employee1.getStartDate().isBefore(employee2.getStartDate()) ?
                        employee2.getStartDate() : employee1.getStartDate();
                LocalDate overlapEndDate = employee1.getEndDate().isBefore(employee2.getEndDate()) ?
                        employee1.getEndDate() : employee2.getEndDate();

                if (overlapStartDate.isAfter(overlapEndDate)) {
                    continue; // No overlap
                }

                int daysWorked = countDaysBetween(overlapStartDate, overlapEndDate);
                if (daysWorked > maxDaysBetween) {
                    maxDaysBetween = daysWorked;
                    maxDuo.clear(); // Clear previous duos
                    maxDuo.add(employee1);
                    maxDuo.add(employee2);
                }
            }
        }

        map.put(maxDaysBetween, maxDuo);
        return map;
    }
}
