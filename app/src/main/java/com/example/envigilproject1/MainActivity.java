package com.example.envigilproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView autoCompleteTextView;
    String[] arr = {"Unreachable", "Wet Floor", "Height Issues", "Out of Service"};
    TextView tvComponent, tvSize, tvDate, tvTime;
    ImageView imgDateReading, imgTimeReading;
    Button btnSave;
    EditText edReading;
    Switch simSwitch;
    private int mDay, mMonth, mYear;
    private int mHour, mMinute, mSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvComponent = findViewById(R.id.tv_component);
        tvSize = findViewById(R.id.tv_size_);
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        edReading = findViewById(R.id.ed_reading);
        simSwitch = findViewById(R.id.simple_switch);
        autoCompleteTextView = findViewById(R.id.auto_skip_reason);
        btnSave = findViewById(R.id.btn_save);
        imgDateReading = findViewById(R.id.img_date_reading);
        imgTimeReading = findViewById(R.id.img_time_reading);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arr);
        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setAdapter(adapter);

        String curr_date = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        tvDate.setText(curr_date);

        Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);
        mSeconds = calendar.get(Calendar.SECOND);
        tvTime.setText(mHour +":" + mMinute +":" + mSeconds);

        simSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (simSwitch.isChecked()) {
                    autoCompleteTextView.setEnabled(true);
                }
                else {
                    autoCompleteTextView.setEnabled(false);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = getLayoutInflater().inflate(R.layout.fragment_leak_identifier_bottom, null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(dialogView);
                bottomSheetDialog.show();
            }
        });

        imgDateReading.setOnClickListener(this);
        imgTimeReading.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == imgDateReading) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            mMonth = calendar.get(Calendar.MONTH);

            DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                    tvDate.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year);
                }
            },mYear, mMonth, mDay);
            pickerDialog.show();
        }

        if (view == imgTimeReading) {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
            mSeconds = calendar.get(Calendar.SECOND);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourofday, int minuteofday) {
                    tvTime.setText(hourofday + ":" + minuteofday);
                }
            }, mHour, mMinute,false);
            timePickerDialog.show();
        }
    }

    public void viewButton(View view) {
        startActivity(new Intent(getApplicationContext(), LeakReportActivity.class));
    }
}