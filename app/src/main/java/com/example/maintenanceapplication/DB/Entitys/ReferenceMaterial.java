package com.example.maintenanceapplication.DB.Entitys;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ReferenceMaterial {
    @PrimaryKey(autoGenerate = true)
    private int Series;
    private int ItemNo, Unit, Reference, used;
    private String ItemName, ItemStatus;
    private double cost, Quantity;

    public ReferenceMaterial() {
    }

    public ReferenceMaterial( int itemNo, int unit, int reference, int used, String itemName, String itemStatus, double cost, double quantity) {
        ItemNo = itemNo;
        Unit = unit;
        Reference = reference;
        this.used = used;
        ItemName = itemName;
        ItemStatus = itemStatus;
        this.cost = cost;
        Quantity = quantity;
    }

    public int getSeries() {
        return Series;
    }

    public void setSeries(int series) {
        Series = series;
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
}
