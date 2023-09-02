package com.example.calculator;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "CalculateRecords")
public class CalculationRecord {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Serial")
    private int serial;

    @ColumnInfo(name = "Record")
    private String calculateRecord;

    @ColumnInfo(name = "answer")
    private String answer;

    public CalculationRecord(int serial, String calculateRecord, String answer) {
        this.serial = serial;
        this.calculateRecord = calculateRecord;
        this.answer = answer;
    }

    public int getSerial() {
        return serial;
    }


    public String getCalculateRecord() {
        return calculateRecord;
    }

    public void setCalculateRecord(String calculateRecord) {
        this.calculateRecord = calculateRecord;
    }
    @Ignore
    public CalculationRecord(String calculateRecord, String answer) {
        this.calculateRecord = calculateRecord;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
