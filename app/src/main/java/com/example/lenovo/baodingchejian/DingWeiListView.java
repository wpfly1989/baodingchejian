package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
                AdapterForDingWeiListView adapter = new AdapterForDingWeiListView(DingWeiListView.this,R.layout.layout6, dingweiListViewItemList);
                dingweiListView.setAdapter(adapter);
                dingweiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        intent.setData(Uri.parse("baidumap://map/marker?location=" + add.get(position) + "&title=" + dingweiListViewItemList.get(position).getBiaoti() + "&content=makeamarker&traffic=on"));
                        startActivity(intent);

                    }
                });

            }
        });
    }
}
