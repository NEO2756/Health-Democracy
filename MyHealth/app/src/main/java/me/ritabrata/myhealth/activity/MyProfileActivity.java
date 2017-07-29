package me.ritabrata.myhealth.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.ritabrata.myhealth.R;
import me.ritabrata.myhealth.helpers.ConfigHelper;

public class MyProfileActivity extends AppCompatActivity {

    private TextView myName,myPhone,myAddress,myDob,myBloodGroup,myTransactionAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myName = (TextView) findViewById(R.id.myName);
        myPhone = (TextView) findViewById(R.id.myPhone);
        myAddress = (TextView) findViewById(R.id.myAddress);
        myDob = (TextView) findViewById(R.id.myDob);
        myBloodGroup = (TextView) findViewById(R.id.myBloodGroup);
        myTransactionAddress = (TextView) findViewById(R.id.myTransactionAddress);

    }


    @Override
    protected void onResume() {
        super.onResume();

        myName.setText(ConfigHelper.myUser.name);
        myPhone.setText(ConfigHelper.myUser.mobileNo);
        myAddress.setText(ConfigHelper.myUser.address);
        myDob.setText(ConfigHelper.myUser.dob);
        myBloodGroup.setText(ConfigHelper.myUser.bloodGroup);
        myTransactionAddress.setText(ConfigHelper.myUser.from);

    }

}
