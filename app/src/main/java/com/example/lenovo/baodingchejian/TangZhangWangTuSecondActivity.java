package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

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
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TangZhangWangTuSecondActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private MagicIndicator magicIndicator;
    private Fragment contentFragment = new Fragment();
    private List<String> name = new ArrayList<>();
    private List<String> add = new ArrayList<>();
    private TaiZhangWangTuFragment taiZhangWangTuFragment = new TaiZhangWangTuFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tang_zhang_wang_tu_second);
        toolbar = (Toolbar)findViewById(R.id.taizhangwangtu2toolbar);
        magicIndicator = (MagicIndicator)findViewById(R.id.taizhangwangtu2magic);

        Intent intent = getIntent();
        String tittl = intent.getStringExtra("tittl");
        String url = intent.getStringExtra("add");

        toolbar.setTitle(tittl);
        parseJSON(url,name,add);

    }

    public void parseJSON(final String url, final List<String> name, final List<String> add) {
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

                            name.add(jsonObject.getString("name"));
                            add.add(jsonObject.getString("add"));


                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    //domainz();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction transaction = fm.beginTransaction();

                            Bundle bundle = new Bundle();
                            bundle.putString("add",add.get(0));
                            taiZhangWangTuFragment.setArguments(bundle);

                            transaction.replace(R.id.taizhangwangtu2contentframelayout,taiZhangWangTuFragment).show(taiZhangWangTuFragment).commit();

                            CommonNavigator commonNavigator = new CommonNavigator(TangZhangWangTuSecondActivity.this);
                            commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                                @Override
                                public int getCount() {
                                    return name.size();
                                }

                                @Override
                                public IPagerTitleView getTitleView(Context context, final int index) {
                                    BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);

                                    SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);//设置为可渐变的View
                                    simplePagerTitleView.setNormalColor(Color.GRAY);//字渐变前
                                    simplePagerTitleView.setSelectedColor(Color.BLACK);//字渐变后
                                    simplePagerTitleView.setText(name.get(index));
                                    simplePagerTitleView.setTextSize(20);//设置字体大小
                                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            magicIndicator.onPageSelected(index);
                                            magicIndicator.onPageScrolled(index, 0.0F, 0);
                                            FragmentManager fm = getSupportFragmentManager();
                                            FragmentTransaction transaction = fm.beginTransaction();
                                            transaction.remove(taiZhangWangTuFragment);

                                            TaiZhangWangTuFragment taiZhangWangTuFragment = new TaiZhangWangTuFragment();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("add",add.get(index));
                                            taiZhangWangTuFragment.setArguments(bundle);

                                            transaction.replace(R.id.taizhangwangtu2contentframelayout,taiZhangWangTuFragment).show(taiZhangWangTuFragment).commit();


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
                    });

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
