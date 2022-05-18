package com.example.maintenanceapplication.DB.Daos;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;


import com.example.maintenanceapplication.DB.DateConverter;
import com.example.maintenanceapplication.DB.Entitys.WorkTasks;

import java.util.List;
@TypeConverters({DateConverter.class})
@Dao
public interface WorkTasksDao {

    @Insert
    void insertWorkTask(WorkTasks... WorkTasks);

    @Update
    void UpdateWorkTask(WorkTasks... WorkTasks);

    @Delete
    void deleteWorkTask(WorkTasks... WorkTasks);


    @Query("select * from WorkTasks")
    LiveData<List<WorkTasks>> getAllWorkTasks();
}
