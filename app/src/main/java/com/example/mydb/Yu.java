package com.example.mydb;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Yu extends AppCompatActivity {

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
        myAdapter adapter = new myAdapter(Yu.this, R.layout.anlistview, myBeanList);
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
        title.setText("麻辣水煮鱼");
        TextView content  = (TextView)findViewById(R.id.style_material);
        content.setText("主料：鱼肉400克、白皮黄瓜1条、榨菜50克\n"+
                "辅料1：生姜5-6片、大蒜1头、干尖椒1把、花椒15克、小葱末适量、盐1小勺、料酒15ML、生粉10克。\n" +
                "辅料2：蛋清1个、德庄水煮鱼调料一包、色拉油200克\n");

        myBean bean1 = new myBean("1,3斤左右的草鱼一条，去头、内脏、鱼骨、鱼皮，只留净鱼肉。", R.drawable.yuone);
        myBeanList.add(bean1);

        myBean bean2 = new myBean("2,锅中倒入100克油，大火烧至七成热时，依次下入生姜、大蒜、干尖椒、花椒，爆香，小火炼出辣油。", R.drawable.yutwo);
        myBeanList.add(bean2);

        myBean bean3 = new myBean("3,转大火，将锅中汤汁煮至沸腾，下入腌好的鱼片。", R.drawable.yuthree);
        myBeanList.add(bean3);

        myBean bean4 = new myBean("4,将热油浇于鱼表皮即可。", R.drawable.yufour);
        myBeanList.add(bean4);

    }
}