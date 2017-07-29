package me.ritabrata.myhealth.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import me.ritabrata.myhealth.R;
import me.ritabrata.myhealth.helpers.ConfigHelper;
import me.ritabrata.myhealth.helpers.Notificationmassage;
import me.ritabrata.myhealth.helpers.TaskCallback;
import me.ritabrata.myhealth.models.PrescriptionModel;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView myNavName,myNavAddress,myNavMobile,myNavAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),AddPrescriptionActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        myNavName= (TextView) navigationView.getHeaderView(0).findViewById(R.id.myNavName);
        myNavAddress= (TextView) navigationView.getHeaderView(0).findViewById(R.id.myNavAddress);
        myNavMobile= (TextView) navigationView.getHeaderView(0).findViewById(R.id.myNavMobile);
        myNavAge= (TextView) navigationView.getHeaderView(0).findViewById(R.id.myNavAge);

        myNavName.setText(ConfigHelper.myUser.name);
        myNavAddress.setText(ConfigHelper.myUser.address);
        myNavAge.setText(ConfigHelper.myUser.dob);
        myNavMobile.setText(ConfigHelper.myUser.mobileNo);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    public void sendNotificationAlert()
    {
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.MONTH,06);
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.DAY_OF_MONTH,29);
        calendar.set(Calendar.HOUR_OF_DAY,02);
        calendar.set(Calendar.MINUTE, 04);
        calendar.set(Calendar.SECOND, 0);
        // calendar.set(Calendar.AM_PM,Calendar.AM);


        Intent myIntent = new Intent(HomeActivity.this, Notificationmassage.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(HomeActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
    }


    public void doShowMorningList(View v)
    {
        startActivity(new Intent(getApplicationContext(),DayListActivity.class));
    }


    public void doShowMyMedicines(View v)
    {
        ConfigHelper.myPrescriptionList.clear();

        PrescriptionModel.getPrescriptionLogs(new TaskCallback()
        {

            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);

                ArrayList<PrescriptionModel> myList = ConfigHelper.myPrescriptionList;

                startActivity(new Intent(getApplicationContext(),MyMedicinesActivity.class));

            }

            @Override
            public void onError(String response) {
                super.onError(response);

            }

        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_profile) {

            startActivity(new Intent(getApplicationContext(),MyProfileActivity.class));

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_about) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
