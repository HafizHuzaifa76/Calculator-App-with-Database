package com.example.calculator;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CalculationRecordDao {

    @Insert
    void insert(CalculationRecord calculationRecord);

    @Delete
    void delete(CalculationRecord calculationRecord);

    @Query("SELECT * FROM CalculateRecords")
    List<CalculationRecord> getRecords();
    @Query("SELECT * FROM CalculateRecords WHERE Serial = :serial")
    CalculationRecord getThisRecord(int serial);
}
