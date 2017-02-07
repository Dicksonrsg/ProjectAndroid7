package com.cia.ryuu.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/*https://developer.android.com/guide/topics/ui/menus.html
* */
public class HomeScreen extends AppCompatActivity{

    private Button btnRegister, btnList;
    private Toolbar tbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
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
        btnRegister = (Button) findViewById(R.id.hs_btn_register);
        btnList = (Button) findViewById(R.id.hs_btn_list);

        tbar = (Toolbar) findViewById(R.id.hs_tb01);
        tbar.setVisibility(View.GONE);
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
