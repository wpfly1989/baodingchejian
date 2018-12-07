package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BanZuFragment extends Fragment{
    private ArrayList<ProjectItemForBanZuTuBiao> putie = new ArrayList<>();
    private ArrayList<ProjectItemForBanZuTuBiao> jidi = new ArrayList<>();

    //这个应该是防止Fragment来回切的时候出错的
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewForBanZu = inflater.inflate(R.layout.banzu_fragment,container,false);
        initPuTie();

        initJiDi();

        TuBiaoAdapter adapter1 = new TuBiaoAdapter(getActivity(),R.layout.layout3,putie);
        ListView listView1 = (ListView)viewForBanZu.findViewById(R.id.putiexianchangbanzu);
        listView1.setFocusable(false);
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectItemForBanZuTuBiao banZuTuBiao = putie.get(position);
                if (banZuTuBiao.getTuBiaoDa().contains("保定东高铁")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","baodingdonggaotiewenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("高碑店")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","gaobeidianwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("固城")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","guchengwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("保定通信")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","baodingwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("保定南")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","baodingnanwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("望都")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","wangduwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("定州通信")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","dingzhouwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("新乐")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","xinlewenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("白沟")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","baigouwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao.getTuBiaoDa().contains("定州东")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","dingzhoudonggaotiewenjianbeiwang");
                    startActivity(intent);
                }
            }
        });



        TuBiaoAdapter adapter3 = new TuBiaoAdapter(getActivity(),R.layout.layout3,jidi);
        final ListView listView3 = (ListView)viewForBanZu.findViewById(R.id.jidibanzu);
        listView3.setFocusable(false);
        listView3.setAdapter(adapter3);
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectItemForBanZuTuBiao banZuTuBiao1 = jidi.get(position);
                if (banZuTuBiao1.getTuBiaoDa().contains("无线")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao1.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","wuxianwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao1.getTuBiaoDa().contains("光电")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao1.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","guangdianwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao1.getTuBiaoDa().contains("网监")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao1.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","wangjianwenjianbeiwang");
                    startActivity(intent);
                } else if (banZuTuBiao1.getTuBiaoDa().contains("电报")) {
                    Intent intent = new Intent(getActivity(),BanZuXinXi.class);
                    intent.putExtra("name", banZuTuBiao1.getTuBiaoDa());
                    intent.putExtra("wenjianbeiwangname","dianbaosuowenjianbeiwang");
                    startActivity(intent);
                }
            }
        });
        return viewForBanZu;
    }

    private void initPuTie() {
        ProjectItemForBanZuTuBiao gaobeidian = new ProjectItemForBanZuTuBiao("高碑店通信工区", "车间包保干部 朱红海", "京广线：K64+50 -- K88+411", R.mipmap.gaobeidian);
        putie.add(gaobeidian);
        ProjectItemForBanZuTuBiao gucheng = new ProjectItemForBanZuTuBiao("固城通信工区", "车间包保干部 朱红海", "京广线：K88+411（不含） -- K123+914（不含）", R.mipmap.gucheng);
        putie.add(gucheng);
        ProjectItemForBanZuTuBiao baoding = new ProjectItemForBanZuTuBiao("保定通信工区", "车间包保干部 王会然", "京广线：K123+914 -- K134+800", R.mipmap.baoding);
        putie.add(baoding);
        ProjectItemForBanZuTuBiao baodingnan = new ProjectItemForBanZuTuBiao("保定南通信工区", "车间包保干部 孙亚军", "京广线：K134+800（不含） -- K146+886，保满线，满神线", R.mipmap.baodingnan);
        putie.add(baodingnan);
        ProjectItemForBanZuTuBiao wangdu = new ProjectItemForBanZuTuBiao("望都通信工区", "车间包保干部 高利民", "京广线：K146+886（不含） -- K181+511（不含）", R.mipmap.wangdu);
        putie.add(wangdu);
        ProjectItemForBanZuTuBiao dingzhou = new ProjectItemForBanZuTuBiao("定州通信工区", "车间包保干部 张艳春", "京广线：K181+511 -- K205+818", R.mipmap.dingzhou);
        putie.add(dingzhou);
        ProjectItemForBanZuTuBiao xinle = new ProjectItemForBanZuTuBiao("新乐通信工区", "车间包保干部 高利民", "京广线：K205+818（不含） -- K242+330（不含）", R.mipmap.xinle);
        putie.add(xinle);
        ProjectItemForBanZuTuBiao baigou = new ProjectItemForBanZuTuBiao("白沟通信工区", "车间包保干部 胡旭瑞", "霸徐铁路：K81+444 -- K138+347", R.mipmap.baigou);
        putie.add(baigou);
        ProjectItemForBanZuTuBiao baodingdong = new ProjectItemForBanZuTuBiao("保定东高铁通信工区", "车间包保干部 杨秀玉", "京广高铁：K73+915（不含）-- K164+201", R.mipmap.baodingdong);
        putie.add(baodingdong);
        ProjectItemForBanZuTuBiao dingzhoudong = new ProjectItemForBanZuTuBiao("定州东高铁通信工区", "车间包保干部 王健", "京广高铁：K164+201（不含）-- K244+943", R.mipmap.dingzhoudong);
        putie.add(dingzhoudong);

    }

    private void initJiDi() {
        ProjectItemForBanZuTuBiao wuxian = new ProjectItemForBanZuTuBiao("无线集中修工区", "车间包保干部 张艳春", "基地班组", R.mipmap.wuxian);
        jidi.add(wuxian);
        ProjectItemForBanZuTuBiao guangdian = new ProjectItemForBanZuTuBiao("保定光电工区", "车间包保干部 李保军", "基地班组", R.mipmap.guangdian);
        jidi.add(guangdian);
        ProjectItemForBanZuTuBiao wangjian = new ProjectItemForBanZuTuBiao("网监室", "车间包保干部 李保军", "基地班组", R.mipmap.wangjian);
        jidi.add(wangjian);
        ProjectItemForBanZuTuBiao dianbao = new ProjectItemForBanZuTuBiao("电报所", "车间包保干部 马红岩", "基地班组", R.mipmap.dianbaosuo);
        jidi.add(dianbao);

    }


}
