package com.example.maintenanceapplication.DB.Entitys;

import androidx.room.TypeConverters;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.example.maintenanceapplication.DB.DateConverter;

import java.io.Serializable;
import java.util.Date;

@TypeConverters({DateConverter.class})

@Entity
public class Orders implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int Series;
    private int SignalNumber;
    private Date Date;
    private int MobileNumber, SubscriptionNumber;
    private String Problem, Name, Place, Converter, SignalType, Entrance, Region, Province;

    public Orders() {
    }




    public Orders(int signalNumber,int subscriptionNumber, String name, String place, String converter, String signalType, String entrance, String region, String province,Date date) {
        SignalNumber = signalNumber;
        SubscriptionNumber = subscriptionNumber;
        Name = name;
        Place = place;
        Converter = converter;
        SignalType = signalType;
        Entrance = entrance;
        Region = region;
        Province = province;
        Date =date;
    }

    public Orders(int signalNumber, Date date, int mobileNumber, int subscriptionNumber, String problem, String name, String place, String converter, String signalType, String entrance, String region, String province) {
        SignalNumber = signalNumber;
        Date = date;
        MobileNumber = mobileNumber;
        SubscriptionNumber = subscriptionNumber;
        Problem = problem;
        Name = name;
        Place = place;
        Converter = converter;
        SignalType = signalType;
        Entrance = entrance;
        Region = region;
        Province = province;
    }

    public int getSeries() {
        return Series;
    }

    public void setSeries(int series) {
        Series = series;
    }

    public int getSignalNumber() {
        return SignalNumber;
    }

    public void setSignalNumber(int signalNumber) {
        SignalNumber = signalNumber;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public int getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public int getSubscriptionNumber() {
        return SubscriptionNumber;
    }

    public void setSubscriptionNumber(int subscriptionNumber) {
        SubscriptionNumber = subscriptionNumber;
    }

    public String getProblem() {
        return Problem;
    }

    public void setProblem(String problem) {
        Problem = problem;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getConverter() {
        return Converter;
    }

    public void setConverter(String converter) {
        Converter = converter;
    }

    public String getSignalType() {
        return SignalType;
    }

    public void setSignalType(String signalType) {
        SignalType = signalType;
    }

    public String getEntrance() {
        return Entrance;
    }

    public void setEntrance(String entrance) {
        Entrance = entrance;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }
}

