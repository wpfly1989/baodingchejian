package com.example.lenovo.baodingchejian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

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
    private XBanner xBanner1;
    private ArrayList<Integer> image;
    private ArrayList<Integer> image1;
    private ArrayList<String> tittle;
    private ArrayList<String> tittle1;
    private ArrayList<ProjectItemForCheJian> projectList = new ArrayList<>();
    private ImageView dongtaitaizhang;
    private NiceVideoPlayer niceVideoPlayer;

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
        xBanner1 = (XBanner)viewForCheJian.findViewById(R.id.xbanner1);
        dongtaitaizhang = (ImageView)viewForCheJian.findViewById(R.id.dongtaitaizhangimageview);
        niceVideoPlayer = (viewForCheJian).findViewById(R.id.nicevideoplayer);

        //以下内容为滚动字幕中安全生产天数的计算
        calendarbeFore = Calendar.getInstance();
        calendarbeFore.set(2010, 2, 21);
        long timeOfDuan = calendarbeFore.getTimeInMillis();
        calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        long timeNow = calendarNow.getTimeInMillis();
        long daysOfDuan = (timeNow - timeOfDuan) / 1000 / 60 / 60 / 24 - 1;
        String msg = "不忘初心 牢记使命 交通强国 铁路先行     段安全生产" + daysOfDuan + "天 车间安全生产" + daysOfDuan + "天";
        scrollingTextView.setText(msg);
        scrollingTextView.setTextColor(Color.parseColor("#3D9140"));

        //自动轮播
        image = new ArrayList<>();
        tittle = new ArrayList<>();
        image.add(R.mipmap.chejiandongtai);
        image.add(R.mipmap.tianqiyubao1);
        tittle.add("车间动态及通告");
        tittle.add("保定市今日限行");
        xBanner.setData(image, tittle);
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @SuppressLint("NewApi")
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(Objects.requireNonNull(getActivity())).load(image.get(position)).into((ImageView) view);
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
                    Intent intent = new Intent(getActivity(),CheJianJianJieActivity.class);
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.shangjiwenjian) {
                    Intent intent = new Intent(getActivity(),ShangJiWenJianActivity.class);
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.shigonganquan) {
                    Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                    intent.putExtra("tittl","施工安全");
                    intent.putExtra("add", "http://43.226.46.228/shigonganquan/shigonganquan.txt");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.dangjianyutuan) {
                    Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                    intent.putExtra("tittl","党团建设");
                    intent.putExtra("add", "http://43.226.46.228/dangjianyutuan/dangjianyutuan.txt");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.jianchabiaozhun) {
                    Intent intent = new Intent(getActivity(), CommonSecondActivity.class);
                    intent.putExtra("tittl","检查标准");
                    intent.putExtra("add", "http://43.226.46.228/jianchabiaozhun/jianchabiaozhun.txt");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.biaozhunhuajianshe) {
                    Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                    intent.putExtra("tittl","标准化建设");
                    intent.putExtra("add", "http://43.226.46.228/biaozhunhuajianshe/biaozhunhuajianshe.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.qiangxianyanlian) {
                    Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                    intent.putExtra("tittl","应急抢险");
                    intent.putExtra("add", "http://43.226.46.228/qiangxianyanlian/qiangxianyanlian.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.guanlimuban) {
                    Intent intent = new Intent(getActivity(), CommonSecondActivity.class);
                    intent.putExtra("tittl","管理模板");
                    intent.putExtra("add", "http://43.226.46.228/guanlimuban/guanlimuban.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.zhuanyezhishi) {
                    Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                    intent.putExtra("tittl","专业技能");
                    intent.putExtra("add", "http://43.226.46.228/zhuanyezhishi/zhuanyezhishi.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.guizhangzhidu) {
                    Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                    intent.putExtra("tittl","规章制度");
                    intent.putExtra("add", "http://43.226.46.228/guizhangzhidu/guizhangzhidu.txt");
                    startActivity(intent);
                }else if (projectList.get(position).getImageId() == R.mipmap.dianhuachuanzhen) {
                    Intent intent = new Intent(getActivity(), CommonWebViewActivity.class);
                    intent.putExtra("add","http://43.226.46.228/dianhuachuanzhen/dianhuachuanzhen.html");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.jishulvli) {
                    Intent intent = new Intent(getActivity(), TaiZhangWangTu2.class);
                    startActivity(intent);
                }  else if (projectList.get(position).getImageId() == R.mipmap.xiangguankaoshi) {
                    Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                    intent.putExtra("tittl","职教相关");
                    intent.putExtra("add", "http://43.226.46.228/zhijiaoxiangguan/zhijiaoxiangguan.txt");
                    startActivity(intent);
                } else if (projectList.get(position).getImageId() == R.mipmap.jishuxuexi) {
                    Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                    intent.putExtra("tittl","人事财务");
                    intent.putExtra("add", "http://43.226.46.228/renshicaiwu/renshicaiwu.txt");
                    startActivity(intent);
                }
            }
        });

        //动态台账
        dongtaitaizhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CommonSecondActivity.class);
                intent.putExtra("tittl","车间动态台账");
                intent.putExtra("add","http://43.226.46.228/dongtaitaizhang/dongtaitaizhang.txt");
                startActivity(intent);
            }
        });

        //车间企业文化视频
       niceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK);
       niceVideoPlayer.setUp("http://43.226.46.228/video/kanong.mp4",null);
        TxVideoPlayerController controller = new TxVideoPlayerController(getActivity());
        controller.setTitle("");
        controller.setLenght(409000);
        Glide.with(getActivity()).load("http://43.226.46.228/image/qiyewenhua/qiyewenhua.png")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(controller.imageView());
        niceVideoPlayer.setController(controller);

        //奖状轮播
        image1 = new ArrayList<>();
        tittle1 = new ArrayList<>();
        image1.add(R.mipmap.jz1);
        image1.add(R.mipmap.jz2);
        image1.add(R.mipmap.jz3);
        image1.add(R.mipmap.jz4);
        image1.add(R.mipmap.jz5);
        image1.add(R.mipmap.jz6);
        tittle1.add("光缆畅通无阻");
        tittle1.add("五四红旗团支部");
        tittle1.add("合作共赢比赛");
        tittle1.add("2011年度先进集体");
        tittle1.add("定点投篮比赛");
        tittle1.add("安全风险管理知识竞赛");
        xBanner1.setData(image1, tittle1);
        xBanner1.setmAdapter(new XBanner.XBannerAdapter() {
            @SuppressLint("NewApi")
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(Objects.requireNonNull(getActivity())).load(image1.get(position)).into((ImageView) view);
            }
        });
        xBanner1.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(),MaxImageView.class);
                    intent.putExtra("add","http://43.226.46.228/image/suohuojiangli/11.jpg");
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getActivity(),MaxImageView.class);
                    intent.putExtra("add","http://43.226.46.228/image/suohuojiangli/22.jpg");
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getActivity(),MaxImageView.class);
                    intent.putExtra("add","http://43.226.46.228/image/suohuojiangli/33.jpg");
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(getActivity(),MaxImageView.class);
                    intent.putExtra("add","http://43.226.46.228/image/suohuojiangli/44.jpg");
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(getActivity(),MaxImageView.class);
                    intent.putExtra("add","http://43.226.46.228/image/suohuojiangli/55.jpg");
                    startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(getActivity(),MaxImageView.class);
                    intent.putExtra("add","http://43.226.46.228/image/suohuojiangli/66.jpg");
                    startActivity(intent);
                }
            }
        });



        return viewForCheJian;
    }

        //初始化车间内容的列表
    private void intProject() {
        ProjectItemForCheJian cjjj = new ProjectItemForCheJian("车间简介",R.mipmap.chejianjieshao);
        projectList.add(cjjj);
        ProjectItemForCheJian sjwj = new ProjectItemForCheJian("文件系统",R.mipmap.shangjiwenjian);
        projectList.add(sjwj);
        ProjectItemForCheJian sgan = new ProjectItemForCheJian("施工安全",R.mipmap.shigonganquan);
        projectList.add(sgan);
        ProjectItemForCheJian djyt = new ProjectItemForCheJian("党团建设",R.mipmap.dangjianyutuan);
        projectList.add(djyt);
        ProjectItemForCheJian jcbz = new ProjectItemForCheJian("检查标准",R.mipmap.jianchabiaozhun);
        projectList.add(jcbz);
        ProjectItemForCheJian bzhjs = new ProjectItemForCheJian("标准化建设",R.mipmap.biaozhunhuajianshe);
        projectList.add(bzhjs);
        ProjectItemForCheJian qxyl = new ProjectItemForCheJian("应急抢险",R.mipmap.qiangxianyanlian);
        projectList.add(qxyl);
        ProjectItemForCheJian glmb = new ProjectItemForCheJian("管理模板",R.mipmap.guanlimuban);
        projectList.add(glmb);
        ProjectItemForCheJian zyzs = new ProjectItemForCheJian("专业技能",R.mipmap.zhuanyezhishi);
        projectList.add(zyzs);
        ProjectItemForCheJian gzzd = new ProjectItemForCheJian("规章制度",R.mipmap.guizhangzhidu);
        projectList.add(gzzd);
        ProjectItemForCheJian dhcz = new ProjectItemForCheJian("电话传真",R.mipmap.dianhuachuanzhen);
        projectList.add(dhcz);
        ProjectItemForCheJian jsll = new ProjectItemForCheJian("台账网图",R.mipmap.jishulvli);
        projectList.add(jsll);
        ProjectItemForCheJian zjxg = new ProjectItemForCheJian("职教相关",R.mipmap.xiangguankaoshi);
        projectList.add(zjxg);
        ProjectItemForCheJian rscw = new ProjectItemForCheJian("人事财务",R.mipmap.jishuxuexi);
        projectList.add(rscw);
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


    @Override
    public void onResume() {
        super.onResume();
        xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xBanner.stopAutoPlay();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }
}
