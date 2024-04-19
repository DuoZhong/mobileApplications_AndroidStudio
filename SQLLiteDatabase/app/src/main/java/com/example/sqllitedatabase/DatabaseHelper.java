package com.example.sqllitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static  final String DATABASE_NAME = "Mysqllite";

    static final int DATABASE_VERSION = 1;

    static final String USER_ID="ID";

    static final String USER_NAME = "USERNAME";

    static final String PASSWORD = "PASSWORD";

    static final String TABLENAME = "UserTable";

    static final String CREATE_TABLE_QUERY= "CREATE TABLE "+TABLENAME+"("+USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME+" TEXT NOT NULL,"+PASSWORD+" TEXT NOT NULL);";



    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME+";");
        onCreate(db);

    }
}
