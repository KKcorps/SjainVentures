package com.example.kkcorps.sjainventures;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.*;


public class exploreData extends Activity{

    String TAG = "sjainVentures";
    public static TextView info;
    public static SQLiteDatabase database;
    public static LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.exploredata);
        ll = (LinearLayout) findViewById(R.id.linearLayoutdata);
        //database.execSQL("Select * from table Users");
        File dbFile = this.getDatabasePath("test_users.db");
        String DB_PATH = dbFile.getAbsolutePath();
        if(dbFile.exists()){
            Log.i(TAG,DB_PATH);
            try{
                database = SQLiteDatabase.openDatabase(DB_PATH,null, Context.MODE_PRIVATE);
                Cursor cursor = database.query("Users", new String[]{"_id", "email", "name", "dob"}, null, null, null, null, null);
                cursor.moveToFirst();
                if(cursor!=null) {
                    do {

                        info = new TextView(this);
                        int emailno = cursor.getColumnIndex("email");
                        int nameno = cursor.getColumnIndex("name");
                        int dobno = cursor.getColumnIndex("dob");

                        Log.i(TAG,cursor.getString(emailno));
                        String infoString = cursor.getString(emailno) + "," +cursor.getString(nameno) + "," +cursor.getString(dobno) ;
                        info.setText(infoString);
                        ll.addView(info);
                        Log.i(TAG, infoString);
                    }
                    while(cursor.moveToNext() && cursor!=null);
                }
            }catch (SQLiteCantOpenDatabaseException e){
                Log.i(TAG,"Database not Found!");
            }



        }

    }

}


