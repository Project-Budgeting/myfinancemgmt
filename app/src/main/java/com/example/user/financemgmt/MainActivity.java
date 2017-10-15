package com.example.user.financemgmt;
/*
Добавили TabLayaout  вместо PagerTabStrip
05.10.2017 Добавлен Butter Knife
 */


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.user.financemgmt.ExpensePage.ExpenseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements RecViewAdapt.CustomAdapterCallBack {

    @BindView(R.id.mainActivityPager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.start);
        viewPager.startAnimation(animation);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TestFragment(), "Finance management");
        adapter.addFragment(new TestFragment2(), "Expenses journal");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        // пока на любом клике откроется одна и та же активность, ибо некогда
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.alpha,R.anim.fadeout);
    }
}