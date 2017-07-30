package me.ritabrata.myhealth.activity;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.ritabrata.myhealth.R;
import me.ritabrata.myhealth.helpers.ConfigHelper;
import me.ritabrata.myhealth.helpers.TaskCallback;
import me.ritabrata.myhealth.models.PrescriptionModel;

public class AddPrescriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText myMedFromDateEditText, myMedTillDateEditText, myMedNameEditText, myMedDoctorIdEditText;
    private int startmYear, startmMonth, startmDay, startmHour, startmMinute;
    private int endmYear, endmMonth, endmDay;

    private LinearLayout clockFourL, clockThreeL, clockTwoL, clockOneL;

    private EditText myClock1, myClock2, myClock3, myClock4;

    private Spinner myTimesADaySpinner;

    private String myMedTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myMedFromDateEditText = (EditText) findViewById(R.id.myMedFromDateEditText);
        myMedTillDateEditText = (EditText) findViewById(R.id.myMedTillDateEditText);
        myMedNameEditText = (EditText) findViewById(R.id.myMedNameEditText);

        myMedDoctorIdEditText = (EditText) findViewById(R.id.myMedDoctorIdEditText);

        myClock1 = (EditText) findViewById(R.id.myClock1);
        myClock2 = (EditText) findViewById(R.id.myClock2);
        myClock3 = (EditText) findViewById(R.id.myClock3);
        myClock4 = (EditText) findViewById(R.id.myClock4);

        clockOneL = (LinearLayout) findViewById(R.id.clockOneL);
        clockTwoL = (LinearLayout) findViewById(R.id.clockTwoL);
        clockThreeL = (LinearLayout) findViewById(R.id.clockThreeL);
        clockFourL = (LinearLayout) findViewById(R.id.clockFourL);

        myTimesADaySpinner = (Spinner) findViewById(R.id.myTimesADaySpinner);
        myTimesADaySpinner.setOnItemSelectedListener(this);

        List<String> times = new ArrayList<String>();
        times.add("Select Times A Day");
        times.add("1 Time");
        times.add("2 Times");
        times.add("3 Times");
        times.add("4 Times");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myTimesADaySpinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        myMedTimes = item.replaceAll(" .+$", "");

        if (myMedTimes.contentEquals("1")) {
            clockOneL.setVisibility(View.VISIBLE);
            clockTwoL.setVisibility(View.GONE);
            clockThreeL.setVisibility(View.GONE);
            clockFourL.setVisibility(View.GONE);
        } else if (myMedTimes.contentEquals("2")) {
            clockOneL.setVisibility(View.VISIBLE);
            clockTwoL.setVisibility(View.VISIBLE);
            clockThreeL.setVisibility(View.GONE);
            clockFourL.setVisibility(View.GONE);
        } else if (myMedTimes.contentEquals("3")) {
            clockOneL.setVisibility(View.VISIBLE);
            clockTwoL.setVisibility(View.VISIBLE);
            clockThreeL.setVisibility(View.VISIBLE);
            clockFourL.setVisibility(View.GONE);
        } else if (myMedTimes.contentEquals("4")) {
            clockOneL.setVisibility(View.VISIBLE);
            clockTwoL.setVisibility(View.VISIBLE);
            clockThreeL.setVisibility(View.VISIBLE);
            clockFourL.setVisibility(View.VISIBLE);
        } else {

        }


    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void doClockOne(View v) {

        final Calendar c = Calendar.getInstance();

        startmHour = c.get(Calendar.HOUR_OF_DAY);
        startmMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        myClock1.setText(hourOfDay + ":" + minute);
                    }
                }, startmHour, startmMinute, false);
        timePickerDialog.show();

    }

    public void doClockTwo(View v) {

        final Calendar c = Calendar.getInstance();

        startmHour = c.get(Calendar.HOUR_OF_DAY);
        startmMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        myClock1.setText(hourOfDay + ":" + minute);
                    }
                }, startmHour, startmMinute, false);
        timePickerDialog.show();

    }

    public void doClockThree(View v) {

        final Calendar c = Calendar.getInstance();

        startmHour = c.get(Calendar.HOUR_OF_DAY);
        startmMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        myClock1.setText(hourOfDay + ":" + minute);
                    }
                }, startmHour, startmMinute, false);
        timePickerDialog.show();
    }

    public void doClockFour(View v) {

        final Calendar c = Calendar.getInstance();

        startmHour = c.get(Calendar.HOUR_OF_DAY);
        startmMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        myClock1.setText(hourOfDay + ":" + minute);
                    }
                }, startmHour, startmMinute, false);
        timePickerDialog.show();

    }


    public void doSelectStartDate(View v) {
        final Calendar c = Calendar.getInstance();
        startmYear = c.get(Calendar.YEAR);
        startmMonth = c.get(Calendar.MONTH);
        startmDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        myMedFromDateEditText.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);

                    }
                }, startmYear, startmMonth, startmDay);
        datePickerDialog.show();

    }


    public void doSelectEndDate(View v) {
        final Calendar c = Calendar.getInstance();
        endmYear = c.get(Calendar.YEAR);
        endmMonth = c.get(Calendar.MONTH);
        endmDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        myMedTillDateEditText.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);

                    }
                }, endmYear, endmMonth, endmDay);
        datePickerDialog.show();

    }


    public void doSaveMedicine(View v) {

        PrescriptionModel.addPrescription(myMedNameEditText.getText().toString(), myMedTimes, myMedFromDateEditText.getText().toString(), myMedTillDateEditText.getText().toString(), myMedDoctorIdEditText.getText().toString(), myClock1.getText().toString(), myClock2.getText().toString(), myClock3.getText().toString(), myClock4.getText().toString(), new TaskCallback() {
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);

                Toast.makeText(AddPrescriptionActivity.this, "Medicine Added Successfully!", Toast.LENGTH_LONG).show();

                PrescriptionModel.getPrescriptionLogs(new TaskCallback() {
                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
                        finish();
                    }

                    @Override
                    public void onError(String response) {
                        super.onError(response);
                    }
                });
                finish();
            }

            @Override
            public void onError(String response) {
                super.onError(response);
            }

        });

    }
}
