package com.cia.ryuu.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    private Button btnRegister, btnList, btnAva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        setComponents();
        defineEvents();
    }

    private void setComponents(){
        btnRegister = (Button) findViewById(R.id.hs_btn_register);
        btnList = (Button) findViewById(R.id.hs_btn_list);
        btnAva = (Button) findViewById(R.id.hs_btn_ava);
    }

    private void defineEvents(){

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToList();
            }
        });

        btnAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAva();
            }
        });
    }

    public void goToList(){
        Intent iList = new Intent(this, availability.class);
        startActivity(iList);
    }

    public void goToRegister(){
        Intent iRegister = new Intent(this, register.class);
        startActivity(iRegister);
    }

    public void goToAva(){
        Intent iAva = new Intent(this, reg_available.class);
        startActivity(iAva);
    }
}
