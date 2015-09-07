package com.example.meihanred.quickresume;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meihanred.quickresume.MainActivity;
import com.example.meihanred.quickresume.R;
import com.itextpdf.text.BaseColor;

public class MyAdapter extends BaseAdapter {

    private List<String> arrays = null;
    private Context mContext;
    private Button curDel_btn;
    private float x,ux;
    private com.example.meihanred.quickresume.resumelist resumelist;

    final List<File> files = getListFiles(new File("/mnt/sdcard/QuickResume/"));
    public String path = "/mnt/sdcard/QuickResume/";

    public MyAdapter(com.example.meihanred.quickresume.resumelist activity, Context mContext, List<String> arrays) {
        this.mContext = mContext;
        this.arrays = arrays;
        resumelist = activity;

    }

    public int getCount() {
        return this.arrays.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.resumelistitem, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.resumenamelistitem);
            viewHolder.btnDel = (Button) view.findViewById(R.id.del);
            view.setTag(viewHolder);
            if(position%2 != 0)
                view.setBackgroundColor(R.color.bright_foreground_material_light);
//            else
//                view.setBackgroundColor(R.color.material_blue_grey_800);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

//        view.setOnClickListener(new OnClickListener() {
//
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                String fpath = path + files.get(position).getName();
//                File file = new File(fpath);
//                // If file does not exists, then create it
//                if (!file.exists())
//                    Toast.makeText(v.getContext(), "File not exist", Toast.LENGTH_SHORT).show();
//                else {
//                    Toast.makeText(v.getContext(), "Reading" + files.get(position) , Toast.LENGTH_SHORT).show();
//
//                    File pdfFile = new File(path + files.get(position).getName());  // -> filename = maven.pdf
//                    Uri path = Uri.fromFile(pdfFile);
//                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//                    pdfIntent.setDataAndType(path, "application/pdf");
//                    pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                    try {
//                        resumelist.startActivity(pdfIntent);
//                    } catch (ActivityNotFoundException e) {
//                        Toast.makeText(v.getContext(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//        });


        //Add listener for every view
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                final ViewHolder holder = (ViewHolder) v.getTag();

                //当按下时处理
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

//					//设置背景为选中状态
//					v.setBackgroundResource(R.drawable.mm_listitem_pressed);
                    //获取按下时的x轴坐标
                    x = event.getX();
                    //判断之前是否出现了删除按钮如果存在就隐藏
                    if (curDel_btn != null) {
                        if(curDel_btn.getVisibility() == View.VISIBLE){
                            curDel_btn.setVisibility(View.GONE);
                            return true;
                        }
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {// 松开处理

                    //设置背景为未选中正常状态
                    //v.setBackgroundResource(R.drawable.mm_listitem_simple);
                    //获取松开时的x坐标
                    ux = event.getX();

                    //判断当前项中按钮控件不为空时
                    if (holder.btnDel != null) {

                        //按下和松开绝对值差当大于20时显示删除按钮，否则不显示

                        if (Math.abs(x - ux) > 20) {
                            holder.btnDel.setVisibility(View.VISIBLE);
                            curDel_btn = holder.btnDel;
                            return true;
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {//当滑动时背景为选中状态
                    return true;
                    //v.setBackgroundResource(R.drawable.mm_listitem_pressed);


                } else {//其他模式
                    //设置背景为未选中正常状态
                    //v.setBackgroundResource(R.drawable.mm_listitem_simple);

                }

                return false;
            }
        });
        viewHolder.tvTitle.setText(this.arrays.get(position));

        //为删除按钮添加监听事件，实现点击删除按钮时删除该项
        viewHolder.btnDel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if(curDel_btn!=null)
                    curDel_btn.setVisibility(View.GONE);
                arrays.remove(position);
                notifyDataSetChanged();


                File f0 = new File(path, files.get(position).getName());
                boolean d0 = f0.delete();


            }
        });
        return view;

    }

    final static class ViewHolder {
        TextView tvTitle;
        Button btnDel;
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


//    public void openActivity(){
//        Intent intent  = new Intent(this, TestActivity.class);
//        startActivity(intent);
//    }
}