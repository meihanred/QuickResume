package com.example.meihanred.quickresume;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meihanred.quickresume.R;
import com.itextpdf.text.BaseColor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class resumelist extends ActionBarActivity {
    public  final static String SER_KEY = "com.example.quickresume.ser";
    public  final static String PAR_KEY = "com.example.quickresume.par";
    public  final static String SORTBY_KEY = "sortbykey";
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

    public String path = "/mnt/sdcard/QuickResume/";
    List<File> files = new ArrayList<File>();   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    List<String> list = new ArrayList<String>();
    String name, career, degree, email, phone;
    int age, style;
    ArrayList<String> experiencetitle;
    ArrayList<String> experiencedetail;
    ArrayList<String> educationtitle;
    ArrayList<String> educationdetail;
    MyAdapter adapter = null;
    ListView rlist = null;
    RadioButton rb1 = null, rb2=null;
    RadioGroup rgroup=null;
    int sortby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumelist);

        if (savedInstanceState != null) {
            sortby = getIntent().getIntExtra(SORTBY_KEY,0);
        }
        name = getIntent().getStringExtra(NAME_KEY);
        age = getIntent().getIntExtra(AGE_KEY, 0);

        style = getIntent().getIntExtra(STYLE_KEY,0);
        career = getIntent().getStringExtra(CAREER_KEY);
        degree = getIntent().getStringExtra(DEGREE_KEY);
        email = getIntent().getStringExtra(EMAIL_KEY);
        phone = getIntent().getStringExtra(PHONE_KEY);
        experiencetitle = getIntent().getStringArrayListExtra(NEWTITLE_KEY);
        experiencedetail = getIntent().getStringArrayListExtra(NEWDETAIL_KEY);
        educationtitle = getIntent().getStringArrayListExtra(NEWEDUTITLE_KEY);
        educationdetail = getIntent().getStringArrayListExtra(NEWEDUDETAIL_KEY);
        rb1 = (RadioButton)findViewById(R.id.rsortbydate);
        rb2 = (RadioButton)findViewById(R.id.rsortbyname);
        rgroup = (RadioGroup)findViewById(R.id.rsortgroup);
//        File directory = new File(Environment.getExternalStorageDirectory() + "/QuickResume");

        File DIR=null;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            DIR=new File(android.os.Environment.getExternalStorageDirectory()+"/QuickResume");
        else
            DIR=getApplicationContext().getCacheDir();
        if(!DIR.exists())
            DIR.mkdirs();
        Log.e("","Path is " + DIR.getAbsolutePath() );
//                new File(getFilesDir() + "/QuickResume");
//        directory.mkdirs();

        File f0 = new File(path,  "thisistemp.pdf");
        if(f0.exists())
            f0.delete();

        files = getListFiles(new File(DIR.getAbsolutePath()));


        Button btnewresume = (Button)findViewById(R.id.btnew);
        rlist = (ListView)findViewById(R.id.listView);
        final List<File> files = getListFiles(new File(DIR.getAbsolutePath()));





        int i;
        for(i=0;i<files.size();i++){
            Log.e("", "file " + i + " name is " + files.get(i).getName());
            list.add(files.get(i).getName());
        }

        adapter = new MyAdapter(this, this,list);
        adapter.notifyDataSetChanged();
        rlist.setAdapter(adapter);
        registerForContextMenu(rlist);

//        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // checkedId is the RadioButton selected
//
//
//                if(checkedId==R.id.rsortbydate) {
//                    sortby = 0;
//                    list.clear();
//                    int i;
//                    for(i=0;i<files.size();i++){
//                        Log.e("", "file " + i + " name is " + files.get(i).getName());
//                        list.add(files.get(i).getName());
//                    }
//                    ((BaseAdapter) rlist.getAdapter()).notifyDataSetChanged();
//                }
//                if(checkedId==R.id.rsortbyname)
//                {
//                    sortby = 1;
//                    list.clear();
//                    int i;
//                    for(i=0;i<files.size();i++){
//                        Log.e("", "file " + i + " name is " + files.get(i).getName());
//                        list.add(files.get(i).getName());
//                    }
//                    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
//                    ((BaseAdapter) rlist.getAdapter()).notifyDataSetChanged();
//                }
//            }
//        });
//        rb1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sortby = 0;
//                    list.clear();
//                    files = getListFiles(new File("/mnt/sdcard/QuickResume/"));
//                    int i;
//                    for(i=0;i<files.size();i++){
//                        Log.e("", "file " + i + " name is " + files.get(i).getName());
//                        list.add(files.get(i).getName());
//                    }
//                    ((BaseAdapter) rlist.getAdapter()).notifyDataSetChanged();
//            }
//        });
//
//        rb2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sortby = 1;
//                list.clear();
//                int i;
//                for(i=0;i<files.size();i++){
//                    Log.e("", "file " + i + " name is " + files.get(i).getName());
//                    list.add(files.get(i).getName());
//                }
//                Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
//                ((BaseAdapter) rlist.getAdapter()).notifyDataSetChanged();
//            }
//        });



        btnewresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent createresume = new Intent(v.getContext(), MainActivity.class);

                createresume.putExtra(NAME_KEY, name);
                createresume.putExtra(AGE_KEY, age);
                createresume.putExtra(STYLE_KEY,style);
                createresume.putExtra(CAREER_KEY, career);
                createresume.putExtra(DEGREE_KEY, degree);
                createresume.putExtra(PHONE_KEY, phone);
                createresume.putExtra(EMAIL_KEY, email);
                createresume.putStringArrayListExtra(TITLE_KEY, experiencetitle);
                createresume.putStringArrayListExtra(DETAIL_KEY, experiencedetail);
                createresume.putStringArrayListExtra(EDUTITLE_KEY, educationtitle);
                createresume.putStringArrayListExtra(EDUDETAIL_KEY, educationdetail);

                createresume.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(createresume);


            }
        });

        rlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fpath = path + files.get(position).getName();
                File file = new File(fpath);
                // If file does not exists, then create it
                if (!file.exists())
                    Toast.makeText(view.getContext(), "File not exist", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(view.getContext(), "Reading" + files.get(position) , Toast.LENGTH_SHORT).show();

                    File pdfFile = new File(path + files.get(position).getName());  // -> filename = maven.pdf
                    Uri path = Uri.fromFile(pdfFile);
                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    pdfIntent.setDataAndType(path, "application/pdf");
                    pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(pdfIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(view.getContext(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rsortbydate:
                if (checked)
                // Pirates are the best
                {
                    sortby = 0;
                    list.clear();
                    files = getListFiles(new File("/mnt/sdcard/QuickResume/"));
                    int i;
                    for(i=0;i<files.size();i++){
                        Log.e("", "file " + i + " name is " + files.get(i).getName());
                        list.add(files.get(i).getName());
                    }

                    ((BaseAdapter) rlist.getAdapter()).notifyDataSetChanged();
                    break;
                }
            case R.id.rsortbyname:
                if (checked) {
                sortby = 1;
                list.clear();
                files = getListFiles(new File("/mnt/sdcard/QuickResume/"));

                int i;
                for(i=0;i<files.size();i++){
                    Log.e("", "file " + i + " name is " + files.get(i).getName());
                    list.add(files.get(i).getName());
                }
                Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
                ((BaseAdapter) rlist.getAdapter()).notifyDataSetChanged();
                break;
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resumelist, menu);
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

    public List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                if(file.getName().endsWith(".pdf")){
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        Log.e("","OUTTTTTTTTTTTTTTTTTTT");
        if (v.getId()==R.id.listView) {

            Log.e("","INNNNNNNNNNNNNNNNNN");
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(files.get(info.position).getName());
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
//            menu.setHeaderTitle("Context Menu");
//            menu.add(0, v.getId(), 0, "Action 1");
//            menu.add(0, v.getId(), 0, "Action 2");



        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.menu);
        String menuItemName = menuItems[menuItemIndex];
        String listItemName = files.get(info.position).getName();

        if(item.getItemId()==1)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] tos = {"meihanred@gmail.com"};
//                String[] ccs = { " " };
//                String[] bccs = {" "};
            File externalStorage = Environment.getExternalStorageDirectory();
            Uri uri = Uri.fromFile(new File(externalStorage.getAbsolutePath() + "/" + "QuickResume/" + files.get(info.position).getName()));
            intent.putExtra(Intent.EXTRA_EMAIL, tos);
//                intent.putExtra(Intent.EXTRA_CC, ccs);
//                intent.putExtra(Intent.EXTRA_BCC, bccs);
            intent.putExtra(Intent.EXTRA_TEXT, "body");
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            intent.setType("application/pdf");
//                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path+filename+".pdf"));
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(intent, "Send email using:"));
//                intent.setType("message/rfc882");
//                Intent.createChooser(intent, "Choose Email Client");
//                startActivity(intent);

        }
        String filetodelete = files.get(info.position).getName();
        if(item.getItemId()==0){

            File f0 = new File(path,filetodelete);
            boolean d0 = f0.delete();
            list.clear();
            files = getListFiles(new File("/mnt/sdcard/QuickResume/"));
            int i;
            for(i=0;i<files.size();i++){
                Log.e("", "file " + i + " name is " + files.get(i).getName());
                list.add(files.get(i).getName());
            }
            ((BaseAdapter) rlist.getAdapter()).notifyDataSetChanged();
//            rlist.invalidateViews();


        }

        Toast.makeText(getApplicationContext(), filetodelete +" deleted", Toast.LENGTH_LONG).show();
//        TextView text = (TextView)findViewById(R.id.footer);
//        text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(SORTBY_KEY, sortby);
    }




}
