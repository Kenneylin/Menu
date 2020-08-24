package com.example.mydb;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Dumplings extends AppCompatActivity {

    private List<myBean> myBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.style_layout);

        //lsitview
        ListView listView = (ListView) findViewById(R.id.Style_listview);
        //初始化listview，添加信息
        init();
        //设置适配器
        myAdapter adapter = new myAdapter(Dumplings.this, R.layout.anlistview, myBeanList);
        //加载适配器
        listView.setAdapter(adapter);

        //设置收藏按钮
        findViewById(R.id.style_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView message = (TextView) findViewById(R.id.style_foodname);
                String foodname = (String) message.getText();
                Data.setB(foodname);
            }
        });
    }


    //初始化内容，多个activity共用一个layout文件style_layout
    private void init() {
        //设置格式内容,添加标题和用料的内容
        TextView title = (TextView)findViewById(R.id.style_foodname);
        title.setText("钟水饺");
        TextView content  = (TextView)findViewById(R.id.style_material);
        content.setText("主料：饺子皮，肉。\n"+
                "辅料：生抽2勺鸡蛋1个水适量细砂糖适量蒜适量辣椒油。\n");

        myBean bean1 = new myBean("1,猪肉去皮剁碎。", R.drawable.done);
        myBeanList.add(bean1);

        myBean bean2 = new myBean("2,姜切细碎加入肉末中，1勺生抽和鸡蛋加入。", R.drawable.dtwo);
        myBeanList.add(bean2);

        myBean bean3 = new myBean("3,混合均匀。", R.drawable.dthree);
        myBeanList.add(bean3);

        myBean bean4 = new myBean("4,将饺子包好捏紧口。", R.drawable.dfour);
        myBeanList.add(bean4);

    }
}