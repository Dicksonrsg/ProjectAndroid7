package com.cia.ryuu.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etPhone, etName;
    private Button btnSave, btnCancel;

    Spinner slanguages = (Spinner) findViewById(R.id.r_spinnerLanguages);

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages_list, android.R.layout.simple_spinner_item);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slanguages.setAdapter(adapter);

        setComponents();
        setEvents();
    }

    private void setComponents(){
        etName = (EditText) findViewById(R.id.r_et_name);

        etPhone = (EditText) findViewById(R.id.r_et_phone);
        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    private void setEvents(){

    }

    public void clean(){
        etName.setText("");
        etPhone.setText("");

        etName.requestFocus();
    }

    /*Spinner related listeners*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
