package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShangJiWenJianActivity extends AppCompatActivity {
    private TextView suoyou;
    private TextView xianqi;
    private TextView zhongyao;
    private ListView suoyouListView;
    private ListView xianqiListView;
    private ListView zhongyaoListView;
    private List<CommonListViewItem> commonListViewItemLists = new ArrayList<>();
    private List<String> adds = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemListx = new ArrayList<>();
    private List<String> addx = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemListz = new ArrayList<>();
    private List<String> addz = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_ji_wen_jian);

        suoyou = (TextView)findViewById(R.id.suoyouwenjian);
        xianqi = (TextView)findViewById(R.id.xianqiwenjian);
        zhongyao = (TextView)findViewById(R.id.zhongyaowenjian);
        suoyouListView = (ListView)findViewById(R.id.suoyouwenjianListView);
        xianqiListView = (ListView)findViewById(R.id.xianqiwenjianListView);
        zhongyaoListView = (ListView)findViewById(R.id.zhongyaowenjianListView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://43.226.46.228/shangjiwenjian/suoyouwenjian.txt")
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    String responseData = response.body().string();
                    parseJSONs(responseData);
                    domains();

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
                            .url("http://43.226.46.228/shangjiwenjian/xianqiwenjian.txt")
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    String responseData = response.body().string();
                    parseJSONx(responseData);
                    domainx();

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
                            .url("http://43.226.46.228/shangjiwenjian/zhongyaowenjian.txt")
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    String responseData = response.body().string();
                    parseJSONz(responseData);
                    domainz();

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        suoyou.setTextColor(Color.parseColor("#00C957"));
        suoyouListView.setVisibility(View.VISIBLE);
        suoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initColor();
                initVB();
                suoyou.setTextColor(Color.parseColor("#00C957"));
                suoyouListView.setVisibility(View.VISIBLE);
            }
        });

        xianqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initColor();
                initVB();
                xianqi.setTextColor(Color.parseColor("#00C957"));
                xianqiListView.setVisibility(View.VISIBLE);
            }
        });

        zhongyao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initColor();
                initVB();
                zhongyao.setTextColor(Color.parseColor("#00C957"));
                zhongyaoListView.setVisibility(View.VISIBLE);
            }
        });



    }

    private void initColor() {
        suoyou.setTextColor(Color.BLACK);
        xianqi.setTextColor(Color.BLACK);
        zhongyao.setTextColor(Color.BLACK);
    }

    private void initVB() {
        suoyouListView.setVisibility(View.GONE);
        xianqiListView.setVisibility(View.GONE);
        zhongyaoListView.setVisibility(View.GONE);
    }

    private void parseJSONs(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                commonListViewItemLists.add(new CommonListViewItem(jsonObject.getString("name")));
                adds.add(jsonObject.getString("add"));


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void domains() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AdapterForCommonListView adapter = new AdapterForCommonListView(ShangJiWenJianActivity.this,R.layout.layout5, commonListViewItemLists);
                suoyouListView.setAdapter(adapter);
                suoyouListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ShangJiWenJianActivity.this,CommonWebViewActivity.class);
                        intent.putExtra("add", adds.get(position));
                        startActivity(intent);

                    }
                });

            }
        });
    }

    private void parseJSONx(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                commonListViewItemListx.add(new CommonListViewItem(jsonObject.getString("name")));
                addx.add(jsonObject.getString("add"));


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void domainx() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AdapterForCommonListView adapter = new AdapterForCommonListView(ShangJiWenJianActivity.this,R.layout.layout5, commonListViewItemListx);
                xianqiListView.setAdapter(adapter);
                xianqiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ShangJiWenJianActivity.this,CommonWebViewActivity.class);
                        intent.putExtra("add", addx.get(position));
                        startActivity(intent);

                    }
                });

            }
        });
    }

    private void parseJSONz(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                commonListViewItemListz.add(new CommonListViewItem(jsonObject.getString("name")));
                addz.add(jsonObject.getString("add"));


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void domainz() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AdapterForCommonListView adapter = new AdapterForCommonListView(ShangJiWenJianActivity.this,R.layout.layout5, commonListViewItemListz);
                zhongyaoListView.setAdapter(adapter);
                zhongyaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ShangJiWenJianActivity.this,CommonWebViewActivity.class);
                        intent.putExtra("add", addz.get(position));
                        startActivity(intent);

                    }
                });

            }
        });
    }
}
