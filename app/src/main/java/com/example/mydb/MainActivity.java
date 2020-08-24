package com.example.mydb;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyHelper myHelper;
    EditText lg_account;
    EditText lg_code;
    //登陆页面的account和code
    String acc;
    String cd;
    //注册页面的account和code
    String rg_account,rg_code;
    SQLiteDatabase db;
    ContentValues values;
    TextView show;
    //查询的结果值
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper= new MyHelper(this);
        lg_account = findViewById(R.id.lg_account);
        lg_code = findViewById(R.id.lg_code);
        show = findViewById(R.id.Show);


        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acc = lg_account.getText().toString();
                cd = lg_code.getText().toString();
                query(acc);
                if (result.equals(cd)) {
                    Toast.makeText(MainActivity.this, result + "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Cuisine.class);
                    startActivity(intent);
                } else if (result == "")
                    Toast.makeText(MainActivity.this, "账号未注册，请先注册账号再登陆", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "账号和密码不匹配，登陆失败", Toast.LENGTH_SHORT).show();

            }
        });


        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivityForResult(intent,1);
            }
        });

        findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryall(show);
            }
        });
    }

    //注册的数据返回登陆界面,回传数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 2){
            rg_account = data.getStringExtra("account");
            rg_code = data.getStringExtra("code");
            //如果账号已经注册了，就不录入数据库
            query(rg_account);
            if(result != ""){
                Toast.makeText(MainActivity.this,rg_account+" 账号已经注册了，请重新更换账号进行注册",Toast.LENGTH_SHORT).show();
            }
            //将注册好的数据传入数据库
            //Toast.makeText(MainActivity.this," into"+rg_account+" "+rg_code,Toast.LENGTH_SHORT).show();
            //将有效的数据录入数据库
            else {
                add(rg_account, rg_code);
                //将注册的数据显示在登陆界面上
                lg_account.setText(rg_account);
                lg_code.setText(rg_code);
            }
        }
    }
    //录入数据库
    public void add(String account, String code){
       db = myHelper.getWritableDatabase();//获取数据库的使用
       values = new ContentValues();
       //Toast.makeText(MainActivity.this,account+" "+code,Toast.LENGTH_LONG).show();
       values.put("account",account);
       values.put("code",code);
       db.insert("surface",null,values);//添加信息
       db.close();//关闭数据库
   }
   //查询所有数据
   public void queryall(TextView tv){
        db = myHelper.getWritableDatabase();//获取数据库的使用
        Cursor cursor = db.query("surface",null,null,null,null,null,null);
        //查询到数据，在移动指针。否则报错
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            tv.setText(cursor.getString(1) + "---" + cursor.getString(2));
            while (cursor.moveToNext()) {
                tv.append("\n " + cursor.getString(1) + "---" + cursor.getString(2));
            }
        }
        cursor.close();
        db.close();
    }

    //查询单一数据
    public void query(String s){
        db = myHelper.getWritableDatabase();//获取数据库的使用
        Cursor cursor = db.rawQuery("select * from surface where account=?",new String[]{s});
        //判断是否查询到数据
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            result = cursor.getString(2);//获取密码
        }
        //没查询到数据，要将result重新定义为空
        else{
            result = "";
        }
        cursor.close();
        db.close();
    }

    //删除数据
    public void delete(){
        db = myHelper.getWritableDatabase();//获取数据库的使用
        db.delete("surface",null,null);
        Toast.makeText(MainActivity.this,"clear",Toast.LENGTH_SHORT).show();
        show.setText("");
        db.close();
    }
}

class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context, "mydata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE surface(_id INTEGER PRIMARY KEY AUTOINCREMENT,account VARCHAR(20), code VARCHAR(30),UNIQUE ('account'))");//定义账号的唯一性
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
