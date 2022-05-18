package com.example.maintenanceapplication.DB.Daos;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;


import com.example.maintenanceapplication.DB.DateConverter;
import com.example.maintenanceapplication.DB.Entitys.ReferenceMaterial;

import java.util.List;
@TypeConverters({DateConverter.class})
@Dao
public interface ReferenceMaterialDao {
    @Insert
    void insertReferenceMaterial(ReferenceMaterial... ReferenceMaterial);

    @Update
    void UpdateReferenceMaterial(ReferenceMaterial... ReferenceMaterial);

    @Delete
    void deleteReferenceMaterial(ReferenceMaterial... ReferenceMaterial);


    @Query("select * from ReferenceMaterial")
    LiveData<List<ReferenceMaterial>> getAllReferenceMaterials();
}
