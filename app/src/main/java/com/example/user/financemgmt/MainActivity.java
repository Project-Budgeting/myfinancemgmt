package com.example.user.financemgmt;
/*
Добавили TabLayaout  вместо PagerTabStrip
 */


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;


    public class MainActivity extends AppCompatActivity {

        private TabLayout tabLayout;
        private ViewPager viewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            viewPager = (ViewPager) findViewById(R.id.mainActivityPager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tablayout);
            tabLayout.setupWithViewPager(viewPager);


        }

        private void setupViewPager(ViewPager viewPager) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new TestFragment(), "Управление Средствами");
            adapter.addFragment(new TestFragment2(), "Журнал расходов");
            viewPager.setAdapter(adapter);
        }
    }