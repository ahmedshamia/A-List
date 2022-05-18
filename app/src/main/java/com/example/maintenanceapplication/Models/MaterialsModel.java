package com.example.maintenanceapplication.Models;

import com.example.maintenanceapplication.DB.Entitys.MaterialsUsed;
import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.DB.Entitys.ReferenceMaterial;

import java.io.Serializable;

public class MaterialsModel implements Serializable {
    private String serialNumber, itemNo, orderId, units, receivedQuantity,
            usedQuantity, backedQuantity, coast, itemStatus, itemName;

    public MaterialsModel() {
    }

    public MaterialsModel(String serialNumber, String itemNo, String orderId, String units, String receivedQuantity, String usedQuantity, String backedQuantity, String coast, String itemStatus, String itemName) {
        this.serialNumber = serialNumber;
        this.itemNo = itemNo;
        this.orderId = orderId;
        this.units = units;
        this.receivedQuantity = receivedQuantity;
        this.usedQuantity = usedQuantity;
        this.backedQuantity = backedQuantity;
        this.coast = coast;
        this.itemStatus = itemStatus;
        this.itemName = itemName;
    }

    //convertToReferenceMaterials
    public ReferenceMaterial convertToReferenceMaterials() {
        return new ReferenceMaterial(Integer.parseInt(itemNo), Integer.parseInt(units), Integer.parseInt(backedQuantity), Integer.parseInt(usedQuantity), itemName, itemStatus, Double.parseDouble(coast), Double.parseDouble(receivedQuantity));

    }

    //convertToMaterialsUsed
    public MaterialsUsed convertToMaterialsUsed() {
        return new MaterialsUsed(Integer.parseInt(itemNo), Integer.parseInt(units), Integer.parseInt(backedQuantity), Integer.parseInt(usedQuantity), Double.parseDouble(coast), Double.parseDouble(receivedQuantity), itemName, itemStatus);

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(String receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public String getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(String usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public String getBackedQuantity() {
        return backedQuantity;
    }

    public void setBackedQuantity(String backedQuantity) {
        this.backedQuantity = backedQuantity;
    }

    public String getCoast() {
        return coast;
    }

    public void setCoast(String coast) {
        this.coast = coast;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
