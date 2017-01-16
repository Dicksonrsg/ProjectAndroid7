package com.cia.ryuu.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.AvaLiAdapter;
import dao.TeacherDAO;
import model.Teacher;

public class availability extends AppCompatActivity {

    private ListView listTeachers;
    private AvaLiAdapter adapter;
    private TeacherDAO tdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        tdao = new TeacherDAO();

        listTeachers = (ListView) findViewById(R.id.ava_lvTeachers);

        List<Teacher> lteachers = new ArrayList();
            if(tdao.listAll() == null || tdao.listAll().isEmpty()){
                Teacher t = new Teacher();
                t.setName("testeName");
                t.setPhone("00000000");
                t.setLanguage("testlangua");
                lteachers.add(t);

                Teacher tea = new Teacher();
                tea.setName("Dickson");
                tea.setPhone("8855-9826");
                tea.setLanguage("Japanese");
                lteachers.add(tea);
            }else{
                lteachers = tdao.listAll();
            }
        adapter = new AvaLiAdapter(this, lteachers);
        listTeachers.setAdapter(adapter);
    }
}
