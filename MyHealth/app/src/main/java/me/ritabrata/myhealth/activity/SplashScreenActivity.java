package me.ritabrata.myhealth.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.ritabrata.myhealth.R;
import me.ritabrata.myhealth.helpers.ConfigHelper;
import me.ritabrata.myhealth.helpers.TaskCallback;
import me.ritabrata.myhealth.models.UserModel;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    private int successCount = 0, errorCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

//                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//                finish();
            }
        }, SPLASH_TIME_OUT);


    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!(ConfigHelper.getUserID(this).isEmpty())) {

            ConfigHelper.myUserID= ConfigHelper.getUserID(this);

            syncAll();

        }

        else
        {
            startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
        }

    }


    public void goHome() {
        if (successCount + errorCount == 1) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finishAffinity();
        }
    }

    public void syncAll() {

        UserModel.getPatientDetail(new TaskCallback()
        {
            @Override
            public void onSuccess(String apiResponse) {
                super.onSuccess(apiResponse);
                onSyncSuccess();
            }

            @Override
            public void onError(String apiResponse) {
                super.onError(apiResponse);
                onSyncError();
            }
        });

    }

    public void onSyncSuccess() {
        successCount += 1;
        goHome();
    }

    public void onSyncError() {
        errorCount += 1;
        goHome();
    }

}
