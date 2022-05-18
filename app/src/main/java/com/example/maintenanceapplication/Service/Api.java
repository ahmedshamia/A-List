package com.example.maintenanceapplication.Service;

import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.Entitys.ReferenceMaterial;
import com.example.maintenanceapplication.DB.Entitys.WorkTasks;
import com.example.maintenanceapplication.Models.MaterialsModel;
import com.example.maintenanceapplication.Models.MechanismsModel;
import com.example.maintenanceapplication.Models.OrdersModel;
import com.example.maintenanceapplication.Models.WorkTasksModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("b/0EUA")
    Call<List<OrdersModel>> getOrders();

    @GET("b/7C1R")
    Call<List<MechanismsModel>> getMechanisms();

    @GET("b/5MYN")
    Call<List<WorkTasksModel>> getWorkTasks();

    @GET("b/6938")
    Call<List<MaterialsModel>> getMaterials();

}
