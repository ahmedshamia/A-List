package com.example.maintenanceapplication.Models;

import androidx.room.TypeConverters;

import com.example.maintenanceapplication.DB.DateConverter;
import com.example.maintenanceapplication.DB.Entitys.Orders;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@TypeConverters({DateConverter.class})

public class OrdersModel implements Serializable {

    private String citizenName, headquarters, governorate, region, transformer, signalType,
            problem, employeeName, open, serialNumber, signalNumber, subscriptionNumber;
    Date date;


    public OrdersModel() {
    }

    public OrdersModel(String citizenName, String headquarters, String governorate, String region, String transformer, String signalType, String problem, String employeeName, String open, Date date, String serialNumber, String signalNumber, String subscriptionNumber) {
        this.citizenName = citizenName;
        this.headquarters = headquarters;
        this.governorate = governorate;
        this.region = region;
        this.transformer = transformer;
        this.signalType = signalType;
        this.problem = problem;
        this.employeeName = employeeName;
        this.open = open;
        this.date = date;
        this.serialNumber = serialNumber;
        this.signalNumber = signalNumber;
        this.subscriptionNumber = subscriptionNumber;
    }

    //convertToOrder
    public Orders convertToOrder() {
        return new Orders(Integer.parseInt(signalNumber), Integer.parseInt(subscriptionNumber), citizenName, headquarters, transformer, signalType, employeeName, region, governorate, date);

    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTransformer() {
        return transformer;
    }

    public void setTransformer(String transformer) {
        this.transformer = transformer;
    }

    public String getSignalType() {
        return signalType;
    }

    public void setSignalType(String signalType) {
        this.signalType = signalType;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSignalNumber() {
        return signalNumber;
    }

    public void setSignalNumber(String signalNumber) {
        this.signalNumber = signalNumber;
    }

    public String getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(String subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
    }
}