package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText zhanghao;
    private EditText mima;
    private Button denglu;
    private Button quxiao;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        zhanghao = (EditText)findViewById(R.id.zhanghao);
        mima = (EditText)findViewById(R.id.mima);
        denglu = (Button)findViewById(R.id.denglu);
        quxiao = (Button)findViewById(R.id.quxiao);
        //sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);

        if (pref.getBoolean("AUTO_ISCHECK",false)) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }

        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = zhanghao.getText().toString();
                password = mima.getText().toString();
                if (username.equals("bdcj") && password.equals("03127942426")) {
                    //sp.edit().putBoolean("AUTO_ISCHECK", true).commit();
                    SharedPreferences.Editor editor = getSharedPreferences("login",MODE_PRIVATE).edit();
                    editor.putBoolean("AUTO_ISCHECK",true);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "账号或者密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
