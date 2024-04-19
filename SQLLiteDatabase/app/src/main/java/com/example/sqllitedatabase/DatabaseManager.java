package com.example.sqllitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper databaseHelper;

    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context context){
        this.context =context;

    }

    public DatabaseManager open() throws SQLException{
        databaseHelper = new DatabaseHelper(this.context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }


    public  void insert(String username, String password){
        ContentValues temp = new ContentValues();
        temp.put(DatabaseHelper.USER_NAME,username);
        temp.put(DatabaseHelper.PASSWORD, password);
        database.insert(DatabaseHelper.TABLENAME,null,temp);
    }

    public Cursor fetch(){
        String[] columns = {DatabaseHelper.USER_ID, DatabaseHelper.USER_NAME,DatabaseHelper.PASSWORD};
        Cursor cursor =database.query(DatabaseHelper.TABLENAME, columns, null,
                null, null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public int update(int id, String username, String password){
        ContentValues temp =new ContentValues();
        temp.put(DatabaseHelper.USER_NAME, username);
        temp.put(DatabaseHelper.PASSWORD, password);

        int returnval = database.update(DatabaseHelper.TABLENAME,temp,DatabaseHelper.USER_ID+"=="+id,
                null);

        return returnval;

    }

    public int delete(int id){
        database.delete(DatabaseHelper.TABLENAME,DatabaseHelper.USER_ID +"=="+id,null);

        return id;
    }


}
