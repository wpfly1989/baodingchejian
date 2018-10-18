package com.example.lenovo.baodingchejian;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommonListView extends AppCompatActivity {
    private ListView listView;
    private List<CommonListViewItem> commonListViewItemList = new ArrayList<>();
    private List<String> add = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_list_view);
        listView = (ListView)findViewById(R.id.commonListView);
        progressBar = (ProgressBar)findViewById(R.id.commonListViewProgressBar);

        Intent intent = getIntent();
        final String data = intent.getStringExtra("addforhttp");


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

                commonListViewItemList.add(new CommonListViewItem(jsonObject.getString("name")));
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
                AdapterForCommonListView adapter = new AdapterForCommonListView(CommonListView.this,R.layout.layout5, commonListViewItemList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(CommonListView.this,CommonWebViewActivity.class);
                        intent.putExtra("add", add.get(position));
                        startActivity(intent);

                    }
                });

            }
        });
    }


}
