package com.example.maintenanceapplication.DB.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;


import com.example.maintenanceapplication.DB.DateConverter;
import com.example.maintenanceapplication.DB.Entitys.Orders;

import java.util.Date;
import java.util.List;

@TypeConverters({DateConverter.class})
@Dao
public interface OrdersDao {

    @Insert
    void insertOrder(Orders... Orders);

    @Update
    void UpdateOrders(Orders... Orders);

    @Delete
    void deleteOrder(Orders... Orders);


    @Query("select * from Orders")
    LiveData<List<Orders>> getAllOrders();


    @Query("SELECT * FROM Orders WHERE Name like '%'||:search||'%'")
    public LiveData<List<Orders>> findOrdersWithName(String search);

    @Query("SELECT * FROM Orders WHERE Entrance LIKE :search")
    public List<Orders> findOrdersWithEntrance(String search);





    @Query("SELECT * FROM Orders WHERE region IN (:regions)")
    public List<Orders> loadOrdersFromRegions(List<String> regions);

    @Query("SELECT * FROM Orders WHERE Place IN (:places)")
    public List<Orders> loadOrdersFromPlaces(List<String> places);

    @Query("SELECT * FROM Orders WHERE SignalType IN (:signalTypes)")
    public List<Orders> loadOrdersFromSignalTypes(List<String> signalTypes);

    @Query("SELECT * FROM Orders WHERE Converter IN (:converters)")
    public List<Orders> loadOrdersFromConverters(List<String> converters);

    @Query("SELECT * FROM Orders WHERE Province IN (:provinces)")
    public List<Orders> loadOrdersFromProvinces(List<String> provinces);


    @Query("SELECT * FROM Orders WHERE Name like '%'||:search||'%' AND SubscriptionNumber = :num")
    public LiveData<List<Orders>> findOrdersWithNameAndNum(String search, int num);




    @Query("SELECT * FROM Orders WHERE Name like '%'||:search||'%' AND Entrance LIKE '%'||:entranceSearch||'%' AND region like '%'||:regions||'%' AND Place like '%'||:places||'%' AND SignalType like '%'||:signalTypes||'%' AND Converter like '%'||:converters||'%' AND Province like '%'||:provinces||'%'")
    public LiveData<List<Orders>> findOrdersStringsFilter(String search, String entranceSearch, String regions, String places, String signalTypes, String converters, String provinces);

    @Query("SELECT * FROM Orders WHERE SubscriptionNumber = :search")
    public LiveData<List<Orders>> findOrdersWithSubscriptionNumber(int search);

    @Query("SELECT * FROM Orders WHERE SignalNumber = :signalNumber")
    public LiveData<List<Orders>> findOrdersWithSignalNumber(int signalNumber);

    @Query("SELECT * FROM Orders WHERE SubscriptionNumber = :subnum or SignalNumber = :signalnum")
    public LiveData<List<Orders>> findOrdersSubscriptionANDSignalNumberFilter(int subnum, int signalnum);

    @Query("SELECT * FROM Orders WHERE SubscriptionNumber = :subnum or SignalNumber = :signalnum AND Series IN (:series)")
    public LiveData<List<Orders>> findOrdersIntFilter(int subnum, int signalnum, List<Integer> series);




    @Query("SELECT * FROM Orders WHERE Date BETWEEN :startDate AND :endDate AND Series IN (:series)")
    public LiveData<List<Orders>> findOrdersDatesFilter(Date startDate, Date endDate, List<Integer> series);

    @Query("SELECT * FROM Orders WHERE Date BETWEEN :startDate AND :endDate")
    public LiveData<List<Orders>> OrdersDatesFilter(Date startDate, Date endDate);


}
