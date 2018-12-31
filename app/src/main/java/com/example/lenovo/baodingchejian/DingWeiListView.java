package com.example.lenovo.baodingchejian;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DingWeiListView extends AppCompatActivity {
    private ListView dingweiListView;
    private List<DingWeiListViewItem> dingweiListViewItemList = new ArrayList<>();
    private List<String> add = new ArrayList<>();
    private List<String> lat = new ArrayList<>();
    private List<String> lon = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_wei_list_view);

        dingweiListView = (ListView)findViewById(R.id.dingweiListView);
        progressBar = (ProgressBar)findViewById(R.id.dingweiProgressBar);

        Intent intent = getIntent();
        final String data = intent.getStringExtra("dingwei");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(data)
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    String responseData = response.body().string();
                    parseJSON(responseData);
                    domain();

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSON(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //dingweiListViewItemList.add(new CommonListViewItem(jsonObject.getString("name")), );
                dingweiListViewItemList.add(new DingWeiListViewItem(jsonObject.getString("biaoti"), jsonObject.getString("gongli")));
                add.add(jsonObject.getString("add"));
                lat.add(jsonObject.getString("lat"));
                lon.add(jsonObject.getString("lon"));

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void domain() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                final int[] a = new int[1];
                AdapterForDingWeiListView adapter = new AdapterForDingWeiListView(DingWeiListView.this,R.layout.layout6, dingweiListViewItemList);
                dingweiListView.setAdapter(adapter);
                dingweiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        a[0] = position;
                        View view1 = LayoutInflater.from(DingWeiListView.this).inflate(R.layout.mydialog,null,false);
                        final AlertDialog dialog = new AlertDialog.Builder(DingWeiListView.this).setView(view1).create();

                        Button baidu = (Button) view1.findViewById(R.id.baidu);
                        Button gaode = (Button)view1.findViewById(R.id.gaode);

                        baidu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setData(Uri.parse("baidumap://map/marker?location=" + add.get(a[0]) + "&title=" + dingweiListViewItemList.get(a[0]).getBiaoti() + "&content=makeamarker&traffic=on"));
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });

                        gaode.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setData(Uri.parse("androidamap://viewMap?sourceApplication=appname&poiname=" + dingweiListViewItemList.get(a[0]).getBiaoti() + "&lat=" + lat.get(a[0]) + "&lon=" + lon.get(a[0]) + "&dev=0"));
                                startActivity(intent);

                                dialog.dismiss();
                            }
                        });

                        dialog.show();


                    }
                });

            }
        });
    }




}
