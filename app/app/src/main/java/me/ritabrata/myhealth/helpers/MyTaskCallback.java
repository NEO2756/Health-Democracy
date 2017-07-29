package me.ritabrata.myhealth.helpers;


public interface MyTaskCallback {
    void onSuccess(String res);

    void onError(String res);
}