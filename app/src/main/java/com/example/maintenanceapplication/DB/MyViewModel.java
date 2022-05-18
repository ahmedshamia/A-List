package com.example.maintenanceapplication.DB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.example.maintenanceapplication.DB.Entitys.MaterialsUsed;
import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.Entitys.ReferenceMaterial;
import com.example.maintenanceapplication.DB.Entitys.WorkTasks;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyViewModel extends AndroidViewModel {

    Repository repository;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public MyViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }

    //Orders

    public void insertOrder(Orders... Orders) {
        repository.insertOrder(Orders);
    }

    public void UpdateOrder(Orders... Orders) {
        repository.UpdateOrder(Orders);
    }

    public void deleteOrder(Orders... Orders) {
        repository.deleteOrder(Orders);
    }

    public LiveData<List<Orders>> getAllOrders() {
        return repository.getAllOrders();
    }

    public LiveData<List<Orders>> findOrdersWithName(String search) {
        return repository.findOrdersWithName(search);
    }

    public List<Orders> findOrdersWithEntrance(String search) {
        return repository.findOrdersWithEntrance(search);

    }

    public LiveData<List<Orders>> findOrdersWithSubscriptionNumber(int search) {
        return repository.findOrdersWithSubscriptionNumber(search);

    }



    public List<Orders> loadOrdersFromRegions(List<String> regions) {
        return repository.loadOrdersFromRegions(regions);

    }

    public List<Orders> loadOrdersFromPlaces(List<String> places) {
        return repository.loadOrdersFromPlaces(places);

    }

    public List<Orders> loadOrdersFromSignalTypes(List<String> signalTypes) {
        return repository.loadOrdersFromSignalTypes(signalTypes);

    }

    public List<Orders> loadOrdersFromConverters(List<String> converters) {
        return repository.loadOrdersFromConverters(converters);

    }

    public List<Orders> loadOrdersFromProvinces(List<String> provinces) {
        return repository.loadOrdersFromProvinces(provinces);

    }

    //MaterialsUsed

    public void insertMaterialsUsed(MaterialsUsed... MaterialsUsed) {
        repository.insertMaterialsUsed(MaterialsUsed);
    }

    public void UpdateMaterialsUsed(MaterialsUsed... MaterialsUsed) {
        repository.UpdateMaterialsUsed(MaterialsUsed);
    }

    public void deleteMaterialsUsed(MaterialsUsed... MaterialsUsed) {
        repository.deleteMaterialsUsed(MaterialsUsed);
    }

    public LiveData<List<MaterialsUsed>> getAllMaterialsUsed() {
        return repository.getAllMaterialsUsed();
    }

    //WorkTasks

    public void insertWorkTask(WorkTasks... WorkTasks) {
        repository.insertWorkTasks(WorkTasks);
    }

    public void UpdateWorkTask(WorkTasks... WorkTasks) {
        repository.UpdateWorkTasks(WorkTasks);
    }

    public void deleteWorkTask(WorkTasks... WorkTasks) {
        repository.deleteWorkTasks(WorkTasks);
    }

    public LiveData<List<WorkTasks>> getAllWorkTasks() {
        return repository.getAllWorkTasks();
    }


    //ReferenceMaterial


    public void insertReferenceMaterial(ReferenceMaterial... ReferenceMaterial) {
        repository.insertReferenceMaterial(ReferenceMaterial);
    }

    public void UpdateReferenceMaterial(ReferenceMaterial... ReferenceMaterial) {
        repository.UpdateReferenceMaterial(ReferenceMaterial);
    }

    public void deleteReferenceMaterial(ReferenceMaterial... ReferenceMaterial) {
        repository.deleteReferenceMaterial(ReferenceMaterial);
    }

    public LiveData<List<ReferenceMaterial>> getAllReferenceMaterials() {
        return repository.getAllReferenceMaterials();
    }

    //Mechanisms

    public void insertMechanism(Mechanisms... Mechanisms) {
        repository.insertMechanism(Mechanisms);
    }

    public void UpdateMechanism(Mechanisms... Mechanisms) {
        repository.UpdateMechanism(Mechanisms);
    }

    public void deleteMechanism(Mechanisms... Mechanisms) {
        repository.deleteMechanism(Mechanisms);
    }

    public LiveData<List<Mechanisms>> getAllMechanisms() {
        return repository.getAllMechanisms();
    }

    //serch

    public LiveData<List<Orders>> findOrdersWithNameAndNum(String search, int num) {
        return repository.findOrdersWithNameAndNum(search, num);
    }

    public LiveData<List<Orders>> findOrdersStringsFilter(String search, String entranceSearch, String regions, String places, String signalTypes, String converters, String provinces) {
        return repository.findOrdersStringsFilter(search, entranceSearch, regions, places, signalTypes, converters, provinces);
    }

    public LiveData<List<Orders>> findOrdersIntFilter(int subnum, int signalnum, List<Integer> series) {
        return repository.findOrdersIntFilter(subnum, signalnum, series);
    }

    public LiveData<List<Orders>> findOrdersDatesFilter(Date startDate, Date endDate, List<Integer> series) {
        return repository.findOrdersDatesFilter(startDate, endDate,series);
    }

    public LiveData<List<Orders>> findOrdersSubscriptionANDSignalNumberFilter(int subnum, int signalnum){
        return repository.findOrdersSubscriptionANDSignalNumberFilter(subnum,signalnum);
    }

    public LiveData<List<Orders>> findOrdersWithSignalNumber(int signalNumber){
        return repository.findOrdersWithSignalNumber(signalNumber);
    }

    public LiveData<List<Orders>> OrdersDatesFilter(Date startDate, Date endDate){
        return repository.OrdersDatesFilter(startDate,endDate);
    }
}
