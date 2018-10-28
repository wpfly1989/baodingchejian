package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaiZhangWangTu extends AppCompatActivity {
    private Toolbar toolbar;
    private Fragment currentFragment = new Fragment();
    private FragmentForJingGuangGao fragmentForJingGuangGao;
    private FragmentForJinBao fragmentForJinBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_zhang_wang_tu);

        toolbar = (Toolbar)findViewById(R.id.taizhangwangtutoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("京广高");

        fragmentForJingGuangGao = new FragmentForJingGuangGao();
        fragmentForJinBao = new FragmentForJinBao();
        showFragment(fragmentForJingGuangGao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taizhangwangtutoolbar, menu);
        return true;
    }
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;

            if (!fragment.isAdded()) {
                transaction.add(R.id.taizhangwangtuframelayout, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.jingguanggao:
                getSupportActionBar().setTitle("京广高");
                showFragment(fragmentForJingGuangGao);
                break;
            case R.id.jinbaoxian:
                getSupportActionBar().setTitle("津保线");
                showFragment(fragmentForJinBao);
                break;
            case R.id.jingguangxian:
                Toast.makeText(TaiZhangWangTu.this,"数据正在收集中......",Toast.LENGTH_SHORT).show();
                break;
            case R.id.baomanxian:
                Toast.makeText(TaiZhangWangTu.this,"数据正在收集中......",Toast.LENGTH_SHORT).show();
                break;
            case R.id.manshenxian:
                Toast.makeText(TaiZhangWangTu.this,"数据正在收集中......",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
