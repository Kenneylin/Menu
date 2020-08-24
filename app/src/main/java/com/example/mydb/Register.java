package com.example.mydb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText lg_mailbox,lg_password,lg_confirm;
    String mailbox,password,con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //获取注册的邮箱和密码
        lg_mailbox = findViewById(R.id.rg_mailbox);
        lg_password = findViewById(R.id.rg_password);
        lg_confirm = findViewById(R.id.rg_confirm);
        findViewById(R.id.rg_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                mailbox = lg_mailbox.getText().toString();
                password = lg_password.getText().toString();
                con = lg_confirm.getText().toString();

                if (mailbox.length() > 0 && password.equals(con) && password.length()> 0) {//判断注册的数据是否有效
                    intent.putExtra("account", mailbox);
                    intent.putExtra("code", password);
                    setResult(2, intent);
                    finish();
                } else {
                    Toast.makeText(Register.this, "注册失败，密码重输入错误。请重新注册", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
