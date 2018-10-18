package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private Fragment currentFragment = new Fragment();
    private CheJianFragment cheJianFragment;
    private BanZuFragment banZuFragment;
    private TongZhiFragment tongZhiFragment;
    private FankuiFragment fankuiFragment;
    private RadioButton chejianRadioButton;
    private RadioButton banzuRadioButton;
    private RadioButton tongzhiRadioButton;
    private RadioButton fankuiRadioButton;
    private RadioGroup radioGroup;
    private Button button;
    private Button shuomingbutton;
    private long exitTime = 0;
    private final String updatecode = BuildConfig.VERSION_NAME;

    private String zhuyetongzhi;
    private String tongzhineirong;
    private String tongzhibutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar的显示和设定
        Toolbar toolbar = (Toolbar)findViewById(R.id.mainActivityToolbar);
        setSupportActionBar(toolbar);

        //初始化radiogroup和radiobutton
        radioGroup = (RadioGroup)findViewById(R.id.mainActivityRadioGroup);
        chejianRadioButton = (RadioButton)findViewById(R.id.chejianradiobutton);
        banzuRadioButton = (RadioButton)findViewById(R.id.banzuradiobutton);
        tongzhiRadioButton = (RadioButton)findViewById(R.id.tongzhiradiobutton);
        fankuiRadioButton = (RadioButton)findViewById(R.id.fankuiradiobutton);
        button = (Button)findViewById(R.id.toumingbutton);
        shuomingbutton = (Button)findViewById(R.id.shuomingbutton);
        radioGroup.setOnCheckedChangeListener(this);


        cheJianFragment = new CheJianFragment();
        banZuFragment = new BanZuFragment();
        tongZhiFragment = new TongZhiFragment();
        fankuiFragment = new FankuiFragment();



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://43.226.46.228/notification/tongzhimsg1dian1.txt")
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    final String responseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tongzhineirong = responseData;
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("通知");
                            dialog.setMessage(tongzhineirong);
                            dialog.setCancelable(false);
                            dialog.setNegativeButton("现在查看", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this,CommonWebViewActivity.class);
                                    intent.putExtra("add","http://43.226.46.228/notification/xianzaichakan1dian1.html");
                                    startActivity(intent);
                                }
                            });
                            dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            dialog.show();
                        }
                    });


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //AlertDialog



        //这个是badgeview开源框架
        final QBadgeView qBadgeView = new QBadgeView(this);
        qBadgeView.bindTarget(button);
        qBadgeView.setVisibility(View.GONE);

        final QBadgeView qBadgeView1 = new QBadgeView(this);
        qBadgeView1.bindTarget(shuomingbutton);

        zhuyetongzhi =load("data");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://43.226.46.228/notification/zhuyetongzhi.txt")
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    final String responseData = response.body().string();

                    if (!zhuyetongzhi.equals(responseData)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("新");
                                zhuyetongzhi = responseData;
                            }
                        });


                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://43.226.46.228/update/update.txt")
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    final String responseData = response.body().string();

                    if (!updatecode.equals(responseData)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView1.setBadgeText("升级");

                            }
                        });


                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        tongzhiRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qBadgeView.isShown()) {

                    qBadgeView.hide(true);

                    write("data", zhuyetongzhi);

                }
                showFragment(tongZhiFragment);
                initColor();
                tongzhiRadioButton.setTextColor(Color.parseColor("#00C957"));

            }
        });



        chejianRadioButton.setTextColor(Color.parseColor("#00C957"));
       showFragment(cheJianFragment);
    }

    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;

            if (!fragment.isAdded()) {
                transaction.add(R.id.contentFragment, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }

        }
    }

    //加载toolbar菜单文件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    //为toolbar按钮提供点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fenxiangciruanjian :
                Intent intent = new Intent(MainActivity.this,DownloadActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
        return true;
    }

    //radiobutton文字颜色初始化
    private void initColor() {
        chejianRadioButton.setTextColor(Color.BLACK);
        banzuRadioButton.setTextColor(Color.BLACK);
        tongzhiRadioButton.setTextColor(Color.BLACK);
        fankuiRadioButton.setTextColor(Color.BLACK);
    }

    //radiogroup点击事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.chejianradiobutton :
                initColor();
                chejianRadioButton.setTextColor(Color.parseColor("#00C957"));
                showFragment(cheJianFragment);
                break;
            case R.id.banzuradiobutton :
                showFragment(banZuFragment);
                initColor();
                banzuRadioButton.setTextColor(Color.parseColor("#00C957"));

                break;
           /* case R.id.tongzhiradiobutton:
               showFragment(tongZhiFragment);
               initColor();
               tongzhiRadioButton.setTextColor(Color.parseColor("#00C957"));
                break;*/
            case R.id.fankuiradiobutton:
                showFragment(fankuiFragment);
                initColor();
                fankuiRadioButton.setTextColor(Color.parseColor("#00C957"));
                default:
                    break;
        }
    }

    private String load(String data) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput(data);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }



    private void write(String add, String msg) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput(add, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(msg);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                //finish();
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
