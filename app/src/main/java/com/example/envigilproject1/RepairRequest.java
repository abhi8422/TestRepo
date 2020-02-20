package com.example.envigilproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RepairRequest extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    TextView tvCompoRepair, tvSizeRepair, tvLocaRepair, tvDateRepair, tvTimeRepair;
    EditText edPostLeak;
    Button btnSveRepair;
    ImageView imgRepairDate, imgRepairTime;
    private int mDay, mMonth, mYear;
    private int mHour, mMinute, mSeconds;
    String[] employees = {"Mark", "Joe", "Michael", "Nick", "Alexis", "Catalina"};
    String[] repairType = {"SS Tubing", "Valve Replace", "Valve Corrosion", "Tubing Loose", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_request);

        tvCompoRepair = findViewById(R.id.tv_component_repair);
        tvSizeRepair = findViewById(R.id.tv_size_repair_);
        tvLocaRepair = findViewById(R.id.tv_location_repair);
        tvDateRepair = findViewById(R.id.date_repair_);
        tvTimeRepair = findViewById(R.id.time_repair);
        edPostLeak = findViewById(R.id.ed_leak_rate);
        imgRepairDate = findViewById(R.id.repair_date_reading);
        imgRepairTime = findViewById(R.id.repair_time_reading);
        btnSveRepair = findViewById(R.id.btn_sve_repair);

        String curr_date = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        tvDateRepair.setText(curr_date);

        Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);
        mSeconds = calendar.get(Calendar.SECOND);
        tvTimeRepair.setText(mHour +":" + mMinute +":" + mSeconds);

        imgRepairDate.setOnClickListener(this);
        imgRepairTime.setOnClickListener(this);
        btnSveRepair.setOnClickListener(this);

        Spinner spinner = findViewById(R.id.spinner_employee);
        ArrayAdapter<String> employeeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, employees);
        employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(employeeAdapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 = findViewById(R.id.spinner_repair);
        ArrayAdapter<String> repairAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, repairType);
        repairAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(repairAdapter);
        spinner1.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.spinner_employee:
                Toast.makeText(getApplicationContext(), "Selected Employee: " + employees[position], Toast.LENGTH_LONG).show();
            case R.id.spinner_repair:
                Toast.makeText(getApplicationContext(), "Selected Employee: " + repairType[position], Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view == imgRepairDate) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            mMonth = calendar.get(Calendar.MONTH);

            DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                    tvDateRepair.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year);
                }
            },mYear, mMonth, mDay);
            pickerDialog.show();
        }

        if (view == imgRepairTime) {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
            mSeconds = calendar.get(Calendar.SECOND);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourofday, int minuteofday) {
                    tvTimeRepair.setText(hourofday + ":" + minuteofday);
                }
            }, mHour, mMinute,false);
            timePickerDialog.show();
        }

        if (view == btnSveRepair) {
            startActivity(new Intent(this, Inspections.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
