package com.cia.ryuu.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.CustomOnItemSelectedListener;
import adapter.HintAdapter;
import dao.TeacherDAO;
import model.Teacher;

public class register extends AppCompatActivity {

    private EditText etPhone, etName;
    private Button btnSave, btnCancel;
    private TeacherDAO tdao;
    private Spinner slanguages;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setComponents();
        setEvents();
    }

    private void setComponents(){
        tdao = new TeacherDAO();

        etName = (EditText) findViewById(R.id.r_et_name);

        etPhone = (EditText) findViewById(R.id.r_et_phone);
        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        context = getApplicationContext();

        List<String> items = new ArrayList<String>();
        items.add("English");
        items.add("French");
        items.add("German");
        items.add("Italian");
        items.add("Spanish");
        items.add("Choose a language");

        HintAdapter hintAdapter = new HintAdapter(context, items, android.R.layout.simple_spinner_dropdown_item);
        hintAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        slanguages = (Spinner) findViewById(R.id.r_spinnerLanguages);
        slanguages.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        slanguages.setAdapter(hintAdapter);
        slanguages.setSelection(hintAdapter.getCount());

        btnSave = (Button) findViewById(R.id.r_btnRegister);
        btnCancel = (Button) findViewById(R.id.r_btnCancel);
    }

    private void setEvents(){

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher();
                clean();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });
    }

    public void clean(){
        etName.setText("");
        etPhone.setText("");

        etName.requestFocus();
    }

    public void addTeacher(){
        Teacher teacher = new Teacher();
        teacher.setName(etName.getText().toString());
        teacher.setPhone(etPhone.getText().toString());
        teacher.setLanguage(String.valueOf(slanguages.getSelectedItem()));

        Toast.makeText(register.this, "Teacher: " + "\nName: " + teacher.getName() + "\nPhone: " + teacher.getPhone() + "\nLanguage:" + teacher.getLanguage(), Toast.LENGTH_LONG).show();
        tdao.register(teacher);
    }

}
