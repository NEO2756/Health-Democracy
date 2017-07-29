package me.ritabrata.myhealth.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import me.ritabrata.myhealth.R;
import me.ritabrata.myhealth.helpers.ConfigHelper;
import me.ritabrata.myhealth.helpers.TaskCallback;
import me.ritabrata.myhealth.models.UserModel;

public class RegistrationActivity extends Activity {

    private EditText myRegNameEditText,myRegMobileEditText,myRegDobEditText,myRegAddressEditText,myRegBloodGroupEditText,myRegFromEditText;
    private Button myRegButton;
    private int mYear, mMonth, mDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        myRegNameEditText = (EditText) findViewById(R.id.myRegNameEditText);
        myRegMobileEditText = (EditText) findViewById(R.id.myRegMobileEditText);
        myRegDobEditText = (EditText) findViewById(R.id.myRegDobEditText);
        myRegAddressEditText = (EditText) findViewById(R.id.myRegAddressEditText);
        myRegBloodGroupEditText = (EditText) findViewById(R.id.myRegBloodGroupEditText);
        myRegFromEditText = (EditText) findViewById(R.id.myRegFromEditText);

        myRegButton = (Button) findViewById(R.id.myRegButton);

    }


    public void doRegisterUser(View v)
    {

        UserModel.registerNewUser(myRegNameEditText.getText().toString(),myRegMobileEditText.getText().toString(),myRegAddressEditText.getText().toString(),myRegDobEditText.getText().toString(),myRegBloodGroupEditText.getText().toString(),myRegFromEditText.getText().toString(), new TaskCallback(){

            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);

                ConfigHelper.myUser.name = myRegNameEditText.getText().toString();
                ConfigHelper.myUser.mobileNo= myRegMobileEditText.getText().toString();
                ConfigHelper.myUser.address=myRegAddressEditText.getText().toString();
                ConfigHelper.myUser.dob=myRegDobEditText.getText().toString();
                ConfigHelper.myUser.bloodGroup=myRegBloodGroupEditText.getText().toString();
                ConfigHelper.myUser.from=myRegFromEditText.getText().toString();


            if(!(ConfigHelper.myUserID.isEmpty())) {
                UserModel.getPatientDetail(new TaskCallback() {

                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);

                        ConfigHelper.setUserID(ConfigHelper.myUserID, getApplicationContext());
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                    }

                    @Override
                    public void onError(String response) {
                        super.onError(response);

                    }

                });
            }
            else{
                Toast.makeText(RegistrationActivity.this, "Null Patient ID", Toast.LENGTH_SHORT).show();
            }



            }

            @Override
            public void onError(String response) {
                super.onError(response);

            }

        });

      //  startActivity(new Intent(getApplicationContext(), HomeActivity.class));

    }


    public void doSelectDOB(View v)
    {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        myRegDobEditText.setText(dayOfMonth + "" + (monthOfYear + 1) + "" + year);
                        ConfigHelper.userAge= mYear-year;

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();



    }
}
