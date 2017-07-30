package me.ritabrata.myhealth.helpers;

import me.ritabrata.myhealth.models.PrescriptionModel;
import me.ritabrata.myhealth.models.UserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiInterface {

    @POST("getPatinetDetails")
    @FormUrlEncoded
    Call<UserModel.responseGetProfile> getProfile(
            @Field("patientId") String patientId
    );


    @POST("registerPatient")
    @FormUrlEncoded
    Call<UserModel.responseNewUserReg> newUserReg(
            @Field("name") String name,
            @Field("mobileNo") String mobileNo,
            @Field("address") String address,
            @Field("dob") String dob,
            @Field("bloodGroup") String bloodGroup

    );

    @POST("addPrescription")
    @FormUrlEncoded
    Call<PrescriptionModel.responseAddPrescription> addPrescription(
            @Field("patientId") String patientId,
            @Field("medicineName") String medicineName,
            @Field("timesADay") String timesADay,
            @Field("fromDate") String fromDate,
            @Field("tillDate") String tillDate,
            @Field("doctorId") String doctorId,
            @Field("clockOne") String clockOne,
            @Field("clockTwo") String clockTwo,
            @Field("clockThree") String clockThree,
            @Field("clockFour") String clockFour

    );


    @POST("getPrescriptionLogs")
    @FormUrlEncoded
    Call<PrescriptionModel.responsePrescriptionLogs> getPresLogs(
            @Field("patientId") String patientId
    );


    @POST("addMedicineLogsToBlockchain")
    @FormUrlEncoded
    Call<PrescriptionModel.responseMedicineLogs> sendMedLogs(
            @Field("patientId") String patientId,
            @Field("medicineName") String medicineName,
            @Field("date") String date,
            @Field("time") String time,
            @Field("status") String status
    );


    @POST("sendSMS")
    @FormUrlEncoded
    Call<PrescriptionModel.responseSendSms> sendSMSCall(
            @Field("phone") String phone,
            @Field("medicine") String medicine,
            @Field("patientName") String patientName

    );

}

