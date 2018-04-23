package com.attendance.work_assistant.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "wirelessqa.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context paramContext)
    {
        super(paramContext, "wirelessqa.db", null, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS addressList(_id INTEGER PRIMARY KEY AUTOINCREMENT,Name STRING,EmployeeId INTEGER,pinyin STRING,RealName STRING,Mobile STRING, Telephone STRING,email STRING,departName STRING,avatarImage STRING,position STRING,sex STRING,address STRING,lastUpdateTime STRING,bigAvatarImage STRING,DepartmentId TEXT,EmployeeNumber TEXT)");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        onCreate(paramSQLiteDatabase);
    }
}
