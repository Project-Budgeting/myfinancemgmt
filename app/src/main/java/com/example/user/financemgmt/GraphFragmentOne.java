package com.example.user.financemgmt;

/*
05.10.2017 Добавлен Butter Knife
*/

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class GraphFragmentOne extends Fragment {

    private Unbinder unbinder; //Butter Knife Refactoring

    @BindView(R.id.piechart) //Butter Knife Refactoring
            PieChart chart; //Butter Knife Refactoring

    float expensest[] = {1276.5f, 1858.56f, 3450.00f, 19800.25f, 2700.00f, 5780.31f};
    String category[] = {"Проезд", "Заправка", "Квартплата", "Еда", "Хоз. товары", "Прочие"};
    int colors[] = {
            Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31), Color.rgb(179, 100, 53), Color.rgb(109, 100, 53),
            Color.rgb(179, 020, 53)
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.graph_fragment_one, container, false);

        unbinder = ButterKnife.bind(this, v); //Butter Knife Refactoring

        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < expensest.length; i++) {
            pieEntries.add(new PieEntry(expensest[i], category[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Ваши расходы за месяц");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);

        //Рисуем диаграмму
        /*PieChart chart = (PieChart) v.findViewById(R.id.piechart);*/
        chart.setData(data);
        chart.animateY(1500);
        chart.invalidate();

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}