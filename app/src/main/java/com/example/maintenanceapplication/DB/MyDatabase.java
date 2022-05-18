package com.example.maintenanceapplication.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.maintenanceapplication.DB.Daos.MaterialsUsedDao;
import com.example.maintenanceapplication.DB.Daos.MechanismsDao;
import com.example.maintenanceapplication.DB.Daos.OrdersDao;
import com.example.maintenanceapplication.DB.Daos.ReferenceMaterialDao;
import com.example.maintenanceapplication.DB.Daos.WorkTasksDao;
import com.example.maintenanceapplication.DB.Entitys.MaterialsUsed;
import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.Entitys.ReferenceMaterial;
import com.example.maintenanceapplication.DB.Entitys.WorkTasks;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Orders.class, MaterialsUsed.class, ReferenceMaterial.class, WorkTasks.class, Mechanisms.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract OrdersDao OrdersDao();
    public abstract MechanismsDao MechanismsDao();

    public abstract ReferenceMaterialDao ReferenceMaterialDao();

    public abstract WorkTasksDao WorkTasksDao();

    public abstract MaterialsUsedDao MaterialsUsedDao();


    private static volatile MyDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =

            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.

            });
        }
    };
}
