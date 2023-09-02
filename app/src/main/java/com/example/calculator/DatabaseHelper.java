package com.example.calculator;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CalculationRecord.class},exportSchema = false,version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    private static final String Database_Name = "CalculationDatabase";

    private static DatabaseHelper Instance;
    public static synchronized DatabaseHelper getDatabase(Context context){
        if(Instance == null){
            Instance = Room.databaseBuilder(context,DatabaseHelper.class,Database_Name)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return Instance;
    }

    public abstract CalculationRecordDao getCalculationRecordDao();
}
