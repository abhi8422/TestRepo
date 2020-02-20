package com.example.envigilproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Inspections extends AppCompatActivity implements View.OnClickListener {

    EditText ed_lunch, ed_travel, ed_admin, ed_repair, ed_reinspect;
    TextView tv_insp_date, tv_insp_strt, tv_insp_end;
    ImageView imgInspDate, imgStrtInsp, imgEndInsp;
    Button sveInspection;
    private int mDay, mMonth, mYear;
    private int mHour, mMinute, mSeconds;
    String[] employees = {"Mark", "Joe", "Michael", "Nick", "Alexis", "Catalina"};
    String[] repairType = {"SS Tubing", "Valve Replace", "Valve Corrosion", "Tubing Loose", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspections);

        tv_insp_date = findViewById(R.id.tv_insp_date);
        ed_lunch = findViewById(R.id.ed_lunch_time);
        ed_travel = findViewById(R.id.ed_travel);
        ed_admin = findViewById(R.id.ed_admin);
        ed_repair = findViewById(R.id.ed_repair);
        ed_reinspect = findViewById(R.id.ed_reinspect);
        tv_insp_strt = findViewById(R.id.tv_strt_time);
        tv_insp_end = findViewById(R.id.tv_end_time);
        imgInspDate = findViewById(R.id.img_insp_date);
        imgStrtInsp = findViewById(R.id.img_strt_time);
        imgEndInsp = findViewById(R.id.img_end_time);
        sveInspection = findViewById(R.id.btn_sve_insp);

        String curr_date = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        tv_insp_date.setText(curr_date);

        imgInspDate.setOnClickListener(this);
        imgStrtInsp.setOnClickListener(this);
        imgEndInsp.setOnClickListener(this);
        sveInspection.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == imgInspDate) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            mMonth = calendar.get(Calendar.MONTH);

            DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                    tv_insp_strt.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year);
                }
            },mYear, mMonth, mDay);
            pickerDialog.show();
        }
        if (view == imgStrtInsp) {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
            mSeconds = calendar.get(Calendar.SECOND);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourofday, int minuteofday) {
                    tv_insp_strt.setText(hourofday + ":" + minuteofday);
                }
            }, mHour, mMinute,false);
            timePickerDialog.show();
        }

        if (view == imgEndInsp) {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
            mSeconds = calendar.get(Calendar.SECOND);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourofday, int minuteofday) {
                    tv_insp_end.setText(hourofday + ":" + minuteofday);
                }
            }, mHour, mMinute,false);
            timePickerDialog.show();
        }

        if (view == sveInspection) {
            Toast.makeText(this, "Your route is uploaded successfully.", Toast.LENGTH_LONG).show();
        }
    }
}
