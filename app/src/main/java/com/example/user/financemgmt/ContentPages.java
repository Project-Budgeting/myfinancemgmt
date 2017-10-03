package com.example.user.financemgmt;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by Palibin 30.09.2017.
 * Данный класс предназначен для управления заполнением ViewPager для Main Activity
 * Данный класс реализует паттерн Singleton
 */

// городить Singleton для получение фрагментов для адаптера - лишнее. 
// Достаточно фрагменты передать/создать в конструкторе адаптера, и хранить в самом адаптере
public class ContentPages {
    private static ContentPages singleObject;
    private ArrayList<Fragment> pagesList;       //лист значений табов ViewPager
    private static Context context;

    public ContentPages(Context c) {
        this.context = c;
        pagesList = new ArrayList<Fragment>();
        pagesList.add(new TestFragment());
        pagesList.add(new TestFragment2());
    }

    public static ContentPages getContentPages(Context c) {
        if (singleObject==null) singleObject = new ContentPages(c.getApplicationContext());
        return singleObject;
    }

    public ArrayList<Fragment> getPagesList() {
        return pagesList;
    }
}
