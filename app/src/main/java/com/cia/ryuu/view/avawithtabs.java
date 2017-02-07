package com.cia.ryuu.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.AvaLiAdapter;
import adapter.CustomOnItemSelectedListener;
import adapter.HintAdapter;
import dao.TeacherDAO;
import model.Teacher;

public class avawithtabs extends AppCompatActivity {

    private TabHost tabHost;
    private Spinner spinDays, spinShifts;
    private ListView lvAvas, lvTeachers;
    private Context context;
    private AvaLiAdapter listTeacherAdapter;
    private TeacherDAO tdao;
    private TextView tvt2Title;
    private Toolbar tbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avawithtabs);

        setComponents();
        defineEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item){
        switch (item.getItemId()){
            case R.id.item01:
                goToRegister();
                return true;
            case R.id.item02:
                goToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setComponents(){
        context = getApplicationContext();
        tdao = new TeacherDAO();

        tbar = (Toolbar) findViewById(R.id.awt_tb02);
        tbar.setVisibility(View.GONE);

        tabHost = (TabHost) findViewById(R.id.awt_thLists);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Availalble");
        spec.setContent(R.id.awt_tab1);
        spec.setIndicator("Available");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Teachers");
        spec.setContent(R.id.awt_tab2);
        spec.setIndicator("Teachers");
        tabHost.addTab(spec);

        /*Available list - tab1*/
        List<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Choose a day");

        HintAdapter adapterDays = new HintAdapter(context, days, android.R.layout.simple_spinner_dropdown_item);
        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinDays = (Spinner) findViewById(R.id.awt_t1_spinDay);
        spinDays.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinDays.setAdapter(adapterDays);
        spinDays.setSelection(adapterDays.getCount());

        List<String> shifts = new ArrayList<>();
        shifts.add("07:20-09:20");
        shifts.add("09:30-11:30");
        shifts.add("13:30-15:30");
        shifts.add("15:40-17:40");
        shifts.add("18:00-20:00");
        shifts.add("20:00-22:00");
        shifts.add("Choose a shift");

        HintAdapter adapterShifts = new HintAdapter(context, shifts, android.R.layout.simple_spinner_dropdown_item);
        adapterShifts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinShifts = (Spinner) findViewById(R.id.awt_t1_spinShift);
        spinShifts.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinShifts.setAdapter(adapterShifts);
        spinShifts.setSelection(adapterShifts.getCount());

        lvAvas = (ListView) findViewById(R.id.awt_t1_lvAva);


        /*Teachers' list - tab2*/
        tvt2Title = (TextView) findViewById(R.id.awt_t2_tvTitle);
        tvt2Title.setText("Teachers' list");

        lvTeachers = (ListView) findViewById(R.id.awt_t2_lvTeachers);
        List<Teacher> lteachers = new ArrayList();
        if(tdao.listAll() == null || tdao.listAll().isEmpty()){
            Teacher t = new Teacher();
            t.setName("testeName");
            t.setPhone("00000000");
            t.setRg(0007);
            t.setLanguage("testlangua");
            lteachers.add(t);

            Teacher tea = new Teacher();
            tea.setName("Dickson");
            tea.setPhone("8855-9826");
            tea.setRg(2443);
            tea.setLanguage("Japanese");
            lteachers.add(tea);
        }else{
            lteachers = tdao.listAll();
        }
        listTeacherAdapter = new AvaLiAdapter(this, lteachers);
        lvTeachers.setAdapter(listTeacherAdapter);
    }

    private void defineEvents(){

    }

    public void goToList(){
        Intent iList = new Intent(this, avawithtabs.class);
        startActivity(iList);
    }

    public void goToRegister(){
        Intent iRegister = new Intent(this, RegisterWTab.class);
        startActivity(iRegister);
    }
}
