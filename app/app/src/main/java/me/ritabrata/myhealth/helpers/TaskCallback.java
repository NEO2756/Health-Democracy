package me.ritabrata.myhealth.helpers;

import android.util.Log;

public class TaskCallback implements MyTaskCallback {
    @Override
    public void onSuccess(String apiResponse) {
        Log.d("APP", "success" + apiResponse.toString());
    }

    @Override
    public void onError(String apiResponse) {
        Log.e("APP", "error" + apiResponse.toString());
    }

}
