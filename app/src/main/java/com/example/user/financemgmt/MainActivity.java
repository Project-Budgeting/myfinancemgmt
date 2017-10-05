package com.example.user.financemgmt;
/*
Добавили TabLayaout  вместо PagerTabStrip
05.10.2017 Добавлен Butter Knife
 */


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    /*private TabLayout tabLayout;
      private ViewPager viewPager;*/

    @BindView(R.id.mainActivityPager) //Butter Knife Refactoring
            ViewPager viewPager; //Butter Knife Refactoring
    @BindView(R.id.tablayout) //Butter Knife Refactoring
            TabLayout tabLayout; //Butter Knife Refactoring

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //Butter Knife Refactoring

         /*viewPager = (ViewPager) findViewById(R.id.mainActivityPager);*/
        setupViewPager(viewPager);
        /*tabLayout = (TabLayout) findViewById(R.id.tablayout);*/
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TestFragment(), "Управление Средствами");
        adapter.addFragment(new TestFragment2(), "Журнал расходов");
        viewPager.setAdapter(adapter);
    }
}