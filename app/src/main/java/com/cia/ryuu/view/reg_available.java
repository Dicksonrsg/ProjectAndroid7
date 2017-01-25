package com.cia.ryuu.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.CustomOnItemSelectedListener;
import adapter.HintAdapter;

public class reg_available extends AppCompatActivity {

    private Button btnSave, btnCancel;
    private Context context;
    private Spinner spinTeachers;
    private Switch stMonday, stTuesday, stWednesday, stThursday, stFriay;
    private TableLayout tlMonday, tlTuesday, tlWednesday, tlThursday, tlFriday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_available);
        setComponents();
        setEvents();
    }
    private void setComponents() {

        context = getApplicationContext();

        List<String> lTeachers = new ArrayList<>();
        lTeachers.add("Dickson Silva");
        lTeachers.add("Rafhael Galdino");
        lTeachers.add("Jose Felix");
        lTeachers.add("Choose a teacher");

        HintAdapter hintAdapterteachers = new HintAdapter(context, lTeachers, android.R.layout.simple_spinner_dropdown_item);
        hintAdapterteachers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinTeachers = (Spinner) findViewById(R.id.rav_spinTeachers);
        spinTeachers.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinTeachers.setAdapter(hintAdapterteachers);
        spinTeachers.setSelection(hintAdapterteachers.getCount());

        stTuesday = (Switch) findViewById(R.id.rav_stTuesday);
        stTuesday.setChecked(false);
        stMonday = (Switch) findViewById(R.id.rav_stMonday);
        stMonday.setChecked(false);
        stWednesday = (Switch) findViewById(R.id.rav_stWednesday);
        stWednesday.setChecked(false);
        stThursday = (Switch) findViewById(R.id.rav_stTh);
        stThursday.setChecked(false);
        stFriay = (Switch) findViewById(R.id.rav_stFriday);
        stFriay.setChecked(false);

        tlTuesday = (TableLayout) findViewById(R.id.rav_tlTuesday);
        tlTuesday.setVisibility(View.GONE);
        tlMonday = (TableLayout) findViewById(R.id.rav_tlMonday);
        tlMonday.setVisibility(View.GONE);
        tlWednesday = (TableLayout) findViewById(R.id.rav_tlWenesday);
        tlWednesday.setVisibility(View.GONE);
        tlThursday = (TableLayout) findViewById(R.id.rav_tlThursday);
        tlThursday.setVisibility(View.GONE);
        tlFriday = (TableLayout) findViewById(R.id.rav_tlFriday);
        tlFriday.setVisibility(View.GONE);

        btnSave = (Button) findViewById(R.id.rav_btnSave);
        btnCancel = (Button) findViewById(R.id.rav_btnCancel);

    }

    private void setEvents() {
        stMonday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckedM) {
                if(isCheckedM){
                    tlMonday.setVisibility(View.VISIBLE);
                }else{
                    tlMonday.setVisibility(View.GONE);
                }
            }
        });

        stTuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckedT) {
                if(isCheckedT){
                    tlTuesday.setVisibility(View.VISIBLE);
                }else{
                    tlTuesday.setVisibility(View.GONE);
                }
            }
        });

        stWednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckedW) {
                if(isCheckedW){
                    tlWednesday.setVisibility(View.VISIBLE);
                }else{
                    tlWednesday.setVisibility(View.GONE);
                }
            }
        });

        stThursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckedTh) {
                if(isCheckedTh){
                    tlThursday.setVisibility(View.VISIBLE);
                }else{
                    tlThursday.setVisibility(View.GONE);
                }
            }
        });

        stFriay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isCheckedF) {
                if(isCheckedF){
                    tlFriday.setVisibility(View.VISIBLE);
                }else{
                    tlFriday.setVisibility(View.GONE);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
