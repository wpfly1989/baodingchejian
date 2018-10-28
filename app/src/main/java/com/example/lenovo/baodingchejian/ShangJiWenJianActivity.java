package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShangJiWenJianActivity extends AppCompatActivity {
   private MagicIndicator magicIndicator;
    private String[] tittle = new String[]{"长效文件","年度办公网下发所有文件","记名传达文件"};
    private List<String> mList = Arrays.asList(tittle);
    private EditText editText;
   private ListView listView1;
   private ListView listView2;
   private ListView listView3;
    private List<CommonListViewItem> commonListViewItemList1 = new ArrayList<>();
    private List<String> add1 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemList2 = new ArrayList<>();
    private List<String> add2 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemList3 = new ArrayList<>();
    private List<String> add3 = new ArrayList<>();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_ji_wen_jian);


       magicIndicator = (MagicIndicator)findViewById(R.id.shangjiwenjianmagic);
       progressBar = (ProgressBar)findViewById(R.id.shangjiwenjianprogressbar);
       editText = (EditText)findViewById(R.id.shangjiwenjianedittext);
       listView1 = (ListView)findViewById(R.id.shangjiwenjianlistview1);
       listView2 = (ListView)findViewById(R.id.shangjiwenjianlistview2);
       listView3 = (ListView)findViewById(R.id.shangjiwenjianlistview3);

        downloadParseJson("http://43.226.46.228/wenjianxitong/changxiaowenjian/changxiaowenjian.txt",commonListViewItemList1,add1,listView1);
        downloadParseJson("http://43.226.46.228/wenjianxitong/suoyouwenjian/suoyouwenjian.txt",commonListViewItemList2,add2,listView2);
        downloadParseJson("http://43.226.46.228/wenjianxitong/jimingchuandawenjian/jimingchuandawenjian.txt",commonListViewItemList3,add3,listView3);
        listView1.setVisibility(View.VISIBLE);


       editText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ShangJiWenJianActivity.this,WenJianSouSuo.class);
               intent.putExtra("name", tittle);
               startActivity(intent);
           }
       });


        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);

                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);//设置为可渐变的View
                simplePagerTitleView.setNormalColor(Color.GRAY);//字渐变前
                simplePagerTitleView.setSelectedColor(Color.BLACK);//字渐变后
                simplePagerTitleView.setText(mList.get(index));
                simplePagerTitleView.setTextSize(20);//设置字体大小
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        magicIndicator.onPageSelected(index);
                        magicIndicator.onPageScrolled(index, 0.0F, 0);
                        switch (index) {
                            case 0:
                                initListView();
                                listView1.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                initListView();
                                listView2.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                initListView();
                                listView3.setVisibility(View.VISIBLE);
                                break;

                            default:
                                break;
                        }
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);

                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setColors(Color.RED);//线的颜色
                return linePagerIndicator;

            }
        });
        magicIndicator.setNavigator(commonNavigator);

    }

    private void initListView() {
        listView1.setVisibility(View.GONE);
        listView2.setVisibility(View.GONE);
        listView3.setVisibility(View.GONE);
    }


    public void downloadParseJson(final String url, final List<CommonListViewItem> commonListViewItemList, final List<String> add, final ListView listView) {
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            AdapterForCommonListView adapter = new AdapterForCommonListView(ShangJiWenJianActivity.this,R.layout.layout5, commonListViewItemList);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(ShangJiWenJianActivity.this,CommonWebViewActivity.class);
                                    intent.putExtra("add", add.get(position));
                                    startActivity(intent);

                                }
                            });

                        }
                    });

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
