package com.example.kkcorps.sjainventures;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class EnquiryForm extends Activity{

    String TAG = "sjainVentures";
    ArrayList<String> details = new ArrayList<String>();
    public static EditText email;
    public static EditText name;
    public static EditText dob;
    public static TextView textView;
    public static SQLiteDatabase db;
    public static MySQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Enquiry Form started");
        setContentView(R.layout.login);

        textView = (TextView) findViewById(R.id.errorView);
        email = (EditText) findViewById(R.id.editTextEmail);
        name = (EditText) findViewById(R.id.editTextName);
        dob = (EditText) findViewById(R.id.editTextDate);

        Button submit = (Button) findViewById(R.id.Submit);
        Button checkServer = (Button) findViewById(R.id.checkserver);

        db = openOrCreateDatabase("test_users.db", Context.MODE_PRIVATE, null);


        helper = new MySQLiteHelper(getApplicationContext());

        //helper.onUpgrade(db,1,1);

        helper.onCreate(db);
        Log.i(TAG, "Database created successfully");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText()==null || name.getText()==null || dob.getText()==null  ){
                        textView.setText("A field has been left incomplete.");
                        return;
                }
                Log.i(TAG, "Database created successfully");

                details.clear();

                String Email = email.getText().toString();
                String Name = name.getText().toString();
                String Dob = dob.getText().toString();
                helper.onInsert(db,Email, Name, Dob);

                Log.i(TAG, "Details Inserted in Database");

                Intent data = new Intent();
                data.putExtra("userName",Name);
                setResult(EnquiryForm.RESULT_OK,data);
                finish();
                Log.i(TAG, "HomeScreen Activity Started");

            }
        });

        checkServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ServerCheck = new Intent(EnquiryForm.this, ServerData.class);
                startActivity(ServerCheck);
            }
        });



    }


}
