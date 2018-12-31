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

public class WangTuHuanTuListView extends AppCompatActivity {
    private ProgressBar progressBar;
    private ListView listView;
    private List<CommonListViewItem> commonListViewItemList = new ArrayList<>();
    private List<String> add = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_tu_huan_tu_list_view);

        listView = (ListView)findViewById(R.id.wangtuhuantulistview);
        progressBar = (ProgressBar)findViewById(R.id.wangtuhuantuprogressbar);

        Intent intent = getIntent();
        final String url = intent.getStringExtra("addforimage");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
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
                AdapterForCommonListView adapter = new AdapterForCommonListView(WangTuHuanTuListView.this,R.layout.layout5, commonListViewItemList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (commonListViewItemList.get(position).getName().contains("网页")) {
                            Intent intent = new Intent(WangTuHuanTuListView.this,CommonWebViewActivity.class);
                            intent.putExtra("add",add.get(position));
                            startActivity(intent);
                        } else if (commonListViewItemList.get(position).getName().contains("CAD")){
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(add.get(position)));
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(WangTuHuanTuListView.this, SubsamplingImageActivity.class);
                            intent.putExtra("addforimage", add.get(position));
                            startActivity(intent);
                        }
                    }
                });

            }
        });
    }

}

