package com.example.mydb;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Chuanc extends AppCompatActivity {

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
        myAdapter adapter = new myAdapter(Chuanc.this, R.layout.anlistview, myBeanList);
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


    private void init() {
        //设置格式内容
        TextView title = (TextView)findViewById(R.id.style_foodname);
        title.setText("串串");
        TextView content  = (TextView)findViewById(R.id.style_material);
        content.setText("主料：豆皮适量、油豆泡适量、海带适量、鸡肫5个、土豆3个、菠菜适量、魔芋粉适量、香菇适量。\n"+
                "辅料：重庆火锅底料适量、葱适量、姜适量、蒜适量、糖少许、生抽少许、胡椒粉少许、料酒少许、鸡精少许。\n");

        myBean bean1 = new myBean("1,准备好自己喜欢的所有食材。", R.drawable.ccone);
        myBeanList.add(bean1);

        myBean bean2 = new myBean("2,锅中烧开水，放入所需的需要焯的食材。", R.drawable.cctwo);
        myBeanList.add(bean2);

        myBean bean3 = new myBean("3,所有食材用竹签串好备用。", R.drawable.ccthree);
        myBeanList.add(bean3);

        myBean bean4 = new myBean("4,煮好的汤汁倒入，把小锅和酒精炉一起上桌，随吃随煮极好。", R.drawable.ccfour);
        myBeanList.add(bean4);

    }
}