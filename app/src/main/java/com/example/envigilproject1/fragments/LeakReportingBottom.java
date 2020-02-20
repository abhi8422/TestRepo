package com.example.envigilproject1.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.envigilproject1.R;

public class LeakReportingBottom extends Fragment {

    Button btnCanReport, btnReport;

    public LeakReportingBottom() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leak_reporting_bottom, container, false);
        btnCanReport = view.findViewById(R.id.btn_cancel_report);
        btnReport = view.findViewById(R.id.btn_repair_report);
        return view;
    }

}
