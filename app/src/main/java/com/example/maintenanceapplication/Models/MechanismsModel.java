package com.example.maintenanceapplication.Models;

import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.DB.Entitys.Orders;

import java.io.Serializable;

public class MechanismsModel implements Serializable {

    private String mechanism, serialNumber;

    public MechanismsModel() {
    }

    public MechanismsModel(String mechanism) {
        this.mechanism = mechanism;
    }

    //convertToMechanisms
    public Mechanisms convertToMechanisms() {
        return new Mechanisms(Integer.parseInt(serialNumber),mechanism);

    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
