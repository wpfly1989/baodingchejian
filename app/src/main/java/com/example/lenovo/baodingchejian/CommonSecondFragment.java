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
import android.widget.Toast;

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

public class CommonSecondFragment extends Fragment {
    private ListView listView;
    private ProgressBar progressBar;
    private List<CommonListViewItem> name = new ArrayList<>();
    private List<String> add = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secondactivityfragment,container,false);
        listView = (ListView)view.findViewById(R.id.commonsecondactivitylistview);
        progressBar = (ProgressBar)view.findViewById(R.id.commonsecondactivityprogressbar);

       // Intent intent = getActivity().getIntent();
        //String url = intent.getStringExtra("add");

        Bundle bundle = this.getArguments();
        String url = bundle.getString("add");

        parseJSON(url,name,add,listView);

        return view;
    }

    public void parseJSON(final String url, final List<CommonListViewItem> name, final List<String> add, final ListView listView) {
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

                            name.add(new CommonListViewItem(jsonObject.getString("name")));
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
                            AdapterForCommonListView adapter = new AdapterForCommonListView(getActivity(),R.layout.layout5, name);
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
