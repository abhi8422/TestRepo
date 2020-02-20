package com.example.envigilproject1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LeakReportActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvComponentRepo, tvSizeRepo, tvDateRepo, tvTimeRepo;
    ImageView reportDate, reportTime, imgComponent;
    Button btnSaveRepo, btnPPM, btnDPM, btnLEL;
    EditText edReadingRepo;
    private int mDay, mMonth, mYear;
    private int mHour, mMinute, mSeconds;
    static int SELECT_FROM_CAMERA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_report);

        tvComponentRepo = findViewById(R.id.tv_component_report);
        tvSizeRepo = findViewById(R.id.tv_size_report_);
        tvDateRepo = findViewById(R.id.date_report_);
        tvTimeRepo = findViewById(R.id.time_report);
        edReadingRepo = findViewById(R.id.ed_leak_rate);
        btnSaveRepo = findViewById(R.id.btn_save_report);
        btnPPM = findViewById(R.id.btn_ppm);
        btnDPM = findViewById(R.id.btn_dpm);
        btnLEL = findViewById(R.id.btn_lel);
        reportDate = findViewById(R.id.report_date_reading);
        reportTime = findViewById(R.id.report_time_reading);
        imgComponent = findViewById(R.id.img_component);

        String curr_date = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        tvDateRepo.setText(curr_date);

        Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);
        mSeconds = calendar.get(Calendar.SECOND);
        tvTimeRepo.setText(mHour +":" + mMinute +":" + mSeconds);

        btnSaveRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = getLayoutInflater().inflate(R.layout.fragment_leak_reporting_bottom, null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(LeakReportActivity.this);
                bottomSheetDialog.setContentView(dialogView);
                bottomSheetDialog.show();
            }
        });

        reportDate.setOnClickListener(this);
        reportTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == reportDate) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            mMonth = calendar.get(Calendar.MONTH);

            DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                    tvDateRepo.setText(dayofmonth + "/" + (monthofyear + 1) + "/" + year);
                }
            },mYear, mMonth, mDay);
            pickerDialog.show();
        }

        if (view == reportTime) {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinute = calendar.get(Calendar.MINUTE);
            mSeconds = calendar.get(Calendar.SECOND);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourofday, int minuteofday) {
                    tvTimeRepo.setText(hourofday + ":" + minuteofday);
                }
            }, mHour, mMinute,false);
            timePickerDialog.show();
        }
    }

    public void repairReading(View view) {
        startActivity(new Intent(getApplicationContext(), RepairRequest.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void cameraTapped(View view) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, SELECT_FROM_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == SELECT_FROM_CAMERA)
            {
                Bundle extras = data.getExtras();
                Bitmap componentBitmap = (Bitmap) extras.get("data");
                imgComponent.setImageBitmap(componentBitmap);
            }
        }
    }
}
