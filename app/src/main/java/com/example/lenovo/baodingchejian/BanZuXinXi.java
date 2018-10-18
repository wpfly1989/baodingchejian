package com.example.lenovo.baodingchejian;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BanZuXinXi extends AppCompatActivity {
    private TextView scrollingTextView;
    private TextView banzuxinxiBiaoTi;
    private ArrayList<ProjectItemForBanZuXinXi> arrayList = new ArrayList<>();
    private Calendar calendarNow;
    private Calendar calendarOfBaoDingDong;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_zu_xin_xi);

        //从上个activity获取数据
        Intent intent = getIntent();
        final String data = intent.getStringExtra("name");

        scrollingTextView = (TextView)findViewById(R.id.anquanshengchantianshu);
        banzuxinxiBiaoTi = (TextView)findViewById(R.id.banzuxinxiBiaoTi);
        banzuxinxiBiaoTi.setText(data);
        if (data.contains("保定东高铁") || data.contains("定州东高铁")) {
            scrollingTextView.setText("班组安全生产" + Long.toString(daysOfBaoDingDong()) + "天");
        } else if (data.contains("白沟")){
            scrollingTextView.setText("班组安全生产" + Long.toString(daysOfBaoDingDong() - 1236) + "天");
            //scrollingTextView.setText("班组安全生产" + Long.toString(daysOfBaoDingDong() + 865) + "天");
        } else {
            scrollingTextView.setText("班组安全生产" + Long.toString(daysOfBaoDingDong() + 865) + "天");
        }
        //scrollingTextView.setText("这是测试");

        initBanZuXinXi();
        AdapterForBanZuXinXi adapter = new AdapterForBanZuXinXi(BanZuXinXi.this,R.layout.layout4, arrayList);
        ListView listView = (ListView)findViewById(R.id.banzuxinxilistView);
        listView.setFocusable(false);
        listView.setAdapter(adapter);

        //listView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arrayList.get(position).getImageId() == R.mipmap.gongqujieshao) {
                    switch (data) {
                        case "高碑店通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/gaobeidiangongqujieshao.html");
                            break;
                        case "固城通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/guchenggongqujieshao.html");
                            break;
                        case "保定通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/baodinggongqujieshao.html");
                            break;
                        case "保定南通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/baodingnangongqujieshao.html");
                            break;
                        case "望都通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/wangdugongqujieshao.html");
                            break;
                        case "定州通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/dingzhougongqujieshao.html");
                            break;
                        case "新乐通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/xinlegongqujieshao.html");
                            break;
                        case "白沟通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/baigougongqujieshao.html");
                            break;
                        case "保定东高铁通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/baodingdonggaotiegongqujieshao.html");
                            break;
                        case "定州东高铁通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/dingzhoudonggaotiegongqujieshao.html");
                            break;
                        case "无线集中修工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/wuxiangongqujieshao.html");
                            break;
                        case "保定光电工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/guangdiangongqujieshao.html");
                            break;
                        case "网监室" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/wangjiangongqujieshao.html");
                            break;
                        case "电报所" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqujieshao/dianbaosuogongqujieshao.html");
                            break;
                            default:
                                break;
                    }
                } else if (arrayList.get(position).getImageId() == R.mipmap.banzufengmao) {
                    switch (data) {
                        case "高碑店通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/gaobeidianbanzufengmao.html");
                            break;
                        case "固城通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/guchengbanzufengmao.html");
                            break;
                        case "保定通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/baodingbanzufengmao.html");
                            break;
                        case "保定南通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/baodingnanbanzufengmao.html");
                            break;
                        case "望都通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/wangdubanzufengmao.html");
                            break;
                        case "定州通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/dingzhoubanzufengmao.html");
                            break;
                        case "新乐通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/xinlebanzufengmao.html");
                            break;
                        case "白沟通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/baigoubanzufengmao.html");
                            break;
                        case "保定东高铁通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/baodingdonggaotiebanzufengmao.html");
                            break;
                        case "定州东高铁通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/dingzhoudonggaotiebanzufengmao.html");
                            break;
                        case "无线集中修工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/wuxianbanzufengmao.html");
                            break;
                        case "保定光电工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/guangdianbanzufengmao.html");
                            break;
                        case "网监室" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/wangjianbanzufengmao.html");
                            break;
                        case "电报所" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/banzufengmao/dianbaosuobanzufengmao.html");
                            break;
                        default:
                            break;
                    }
                } else if (arrayList.get(position).getImageId() == R.mipmap.guanneiwangtu) {
                    switch (data) {
                        case "高碑店通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/gaobeidianguanneiwangtu.txt");
                            break;
                        case "固城通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/guchengguanneiwangtu.txt");
                            break;
                        case "保定通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/baodingguanneiwangtu.txt");
                            break;
                        case "保定南通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/baodingnanguanneiwangtu.txt");
                            break;
                        case "望都通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/wangduguanneiwangtu.txt");
                            break;
                        case "定州通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/dingzhouguanneiwangtu.txt");
                            break;
                        case "新乐通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/xinleguanneiwangtu.txt");
                            break;
                        case "白沟通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/baigouguanneiwangtu.txt");
                            break;
                        case "保定东高铁通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/baodingdonggaotieguanneiwangtu.txt");
                            break;
                        case "定州东高铁通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/dingzhoudonggaotieguanneiwangtu.txt");
                            break;
                        case "无线集中修工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/wuxianguanneiwangtu.txt");
                            break;
                        case "保定光电工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/guangdianguanneiwangtu.txt");
                            break;
                        case "网监室" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/wangjianguanneiwangtu.txt");
                            break;
                        case "电报所" :
                            startCommonListView("addforhttp", "http://43.226.46.228/guanneiwangtu/dianbaosuoguanneiwangtu.txt");
                            break;
                        default:
                            break;
                    }
                } else if (arrayList.get(position).getImageId() == R.mipmap.gongqudianhuaben) {
                    switch (data) {
                        case "高碑店通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/gaobeidiangongqudianhuaben.html");
                            break;
                        case "固城通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/guchenggongqudianhuaben.html");
                            break;
                        case "保定通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/baodinggongqudianhuaben.html");
                            break;
                        case "保定南通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/baodingnangongqudianhuaben.html");
                            break;
                        case "望都通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/wangdugongqudianhuaben.html");
                            break;
                        case "定州通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/dingzhougongqudianhuaben.html");
                            break;
                        case "新乐通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/xinlegongqudianhuaben.html");
                            break;
                        case "白沟通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/baigougongqudianhuaben.html");
                            break;
                        case "保定东高铁通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/baodingdonggaotiegongqudianhuaben.html");
                            break;
                        case "定州东高铁通信工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/dingzhoudonggaotiegongqudianhuaben.html");
                            break;
                        case "无线集中修工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/wuxiangongqudianhuaben.html");
                            break;
                        case "保定光电工区" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/guangdiangongqudianhuaben.html");
                            break;
                        case "网监室" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/wangjiangongqudianhuaben.html");
                            break;
                        case "电报所" :
                            startCommonWebViewActivity("add", "http://43.226.46.228/gongqudianhuaben/dianbaosuogongqudianhuaben.html");
                            break;
                        default:
                            break;
                    }
                } else if (arrayList.get(position).getImageId() == R.mipmap.dingweidaohang) {
                    switch (data) {
                        case "高碑店通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/gaobeidiandingweidaohang.txt");
                            break;
                        case "固城通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/guchengdingweidaohang.txt");
                            break;
                        case "保定通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/baodingdingweidaohang.txt");
                            break;
                        case "保定南通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/baodingnandingweidaohang.txt");
                            break;
                        case "望都通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/wangdudingweidaohang.txt");
                            break;
                        case "定州通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/dingzhoudingweidaohang.txt");
                            break;
                        case "新乐通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/xinledingweidaohang.txt");
                            break;
                        case "白沟通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/baigoudingweidaohang.txt");
                            break;
                        case "保定东高铁通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/baodingdonggaotiedingweidaohang.txt");
                            break;
                        case "定州东高铁通信工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/dingzhoudonggaotiedingweidaohang.txt");
                            break;
                        case "无线集中修工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/wuxiandingweidaohang.txt");
                            break;
                        case "保定光电工区" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/guangdiandingweidaohang.txt");
                            break;
                        case "网监室" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/wangjiandingweidaohang.txt");
                            break;
                        case "电报所" :
                            startDingWeiListView("dingwei", "http://43.226.46.228/dingweidaohang/dianbaosuodingweidaohang.txt");
                            break;
                        default:
                            break;
                    }
                } else if (arrayList.get(position).getImageId() == R.mipmap.jifangpingmiantu) {
                    switch (data) {
                        case "高碑店通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/gaobeidianjifangshebeipingmiantu.txt");
                            break;
                        case "固城通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/guchengjifangshebeipingmiantu.txt");
                            break;
                        case "保定通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/baodingjifangshebeipingmiantu.txt");
                            break;
                        case "保定南通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/baodingnanjifangshebeipingmiantu.txt");
                            break;
                        case "望都通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/wangdujifangshebeipingmiantu.txt");
                            break;
                        case "定州通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/dingzhoujifangshebeipingmiantu.txt");
                            break;
                        case "新乐通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/xinlejifangshebeipingmiantu.txt");
                            break;
                        case "白沟通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/baigoujifangshebeipingmiantu.txt");
                            break;
                        case "保定东高铁通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/baodingdonggaotiejifangshebeipingmiantu.txt");
                            break;
                        case "定州东高铁通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/dingzhoudonggaotiejifangshebeipingmiantu.txt");
                            break;
                        case "无线集中修工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/wuxianjifangshebeipingmiantu.txt");
                            break;
                        case "保定光电工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/guangdianjifangshebeipingmiantu.txt");
                            break;
                        case "网监室" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/wangjianjifangshebeipingmiantu.txt");
                            break;
                        case "电报所" :
                            startCommonListView("addforhttp", "http://43.226.46.228/jifangshebeipingmiantu/dianbaosuojifangshebeipingmiantu.txt");
                            break;
                        default:
                            break;
                    }
                } else if (arrayList.get(position).getImageId() == R.mipmap.yingjiyuan) {
                    switch (data) {
                        case "高碑店通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/gaobeidianyingjiyuan.txt");
                            break;
                        case "固城通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/guchengyingjiyuan.txt");
                            break;
                        case "保定通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/baodingyingjiyuan.txt");
                            break;
                        case "保定南通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/baodingnanyingjiyuan.txt");
                            break;
                        case "望都通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/wangduyingjiyuan.txt");
                            break;
                        case "定州通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/dingzhouyingjiyuan.txt");
                            break;
                        case "新乐通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/xinleyingjiyuan.txt");
                            break;
                        case "白沟通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/baigouyingjiyuan.txt");
                            break;
                        case "保定东高铁通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/baodingdonggaotieyingjiyuan.txt");
                            break;
                        case "定州东高铁通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/dingzhoudonggaotieyingjiyuan.txt");
                            break;
                        case "无线集中修工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/wuxianyingjiyuan.txt");
                            break;
                        case "保定光电工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/guangdianyingjiyuan.txt");
                            break;
                        case "网监室" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/wangjianyingjiyuan.txt");
                            break;
                        case "电报所" :
                            startCommonListView("addforhttp", "http://43.226.46.228/yingjiyuan/dianbaosuoyingjiyuan.txt");
                            break;
                        default:
                            break;
                    }
                } else if (arrayList.get(position).getImageId() == R.mipmap.duankoutaizhang) {
                    switch (data) {
                        case "高碑店通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/gaobeidianduankoutaizhang.txt");
                            break;
                        case "固城通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/guchengduankoutaizhang.txt");
                            break;
                        case "保定通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/baodingduankoutaizhang.txt");
                            break;
                        case "保定南通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/baodingnanduankoutaizhang.txt");
                            break;
                        case "望都通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/wangduduankoutaizhang.txt");
                            break;
                        case "定州通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/dingzhouduankoutaizhang.txt");
                            break;
                        case "新乐通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/xinleduankoutaizhang.txt");
                            break;
                        case "白沟通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/baigouduankoutaizhang.txt");
                            break;
                        case "保定东高铁通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/baodingdonggaotieduankoutaizhang.txt");
                            break;
                        case "定州东高铁通信工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/dingzhoudonggaotieduankoutaizhang.txt");
                            break;
                        case "无线集中修工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/wuxianduankoutaizhang.txt");
                            break;
                        case "保定光电工区" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/guangdianduankoutaizhang.txt");
                            break;
                        case "网监室" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/wangjianduankoutaizhang.txt");
                            break;
                        case "电报所" :
                            startCommonListView("addforhttp", "http://43.226.46.228/duankoutaizhang/dianbaosuoduankoutaizhang.txt");
                            break;
                        default:
                            break;
                    }
                }
            }
        });

    }

    private void initBanZuXinXi() {
        ProjectItemForBanZuXinXi gqjs = new ProjectItemForBanZuXinXi("工区介绍", R.mipmap.gongqujieshao);
        arrayList.add(gqjs);
        ProjectItemForBanZuXinXi bzfm = new ProjectItemForBanZuXinXi("班组风貌", R.mipmap.banzufengmao);
        arrayList.add(bzfm);
        ProjectItemForBanZuXinXi gnwt = new ProjectItemForBanZuXinXi("管内网图", R.mipmap.guanneiwangtu);
        arrayList.add(gnwt);
        ProjectItemForBanZuXinXi dhb = new ProjectItemForBanZuXinXi("工区电话本", R.mipmap.gongqudianhuaben);
        arrayList.add(dhb);
        ProjectItemForBanZuXinXi pmt = new ProjectItemForBanZuXinXi("机房设备平面图", R.mipmap.jifangpingmiantu);
        arrayList.add(pmt);
        ProjectItemForBanZuXinXi dwdh = new ProjectItemForBanZuXinXi("定位导航", R.mipmap.dingweidaohang);
        arrayList.add(dwdh);
        ProjectItemForBanZuXinXi yjya = new ProjectItemForBanZuXinXi("应急预案", R.mipmap.yingjiyuan);
        arrayList.add(yjya);
        ProjectItemForBanZuXinXi dktz = new ProjectItemForBanZuXinXi("端口台账", R.mipmap.duankoutaizhang);
        arrayList.add(dktz);
    }

    private long daysOfBaoDingDong() {
        calendarOfBaoDingDong = Calendar.getInstance();
        calendarOfBaoDingDong.set(2012, 7, 1);
        long timeOfBaoDingDong = calendarOfBaoDingDong.getTimeInMillis();

        calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        long timeNow = calendarNow.getTimeInMillis();

        return (timeNow - timeOfBaoDingDong) / 1000 / 60 / 60 / 24 - 2;

    }
    private void startCommonListView(String addforhttp, String url) {
        Intent intent = new Intent(BanZuXinXi.this, CommonListView.class);
        intent.putExtra(addforhttp, url);
        startActivity(intent);
    }

    private void startCommonWebViewActivity(String add, String url) {
        Intent intent = new Intent(BanZuXinXi.this,CommonWebViewActivity.class);
        intent.putExtra(add, url);
        startActivity(intent);
    }

    private void startDingWeiListView(String dingwei, String url) {
        Intent intent = new Intent(BanZuXinXi.this,DingWeiListView.class);
        intent.putExtra(dingwei, url);
        startActivity(intent);
    }
}
