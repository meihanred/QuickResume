package com.example.meihanred.quickresume;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class experience extends ActionBarActivity {
    public  final static String EXPERIENCE_KEY = "experiencekey";
    public  final static String TITLE_KEY = "titlekey";
    public  final static String DETAIL_KEY = "detailkey";
    public  final static String SER_KEY = "com.example.quickresume.ser";
    public  final static String NEWTITLE_KEY = "newtitlekey";
    public  final static String NEWDETAIL_KEY = "newdetailkey";
    public  final static String EDUTITLE_KEY = "edutitlekey";
    public  final static String EDUDETAIL_KEY = "edudetailkey";
    public  final static String NEWEDUTITLE_KEY = "newedutitlekey";
    public  final static String NEWEDUDETAIL_KEY = "newedudetailkey";
    public  final static String NAME_KEY = "namekey";
    public  final static String AGE_KEY = "agekey";
    public  final static String CAREER_KEY = "careerkey";
    public  final static String DEGREE_KEY = "degreekey";
    public  final static String EMAIL_KEY = "emailkey";
    public  final static String PHONE_KEY = "phonekey";
    public  final static String STYLE_KEY = "stylekey";
    public String name,career,degree, email, phone;
    public int age, style;
    ArrayList<String> experiencetitle = new ArrayList<>();
    ArrayList<String> experiencedetail = new ArrayList<>();
    ArrayList<String> educationtitle = new ArrayList<>();
    ArrayList<String> educationdetail = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experience);
//        Resume resume = (Resume)getIntent().getSerializableExtra(EXPERIENCE_KEY);
//
//        Toast.makeText(getApplicationContext(),
//                "name is "+ resume.getName(), Toast.LENGTH_SHORT)
//                .show();

//        final ArrayList<String> experiencetitle = resume.getExperiencetitle();
//        final ArrayList<String> experiencedetail = resume.getExperiencedetail();


        final Button btadd = (Button)findViewById(R.id.btadd);
        final Button btremove = (Button)findViewById(R.id.btremove);
        final Button btback = (Button)findViewById(R.id.btback);
        btremove.setEnabled(false);

        experiencetitle = getIntent().getStringArrayListExtra(NEWTITLE_KEY);
        experiencedetail = getIntent().getStringArrayListExtra(NEWDETAIL_KEY);
        educationtitle = getIntent().getStringArrayListExtra(NEWEDUTITLE_KEY);
        educationdetail = getIntent().getStringArrayListExtra(NEWEDUDETAIL_KEY);


        if(experiencetitle!=null) {
            Log.e("", "experience length on experience Create is  " + experiencetitle.size());
            int length = experiencetitle.size();
            int i;
            for(i=0;i<length;i++){
                LinearLayout exlist = (LinearLayout)findViewById(R.id.exlist);
                View child = getLayoutInflater().inflate(R.layout.experienceitem, null);
                exlist.addView(child);
                EditText et = (EditText)child.findViewById(R.id.etitle);
                EditText ed = (EditText)child.findViewById(R.id.edetail);
                et.setText(experiencetitle.get(i));
                ed.setText(experiencedetail.get(i));
                if(0 != exlist.getChildCount())
                    btremove.setEnabled(true);
            }
        }
        name = getIntent().getStringExtra(NAME_KEY);
        age = getIntent().getIntExtra(AGE_KEY,0);
        career = getIntent().getStringExtra(CAREER_KEY);
        degree = getIntent().getStringExtra(DEGREE_KEY);
        phone = getIntent().getStringExtra(PHONE_KEY);
        email = getIntent().getStringExtra(EMAIL_KEY);
        style = getIntent().getIntExtra(STYLE_KEY,0);



        if (savedInstanceState != null) {

            experiencetitle = getIntent().getStringArrayListExtra(NEWTITLE_KEY);
            experiencedetail = getIntent().getStringArrayListExtra(NEWDETAIL_KEY);
            int length = experiencetitle.size();
            int i;
            for(i=0;i<length;i++){
                LinearLayout exlist = (LinearLayout)findViewById(R.id.exlist);
                View child = getLayoutInflater().inflate(R.layout.experienceitem, null);
                exlist.addView(child);
                EditText et = (EditText)child.findViewById(R.id.etitle);
                EditText ed = (EditText)child.findViewById(R.id.edetail);
                et.setText(experiencetitle.get(i));
                ed.setText(experiencedetail.get(i));
                if(0 != exlist.getChildCount())
                    btremove.setEnabled(true);
            }



        }

//        Log.d("name resume experience is" + resume.getName(),"");

        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                experiencetitle = new ArrayList<String>();
                experiencedetail = new ArrayList<String>();
                View child = findViewById(R.id.exitemlist);
                if(child == null){
                    Intent main = new Intent(v.getContext(),MainActivity.class);

                    main.putExtra(NAME_KEY,name);
                    main.putExtra(AGE_KEY,age);
                    main.putExtra(CAREER_KEY,career);
                    main.putExtra(DEGREE_KEY,degree);
                    main.putExtra(PHONE_KEY,phone);
                    main.putExtra(EMAIL_KEY,email);
                    main.putStringArrayListExtra(TITLE_KEY,experiencetitle);
                    main.putStringArrayListExtra(DETAIL_KEY,experiencedetail);
                    main.putExtra(STYLE_KEY,style);
                    main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main);
                    finish();
                    return;
                }
                ViewGroup parent = (ViewGroup) child.getParent();
                int length = parent.getChildCount();
                int i=0;

                for(i=0;i<length ;i++){
                    child = parent.getChildAt(i);

                    EditText etitle = (EditText)child.findViewById(R.id.etitle);
                    experiencetitle.add(etitle.getText().toString());
                    EditText edetail = (EditText)child.findViewById(R.id.edetail);
                    experiencedetail.add(edetail.getText().toString());
//                    Log.e("length is " + length,"titile is " + experiencedetail.get(i));

                }

//                resume.setExperiencetitle(experiencetitle);
//                resume.setExperiencedetail(experiencedetail);
//
//
//
                Intent main = new Intent(v.getContext(),MainActivity.class);
                main.putExtra(NAME_KEY,name);
                main.putExtra(AGE_KEY,age);
                main.putExtra(CAREER_KEY,career);
                main.putExtra(DEGREE_KEY,degree);
                main.putExtra(PHONE_KEY,phone);
                main.putExtra(EMAIL_KEY,email);
                main.putStringArrayListExtra(TITLE_KEY,experiencetitle);
                main.putStringArrayListExtra(DETAIL_KEY,experiencedetail);
                main.putStringArrayListExtra(EDUTITLE_KEY,educationtitle);
                main.putStringArrayListExtra(EDUDETAIL_KEY,educationdetail);
                main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                finish();



            }
        });



        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LinearLayout exlist = (LinearLayout)findViewById(R.id.exlist);
                View child = getLayoutInflater().inflate(R.layout.experienceitem, null);
                exlist.addView(child);
                if(0 != exlist.getChildCount())
                    btremove.setEnabled(true);
                child.requestFocus();


            }
        });


        btremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View childtoremove = findViewById(R.id.exitemlist);
                ViewGroup parent = (ViewGroup) childtoremove.getParent();
                childtoremove = parent.getChildAt(parent.getChildCount()-1);
                if(0 != parent.getChildCount()) {

                    parent.removeView(childtoremove);
                }
                if(0 == parent.getChildCount())
                    btremove.setEnabled(false);





            }
        });

//        listView.setAdapter(new myCursorAdapter(this, R.layout.experienceitem,null,0));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_experience, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
