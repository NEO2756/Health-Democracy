package me.ritabrata.myhealth.models;


import android.util.Log;

import com.google.gson.annotations.SerializedName;

import me.ritabrata.myhealth.helpers.ApiClient;
import me.ritabrata.myhealth.helpers.ApiInterface;
import me.ritabrata.myhealth.helpers.ConfigHelper;
import me.ritabrata.myhealth.helpers.MyTaskCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescriptionModel {

    @SerializedName("medicineName")
    public String medicineName;
    @SerializedName("timesADay")
    public String timesADay;
    @SerializedName("fromDate")
    public String fromDate;
    @SerializedName("tillDate")
    public String tillDate;
    @SerializedName("doctorId")
    public String doctorId;

    @SerializedName("clockOne")
    public String clockOne;
    @SerializedName("clockTwo")
    public String clockTwo;
    @SerializedName("clockThree")
    public String clockThree;
    @SerializedName("clockFour")
    public String clockFour;

    public PrescriptionModel() {
    }

    public static void addPrescription(String medicineName, String timesADay, String fromDate, String tillDate, String doctorId, String clockOne, String clockTwo, String clockThree, String clockFour, final MyTaskCallback callback) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        apiService.addPrescription(ConfigHelper.myUserID, medicineName, timesADay, fromDate, tillDate, doctorId, clockOne, clockTwo, clockThree, clockFour).enqueue(new Callback<PrescriptionModel.responseAddPrescription>() {
            @Override
            public void onResponse(Call<PrescriptionModel.responseAddPrescription> call, Response<PrescriptionModel.responseAddPrescription> response) {
                Log.d("Response", response.toString());

                PrescriptionModel.responseAddPrescription prescription = response.body();
                callback.onSuccess(response.toString());
            }

            @Override
            public void onFailure(Call<PrescriptionModel.responseAddPrescription> call, Throwable t) {
                Log.e("onFailure", t.toString());
                callback.onError(t.toString());
            }
        });
    }

    public static void getPrescriptionLogs(final MyTaskCallback callback) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        apiService.getPresLogs(ConfigHelper.myUserID).enqueue(new Callback<responsePrescriptionLogs>() {
            @Override
            public void onResponse(Call<responsePrescriptionLogs> call, Response<responsePrescriptionLogs> response) {
                Log.d("Response", response.toString());

                PrescriptionModel.responsePrescriptionLogs prescription = response.body();

                for (int i = 0; i < prescription.result.data.length; i++) {
                    for (int j = 0; j < prescription.result.data[i].prescription.length; j++) {
                        PrescriptionModel my = new PrescriptionModel();
                        my.medicineName = prescription.result.data[i].prescription[j].medicineName;
                        my.timesADay = prescription.result.data[i].prescription[j].timesADay;
                        my.fromDate = prescription.result.data[i].prescription[j].fromDate;
                        my.tillDate = prescription.result.data[i].prescription[j].tillDate;
                        my.doctorId = prescription.result.data[i].prescription[j].doctorId;
                        my.clockOne = prescription.result.data[i].prescription[j].clockOne;
                        my.clockTwo = prescription.result.data[i].prescription[j].clockTwo;
                        my.clockThree = prescription.result.data[i].prescription[j].clockThree;
                        my.clockFour = prescription.result.data[i].prescription[j].clockFour;
                        ConfigHelper.myPrescriptionList.add(my);
                    }
                }
                callback.onSuccess(response.toString());
            }

            @Override
            public void onFailure(Call<responsePrescriptionLogs> call, Throwable t) {
                Log.e("onFailure", t.toString());
                callback.onError(t.toString());
            }
        });
    }

    public static void sendMedicineTakenLogs(String medicineName, String date, String time, String status, final MyTaskCallback callback) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        apiService.sendMedLogs(ConfigHelper.myUserID, medicineName, date, time, status).enqueue(new Callback<responseMedicineLogs>() {
            @Override
            public void onResponse(Call<responseMedicineLogs> call, Response<responseMedicineLogs> response) {
                Log.d("Response", response.toString());
                PrescriptionModel.responseMedicineLogs prescription = response.body();
                callback.onSuccess(response.toString());
            }

            @Override
            public void onFailure(Call<responseMedicineLogs> call, Throwable t) {
                Log.e("onFailure", t.toString());
                callback.onError(t.toString());
            }
        });
    }

    public static void sendSMS(String medicineName, final MyTaskCallback callback) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        apiService.sendSMSCall(ConfigHelper.myUser.mobileNo, medicineName, ConfigHelper.myUser.name).enqueue(new Callback<responseSendSms>() {
            @Override
            public void onResponse(Call<responseSendSms> call, Response<responseSendSms> response) {
                Log.d("Response", response.toString());
                PrescriptionModel.responseSendSms prescription = response.body();
                callback.onSuccess(response.toString());
            }

            @Override
            public void onFailure(Call<responseSendSms> call, Throwable t) {
                Log.e("onFailure", t.toString());
                callback.onError(t.toString());
            }
        });
    }

    public class responseAddPrescription {
        @SerializedName("success")
        public String success;

        @SerializedName("data")
        public String data;
    }

    public class Result {

        @SerializedName("success")
        public String success;
        @SerializedName("data")
        public Data data[];

    }

    public class Data {
        @SerializedName("_id")
        public String _id;
        @SerializedName("patientId")
        public String patientId;
        @SerializedName("__v")
        public String __v;
        @SerializedName("prescription")
        public Prescription prescription[];

    }

    public class Prescription {
        @SerializedName("medicineName")
        public String medicineName;
        @SerializedName("timesADay")
        public String timesADay;
        @SerializedName("fromDate")
        public String fromDate;
        @SerializedName("tillDate")
        public String tillDate;
        @SerializedName("doctorId")
        public String doctorId;
        @SerializedName("_id")
        public String _id;
        @SerializedName("clockOne")
        public String clockOne;
        @SerializedName("clockTwo")
        public String clockTwo;
        @SerializedName("clockThree")
        public String clockThree;
        @SerializedName("clockFour")
        public String clockFour;

    }

    public class responsePrescriptionLogs {
        @SerializedName("result")
        public Result result;
    }

    public class responseMedicineLogs {
        @SerializedName("result")
        public String result;
    }

    public class responseSendSms {
        @SerializedName("result")
        public String result;
    }
}
