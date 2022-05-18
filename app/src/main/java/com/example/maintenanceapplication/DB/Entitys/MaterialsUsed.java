package com.example.maintenanceapplication.DB.Entitys;

import androidx.room.TypeConverters;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class MaterialsUsed {
    @PrimaryKey(autoGenerate = true)
    private int Series;
    private int ItemNo, Unit, Reference, used;
    private double cost, Quantity;
    private String ItemName, ItemStatus;

    public MaterialsUsed() {
    }

    public MaterialsUsed( int itemNo, int unit, int reference, int used, double cost, double quantity, String itemName, String itemStatus) {
        ItemNo = itemNo;
        Unit = unit;
        Reference = reference;
        this.used = used;
        this.cost = cost;
        Quantity = quantity;
        ItemName = itemName;
        ItemStatus = itemStatus;
    }



    public void setSeries(int series) {

        Series = series;
    }

    public int getSeries() {
        return Series;
    }

    public int getItemNo() {
        return ItemNo;
    }

    public void setItemNo(int itemNo) {
        ItemNo = itemNo;
    }

    public int getUnit() {
        return Unit;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }

    public int getReference() {
        return Reference;
    }

    public void setReference(int reference) {
        Reference = reference;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemStatus() {
        return ItemStatus;
    }

    public void setItemStatus(String itemStatus) {
        ItemStatus = itemStatus;
    }
}
