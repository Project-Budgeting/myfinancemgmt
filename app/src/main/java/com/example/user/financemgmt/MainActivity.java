package com.example.user.financemgmt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> pagesList; // Значения страниц viewpager
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagesList = ContentPages.getContentPages(this).getPagesList();
        ViewPager mainPager = (ViewPager) findViewById(R.id.mainActivityPager);
        FragmentManager fm = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(fm);
        mainPager.setAdapter(adapter);

    }
    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Integer.toString(position);
        }

        @Override
        public Fragment getItem(int position) {
            return pagesList.get(position);
        }

        @Override
        public int getCount() {
            return pagesList.size();
        }
    }
}
