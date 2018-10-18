package com.example.lenovo.baodingchejian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import q.rorbin.badgeview.QBadgeView;

public class CheJianFragment extends Fragment{

    private ScrollingTextView scrollingTextView;
    private Calendar calendarbeFore;
    private Calendar calendarNow;
    private XBanner xBanner;
    private ArrayList<Integer> image;
    private ArrayList<String> tittle;
    private ArrayList<ProjectItemForCheJian> projectList = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectList1 = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListm = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectLists = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListg = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListh = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListwj = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListwhr = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListlbj = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListzyc = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListzhh = new ArrayList<>();
    private ArrayList<ProjectItemForCheJian> projectListyxy = new ArrayList<>();
    private String libaojunwenjiantonggao;
    private String zhangyanchunwenjiantonggao;
    private String zhuhonghaiwenjiantonggao;
    private String yangxiuyuwenjiantonggao;
    private String chejiandiaoduwenjiantonggao;
    private String mahongyanwenjiantonggao;
    private String sunyajunwenjiantonggao;
    private String gaoliminwenjiantonggao;
    private String huxvruiwenjiantonggao;
    private String wangjianwenjiantonggao;
    private String wanghuiranwenjiantonggao;
    private String zhongjianlianglbj;
    private String zhongjianliangzyc;
    private String zhongjianliangzhh;
    private String zhongjianliangyxy;
    private String zhongjianliangcjdd;
    private String zhongjianliangmhy;
    private String zhongjianliangsyj;
    private String zhongjianliangglm;
    private String zhongjianlianghxr;
    private String zhongjianliangwj;
    private String zhongjianliangwhr;
    private String abc;

    //这个应该是防止Fragment来回切的时候出错的
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewForCheJian = inflater.inflate(R.layout.chejian_fragment, container, false);

        scrollingTextView = (ScrollingTextView)viewForCheJian.findViewById(R.id.scrollingTextViewId);
        xBanner = (XBanner)viewForCheJian.findViewById(R.id.xbanner);

        //以下内容为滚动字幕中安全生产天数的计算
        calendarbeFore = Calendar.getInstance();
        calendarbeFore.set(2010, 2, 21);
        long timeOfDuan = calendarbeFore.getTimeInMillis();
        calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        long timeNow = calendarNow.getTimeInMillis();
        long daysOfDuan = (timeNow - timeOfDuan) / 1000 / 60 / 60 / 24 - 1;
        String msg = "安全优质、兴路强国     段安全生产" + daysOfDuan + "天 车间安全生产" + daysOfDuan + "天";
        scrollingTextView.setText(msg);
        scrollingTextView.setTextColor(Color.parseColor("#3D9140"));

        //自动轮播
        image = new ArrayList<>();
        tittle = new ArrayList<>();
        image.add(R.mipmap.chejiandongtai);
        image.add(R.mipmap.tianqiyubao1);
        image.add(R.mipmap.anquanyujing);
        image.add(R.mipmap.zhigongwencai);
        tittle.add("车间动态及通告");
        tittle.add("保定市今日限行");
        tittle.add("每日安全预警");
        tittle.add("车间职工文采");
        xBanner.setData(image, tittle);
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @SuppressLint("NewApi")
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Picasso.with(Objects.requireNonNull(getActivity())).load(image.get(position)).into((ImageView) view);
            }
        });
        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(),CommonListView.class);
                    intent.putExtra("addforhttp","http://43.226.46.228/chejiandongtai.txt");
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getActivity(),CommonWebViewActivity.class);
                    intent.putExtra("add","http://43.226.46.228/chejianjilunbo/baodingxianxing.html");
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getActivity(),CommonWebViewActivity.class);
                    intent.putExtra("add","http://43.226.46.228/chejianjilunbo/anquanyujing.html");
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(getActivity(),CommonWebViewActivity.class);
                    intent.putExtra("add","http://43.226.46.228/chejianjilunbo/miaobishenghua.html");
                    startActivity(intent);
                }
            }
        });

        //保定车间recyclerview
        intProject();
        RecyclerView recyclerView = (RecyclerView)viewForCheJian.findViewById(R.id.baodingchejianrecyclerview);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 5);
        recyclerView.setLayoutManager( layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        AdapterForBaoDingCheJian adapter = new AdapterForBaoDingCheJian(getActivity() ,projectList);
        recyclerView.setAdapter( adapter);
        adapter.setOnItemClickListener(new AdapterForBaoDingCheJian.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectList.get(position).getImageId() == R.mipmap.chejianjieshao) {
                    Intent intent = new Intent(getActivity(),CommonWebViewActivity.class);
                    intent.putExtra("add","http://43.226.46.228/chejianjianjie/chejianjianjie.html");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.shangjiwenjian) {
                    Intent intent = new Intent(getActivity(),ShangJiWenJianActivity.class);
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.shigonganquan) {
                    Intent intent = new Intent(getActivity(),CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/shigonganquan/shigonganquan.txt");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.dangjianyutuan) {
                    Intent intent = new Intent(getActivity(),CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/dangjianyutuan/dangjianyutuan.txt");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.jianchabiaozhun) {
                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/jianchabiaozhun/jianchabiaozhun.txt");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.biaozhunhuajianshe) {
                    Intent intent = new Intent(getActivity(),CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/biaozhunhuajianshe/biaozhunhuajianshe.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.qiangxianyanlian) {
                    Intent intent = new Intent(getActivity(),CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/qiangxianyanlian/qiangxianyanlian.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.guanlimuban) {
                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/guanlimuban/guanlimuban.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.zhuanyezhishi) {
                    Intent intent = new Intent(getActivity(),CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/zhuanyezhishi/zhuanyezhishi.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.guizhangzhidu) {
                    Intent intent = new Intent(getActivity(),CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/guizhangzhidu/guizhangzhidu.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.dianhuachuanzhen) {
                    Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
                    intent.putExtra("add","http://43.226.46.228/dianhuachuanzhen/dianhuachuanzhen.html");
                    startActivity(intent);
                }
            }
        });

        //李保军的recyclerview
        initLiBaoJun();
        RecyclerView recyclerViewL = (RecyclerView)viewForCheJian.findViewById(R.id.libaojunrecyclerview);

        final QBadgeView qBadgeViewlbj = new QBadgeView(getActivity());
        qBadgeViewlbj.bindTarget(recyclerViewL);
        qBadgeViewlbj.setBadgeGravity(Gravity.CENTER);
        qBadgeViewlbj.setVisibility(View.GONE);

        libaojunwenjiantonggao = load("libaojun");
        zhongjianlianglbj = libaojunwenjiantonggao;
        dowithokhttplbj("http://43.226.46.228/notification/libaojunwenjiantonggao.txt", libaojunwenjiantonggao, qBadgeViewlbj);

        GridLayoutManager layoutManagerL = new GridLayoutManager(getActivity(), 5);
        recyclerViewL.setLayoutManager(layoutManagerL);
        recyclerViewL.setNestedScrollingEnabled(false);
        AdapterForZB adapterL = new AdapterForZB(getActivity(), projectListlbj);
        recyclerViewL.setAdapter(adapterL);
        adapterL.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListlbj.get(position).getImageId() == R.mipmap.gangweizhize) {
                    Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
                    intent.putExtra("add", "http://43.226.46.228/libaojun/libaojungangweizhize.html");
                    startActivity(intent);
                } else if (projectListlbj.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if (qBadgeViewlbj.isShown()) {
                        qBadgeViewlbj.hide(true);
                        write("libaojun", zhongjianlianglbj);
                    }

                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/libaojun/libaojunwenjiantonggao.txt");
                    startActivity(intent);
                } else if (projectListlbj.get(position).getImageId() == R.mipmap.jishulvli) {
                    Intent intent = new Intent(getActivity(),CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/libaojun/libaojunjishulvli.txt");
                    startActivity(intent);
                }
            }
        });



        //张艳春的recyclerview
        initZhangYanChun();
        RecyclerView recyclerViewZYC = (RecyclerView)viewForCheJian.findViewById(R.id.zhangyanchunrecyclerview);

        final QBadgeView qBadgeViewzyc = new QBadgeView(getActivity());
        qBadgeViewzyc.bindTarget(recyclerViewZYC);
        qBadgeViewzyc.setBadgeGravity(Gravity.CENTER);
        qBadgeViewzyc.setVisibility(View.GONE);

        zhangyanchunwenjiantonggao = load("zhangyanchun");
        zhongjianliangzyc = zhangyanchunwenjiantonggao;
        dowithokhttpzyc("http://43.226.46.228/notification/zhangyanchunwenjiantonggao.txt", zhangyanchunwenjiantonggao,qBadgeViewzyc);

        GridLayoutManager layoutManagerZYC = new GridLayoutManager(getActivity(), 5);
        recyclerViewZYC.setLayoutManager(layoutManagerZYC);
        recyclerViewZYC.setNestedScrollingEnabled(false);
        AdapterForZB adapterZYC = new AdapterForZB(getActivity(), projectListzyc);
        recyclerViewZYC.setAdapter(adapterZYC);
        adapterZYC.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListzyc.get(position).getImageId() == R.mipmap.gangweizhize) {
                    Intent intent = new Intent(getActivity(),CommonWebViewActivity.class);
                    intent.putExtra("add", "http://43.226.46.228/zhangyanchun/zhangyanchungangweizhize.html");
                    startActivity(intent);
                } else if (projectListzyc.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if (qBadgeViewzyc.isShown()) {
                        qBadgeViewzyc.hide(true);
                        write("zhangyanchun", zhongjianliangzyc);
                    }

                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/zhangyanchun/zhangyanchunwenjiantonggao.txt");
                    startActivity(intent);
                } else if (projectListzyc.get(position).getImageId() == R.mipmap.jishulvli){
                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/zhangyanchun/zhangyanchunjishulvli.txt");
                    startActivity(intent);
                }
            }
        });

        //朱红海的recyclerview
        initZhuHongHai();
        RecyclerView recyclerViewZHH = (RecyclerView)viewForCheJian.findViewById(R.id.zhuhonghairecyclerview);

        final QBadgeView qBadgeViewzhh = new QBadgeView(getActivity());
        qBadgeViewzhh.bindTarget(recyclerViewZHH);
        qBadgeViewzhh.setBadgeGravity(Gravity.CENTER);
        qBadgeViewzhh.setVisibility(View.GONE);

        zhuhonghaiwenjiantonggao = load("zhuhonghai");
        zhongjianliangzhh = zhuhonghaiwenjiantonggao;
        dowithokhttpzhh("http://43.226.46.228/notification/zhuhonghaiwenjiantonggao.txt", zhuhonghaiwenjiantonggao,qBadgeViewzhh);

        GridLayoutManager layoutManagerZHH = new GridLayoutManager(getActivity(), 5);
        recyclerViewZHH.setLayoutManager(layoutManagerZHH);
        recyclerViewZHH.setNestedScrollingEnabled(false);
        AdapterForZB adapterZHH = new AdapterForZB(getActivity(), projectListzhh);
        recyclerViewZHH.setAdapter(adapterZHH);
        adapterZHH.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListzhh.get(position).getImageId() == R.mipmap.gangweizhize) {
                    Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
                    intent.putExtra("add", "http://43.226.46.228/zhuhonghai/zhuhonghaigangweizhize.html");
                    startActivity(intent);
                } else if (projectListzhh.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if (qBadgeViewzhh.isShown()) {
                        qBadgeViewzhh.hide(true);
                        write("zhuhonghai", zhongjianliangzhh);
                    }

                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/zhuhonghai/zhuhonghaiwenjiantonggao.txt");
                    startActivity(intent);
                } else if (projectListzhh.get(position).getImageId() == R.mipmap.jishulvli) {
                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/zhuhonghai/zhuhonghaijishulvli.txt");
                    startActivity(intent);
                }
            }
        });

        //杨秀玉的recyclerview
        initYangXiuYu();
        RecyclerView recyclerViewY = (RecyclerView)viewForCheJian.findViewById(R.id.yangxiuyurecyclerview);

        final QBadgeView qBadgeViewyxy = new QBadgeView(getActivity());
        qBadgeViewyxy.bindTarget(recyclerViewY);
        qBadgeViewyxy.setBadgeGravity(Gravity.CENTER);
        qBadgeViewyxy.setVisibility(View.GONE);

        yangxiuyuwenjiantonggao = load("yangxiuyu");
        zhongjianliangyxy = yangxiuyuwenjiantonggao;
        dowithokhttpyxy("http://43.226.46.228/notification/yangxiuyuwenjiantonggao.txt", yangxiuyuwenjiantonggao,qBadgeViewyxy);

        GridLayoutManager layoutManagerY = new GridLayoutManager(getActivity(), 5);
        recyclerViewY.setLayoutManager(layoutManagerY);
        recyclerViewY.setNestedScrollingEnabled(false);
        AdapterForZB adapterY = new AdapterForZB(getActivity(), projectListyxy);
        recyclerViewY.setAdapter(adapterY);
        adapterY.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListyxy.get(position).getImageId() == R.mipmap.gangweizhize) {
                    Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
                    intent.putExtra("add", "http://43.226.46.228/yangxiuyu/yangxiuyugangweizhize.html");
                    startActivity(intent);
                } else if (projectListyxy.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if (qBadgeViewyxy.isShown()) {
                        qBadgeViewyxy.hide(true);
                        write("yangxiuyu", zhongjianliangyxy);
                    }
                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/yangxiuyu/yangxiuyuwenjiantonggao.txt");
                    startActivity(intent);
                } else if (projectListyxy.get(position).getImageId() == R.mipmap.jishulvli) {
                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/yangxiuyu/yangxiuyujishulvli.txt");
                    startActivity(intent);
                }
            }
        });

        //车间调度recyclerview
        initForCheJianDiaoDu();
        RecyclerView recyclerView1 = (RecyclerView)viewForCheJian.findViewById(R.id.chejiandiaodurecyclerview);

        final QBadgeView qBadgeViewcjdd = new QBadgeView(getActivity());
        qBadgeViewcjdd.bindTarget(recyclerView1);
        qBadgeViewcjdd.setBadgeGravity(Gravity.CENTER);
        qBadgeViewcjdd.setVisibility(View.GONE);

        chejiandiaoduwenjiantonggao = load("chejiandiaodu");
        zhongjianliangcjdd = chejiandiaoduwenjiantonggao;
        dowithokhttpcjdd("http://43.226.46.228/notification/chejiandiaoduwenjiantonggao.txt", chejiandiaoduwenjiantonggao,qBadgeViewcjdd);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 5);
        recyclerView1.setLayoutManager( layoutManager1);
        recyclerView1.setNestedScrollingEnabled(false);
        AdapterForZB adapter1 = new AdapterForZB(getActivity() ,projectList1);
        recyclerView1.setAdapter( adapter1);
        adapter1.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectList1.get(position).getImageId() == R.mipmap.gangweizhize) {
                    Intent intent = new Intent(getActivity(),CommonWebViewActivity.class);
                    intent.putExtra("add", "http://43.226.46.228/chejiandiaodu/chejiandiaodugangweizhize.html");
                    startActivity(intent);
                } else if (projectList1.get(position).getImageId() == R.mipmap.zhongyaotongzhi) {
                    if (qBadgeViewcjdd.isShown()) {
                        qBadgeViewcjdd.hide(true);
                        write("chejiandiaodu", zhongjianliangcjdd);
                    }
                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/chejiandiaodu/chejiandiaoduzhongyaotongzhi.txt");
                    startActivity(intent);
                } else if (projectList1.get(position).getImageId() == R.mipmap.jishulvli) {
                    Intent intent = new Intent(getActivity(), CommonListView.class);
                    intent.putExtra("addforhttp", "http://43.226.46.228/chejiandiaodu/chejiandiaodujishulvli.txt");
                    startActivity(intent);
                }
            }
        });

        //马红岩岗位recyclerview
        initMaHongYan();
        RecyclerView recyclerView2 = (RecyclerView)viewForCheJian.findViewById(R.id.mahongyanrecyclerview);

        final QBadgeView qBadgeViewmhy = new QBadgeView(getActivity());
        qBadgeViewmhy.bindTarget(recyclerView2);
        qBadgeViewmhy.setBadgeGravity(Gravity.CENTER);
        qBadgeViewmhy.setVisibility(View.GONE);

        mahongyanwenjiantonggao = load("mahongyan");
        zhongjianliangmhy = mahongyanwenjiantonggao;
        dowithokhttpmhy("http://43.226.46.228/notification/mahongyanwenjiantonggao.txt", mahongyanwenjiantonggao,qBadgeViewmhy);

        GridLayoutManager layoutManager2 = new GridLayoutManager(getActivity(), 5);
        recyclerView2.setLayoutManager( layoutManager2);
        recyclerView2.setNestedScrollingEnabled(false);
        AdapterForZB adapter2 = new AdapterForZB(getActivity() ,projectListm);
        recyclerView2.setAdapter( adapter2);
        adapter2.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListm.get(position).getImageId() == R.mipmap.jishulvli) {
                   Intent intent = new Intent(getActivity(),CommonListView.class);
                   intent.putExtra("addforhttp","http://43.226.46.228/mahongyan/mahongyanjishulvli.txt");
                   startActivity(intent);
                } else if (projectListm.get(position).getImageId() == R.mipmap.gangweizhize) {
                    Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
                    intent.putExtra("add", "http://43.226.46.228/mahongyan/mahongyangangweizhize.html");
                    startActivity(intent);
                } else if (projectListm.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if (qBadgeViewmhy.isShown()) {
                        qBadgeViewmhy.hide(true);
                        write("mahongyan", zhongjianliangmhy);
                    }
                    startCommonListView("addforhttp", "http://43.226.46.228/mahongyan/mahongyanwenjiantonggao.txt");
                }
            }
        });

        //孙亚军岗位recyclerview
        initSunYaJun();
        RecyclerView recyclerView3 = (RecyclerView)viewForCheJian.findViewById(R.id.sunyajunrecyclerview);

        final QBadgeView qBadgeViewsyj = new QBadgeView(getActivity());
        qBadgeViewsyj.bindTarget(recyclerView3);
        qBadgeViewsyj.setBadgeGravity(Gravity.CENTER);
        qBadgeViewsyj.setVisibility(View.GONE);

        sunyajunwenjiantonggao = load("sunyajun");
        zhongjianliangsyj = sunyajunwenjiantonggao;
        dowithokhttpsyj("http://43.226.46.228/notification/sunyajunwenjiantonggao.txt", sunyajunwenjiantonggao,qBadgeViewsyj);

        GridLayoutManager layoutManager3 = new GridLayoutManager(getActivity(), 5);
        recyclerView3.setLayoutManager( layoutManager3);
        recyclerView3.setNestedScrollingEnabled(false);
        AdapterForZB adapter3 = new AdapterForZB(getActivity() ,projectLists);
        recyclerView3.setAdapter( adapter3);
        adapter3.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectLists.get(position).getImageId() == R.mipmap.jishulvli) {
                    startCommonListView("addforhttp", "http://43.226.46.228/sunyajun/sunyajunjishulvli.txt");
                } else if (projectLists.get(position).getImageId() == R.mipmap.gangweizhize) {
                    startCommonWebViewActivity("add", "http://43.226.46.228/sunyajun/sunyajungangweizhize.html");
                } else if (projectLists.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if (qBadgeViewsyj.isShown()) {
                        qBadgeViewsyj.hide(true);
                        write("sunyajun", zhongjianliangsyj);
                    }
                    startCommonListView("addforhttp", "http://43.226.46.228/sunyajun/sunyajunwenjiantonggao.txt");
                } else if (projectLists.get(position).getImageId() == R.mipmap.jishuxuexi) {
                    startCommonListView("addforhttp", "http://43.226.46.228/sunyajun/sunyajunjishuxuexi.txt");
                } else if (projectLists.get(position).getImageId() == R.mipmap.xiangguankaoshi) {
                    startCommonListView("addforhttp", "http://43.226.46.228/sunyajun/sunyajunkaoshixiangguan.txt");
                }
            }
        });

        //高利民岗位的recyclerview
        initGaoLiMin();
        RecyclerView recyclerView4 = (RecyclerView)viewForCheJian.findViewById(R.id.gaoliminrecyclerview);

        final QBadgeView qBadgeViewglm = new QBadgeView(getActivity());
        qBadgeViewglm.bindTarget(recyclerView4);
        qBadgeViewglm.setBadgeGravity(Gravity.CENTER);
        qBadgeViewglm.setVisibility(View.GONE);

        gaoliminwenjiantonggao = load("gaolimin");
        zhongjianliangglm = gaoliminwenjiantonggao;
        dowithokhttpglm("http://43.226.46.228/notification/gaoliminwenjiantonggao.txt", gaoliminwenjiantonggao,qBadgeViewglm);

        GridLayoutManager layoutManager4 = new GridLayoutManager(getActivity(), 5);
        recyclerView4.setLayoutManager( layoutManager4);
        recyclerView4.setNestedScrollingEnabled(false);
        AdapterForZB adapter4 = new AdapterForZB(getActivity() ,projectListg);
        recyclerView4.setAdapter( adapter4);
        adapter4.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListg.get(position).getImageId() == R.mipmap.gangweizhize) {
                    startCommonWebViewActivity("add", "http://43.226.46.228/gaolimin/gaolimingangweizhize.html");
                } else if (projectListg.get(position).getImageId() == R.mipmap.wenjiantonggao){
                    if (qBadgeViewglm.isShown()) {
                        qBadgeViewglm.hide(true);
                        write("gaolimin", zhongjianliangglm);
                    }
                    startCommonListView("addforhttp", "http://43.226.46.228/gaolimin/gaoliminwenjiantonggao.txt");
                } else if (projectListg.get(position).getImageId() == R.mipmap.jishulvli) {
                    startCommonListView("addforhttp", "http://43.226.46.228/gaolimin/gaoliminjishulvli.txt");
                }
            }
        });

        //胡旭瑞岗位的recyclerview
        initHuXvRui();
        RecyclerView recyclerView5 = (RecyclerView)viewForCheJian.findViewById(R.id.huxvruirecyclerview);

        final QBadgeView qBadgeViewhxr = new QBadgeView(getActivity());
        qBadgeViewhxr.bindTarget(recyclerView5);
        qBadgeViewhxr.setBadgeGravity(Gravity.CENTER);
        qBadgeViewhxr.setVisibility(View.GONE);

        huxvruiwenjiantonggao = load("huxvrui");
        zhongjianlianghxr = huxvruiwenjiantonggao;
        dowithokhttphxr("http://43.226.46.228/notification/huxvruiwenjiantonggao.txt", huxvruiwenjiantonggao,qBadgeViewhxr);

        GridLayoutManager layoutManager5 = new GridLayoutManager(getActivity(), 5);
        recyclerView5.setLayoutManager( layoutManager5);
        recyclerView5.setNestedScrollingEnabled(false);
        AdapterForZB adapter5 = new AdapterForZB(getActivity() ,projectListh);
        recyclerView5.setAdapter( adapter5);
        adapter5.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListh.get(position).getImageId() == R.mipmap.gangweizhize) {
                    startCommonWebViewActivity("add", "http://43.226.46.228/huxvrui/huxvruigangweizhize.html");
                }else if(projectListh.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if (qBadgeViewhxr.isShown()) {
                        qBadgeViewhxr.hide(true);
                        write("huxvrui", zhongjianlianghxr);
                    }
                    startCommonListView("addforhttp", "http://43.226.46.228/huxvrui/huxvruiwenjiantonggao.txt");
                } else if (projectListh.get(position).getImageId() == R.mipmap.jishulvli) {
                    startCommonListView("addforhttp", "http://43.226.46.228/huxvrui/huxvruijishulvli.txt");
                }
            }
        });

        //王健岗位的recyclerview
        initWangJian();
        RecyclerView recyclerView6 = (RecyclerView)viewForCheJian.findViewById(R.id.wangjianrecyclerview);

        final QBadgeView qBadgeViewwj = new QBadgeView(getActivity());
        qBadgeViewwj.bindTarget(recyclerView6);
        qBadgeViewwj.setBadgeGravity(Gravity.CENTER);
        qBadgeViewwj.setVisibility(View.GONE);

        wangjianwenjiantonggao = load("wangjian");
        zhongjianliangwj = wangjianwenjiantonggao;
        dowithokhttpwj("http://43.226.46.228/notification/wangjianwenjiantonggao.txt", wangjianwenjiantonggao,qBadgeViewwj);

        GridLayoutManager layoutManager6 = new GridLayoutManager(getActivity(), 5);
        recyclerView6.setLayoutManager( layoutManager6);
        recyclerView6.setNestedScrollingEnabled(false);
        AdapterForZB adapter6 = new AdapterForZB(getActivity() ,projectListwj);
        recyclerView6.setAdapter( adapter6);
        adapter6.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListwj.get(position).getImageId() == R.mipmap.gangweizhize) {
                    startCommonWebViewActivity("add", "http://43.226.46.228/wangjian/wangjiangangweizhize.html");
                } else if (projectListwj.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if(qBadgeViewwj.isShown()) {
                        qBadgeViewwj.hide(true);
                        write("wangjian", zhongjianliangwj);
                    }
                    startCommonListView("addforhttp", "http://43.226.46.228/wangjian/wangjianwenjiantonggao.txt");
                } else if (projectListwj.get(position).getImageId() == R.mipmap.jishulvli) {
                    startCommonListView("addforhttp", "http://43.226.46.228/wangjian/wangjianjishulvli.txt");
                }
            }
        });

        //王会然岗位的recyclerview
        initWangHuiRan();
        RecyclerView recyclerView7 = (RecyclerView)viewForCheJian.findViewById(R.id.wanghuiranrecyclerview);

        final QBadgeView qBadgeViewwhr = new QBadgeView(getActivity());
        qBadgeViewwhr.bindTarget(recyclerView7);
        qBadgeViewwhr.setBadgeGravity(Gravity.CENTER);
        qBadgeViewwhr.setVisibility(View.GONE);

        wanghuiranwenjiantonggao = load("wanghuiran");
        zhongjianliangwhr = wanghuiranwenjiantonggao;
        dowithokhttpwhr("http://43.226.46.228/notification/wanghuiranwenjiantonggao.txt", wanghuiranwenjiantonggao,qBadgeViewwhr);

        GridLayoutManager layoutManager7 = new GridLayoutManager(getActivity(), 5);
        recyclerView7.setLayoutManager( layoutManager7);
        recyclerView7.setNestedScrollingEnabled(false);
        AdapterForZB adapter7 = new AdapterForZB(getActivity() ,projectListwhr);
        recyclerView7.setAdapter( adapter7);
        adapter7.setOnItemClickListener(new AdapterForZB.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                if (projectListwhr.get(position).getImageId() == R.mipmap.gangweizhize) {
                    startCommonWebViewActivity("add", "http://43.226.46.228/wanghuiran/wanghuirangangweizhize.html");
                } else if (projectListwhr.get(position).getImageId() == R.mipmap.wenjiantonggao) {
                    if (qBadgeViewwhr.isShown()) {
                        qBadgeViewwhr.hide(true);
                        write("wanghuiran",zhongjianliangwhr);
                    }
                    startCommonListView("addforhttp", "http://43.226.46.228/wanghuiran/wanghuiranwenjiantonggao.txt");
                } else if (projectListwhr.get(position).getImageId() == R.mipmap.jishulvli) {
                    startCommonListView("addforhttp", "http://43.226.46.228/wanghuiran/wanghuiranjishulvli.txt");
                }
            }
        });

        return viewForCheJian;
    }

        //初始化车间内容的列表
    private void intProject() {
        ProjectItemForCheJian cjjj = new ProjectItemForCheJian("车间简介",R.mipmap.chejianjieshao);
        projectList.add(cjjj);
        ProjectItemForCheJian sjwj = new ProjectItemForCheJian("上级文件",R.mipmap.shangjiwenjian);
        projectList.add(sjwj);
        ProjectItemForCheJian sgan = new ProjectItemForCheJian("施工安全",R.mipmap.shigonganquan);
        projectList.add(sgan);
        ProjectItemForCheJian djyt = new ProjectItemForCheJian("党建与团",R.mipmap.dangjianyutuan);
        projectList.add(djyt);
        ProjectItemForCheJian jcbz = new ProjectItemForCheJian("检查标准",R.mipmap.jianchabiaozhun);
        projectList.add(jcbz);
        ProjectItemForCheJian bzhjs = new ProjectItemForCheJian("标准化建设",R.mipmap.biaozhunhuajianshe);
        projectList.add(bzhjs);
        ProjectItemForCheJian qxyl = new ProjectItemForCheJian("抢险演练",R.mipmap.qiangxianyanlian);
        projectList.add(qxyl);
        ProjectItemForCheJian glmb = new ProjectItemForCheJian("管理模板",R.mipmap.guanlimuban);
        projectList.add(glmb);
        ProjectItemForCheJian zyzs = new ProjectItemForCheJian("专业知识",R.mipmap.zhuanyezhishi);
        projectList.add(zyzs);
        ProjectItemForCheJian gzzd = new ProjectItemForCheJian("规章制度",R.mipmap.guizhangzhidu);
        projectList.add(gzzd);
        ProjectItemForCheJian dhcz = new ProjectItemForCheJian("电话传真",R.mipmap.dianhuachuanzhen);
        projectList.add(dhcz);
        }

     private void initLiBaoJun() {
        ProjectItemForCheJian aaa = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectListlbj.add(aaa);
         ProjectItemForCheJian ccc = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
         projectListlbj.add(ccc);
         ProjectItemForCheJian bbb = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
         projectListlbj.add(bbb);
     }

     private void initZhangYanChun() {
         ProjectItemForCheJian aaa = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
         projectListzyc.add(aaa);

         ProjectItemForCheJian ccc = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
         projectListzyc.add(ccc);
         ProjectItemForCheJian bbb = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
         projectListzyc.add(bbb);
     }

    private void initZhuHongHai() {
        ProjectItemForCheJian aaa = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectListzhh.add(aaa);

        ProjectItemForCheJian ccc = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectListzhh.add(ccc);
        ProjectItemForCheJian bbb = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
        projectListzhh.add(bbb);
    }

    private void initYangXiuYu() {
        ProjectItemForCheJian aaa = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectListyxy.add(aaa);

        ProjectItemForCheJian ccc = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectListyxy.add(ccc);
        ProjectItemForCheJian bbb = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
        projectListyxy.add(bbb);
    }

    private void initForCheJianDiaoDu() {
        ProjectItemForCheJian diaodu1 = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectList1.add(diaodu1);
        ProjectItemForCheJian diaodu3 = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectList1.add(diaodu3);
        ProjectItemForCheJian diaodu2 = new ProjectItemForCheJian("重要通知", R.mipmap.zhongyaotongzhi);
        projectList1.add(diaodu2);
    }

    private void initMaHongYan() {
        ProjectItemForCheJian mahongyan1 = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectListm.add(mahongyan1);

        ProjectItemForCheJian mahongyan3 = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectListm.add(mahongyan3);
        ProjectItemForCheJian mahongyan2 = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
        projectListm.add(mahongyan2);
    }
    private void initSunYaJun() {
        ProjectItemForCheJian sunyajun1 = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectLists.add(sunyajun1);

        ProjectItemForCheJian sunyajun3 = new ProjectItemForCheJian("技术学习", R.mipmap.jishuxuexi);
        projectLists.add(sunyajun3);
        ProjectItemForCheJian sunyajun2 = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
        projectLists.add(sunyajun2);
        ProjectItemForCheJian sunyajun4 = new ProjectItemForCheJian("考试相关", R.mipmap.xiangguankaoshi);
        projectLists.add(sunyajun4);
        ProjectItemForCheJian sunyajun5 = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectLists.add(sunyajun5);
    }
    private void initGaoLiMin() {
        ProjectItemForCheJian mahongyan1 = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectListg.add(mahongyan1);

        ProjectItemForCheJian mahongyan3 = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectListg.add(mahongyan3);
        ProjectItemForCheJian mahongyan2 = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
        projectListg.add(mahongyan2);
    }
    private void initHuXvRui() {
        ProjectItemForCheJian mahongyan1 = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectListh.add(mahongyan1);

        ProjectItemForCheJian mahongyan3 = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectListh.add(mahongyan3);
        ProjectItemForCheJian mahongyan2 = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
        projectListh.add(mahongyan2);
    }
    private void initWangJian() {
        ProjectItemForCheJian mahongyan1 = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectListwj.add(mahongyan1);

        ProjectItemForCheJian mahongyan3 = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectListwj.add(mahongyan3);
        ProjectItemForCheJian mahongyan2 = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
        projectListwj.add(mahongyan2);
    }
    private void initWangHuiRan() {
        ProjectItemForCheJian mahongyan1 = new ProjectItemForCheJian("岗位职责", R.mipmap.gangweizhize);
        projectListwhr.add(mahongyan1);

        ProjectItemForCheJian mahongyan3 = new ProjectItemForCheJian("技术履历", R.mipmap.jishulvli);
        projectListwhr.add(mahongyan3);
        ProjectItemForCheJian mahongyan2 = new ProjectItemForCheJian("文件通告", R.mipmap.wenjiantonggao);
        projectListwhr.add(mahongyan2);
    }

    private void startCommonListView(String addforhttp, String url) {
        Intent intent = new Intent(getActivity(), CommonListView.class);
        intent.putExtra(addforhttp, url);
        startActivity(intent);
    }

    private void startCommonWebViewActivity(String add, String url) {
        Intent intent = new Intent(getActivity(),CommonWebViewActivity.class);
        intent.putExtra(add, url);
        startActivity(intent);
    }

    private String load(String add) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = getActivity().openFileInput(add);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    private void write(String add, String msg) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = getActivity().openFileOutput(add, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(msg);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void dowithokhttplbj(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianlianglbj = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpzyc(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangzyc = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpzhh(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangzhh = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpyxy(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangyxy = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpcjdd(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangcjdd = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpmhy(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangmhy = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpsyj(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangsyj = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpglm(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangglm = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttphxr(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianlianghxr = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpwj(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangwj = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void dowithokhttpwhr(final String url, final String wenjiantonggao, final QBadgeView qBadgeView) {
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
                    final String responseData = response.body().string();

                    if (!wenjiantonggao.equals(responseData)) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                qBadgeView.setVisibility(View.VISIBLE);
                                qBadgeView.setBadgeText("有新通告");
                                zhongjianliangwhr = responseData;
                            }
                        });
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xBanner.stopAutoPlay();
    }
}
