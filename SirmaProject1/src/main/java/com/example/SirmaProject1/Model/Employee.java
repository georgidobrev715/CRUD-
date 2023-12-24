package com.example.SirmaProject1.Model;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {


    @Positive(groups = SecondFormValidation.class)
    int EmplId;
    @Positive
    int ProjectId;
    @NotNull
    LocalDate StartDate;
    LocalDate EndDate;

    public int getEmplId() {
        return EmplId;
    }

    public void setEmplId(int emplId) {
        EmplId = emplId;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        DateUtil dateUtil = new DateUtil();
        dateUtil.parseDate(startDate.toString());
        StartDate = startDate;
    }

    public LocalDate getEndDate() {
        if(EndDate==null)
        {
            setEndDate(LocalDate.now());
        }
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }
}