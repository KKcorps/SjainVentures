package com.example.kkcorps.sjainventures;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static String TABLE_USERS = "Users";
    public static String COLUMN_ID = "_id";
    public static String COLUMN_EMAIL = "email";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_DOB = "dob";
    public static String EMAIL;
    public static String NAME;
    public static String DOB;
    private static String DATABASE_NAME = "test_users.db";
    private static int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USERS + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_EMAIL + " text not null, "+ COLUMN_NAME
            + " text not null, "+ COLUMN_DOB + " text not null);" ;


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+ TABLE_USERS +"'", null);

        if(cursor==null || cursor.getCount()<=0) {
            database.execSQL(DATABASE_CREATE);
            Log.i("SQL","Table created");
        }
        Log.i("SQL","Table not created");
    }



   public void onInsert(SQLiteDatabase database, String email, String name, String dob){
        EMAIL = email;
        NAME = name;
        DOB = dob;

       final String DATABASE_INSERT = "insert into "
               +TABLE_USERS + "(" + COLUMN_EMAIL +"," + COLUMN_NAME +"," +
               COLUMN_DOB +") values ('" +EMAIL + "','"+NAME + "','"+DOB + "');" ;

        Log.i("SQL", DATABASE_INSERT);
        database.execSQL(DATABASE_INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        Log.i("SQL","Table upgraded");
        Log.i("SQL", DATABASE_CREATE);
        onCreate(db);
    }

}