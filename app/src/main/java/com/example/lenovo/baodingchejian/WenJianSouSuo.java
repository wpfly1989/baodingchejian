package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WenJianSouSuo extends AppCompatActivity {
    private SearchView searchView;
    private ProgressBar progressBar;
    private ListView listView;
    private List<CommonListViewItem> commonListViewItemList1 = new ArrayList<>();
    private List<String> add1 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemList2 = new ArrayList<>();
    private List<String> add2 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemListSearch = new ArrayList<>();
    private List<String> addSearch = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_jian_sou_suo);

        searchView = (SearchView)findViewById(R.id.wenjiansousuosearchview);
        progressBar = (ProgressBar)findViewById(R.id.wenjiansousuoprogressbar);
        listView = (ListView)findViewById(R.id.wenjiansousuolistview);

        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("请输入关键字");

        downloadParseJson("http://43.226.46.228/wenjianxitong/chaxunwenjian.txt",commonListViewItemList1,add1);
        //downloadParseJson("http://43.226.46.228/wenjianxitong/changxiaowenjian/changxiaowenjian.txt",commonListViewItemList1,add1);
        //downloadParseJson("http://43.226.46.228/wenjianxitong/suoyouwenjian/suoyouwenjian.txt",commonListViewItemList2,add2);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                commonListViewItemListSearch.clear();
                addSearch.clear();
                /*for (CommonListViewItem commonListViewItem : commonListViewItemList2) {
                    if (commonListViewItem.getName().contains(query)) {
                        commonListViewItemListSearch.add(commonListViewItem);
                        addSearch.add(add2.get(commonListViewItemList2.indexOf(commonListViewItem)));
                    }
                }*/
                for(CommonListViewItem commonListViewItem : commonListViewItemList1) {
                    if (commonListViewItem.getName().contains(query)) {
                        commonListViewItemListSearch.add(commonListViewItem);
                        addSearch.add(add1.get(commonListViewItemList1.indexOf(commonListViewItem)));
                    }
                }


                AdapterForCommonListView adapter = new AdapterForCommonListView(WenJianSouSuo.this,R.layout.layout5, commonListViewItemListSearch);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(WenJianSouSuo.this,CommonWebViewActivity.class);
                        intent.putExtra("add", addSearch.get(position));
                        startActivity(intent);

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



    }

    public void downloadParseJson(final String url, final List<CommonListViewItem> commonListViewItemList, final List<String> add) {
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
                    try {
                        JSONArray jsonArray = new JSONArray(responseData);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            commonListViewItemList.add(new CommonListViewItem(jsonObject.getString("name")));
                            add.add(jsonObject.getString("add"));


                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    //domainz();
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AdapterForCommonListView adapter = new AdapterForCommonListView(WenJianSouSuo.this,R.layout.layout5, commonListViewItemList);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(WenJianSouSuo.this,CommonWebViewActivity.class);
                                    intent.putExtra("add", add.get(position));
                                    startActivity(intent);

                                }
                            });

                        }
                    });*/

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
