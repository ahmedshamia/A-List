package com.example.maintenanceapplication.DB.Entitys;

import androidx.room.TypeConverters;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.maintenanceapplication.DB.DateConverter;
import java.util.Date;

@TypeConverters({DateConverter.class})

@Entity
public class WorkTasks {
    @PrimaryKey(autoGenerate = true)
    private int SerialNumber;
    private String Mission, TaskDescription, StartTime, EndTime, Duration_Minutes_Hours, EntryName;
    private String ExecutionDate, EntryDate;

    public WorkTasks() {
    }

    public WorkTasks(String mission, String taskDescription, String startTime, String endTime, String duration_Minutes_Hours, String entryName, String executionDate, String entryDate) {
        Mission = mission;
        TaskDescription = taskDescription;
        StartTime = startTime;
        EndTime = endTime;
        Duration_Minutes_Hours = duration_Minutes_Hours;
        EntryName = entryName;
        ExecutionDate = executionDate;
        EntryDate = entryDate;
    }

    public int getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getMission() {
        return Mission;
    }

    public void setMission(String mission) {
        Mission = mission;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        TaskDescription = taskDescription;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getDuration_Minutes_Hours() {
        return Duration_Minutes_Hours;
    }

    public void setDuration_Minutes_Hours(String duration_Minutes_Hours) {
        Duration_Minutes_Hours = duration_Minutes_Hours;
    }

    public String getEntryName() {
        return EntryName;
    }

    public void setEntryName(String entryName) {
        EntryName = entryName;
    }

    public String getExecutionDate() {
        return ExecutionDate;
    }

    public void setExecutionDate(String executionDate) {
        ExecutionDate = executionDate;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }
}
