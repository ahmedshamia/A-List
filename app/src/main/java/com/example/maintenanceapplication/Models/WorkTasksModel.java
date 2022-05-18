package com.example.maintenanceapplication.Models;

import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.DB.Entitys.WorkTasks;

import java.io.Serializable;

public class WorkTasksModel implements Serializable {
    private String task, taskDescription, duration, employeeName, startingTime, endingTime,
            workDate, entryDate, serialNumber, orderId;

    public WorkTasksModel() {
    }


    public WorkTasksModel(String task, String taskDescription, String duration, String employeeName, String startingTime, String endingTime, String workDate, String entryDate, String serialNumber, String orderId) {
        this.task = task;
        this.taskDescription = taskDescription;
        this.duration = duration;
        this.employeeName = employeeName;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.workDate = workDate;
        this.entryDate = entryDate;
        this.serialNumber = serialNumber;
        this.orderId = orderId;
    }

    //convertToWorkTasks
    public WorkTasks convertToWorkTasks() {
        return new WorkTasks(task, taskDescription, startingTime, endingTime, duration, employeeName, workDate, entryDate);

    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
