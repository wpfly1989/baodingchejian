package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

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

public class FragmentForJinBao extends Fragment {
    private MagicIndicator magicIndicator;
    private String[] tittle = new String[]{"网图、环图","光缆芯线运用","基站、机房平面图","设备面板图","设备、板件说明书","端口台账"};
    private List<String> mList = Arrays.asList(tittle);
    private ListView listView1;
    private ListView listView2;
    private ListView listView2_1;
    private ListView listView2_2;
    private ListView listView3;
    private ListView listView4;
    private List<CommonListViewItem> commonListViewItemList1 = new ArrayList<>();
    private List<String> add1 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemList2 = new ArrayList<>();
    private List<String> add2 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemList2_1 = new ArrayList<>();
    private List<String> add2_1 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemList2_2 = new ArrayList<>();
    private List<String> add2_2 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemList3 = new ArrayList<>();
    private List<String> add3 = new ArrayList<>();
    private List<CommonListViewItem> commonListViewItemList4 = new ArrayList<>();
    private List<String> add4 = new ArrayList<>();
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentforjinbao,container,false);
        magicIndicator = (MagicIndicator)view.findViewById(R.id.jinbaoxianmagic);
        listView1 = (ListView)view.findViewById(R.id.jinbaoxianlistview1);
        listView2 = (ListView)view.findViewById(R.id.jinbaoxianlistview2);
        listView2_1 = (ListView)view.findViewById(R.id.jinbaoxianlistview2_1);
        listView2_2 = (ListView)view.findViewById(R.id.jinbaoxianlistview2_2);
        listView3 =  (ListView)view.findViewById(R.id.jinbaoxianlistview3);
        listView4 = (ListView)view.findViewById(R.id.jinbaoxianlistview4);
        progressBar = (ProgressBar)view.findViewById(R.id.jinbaoxianprogressbar);

        downloadParseJson("http://43.226.46.228/taizhangwangtu/jinbao/jinbaowang.txt",commonListViewItemList1,add1,listView1);
        downloadParseJson("http://43.226.46.228/taizhangwangtu/jinbao/jinbaoguanglanxinxianyunyong.txt",commonListViewItemList2,add2,listView2);
        downloadParseJson("http://43.226.46.228/taizhangwangtu/jinbao/jinbao3.txt",commonListViewItemList2_1,add2_1,listView2_1);
        downloadParseJson("http://43.226.46.228/taizhangwangtu/jinbao/jinbao4.txt",commonListViewItemList2_2,add2_2,listView2_2);
        downloadParseJson("http://43.226.46.228/taizhangwangtu/jinbao/jinbaoshebei.txt",commonListViewItemList3,add3,listView3);
        downloadParseJson("http://43.226.46.228/taizhangwangtu/jinbao/jinbaoduankoutaizhang.txt",commonListViewItemList4,add4,listView4);

        listView1.setVisibility(View.VISIBLE);

        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
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
                                //downloadParseJson("http://43.226.46.228/taizhangwangtu/jingguanggao/jingguanggaowangtu.txt",commonListViewItemList1,add1,listView1);
                                break;
                            case 1:
                                initListView();
                                listView2.setVisibility(View.VISIBLE);
                                //downloadParseJson("http://43.226.46.228/taizhangwangtu/jingguanggao/jingguanggaoguanglanxinxianyunyong.txt",commonListViewItemList2,add2,listView2);
                                break;
                            case 2:
                                initListView();
                                listView2_1.setVisibility(View.VISIBLE);
                                //downloadParseJson("http://43.226.46.228/taizhangwangtu/jingguanggao/jingguanggaoshebei.txt",commonListViewItemList3,add3,listView3);
                                break;
                            case 3:
                                initListView();
                                listView2_2.setVisibility(View.VISIBLE);
                                //downloadParseJson("http://43.226.46.228/taizhangwangtu/jingguanggao/jingguanggaoduankoutaizhang.txt",commonListViewItemList4,add4,listView4);
                                break;
                            case 4:
                                initListView();
                                listView3.setVisibility(View.VISIBLE);
                                break;
                            case 5:
                                initListView();
                                listView4.setVisibility(View.VISIBLE);
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


        return view;
    }

    private void initListView() {
        listView1.setVisibility(View.GONE);
        listView2.setVisibility(View.GONE);
        listView2_1.setVisibility(View.GONE);
        listView2_2.setVisibility(View.GONE);
        listView3.setVisibility(View.GONE);
        listView4.setVisibility(View.GONE);
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            AdapterForCommonListView adapter = new AdapterForCommonListView(getActivity(),R.layout.layout5, commonListViewItemList);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getActivity(),CommonWebViewActivity.class);
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
