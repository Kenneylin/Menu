package com.example.mydb;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Cuisine extends AppCompatActivity implements View.OnClickListener {

    private List<myBean> myBeanList = new ArrayList<>();
    private Button btnStart,btnStop;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
        //找到菜系的Button控件和收藏记录的控件
        Button chuan = findViewById(R.id.chuan);
        Button lu = findViewById(R.id.lu);
        Button yue = findViewById(R.id.yue);
        Button su = findViewById(R.id.su);
        Button record = findViewById(R.id.recrod);
        //为菜系控件和收藏记录的控件添加监听器
        chuan.setOnClickListener(this);
        lu.setOnClickListener(this);
        yue.setOnClickListener(this);
        su.setOnClickListener(this);
        record.setOnClickListener(this);
        //找到两个listview
        ListView listView = (ListView) findViewById(R.id.listview);
        //初始化listview，添加信息
        init();
        //设置适配器
        myAdapter adapter = new myAdapter(Cuisine.this,R.layout.list_item,myBeanList);
        //加载适配器
        listView.setAdapter(adapter);
        //推荐的菜肴添加监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(Cuisine.this, position + "第几项", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        Turn(Bolied_meat.class);
                        break;
                    case 1:
                       Turn(Dumplings.class);
                       break;
                    case 2:
                        Turn(Chuanc.class);
                        break;
                    case 3:
                        Turn(Yu.class);
                        break;
                }

            }
        });

        //播放音乐
        btnStart=(Button)findViewById(R.id.Startbtn);
        btnStop=(Button)findViewById(R.id.Stopbtn);
        intent=new Intent(Cuisine.this,MyIntentService.class);
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startService(intent);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                stopService(intent);
            }
        });

    }
    private void init(){
        myBean bean1 = new myBean("水煮肉片",R.drawable.shui);
        myBeanList.add(bean1);

        myBean bean2 = new myBean("钟水饺",R.drawable.jiao);
        myBeanList.add(bean2);

        myBean bean3 = new myBean("麻辣串串锅",R.drawable.chuan);
        myBeanList.add(bean3);

        myBean bean4 = new myBean("麻辣鱼",R.drawable.yu);
        myBeanList.add(bean4);

    }

    //设置普通对话框，点击返回按键就退出应用

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setMessage("Confirm to quit?")
                .setTitle("NormalDialog")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭对话框
                        dialog.dismiss();
                        //关闭音乐
                        stopService(intent);
                        //android.os.Process.killProcess(android.os.Process.myPid());  //获取PID
                        //System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
                        Cuisine.this.finish();
                    }
                })
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //点击菜系分类的按钮，跳转
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chuan:
                Turn(Chuan.class);
                break;
            case R.id.lu:
                Turn(Dumplings.class);
                break;
            case R.id.yue:
                Turn(Chuanc.class);
                break;
            case R.id.su:
                Turn(Yu.class);
                break;
            case R.id.recrod:
                Turn(Records.class);
                break;
        }
    }

    //跳转次数太多，写个方法节省代码
    public void Turn(Class s){
        Intent intent = new Intent(Cuisine.this,s);
        startActivity(intent);
    }
}