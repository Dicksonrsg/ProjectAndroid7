package com.cia.ryuu.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.CustomOnItemSelectedListener;
import adapter.HintAdapter;
import dao.TeacherDAO;
import model.Teacher;

public class RegisterWTab extends AppCompatActivity {

    private TabHost tabHost;
    private TeacherDAO tdao;
    private EditText etName, etPhone, etSI;
    private Button t1btnSave, t1btnCancel, t2btnSave, t2btnCancel;
    private Context context;
    private Spinner slanguages, spinTeachers;
    private Switch stMonday, stTuesday, stWednesday, stThursday, stFriay;
    private TableLayout tlMonday, tlTuesday, tlWednesday, tlThursday, tlFriday;
    private Toolbar tbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_wtab);

        setComponents();
        setEvents();
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

        tbar = (Toolbar) findViewById(R.id.rwt_tb03);
        tbar.setVisibility(View.GONE);

        /*Tabhost settings, such as name*/
        tabHost = (TabHost) findViewById(R.id.rwt_tabHostTeacher);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Teacher");
        spec.setContent(R.id.rwt_tab1);
        spec.setIndicator("Teacher");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Availability");
        spec.setContent(R.id.rwt_tab2);
        spec.setIndicator("Availability");
        tabHost.addTab(spec);

        /*Teacher registration tab1: items*/
        tdao = new TeacherDAO();
        etName = (EditText) findViewById(R.id.rwt_t1_etName);
        etPhone = (EditText) findViewById(R.id.rwt_t1_etPhone);
        etSI = (EditText) findViewById(R.id.rwt_t1_etSI);

        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(){
            private boolean backspacingFlag = false;
            private boolean editedFlag = false;
            private int cursorComplement;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                cursorComplement = s.length()-etPhone.getSelectionStart();
                if(count > after){
                    backspacingFlag = true;
                }else{
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // nothing to do here =D
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                //what matters are the phone digits beneath the mask, so we always work with a raw string with only digits
                String phone = string.replaceAll("[^\\d]", "");

                //if the text was just edited, :afterTextChanged is called another time... so we need to verify the flag of edition
                //if the flag is false, this is a original user-typed entry. so we go on and do some magic
                if (!editedFlag) {

                    //we start verifying the worst case, many characters mask need to be added
                    //example: 999999999 <- 6+ digits already typed
                    // masked: (999) 999-999
                    if (phone.length() >= 7 && !backspacingFlag) {
                        //we will edit. next call on this textWatcher will be ignored
                        editedFlag = true;
                        //here is the core. we substring the raw digits and add the mask as convenient
                        String ans = "(" + phone.substring(0, 2) + ") " + phone.substring(2,7) + "-" + phone.substring(7);
                        etPhone.setText(ans);
                        //we deliver the cursor to its original position relative to the end of the string
                        etPhone.setSelection(etPhone.getText().length()-cursorComplement);

                        //we end at the most simple case, when just one character mask is needed
                        //example: 99999 <- 3+ digits already typed
                        // masked: (999) 99
                    } else if (phone.length() >= 2 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = "(" +phone.substring(0, 2) + ") " + phone.substring(2);
                        etPhone.setText(ans);
                        etPhone.setSelection(etPhone.getText().length()-cursorComplement);
                    }
                    // We just edited the field, ignoring this cycle of the watcher and getting ready for the next
                } else {
                    editedFlag = false;
                }
            }
        });

        List<String> items = new ArrayList<String>();
        items.add("English");
        items.add("French");
        items.add("German");
        items.add("Italian");
        items.add("Spanish");
        items.add("Choose a language");

        HintAdapter hintAdapterLang = new HintAdapter(context, items, android.R.layout.simple_spinner_dropdown_item);
        hintAdapterLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        slanguages = (Spinner) findViewById(R.id.rwt_t1_spinLanguages);
        slanguages.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        slanguages.setAdapter(hintAdapterLang);
        slanguages.setSelection(hintAdapterLang.getCount());

        t1btnSave = (Button) findViewById(R.id.rwt_t1_btnSave);
        t1btnCancel = (Button) findViewById(R.id.rwt_t1_btnCancel);

        /*Availability tab2: items*/
        List<String> lTeachers = new ArrayList<>();
        lTeachers.add("Dickson Silva");
        lTeachers.add("Rafhael Galdino");
        lTeachers.add("Jose Felix");
        lTeachers.add("Choose a teacher");

        HintAdapter hintAdapterteachers = new HintAdapter(context, lTeachers, android.R.layout.simple_spinner_dropdown_item);
        hintAdapterteachers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinTeachers = (Spinner) findViewById(R.id.rwt_t2_spinTeachers);
        spinTeachers.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinTeachers.setAdapter(hintAdapterteachers);
        spinTeachers.setSelection(hintAdapterteachers.getCount());

        stTuesday = (Switch) findViewById(R.id.rwt_t2_stTuesday);
        stTuesday.setChecked(false);
        stMonday = (Switch) findViewById(R.id.rwt_t2_stMonday);
        stMonday.setChecked(false);
        stWednesday = (Switch) findViewById(R.id.rwt_t2_stWednesday);
        stWednesday.setChecked(false);
        stThursday = (Switch) findViewById(R.id.rwt_t2_stTh);
        stThursday.setChecked(false);
        stFriay = (Switch) findViewById(R.id.rwt_t2_stFriday);
        stFriay.setChecked(false);

        tlMonday = (TableLayout) findViewById(R.id.rwt_t2_tlMonday);
        tlMonday.setVisibility(View.GONE);
        tlTuesday = (TableLayout) findViewById(R.id.rwt_t2_tlTuesday);
        tlTuesday.setVisibility(View.GONE);
        tlWednesday = (TableLayout) findViewById(R.id.rwt_t2_tlWenesday);
        tlWednesday.setVisibility(View.GONE);
        tlThursday = (TableLayout) findViewById(R.id.rwt_t2_tlThursday);
        tlThursday.setVisibility(View.GONE);
        tlFriday = (TableLayout) findViewById(R.id.rwt_t2_tlFriday);
        tlFriday.setVisibility(View.GONE);

        t2btnSave = (Button) findViewById(R.id.rwt_t2_btnSave);
        t2btnCancel = (Button) findViewById(R.id.rwt_t2_btnCancel);

    }
    private void setEvents(){

        /*t1btns refer to the buttons on tab1 of register activity*/
        t1btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher();
                clean();
            }
        });

        t1btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        /*the events described bellow belong to tab2 - availability*/
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

        t2btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        t2btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void clean(){
        etName.setText("");
        etPhone.setText("");
        etSI.setText("");

        etName.requestFocus();
    }

    public void addTeacher(){
        Teacher teacher = new Teacher();
        teacher.setName(etName.getText().toString());
        teacher.setPhone(etPhone.getText().toString());
        int si = Integer.parseInt(etSI.getText().toString());
        teacher.setRg(si);
        teacher.setLanguage(String.valueOf(slanguages.getSelectedItem()));

        Toast.makeText(RegisterWTab.this, "Teacher: " + "\nName: " + teacher.getName() + "\nPhone: " + teacher.getPhone() + "\nLanguage:" + teacher.getLanguage(), Toast.LENGTH_LONG).show();
        tdao.register(teacher);
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
