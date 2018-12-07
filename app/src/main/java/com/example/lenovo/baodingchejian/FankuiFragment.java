package com.example.lenovo.baodingchejian;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import q.rorbin.badgeview.QBadgeView;

public class FankuiFragment extends Fragment{
    private final String update = BuildConfig.VERSION_NAME;
    private ListView listView;
    private String[] data = {"声明：\n1、本软件仅供内部使用；\n2、软件内个别图片来源于网络，若涉及侵权请及时联系作者删除。",
    "版本：\n" + update,
    "反馈：\n若发现本软件内容有错误或者需要修改的地方，或者对本软件有更好的建议，请联系作者，不胜感谢!",
            "联系作者：\n邮箱：192388546@qq.com",
    "检查新版本"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewForFankui =inflater.inflate(R.layout.fankui_fragment,container,false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
        listView = (ListView)viewForFankui.findViewById(R.id.fankuiListView);
        listView.setAdapter(adapter);

        final QBadgeView qBadgeViewshuoming = new QBadgeView(getActivity());
        qBadgeViewshuoming.bindTarget(listView);
        qBadgeViewshuoming.setBadgeGravity(Gravity.BOTTOM|Gravity.CENTER);
        qBadgeViewshuoming.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://43.226.46.228/update/update.txt")
                            .build();
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    final String responseData = response.body().string();

                    if (!update.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeViewshuoming.setVisibility(View.VISIBLE);
                                qBadgeViewshuoming.setBadgeText("发现新版本，请及时更新");

                            }
                        });


                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data[position].contains("检查新")) {
                    if (qBadgeViewshuoming.isShown()) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("通知");
                        dialog.setMessage("发现最新版本，请下载更新");
                        dialog.setCancelable(false);
                        dialog.setNegativeButton("更新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("https://www.lanzous.com/b399282/"));
                                startActivity(intent);
                            }
                        });

                        dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                    } else {
                        Toast.makeText(getActivity(), "这是最新版本", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        return viewForFankui;
    }
}
