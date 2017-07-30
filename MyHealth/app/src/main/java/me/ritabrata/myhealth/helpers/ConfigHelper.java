package me.ritabrata.myhealth.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import me.ritabrata.myhealth.models.PrescriptionModel;
import me.ritabrata.myhealth.models.UserModel;


public class ConfigHelper {

   // public static String DataServiceUrl = "http://13.126.159.208:7000/eth/";

    public static String DataServiceUrl = "http://192.168.43.195:7000/eth/";
    public static UserModel myUser = new UserModel();
    public static String myUserID;
    public static String notiMedName;
    public static ArrayList<PrescriptionModel> myPrescriptionList = new ArrayList<PrescriptionModel>();
    public static int userAge;
    private static String SharedPrefName = "MyPref";

    public static String stringToStringDetailedDateTime(String date) {
        String aFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        if (date.length() == 19) {
            aFormat = "yyyy-MM-dd'T'HH:mm:ss";
        } else {
            aFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSS";
        }
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        simpledateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date endDate = simpledateformat.parse(date, pos);
        Calendar myDate = Calendar.getInstance();
        myDate.setTime(endDate);
        Calendar today = Calendar.getInstance();
        long diff = (today.getTimeInMillis() - myDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        return dateFormat.format(endDate);

    }

    public static String stringToStringDetailedDateTimeNoDashes(String date) {
        String aFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        if (date.length() == 19) {
            aFormat = "yyyy-MM-dd'T'HH:mm:ss";
        } else {
            aFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSS";
        }
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        simpledateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date endDate = simpledateformat.parse(date, pos);
        Calendar myDate = Calendar.getInstance();
        myDate.setTime(endDate);
        Calendar today = Calendar.getInstance();
        long diff = (today.getTimeInMillis() - myDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);

        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", Locale.US);
        return dateFormat.format(endDate);

    }

    public static String getUserID(Context myContext) {
        SharedPreferences mySharedPref = myContext.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
        return mySharedPref.getString("UserID", "");
    }

    public static void setUserID(String myUserID, Context myContext) {
        SharedPreferences mySharedPref = myContext.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPref.edit();
        myEditor.putString("UserID", myUserID);
        myEditor.commit();
    }

}
