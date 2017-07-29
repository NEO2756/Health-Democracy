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

/**
 * Created by ritab on 7/25/2017.
 */

public class UserModel {

    @SerializedName("mobileNo")
    public String mobileNo;
    @SerializedName("name")
    public String name;
    @SerializedName("address")
    public String address;
    @SerializedName("dob")
    public String dob;
    @SerializedName("bloodGroup")
    public String bloodGroup;
    @SerializedName("from")
    public String from;


    public UserModel() {
    }


    public class responseGetProfile {
        @SerializedName("success")
        public String success;

        @SerializedName("name")
        public String name;

        @SerializedName("mobileNo")
        public String mobileNo;

        @SerializedName("bloodGroup")
        public String bloodGroup;

        @SerializedName("dob")
        public String dob;

        @SerializedName("address")
        public String address;

    }

    public static class responseNewUserReg {

        @SerializedName("success")
        public String success;

        @SerializedName("patientsAddress")
        public String patientsAddress;

        @SerializedName("txHash")
        public String txHash;

    }


    public static void registerNewUser(String name, String mobileNo, String address, String dob, String bloodGroup, String from, final MyTaskCallback callback)
    {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        apiService.newUserReg(name,mobileNo,address,dob, bloodGroup ,from).enqueue(new Callback<responseNewUserReg>() {
            @Override
            public void onResponse(Call<responseNewUserReg> call, Response<responseNewUserReg> response) {
            //    Log.d(TAG, "response : " + response.toString());
               responseNewUserReg user = response.body();

                       ConfigHelper.myUserID= user.patientsAddress;

                String id = ConfigHelper.myUserID;


                callback.onSuccess(response.toString());
            }

            @Override
            public void onFailure(Call<responseNewUserReg> call, Throwable t) {
                Log.e("Request Failed:" , t.toString());
                callback.onError(t.toString());
            }
        });

    }


    public static void getPatientDetail(final MyTaskCallback callback)
    {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        apiService.getProfile(ConfigHelper.myUserID).enqueue(new Callback<responseGetProfile>()
        {
            @Override
            public void onResponse(Call<responseGetProfile> call, Response<responseGetProfile> response) {
                Log.d("Response", response.toString());

                responseGetProfile user = response.body();

                ConfigHelper.myUser = new UserModel();

                ConfigHelper.myUser.name= user.name;
                ConfigHelper.myUser.bloodGroup=user.bloodGroup;
                ConfigHelper.myUser.mobileNo=user.mobileNo;
                ConfigHelper.myUser.dob=user.dob;
                ConfigHelper.myUser.address=user.address;

                callback.onSuccess(response.toString());

            }

            @Override
            public void onFailure(Call<responseGetProfile> call, Throwable t) {
                Log.e("onFailure", t.toString());
                callback.onError(t.toString());
            }
        });
    }


}
