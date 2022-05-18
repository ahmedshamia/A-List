package com.example.maintenanceapplication.DB.Listeners;

import com.example.maintenanceapplication.DB.Entitys.Orders;

import java.util.List;

public interface GetOrderAdapterListener {
    void GetOrders(List<Orders> orders);

}
