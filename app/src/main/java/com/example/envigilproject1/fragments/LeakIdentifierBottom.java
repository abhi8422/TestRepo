package com.example.envigilproject1.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.envigilproject1.LeakReportActivity;
import com.example.envigilproject1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeakIdentifierBottom extends BottomSheetDialogFragment {

    Button canBtn, viewBtn;


    public LeakIdentifierBottom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leak_identifier_bottom, container, false);

        canBtn = view.findViewById(R.id.btn_cancel_report);
        viewBtn = view.findViewById(R.id.btn_view_report);

        return view;
    }
}