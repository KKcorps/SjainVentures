package com.example.kkcorps.sjainventures;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;



public class Homescreen extends Activity{

    String TAG = "sjainVentures";
    int REQUEST = 101;
    public static TextView nameDisplay;
    public static TextView linkView;
    private String[] mAppTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homescreen);
        ImageView imageView = (ImageView) findViewById(R.id.logo);
        TextView textView = (TextView) findViewById(R.id.textView);

        linkView = (TextView) findViewById(R.id.SJainLink);
        nameDisplay = (TextView) findViewById(R.id.user);

        imageView.setImageResource(R.drawable.logo);
        textView.setText(R.string.sjain_intro);
        linkView.setText("http://sjain.ventures/");

        //nameDisplay.setText(getIntent().getStringExtra("userName"));

        Button button_dms = (Button) findViewById(R.id.button_dms);
        Button button_qea = (Button) findViewById(R.id.button_qea);
        Button button_ngm = (Button) findViewById(R.id.button_ngm);
        Button login = (Button) findViewById(R.id.login);


        button_dms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this,DMS.class);
                Homescreen.this.startActivity(intent);
                Log.i(TAG, "DMS activity started");
            }
        });

        button_qea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this,QEA.class);
                Homescreen.this.startActivity(intent);
                Log.i(TAG, "QEA activity started");
            }
        });

        button_ngm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this,NGM.class);
                Homescreen.this.startActivity(intent);
                Log.i(TAG, "NGM activity started");
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this,EnquiryForm.class);
                startActivityForResult(intent, REQUEST);
                Log.i(TAG, "Enquiry Form activity started");
            }
        });

        linkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sjain.ventures/"));
                startActivity(webIntent);
            }
        });

        //Setting up Navigation Drawer
        mAppTitles = getResources().getStringArray(R.array.activity_list);
        //Log.i(TAG, mAppTitles[0]);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        //Toggle Drawer with action Bar
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.drawable.logo,R.string.openSettings,R.string.closeSettings);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mAppTitles));
        //Log.i(TAG,"String Adapter set");

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        Log.i(TAG,"listener set");
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
            Log.i("Navigation", "In Item click listened method");

        }
    }


    /** Starts activity corresponding to the drawer **/

    private void selectItem(int position) {
        Intent navigationDrawerActivity;
        Log.i(TAG, String.valueOf(position));

        switch (position) {
            case 1:

                navigationDrawerActivity = new Intent(Homescreen.this, DMS.class);
                //navigationDrawerActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(navigationDrawerActivity);
                break;

            case 2:
                navigationDrawerActivity = new Intent(Homescreen.this, QEA.class);
                //navigationDrawerActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(navigationDrawerActivity);
                break;

            case 3:
                navigationDrawerActivity = new Intent(Homescreen.this, NGM.class);
                //navigationDrawerActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(navigationDrawerActivity);
                break;

            default:
                mDrawerLayout.closeDrawer(mDrawerList);
                break;
        }

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawers();

        //setTitle(mPlanetTitles[position]);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if( (requestCode==REQUEST) && (resultCode == Homescreen.RESULT_OK) ){
            nameDisplay.setText("Welcome " + data.getStringExtra("userName"));
            Log.i(TAG, "UserName set");

        }
        else {
            Log.i(TAG,"UserName not set");
        }
    }

    /*    @Override
    public void onClick(View view) {
        Intent intent;
        Log.i(TAG, "Switch Case initiated");
        //intent.setAction(Intent.ACTION_VIEW);
        switch(view.getId()) {
            case R.id.button_dms:
                intent = new Intent(Homescreen.this,DMS.class);
                startActivity(intent);
                Log.i(TAG, "DMS activity started");
                break;

            case R.id.button_qea:
                intent = new Intent(Homescreen.this,DMS.class);
                break;

            case R.id.button_ngm:
                intent = new Intent(Homescreen.this,DMS.class);
                break;

            default:
                Log.i(TAG, "Default activity started");
        }

    }*/

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homescreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.data_menu) {
            Intent intent = new Intent(Homescreen.this, exploreData.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
