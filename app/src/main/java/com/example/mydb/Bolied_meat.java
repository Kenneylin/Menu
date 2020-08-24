package com.example.mydb;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Bolied_meat extends AppCompatActivity {

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
        myAdapter adapter = new myAdapter(Bolied_meat.this,R.layout.anlistview, myBeanList);
        //加载适配器
        listView.setAdapter(adapter);

        //设置收藏按钮
        findViewById(R.id.style_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView message = (TextView)findViewById(R.id.style_foodname);
                String foodname = (String) message.getText();
                Data.setB(foodname);
            }
        });
    }


    private void init() {

        //设置格式内容,添加标题和用料的内容
        TextView title = (TextView)findViewById(R.id.style_foodname);
        title.setText("水煮肉片");
        TextView content  = (TextView)findViewById(R.id.style_material);
        content.setText("主料：猪瘦肉200克、生菜120克。\n" +
                "调料1：干辣椒约15克、花椒2克、郫县豆瓣2大匙、蒜5瓣、葱2根、白糖1小匙、生抽1大匙、芝麻油1小匙、鸡精少许、料酒1小匙、植物油适量。\n" +
                "调料2：白糖1/2小匙、盐适量、鸡蛋清1个、酱油1小匙、水淀粉1大匙。");

        myBean bean1 = new myBean("1,将肉切成薄片。",R.drawable.first);
        myBeanList.add(bean1);

        myBean bean2 = new myBean("2,干辣椒擦洗干净，切成段；郫县豆瓣剁碎，大蒜去皮切成末，葱切段，生菜洗净沥干。", R.drawable.second);
        myBeanList.add(bean2);

        myBean bean3 = new myBean("3,锅内放植物油，下入干椒段与花椒，小火，将其炒香后舀出，待用。", R.drawable.third);
        myBeanList.add(bean3);

        myBean bean4 = new myBean("4,锅内余油，放入生菜，大火，炒约一分钟。", R.drawable.forth);
        myBeanList.add(bean4);

    }
}