package com.example.maintenanceapplication.DB.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.maintenanceapplication.DB.Entitys.Mechanisms;

import java.util.List;

@Dao
public interface MechanismsDao {
    @Insert
    void insertMechanism(Mechanisms... Mechanisms);

    @Update
    void UpdateMechanism(Mechanisms... Mechanisms);

    @Delete
    void deleteMechanism(Mechanisms... Mechanisms);


    @Query("select * from Mechanisms")
    LiveData<List<Mechanisms>> getAllMechanisms();

}
