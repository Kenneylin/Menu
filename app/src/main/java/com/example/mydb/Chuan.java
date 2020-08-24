package com.example.mydb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Chuan extends AppCompatActivity {

    private IntentFilter intentFilter;
    private List<myBean> myBeanList = new ArrayList<>();
    private NetworkChangeReceiver networkChangeReceiver;
//动态监听网络状态的连接情况
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuan);
        intentFilter = new IntentFilter();  //指定IntentFilter
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);  //注册广播



        //lsitview
        ListView listView = (ListView) findViewById(R.id.Chuan_listview);
        //初始化listview，添加信息
        init();
        //设置适配器
        myAdapter adapter = new myAdapter(Chuan.this,R.layout.list_item,myBeanList);
        //加载适配器
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(Chuan.this,position+"第几项  "+Data.getB(),Toast.LENGTH_SHORT).show();
                //Cuisine cusine = new Cuisine();
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
    }

    public void Turn(Class s){
        Intent intent = new Intent(Chuan.this,s);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    private void init(){
        myBean bean1 = new myBean("水煮肉片",R.drawable.shui);
        myBeanList.add(bean1);

        myBean bean3 = new myBean("麻辣串串锅",R.drawable.chuan);
        myBeanList.add(bean3);
    }
    //创建广播接收器
    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Toast.makeText(context, "network is Connected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}