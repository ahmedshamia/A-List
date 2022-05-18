package com.example.maintenanceapplication.DB.Entitys;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mechanisms {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private int serialNumber ;
    private String Mechanisms;

    public Mechanisms() {
    }

    public Mechanisms(String mechanisms) {
        Mechanisms = mechanisms;
    }

    public Mechanisms(int id, int serialNumber, String mechanisms) {
        this.id = id;
        this.serialNumber = serialNumber;
        Mechanisms = mechanisms;
    }

    public Mechanisms(int serialNumber, String mechanisms) {
        this.serialNumber = serialNumber;
        Mechanisms = mechanisms;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMechanisms() {
        return Mechanisms;
    }

    public void setMechanisms(String mechanisms) {
        Mechanisms = mechanisms;
    }
}
