package com.example.meihanred.quickresume;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {
    public  final static String SER_KEY = "com.example.quickresume.ser";
    public  final static String PAR_KEY = "com.example.quickresume.par";
    public  final static String NAME_KEY = "namekey";
    public  final static String AGE_KEY = "agekey";
    public  final static String CAREER_KEY = "careerkey";
    public  final static String DEGREE_KEY = "degreekey";
    public  final static String EMAIL_KEY = "emailkey";
    public  final static String PHONE_KEY = "phonekey";
    public  final static String TITLE_KEY = "titlekey";
    public  final static String DETAIL_KEY = "detailkey";
    public  final static String STYLE_KEY = "stylekey";
    public  final static String EDUTITLE_KEY = "edutitlekey";
    public  final static String EDUDETAIL_KEY = "edudetailkey";
    public  final static String NEWEDUTITLE_KEY = "newedutitlekey";
    public  final static String NEWEDUDETAIL_KEY = "newedudetailkey";
    public  final static String NEWTITLE_KEY = "newtitlekey";
    public  final static String NEWDETAIL_KEY = "newdetailkey";
    EditText ename, eage, ecareer, edegree, eemail,ephone;

    public String name="null",career="null",degree="null", email="null", phone = "-1";
    public int age=-1,style=0;
    Button btcreate, btbacktolist, btread, btsend, btnext, btedu;
    public String path = "/mnt/sdcard/QuickResume/";
    public  String filename = "myresume";
    public ArrayList<String> experiencetitle = new ArrayList<>();
    public ArrayList<String> experiencedetail = new ArrayList<>();
    public ArrayList<String> educationtitle = new ArrayList<>();
    public ArrayList<String> educationdetail = new ArrayList<>();
    public Resume resume = new Resume();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btcreate = (Button) findViewById(R.id.btcreate);
        btread = (Button) findViewById(R.id.btread);
        btedu = (Button)findViewById(R.id.btedu);
        btbacktolist = (Button) findViewById(R.id.btbacktolist);
        btnext = (Button) findViewById(R.id.btnext);
        ename = (EditText) findViewById(R.id.ename);
        eage = (EditText) findViewById(R.id.eage);
        ecareer = (EditText) findViewById(R.id.eco);
        edegree = (EditText) findViewById(R.id.edegree);
        eemail = (EditText) findViewById(R.id.eemail);
        ephone = (EditText) findViewById(R.id.ephone);


        name = getIntent().getStringExtra(NAME_KEY);
        age = getIntent().getIntExtra(AGE_KEY, 0);
        style = getIntent().getIntExtra(STYLE_KEY,0);
        RadioButton rb1, rb2;
        if(style ==0){
            rb1 = (RadioButton)findViewById(R.id.rstyle1);
            rb1.setChecked(true);
        }
        else
        {
            rb2 = (RadioButton)findViewById(R.id.rstyle2);
            rb2.setChecked(true);
        }
        career = getIntent().getStringExtra(CAREER_KEY);
        degree = getIntent().getStringExtra(DEGREE_KEY);
        phone = getIntent().getStringExtra(PHONE_KEY);
        email = getIntent().getStringExtra(EMAIL_KEY);
        experiencetitle = getIntent().getStringArrayListExtra(TITLE_KEY);
        experiencedetail = getIntent().getStringArrayListExtra(DETAIL_KEY);
        educationtitle = getIntent().getStringArrayListExtra(EDUTITLE_KEY);
        educationdetail = getIntent().getStringArrayListExtra(EDUDETAIL_KEY);
        if (experiencetitle != null)
        { Log.e("", "experience length on main Create is  " + experiencetitle.size());

        }
        if (educationtitle != null)
        { Log.e("", "education length on main Create is  " + educationtitle.size());

        }
        ename.setText(name);
        if (age == 0)
            eage.setText("");
        else
            eage.setText(Integer.toString(age));
        ecareer.setText(career);
        edegree.setText(degree);
        eemail.setText(email);
        ephone.setText(phone);
        if (savedInstanceState != null) {
            // Restore value of members from saved state
//            resume = (Resume)getIntent().getSerializableExtra(SER_KEY);
//            ename.setText(resume.getName());
//            eage.setText(resume.getAge());
//            ecareer.setText(resume.getCareer());
//            edegree.setText(resume.getDegree());
//            ephone.setText(resume.getPhone());
//            eemail.setText(resume.getEmail());
            name = getIntent().getStringExtra(NAME_KEY);
            age = getIntent().getIntExtra(AGE_KEY, 0);
            style = getIntent().getIntExtra(STYLE_KEY,0);
            career = getIntent().getStringExtra(CAREER_KEY);
            degree = getIntent().getStringExtra(DEGREE_KEY);
            email = getIntent().getStringExtra(EMAIL_KEY);
            phone = getIntent().getStringExtra(PHONE_KEY);
            experiencetitle = getIntent().getStringArrayListExtra(TITLE_KEY);
            experiencedetail = getIntent().getStringArrayListExtra(DETAIL_KEY);
            educationtitle = getIntent().getStringArrayListExtra(EDUTITLE_KEY);
            educationdetail = getIntent().getStringArrayListExtra(EDUDETAIL_KEY);
            ename.setText(name);
            eage.setText(Integer.toString(age));
            ecareer.setText(career);
            edegree.setText(degree);
            eemail.setText(email);
            ephone.setText(phone);


        } else {
            // Probably initialize members with default values for a new instance


            btcreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    name = ename.getText().toString();
                    if (eage.getText().toString().isEmpty())
                        age = 0;
                    else
                        age = Integer.parseInt(eage.getText().toString());

                    career = ecareer.getText().toString();
                    degree = edegree.getText().toString();
                    email = eemail.getText().toString();
                    phone = ephone.getText().toString();

                    if (name.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (age == 0) {
                        Toast.makeText(getApplicationContext(), "Age cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (career.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Job Objective cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (degree.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Degree cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (phone.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Phone cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (email.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(isValidEmail(email)==false){
                        Toast.makeText(getApplicationContext(), "Wrong email address", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (experiencetitle == null ||experiencetitle.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Experience cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (educationtitle == null ||educationtitle.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Education cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }


//                resume = new Resume(filename,name,age,career,degree,email,phone,style, experiencetitle, experiencetitle);
                    filename = name + " " + career;
                    resume.setName(name);
                    resume.setAge(age);
                    resume.setCareer(career);
                    resume.setFilename(filename);
                    resume.setDegree(degree);
                    resume.setEmail(email);
                    resume.setPhone(phone);
                    resume.setStyle(style);
                    resume.setExperiencetitle(experiencetitle);
                    resume.setExperiencedetail(experiencedetail);
                    resume.setEducationtitle(educationtitle);
                    resume.setEducationdetail(educationdetail);
                    FileOperations fop = new FileOperations();
                    fop.write(resume);
                    if (fop.write(resume)) {
                        Toast.makeText(getApplicationContext(),
                                filename + ".pdf created", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(), "I/O error",
                                Toast.LENGTH_SHORT).show();
                    }

                    Intent resumelist = new Intent(v.getContext(), resumelist.class);
                    startActivity(resumelist);
                }
            });
            btread.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
//                // TODO Auto-generated method stub

//              name = ename.getText().toString();
                    if (eage.getText().toString().isEmpty())
                        age = 0;
                    else
                        age = Integer.parseInt(eage.getText().toString());

                    career = ecareer.getText().toString();
                    degree = edegree.getText().toString();
                    email = eemail.getText().toString();
                    phone = ephone.getText().toString();

                    if (ename.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (age == 0 ) {
                        Toast.makeText(getApplicationContext(), "Age cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(age<0){
                        Toast.makeText(getApplicationContext(), "Age cannot be negative", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (career.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Job Objective cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (degree.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Degree cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (phone.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Phone cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (email.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(isValidEmail(email)==false){
                        Toast.makeText(getApplicationContext(), "Wrong email address", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (experiencetitle == null || experiencetitle.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Experience cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (educationtitle == null || educationtitle.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Education cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }


//                resume = new Resume(filename,name,age,career,degree,email,phone,style, experiencetitle, experiencetitle);
                    filename = "thisistemp";
                    resume.setName(name);
                    resume.setAge(age);
                    resume.setCareer(career);
                    resume.setFilename(filename);
                    resume.setDegree(degree);
                    resume.setEmail(email);
                    resume.setPhone(phone);
                    resume.setStyle(style);
                    resume.setExperiencetitle(experiencetitle);
                    resume.setExperiencedetail(experiencedetail);
                    resume.setEducationtitle(educationtitle);
                    resume.setEducationdetail(educationdetail);
                    FileOperations fop = new FileOperations();
                    fop.write(resume);
                    if (fop.write(resume)) {
                        Toast.makeText(getApplicationContext(),
                                filename + ".pdf created", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(), "I/O error",
                                Toast.LENGTH_SHORT).show();
                    }
                    {
                    File pdfFile = new File(path + filename + ".pdf");  // -> filename = maven.pdf
                    Uri path = Uri.fromFile(pdfFile);
                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    pdfIntent.setDataAndType(path, "application/pdf");
                    pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(pdfIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(arg0.getContext(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                    }
                }








                }
            });



            btnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Resume resume = new Resume();
                    name = ename.getText().toString();
                    if (eage.getText().toString().isEmpty())
                        age = 0;
                    else
                        age = Integer.parseInt(eage.getText().toString());
                    career = ecareer.getText().toString();
                    degree = edegree.getText().toString();
                    email = eemail.getText().toString();
                    phone = ephone.getText().toString();
                    resume.setName(name);
//                    resume.name = name;
//                    Log.e("","name after setting is " + resume.getName() );
                    resume.setAge(age);
                    resume.setCareer(career);
                    resume.setFilename(filename);
                    resume.setDegree(degree);
                    resume.setEmail(email);
                    resume.setPhone(phone);
                    resume.setStyle(style);
                    resume.setExperiencetitle(experiencetitle);
                    resume.setExperiencedetail(experiencedetail);
                    resume.setExperiencetitle(educationtitle);
                    resume.setExperiencedetail(educationdetail);


                    Intent next = new Intent(v.getContext(), experience.class);
//                    Bundle b = new Bundle();
//                    b.putSerializable(SER_KEY,resume);

                    next.putExtra(NAME_KEY, name);
                    next.putExtra(AGE_KEY, age);
                    next.putExtra(STYLE_KEY,style);
                    next.putExtra(CAREER_KEY, career);
                    next.putExtra(DEGREE_KEY, degree);
                    next.putExtra(PHONE_KEY, phone);
                    next.putExtra(EMAIL_KEY, email);
                    next.putStringArrayListExtra(NEWTITLE_KEY, experiencetitle);
                    next.putStringArrayListExtra(NEWDETAIL_KEY, experiencedetail);
                    next.putStringArrayListExtra(NEWEDUTITLE_KEY, educationtitle);
                    next.putStringArrayListExtra(NEWEDUDETAIL_KEY, educationdetail);
//                    next.putExtras(b);
                    if (experiencetitle != null)
                        Log.e("", "experience length when click next is  " + experiencetitle.size());
                    if (educationtitle != null)
                        Log.e("", "experience length when click next is  " + educationtitle.size());
                    startActivity(next);
                }
            });

            btedu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Resume resume = new Resume();
                    name = ename.getText().toString();
                    if (eage.getText().toString().isEmpty())
                        age = 0;
                    else
                        age = Integer.parseInt(eage.getText().toString());
                    career = ecareer.getText().toString();
                    degree = edegree.getText().toString();
                    email = eemail.getText().toString();
                    phone = ephone.getText().toString();
                    resume.setName(name);
//                    resume.name = name;
//                    Log.e("","name after setting is " + resume.getName() );
                    resume.setAge(age);
                    resume.setCareer(career);
                    resume.setFilename(filename);
                    resume.setDegree(degree);
                    resume.setEmail(email);
                    resume.setPhone(phone);
                    resume.setStyle(style);
                    resume.setExperiencetitle(experiencetitle);
                    resume.setExperiencedetail(experiencedetail);
                    resume.setExperiencetitle(educationtitle);
                    resume.setExperiencedetail(educationdetail);


                    Intent intentedu = new Intent(v.getContext(), education.class);
//                    Bundle b = new Bundle();
//                    b.putSerializable(SER_KEY,resume);

                    intentedu.putExtra(NAME_KEY, name);
                    intentedu.putExtra(AGE_KEY, age);
                    intentedu.putExtra(STYLE_KEY,style);
                    intentedu.putExtra(CAREER_KEY, career);
                    intentedu.putExtra(DEGREE_KEY, degree);
                    intentedu.putExtra(PHONE_KEY, phone);
                    intentedu.putExtra(EMAIL_KEY, email);
                    intentedu.putStringArrayListExtra(NEWTITLE_KEY, experiencetitle);
                    intentedu.putStringArrayListExtra(NEWDETAIL_KEY, experiencedetail);
                    intentedu.putStringArrayListExtra(NEWEDUTITLE_KEY, educationtitle);
                    intentedu.putStringArrayListExtra(NEWEDUDETAIL_KEY, educationdetail);
//                    next.putExtras(b);
                    if (educationtitle != null)
                        Log.e("", "experience length when click next is  " + educationtitle.size());
                    startActivity(intentedu);
                }
            });
            btbacktolist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent resumelist = new Intent(getApplicationContext(), resumelist.class);
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked

                                    name = ename.getText().toString();
                                    if(eage.getText().toString().isEmpty())
                                        age = 0;
                                    else
                                        age = Integer.parseInt(eage.getText().toString());
                                    career = ecareer.getText().toString();
                                    degree = edegree.getText().toString();
                                    email = eemail.getText().toString();
                                    phone = ephone.getText().toString();

                                    resumelist.putExtra(NAME_KEY, name);
                                    resumelist.putExtra(AGE_KEY, age);
                                    resumelist.putExtra(CAREER_KEY, career);
                                    resumelist.putExtra(DEGREE_KEY, degree);
                                    resumelist.putExtra(PHONE_KEY, phone);
                                    resumelist.putExtra(EMAIL_KEY, email);
                                    resumelist.putStringArrayListExtra(NEWTITLE_KEY, experiencetitle);
                                    resumelist.putStringArrayListExtra(NEWDETAIL_KEY, experiencedetail);
                                    resumelist.putStringArrayListExtra(NEWEDUTITLE_KEY, educationtitle);
                                    resumelist.putStringArrayListExtra(NEWEDUDETAIL_KEY, educationdetail);
                                    resumelist.putExtra(STYLE_KEY,style);


                                    startActivity(resumelist);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked

                                    startActivity(resumelist);
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Do you want to save this?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();









                }
            });

//           eage.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   DialogFragment newFragment = new DatePickerFragment();
//                   newFragment.show(getFragmentManager(),"datePicker" );
//                   eage.setText(newFragment);
//               }
//           });



        }


    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rstyle1:
                if (checked)
                    // Pirates are the best
                {
                    style = 0;
                    break;
                }
            case R.id.rstyle2:
                if (checked) {
                    style = 1;
                    break;
                }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        name = ename.getText().toString();
        if(eage.getText().toString().isEmpty())
            age = 0;
        else
            age = Integer.parseInt(eage.getText().toString());
        career = ecareer.getText().toString();
        degree = edegree.getText().toString();
        email = eemail.getText().toString();
        phone = ephone.getText().toString();
        savedInstanceState.putString(NAME_KEY, name);
        savedInstanceState.putInt(AGE_KEY, age);
        savedInstanceState.putString(CAREER_KEY, career);
        savedInstanceState.putString(DEGREE_KEY, degree);
        savedInstanceState.putString(EMAIL_KEY, email);
        savedInstanceState.putString(PHONE_KEY, phone);
        savedInstanceState.putStringArrayList(TITLE_KEY,experiencetitle);
        savedInstanceState.putStringArrayList(DETAIL_KEY,experiencedetail);
        savedInstanceState.putStringArrayList(EDUTITLE_KEY,educationtitle);
        savedInstanceState.putStringArrayList(EDUDETAIL_KEY,educationdetail);
        savedInstanceState.putInt(STYLE_KEY, style);
//        savedInstanceState.putSerializable(SER_KEY, resume);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
