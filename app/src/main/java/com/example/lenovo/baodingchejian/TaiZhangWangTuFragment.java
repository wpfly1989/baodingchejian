package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TaiZhangWangTuFragment extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<String> add = new ArrayList<>();
    private List<ProjectItemForTaiZhangWangTu> name = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.taizhangwangtu2fragment,container,false);
        progressBar = (ProgressBar)view.findViewById(R.id.taizhangwangtu2fragmentprogressbar);
        recyclerView = (RecyclerView)view.findViewById(R.id.taizhangwangtu2fragmentrecyclerview);
        Bundle bundle = this.getArguments();
        String url = bundle.getString("add");
        parseJSON(url,name,add,recyclerView);

        return view;
    }
    public void parseJSON(final String url, final List<ProjectItemForTaiZhangWangTu> name, final List<String> add, final RecyclerView recyclerView) {
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

                            name.add(new ProjectItemForTaiZhangWangTu(jsonObject.getString("name"),jsonObject.getString("url")));
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
                            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
                            recyclerView.setLayoutManager( layoutManager);
                            recyclerView.setNestedScrollingEnabled(false);
                            AdapterForTaiZhangWangTu adapter = new AdapterForTaiZhangWangTu(name);
                            recyclerView.setAdapter( adapter);
                            adapter.setOnItemClickListener(new AdapterForTaiZhangWangTu.OnItemClickListener() {
                                @Override
                                public void OnClick(View view, int position) {

                                    Intent intent = new Intent(getActivity(), WangTuHuanTuListView.class);
                                    intent.putExtra("addforimage", add.get(position));
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
