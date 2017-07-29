package me.ritabrata.myhealth.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.ritabrata.myhealth.R;
import me.ritabrata.myhealth.helpers.ConfigHelper;
import me.ritabrata.myhealth.models.PrescriptionModel;

public class MyMedicinesActivity extends AppCompatActivity {



    ArrayList<PrescriptionModel> myList = new ArrayList<PrescriptionModel>();

    private RecyclerView myRecycler;
    MyMedicinesRecycler adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicines);


        myList.addAll(ConfigHelper.myPrescriptionList);

        myRecycler = (RecyclerView) findViewById(R.id.myRecyclerView);
        adapter = new MyMedicinesRecycler(getApplicationContext());
        myRecycler.setAdapter(adapter);
        myRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }


    public class MyMedicinesRecycler extends RecyclerView.Adapter<MyMedicinesRecycler.MyViewHolder> {
        private LayoutInflater inflater;
        private Context context;

        public MyMedicinesRecycler(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public MyMedicinesRecycler.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_medicine, parent, false);
            MyMedicinesRecycler.MyViewHolder holder = new MyMedicinesRecycler.MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyMedicinesRecycler.MyViewHolder holder, int position) {
            holder.myMedicineName.setText(myList.get(position).medicineName);
            holder.myMedicineTimesADay.setText(myList.get(position).timesADay);
            holder.myMedicineFromDate.setText(ConfigHelper.stringToStringDetailedDateTime(myList.get(position).fromDate));
            holder.myMedicineTillDate.setText(ConfigHelper.stringToStringDetailedDateTime(myList.get(position).tillDate));
            holder.myMedicineDoctorId.setText(myList.get(position).doctorId);


//            holder.myAttendeeClick.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int id = myList.get(holder.getAdapterPosition()).id;
//
//                    Intent myIntent = new Intent(getActivity(), AttendeeDetailActivity.class);
//                    myIntent.putExtra("id", id);
//
//                    startActivity(myIntent);
//                }
//            });


            if (myList.get(position).medicineName.length() > 0) {
                holder.myMedicineName.setVisibility(View.VISIBLE);
            } else {
                holder.myMedicineName.setVisibility(View.GONE);
            }


            if (myList.get(position).timesADay.length() > 0) {
                holder.myMedicineTimesADay.setVisibility(View.VISIBLE);
            } else {
                holder.myMedicineTimesADay.setVisibility(View.GONE);
            }


            if (myList.get(position).fromDate.length() > 0) {
                holder.myMedicineFromDate.setVisibility(View.VISIBLE);
            } else {
                holder.myMedicineFromDate.setVisibility(View.GONE);
            }
            if (myList.get(position).tillDate.length() > 0) {
                holder.myMedicineTillDate.setVisibility(View.VISIBLE);
            } else {
                holder.myMedicineTillDate.setVisibility(View.GONE);
            }


            if (myList.get(position).doctorId.length() > 0) {
                holder.myMedicineDoctorId.setVisibility(View.VISIBLE);
            } else {
                holder.myMedicineDoctorId.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return myList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView myMedicineName,myMedicineTimesADay,myMedicineFromDate,myMedicineTillDate,myMedicineDoctorId;
            // LinearLayout myAttendeeClick;

            public MyViewHolder(View myView) {
                super(myView);


                myMedicineName = (TextView) myView.findViewById(R.id.myMedicineName);
                myMedicineTimesADay = (TextView) myView.findViewById(R.id.myMedicineTimesADay);
                myMedicineFromDate = (TextView) myView.findViewById(R.id.myMedicineFromDate);
                myMedicineTillDate = (TextView) myView.findViewById(R.id.myMedicineTillDate);
                myMedicineDoctorId = (TextView) myView.findViewById(R.id.myMedicineDoctorId);
                // myImageView = (AvatarImageView) myView.findViewById(R.id.myImage);
                // myAttendeeClick = (LinearLayout) myView.findViewById(R.id.myAttendeeClick);


            }
        }

    }
}
