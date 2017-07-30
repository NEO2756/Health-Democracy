package me.ritabrata.myhealth.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import me.ritabrata.myhealth.R;
import me.ritabrata.myhealth.helpers.ConfigHelper;
import me.ritabrata.myhealth.helpers.TaskCallback;
import me.ritabrata.myhealth.models.PrescriptionModel;

public class MedicineStatusActivity extends Activity {
    private String getdate, getTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_status);
        getdate = "30072017";
        getTime = "1215";

    }

    public void doTakenMedicine(View v) {
        String status = "taken";
        PrescriptionModel.sendMedicineTakenLogs(ConfigHelper.notiMedName, getdate, getTime, status, new TaskCallback() {
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                finishAffinity();
            }

            @Override
            public void onError(String response) {
                super.onError(response);
            }
        });
    }

    public void doNotTakenMedicine(View v) {
        PrescriptionModel.sendSMS(ConfigHelper.notiMedName, new TaskCallback() {
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);

                PrescriptionModel.sendMedicineTakenLogs(ConfigHelper.notiMedName, getdate, getTime, "skipped", new TaskCallback() {
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
            }

            @Override
            public void onError(String response) {
                super.onError(response);

            }
        });
    }

}
