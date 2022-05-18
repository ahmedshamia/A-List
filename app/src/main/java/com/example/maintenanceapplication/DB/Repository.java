package com.example.maintenanceapplication.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.example.maintenanceapplication.DB.Daos.MechanismsDao;
import com.example.maintenanceapplication.DB.Entitys.MaterialsUsed;
import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.Entitys.ReferenceMaterial;

import com.example.maintenanceapplication.DB.Daos.OrdersDao;
import com.example.maintenanceapplication.DB.Daos.ReferenceMaterialDao;
import com.example.maintenanceapplication.DB.Daos.WorkTasksDao;
import com.example.maintenanceapplication.DB.Daos.MaterialsUsedDao;
import com.example.maintenanceapplication.DB.Entitys.WorkTasks;


import java.util.Date;
import java.util.List;

public class Repository {
    private OrdersDao OrdersDao;
    private ReferenceMaterialDao ReferenceMaterialDao;
    private WorkTasksDao WorkTasksDao;
    private MaterialsUsedDao MaterialsUsedDao;
    private MechanismsDao MechanismsDao;


    public Repository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        OrdersDao = db.OrdersDao();
        ReferenceMaterialDao = db.ReferenceMaterialDao();
        WorkTasksDao = db.WorkTasksDao();
        MaterialsUsedDao = db.MaterialsUsedDao();
        MechanismsDao = db.MechanismsDao();

    }

    //Orders

    public void insertOrder(Orders... Orders) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                OrdersDao.insertOrder(Orders);
            }
        });
    }

    public void UpdateOrder(Orders... Orders) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                OrdersDao.UpdateOrders(Orders);
            }
        });
    }

    public void deleteOrder(Orders... Orders) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                OrdersDao.deleteOrder(Orders);
            }
        });
    }

    public LiveData<List<Orders>> getAllOrders() {
        return OrdersDao.getAllOrders();
    }


    public LiveData<List<Orders>> findOrdersWithName(String search) {
        return OrdersDao.findOrdersWithName(search);
    }

    public List<Orders> findOrdersWithEntrance(String search) {
        return OrdersDao.findOrdersWithEntrance(search);

    }






    public List<Orders> loadOrdersFromRegions(List<String> regions) {
        return OrdersDao.loadOrdersFromRegions(regions);

    }

    public List<Orders> loadOrdersFromPlaces(List<String> places) {
        return OrdersDao.loadOrdersFromPlaces(places);

    }

    public List<Orders> loadOrdersFromSignalTypes(List<String> signalTypes) {
        return OrdersDao.loadOrdersFromSignalTypes(signalTypes);

    }

    public List<Orders> loadOrdersFromConverters(List<String> converters) {
        return OrdersDao.loadOrdersFromConverters(converters);

    }

    public List<Orders> loadOrdersFromProvinces(List<String> provinces) {
        return OrdersDao.loadOrdersFromProvinces(provinces);

    }


    //MaterialsUsed

    public void insertMaterialsUsed(MaterialsUsed... MaterialsUsed) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                MaterialsUsedDao.insertMaterialsUsed(MaterialsUsed);
            }
        });
    }

    public void UpdateMaterialsUsed(MaterialsUsed... MaterialsUsed) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                MaterialsUsedDao.UpdateMaterialsUsed(MaterialsUsed);
            }
        });
    }

    public void deleteMaterialsUsed(MaterialsUsed... MaterialsUsed) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                MaterialsUsedDao.deleteMaterialsUsed(MaterialsUsed);
            }
        });
    }

    public LiveData<List<MaterialsUsed>> getAllMaterialsUsed() {
        return MaterialsUsedDao.getAllMaterialsUsed();

    }

    //WorkTasks

    public void insertWorkTasks(WorkTasks... WorkTasks) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                WorkTasksDao.insertWorkTask(WorkTasks);
            }
        });
    }

    public void UpdateWorkTasks(WorkTasks... WorkTasks) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                WorkTasksDao.UpdateWorkTask(WorkTasks);
            }
        });
    }

    public void deleteWorkTasks(WorkTasks... WorkTasks) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                WorkTasksDao.deleteWorkTask(WorkTasks);
            }
        });
    }

    public LiveData<List<WorkTasks>> getAllWorkTasks() {
        return WorkTasksDao.getAllWorkTasks();

    }

    //ReferenceMaterial

    public void insertReferenceMaterial(ReferenceMaterial... ReferenceMaterial) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ReferenceMaterialDao.insertReferenceMaterial(ReferenceMaterial);
            }
        });
    }

    public void UpdateReferenceMaterial(ReferenceMaterial... ReferenceMaterial) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ReferenceMaterialDao.UpdateReferenceMaterial(ReferenceMaterial);
            }
        });
    }

    public void deleteReferenceMaterial(ReferenceMaterial... ReferenceMaterial) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ReferenceMaterialDao.deleteReferenceMaterial(ReferenceMaterial);
            }
        });
    }

    public LiveData<List<ReferenceMaterial>> getAllReferenceMaterials() {
        return ReferenceMaterialDao.getAllReferenceMaterials();

    }

    //Mechanisms
    public void insertMechanism(Mechanisms... Mechanisms) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                MechanismsDao.insertMechanism(Mechanisms);
            }
        });
    }

    public void UpdateMechanism(Mechanisms... Mechanisms) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                MechanismsDao.UpdateMechanism(Mechanisms);
            }
        });
    }

    public void deleteMechanism(Mechanisms... Mechanisms) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                MechanismsDao.deleteMechanism(Mechanisms);
            }
        });
    }

    public LiveData<List<Mechanisms>> getAllMechanisms() {
        return MechanismsDao.getAllMechanisms();

    }

    //SERCH


    public LiveData<List<Orders>> findOrdersWithNameAndNum(String search, int num) {
        return OrdersDao.findOrdersWithNameAndNum(search, num);
    }


    public LiveData<List<Orders>> findOrdersStringsFilter(String search, String entranceSearch, String regions, String places, String signalTypes, String converters, String provinces) {
        return OrdersDao.findOrdersStringsFilter(search, entranceSearch, regions, places, signalTypes, converters, provinces);
    }

    public LiveData<List<Orders>> findOrdersIntFilter(int subnum, int signalnum,List<Integer> series) {
        return OrdersDao.findOrdersIntFilter(subnum, signalnum,series);
    }

    public LiveData<List<Orders>>  findOrdersDatesFilter(Date startDate, Date endDate,List<Integer> series){
        return OrdersDao.findOrdersDatesFilter(startDate,endDate,series);
    }


    public LiveData<List<Orders>> findOrdersSubscriptionANDSignalNumberFilter(int subnum, int signalnum){
        return OrdersDao.findOrdersSubscriptionANDSignalNumberFilter(subnum,signalnum);
    }

    public LiveData<List<Orders>> findOrdersWithSubscriptionNumber(int search) {
        return OrdersDao.findOrdersWithSubscriptionNumber(search);

    }

    public LiveData<List<Orders>> findOrdersWithSignalNumber(int signalNumber){
        return OrdersDao.findOrdersWithSignalNumber(signalNumber);


    }

    public LiveData<List<Orders>> OrdersDatesFilter(Date startDate, Date endDate){
        return OrdersDao.OrdersDatesFilter(startDate,endDate);
    }




}
