package com.example.lenovo.baodingchejian;

import android.content.Intent;
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

public class CheJianJianJieActivity extends AppCompatActivity {
    private ListView listView;
    private ProgressBar progressBar;
    private List<ProjectItemForCheJianJianJie> projectItemForCheJianJianJieList = new ArrayList<>();
    private List<String> add = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_jian_jian_jie);
        listView = (ListView)findViewById(R.id.chejianjianjielistview);
        progressBar = (ProgressBar)findViewById(R.id.chejianjianjieprogressbar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://43.226.46.228/chejianjianjie/chejianjianjie.txt")
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

                projectItemForCheJianJianJieList.add(new ProjectItemForCheJianJianJie(jsonObject.getString("name"),jsonObject.getString("url")));
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
                AdapterForCheJianJianJie adapter = new AdapterForCheJianJianJie(CheJianJianJieActivity.this,R.layout.layout4, projectItemForCheJianJianJieList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (projectItemForCheJianJianJieList.get(position).getName().equals("保定车间管辖范围图示")) {
                            Intent intent = new Intent(CheJianJianJieActivity.this,SubsamplingImageActivity.class);
                            intent.putExtra("addforimage",add.get(position));
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(CheJianJianJieActivity.this, CommonWebViewActivity.class);
                            intent.putExtra("add", add.get(position));
                            startActivity(intent);
                        }
                    }
                });

            }
        });
    }


}
