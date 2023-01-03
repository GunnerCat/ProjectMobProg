package com.example.projectmobprog.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static final String databaseName = "TheDatabase.db";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, name TEXT, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String name, String password){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("password", password);

        long result = db.insert("users", null, contentValues);
        if(result == -1) return false;//If insert failed

        return true;
    }

    public Boolean checkUsername(String username){
        db = this.getWritableDatabase();
        String q = "SELECT * FROM users WHERE username = '"+ username +"'";
        Cursor cursor = db.rawQuery(q, null);
        if (cursor.getCount() > 0) return true;

        return false;
    }

    public Boolean checkPassword(String username, String password){
        db = this.getWritableDatabase();
        String q = "SELECT * FROM users WHERE username = '"+ username +"' AND password = '"+ password +"'";
        Cursor cursor = db.rawQuery(q, null );
        if(cursor.getCount() > 0) return true;

        return false;
    }

    public void updateAccount(String username, String name, String password){
        db = this.getWritableDatabase();
        String q = "UPDATE users SET password = '"+ password +"', name = '"+ name +"' WHERE username = '"+ username +"'";
        db.execSQL(q);
    }

    public void deleteAccount(String username){
        db = this.getWritableDatabase();
        String q = "DELETE FROM users WHERE username = '"+username+"'";
        db.execSQL(q);
    }
}
