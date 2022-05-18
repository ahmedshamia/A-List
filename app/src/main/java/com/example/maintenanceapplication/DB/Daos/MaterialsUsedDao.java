package com.example.maintenanceapplication.DB.Daos;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;


import com.example.maintenanceapplication.DB.DateConverter;
import com.example.maintenanceapplication.DB.Entitys.MaterialsUsed;

import java.util.List;
@TypeConverters({DateConverter.class})
@Dao
public interface MaterialsUsedDao {

    @Insert
    void insertMaterialsUsed(MaterialsUsed... MaterialsUsed);

    @Update
    void UpdateMaterialsUsed(MaterialsUsed... MaterialsUsed);

    @Delete
    void deleteMaterialsUsed(MaterialsUsed... MaterialsUsed);


    @Query("select * from MaterialsUsed")
    LiveData<List<MaterialsUsed>> getAllMaterialsUsed();
}
