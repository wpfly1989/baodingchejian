package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class TaiZhangWangTu2 extends AppCompatActivity {
    private ImageView jingguanggaotie;
    private ImageView baxvxian;
    private ImageView jingguangxian;
    private ImageView baomanxian;
    private ImageView manshenxian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_zhang_wang_tu2);

        jingguanggaotie = (ImageView)findViewById(R.id.taizhangwangtu2jingguanggaotie);
        baxvxian = (ImageView)findViewById(R.id.taizhangwangtu2baxvxian);
        jingguangxian = (ImageView)findViewById(R.id.taizhangwangtu2jingguangxian);
        baomanxian = (ImageView)findViewById(R.id.taizhangwangtu2baomanxian);
        manshenxian = (ImageView)findViewById(R.id.taizhangwangtu2manshenxian);

        jingguanggaotie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTZWTSecond("http://43.226.46.228/taizhangwangtu/jingguanggao/jingguanggao.txt","京广高铁");
            }
        });
        baxvxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTZWTSecond("http://43.226.46.228/taizhangwangtu/baxvxian/baxvxian.txt","霸徐（津保）线");
            }
        });
        jingguangxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTZWTSecond("http://43.226.46.228/taizhangwangtu/jingguangxian/jingguangxian.txt","京广线");
            }
        });
        baomanxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTZWTSecond("http://43.226.46.228/taizhangwangtu/baomanxian/baomanxian.txt","保满线");
            }
        });
        manshenxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTZWTSecond("http://43.226.46.228/taizhangwangtu/manshenxian/manshenxian.txt","满神线");
            }
        });
    }


    public void startTZWTSecond(String url,String tittl) {
        Intent intent = new Intent(TaiZhangWangTu2.this,TangZhangWangTuSecondActivity.class);
        intent.putExtra("add", url);
        intent.putExtra("tittl", tittl);
        startActivity(intent);
    }
}
