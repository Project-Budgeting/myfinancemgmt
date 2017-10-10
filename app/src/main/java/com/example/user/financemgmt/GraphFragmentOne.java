package com.example.user.financemgmt;

/*
05.10.2017 Добавлен Butter Knife
*/

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@RequiresApi(api = Build.VERSION_CODES.N)
public class GraphFragmentOne extends Fragment {

    private Unbinder unbinder;          //Butter Knife Refactoring

    @BindView(R.id.barchart)            //Butter Knife Refactoring
            BarChart barChart;

    @BindView(R.id.cost_piechart)
            PieChart cost_Chart;

    @BindView(R.id.income_piechart)
            PieChart income_Chart;

    @BindView(R.id.dateStart)
            Button dataStart;

    @BindView(R.id.dateFinish)
            Button dataFinish;

    float expensest[] = {1276.5f, 1858.56f, 3450.00f, 19800.25f, 2700.00f, 5780.31f};
    String category[] = {"Проезд", "Заправка", "Квартплата", "Еда", "Хоз. товары", "Прочие"};
    int colors[] = {
            Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31), Color.rgb(179, 100, 53), Color.rgb(109, 100, 53)
    };

    Calendar dataSt = Calendar.getInstance();
    Calendar dataFin = Calendar.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chart_scroll, container, false);

        unbinder = ButterKnife.bind(this, v); //Butter Knife Refactoring

        // установка начальных даты и времени
        dataSt.set(Calendar.MONTH,0);
        dataSt.set(Calendar.DAY_OF_MONTH, 1);

        dataFinish.setText(DateOfString(dataFin));
        dataStart.setText(DateOfString(dataSt));


        dataStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dts,  // отображаем диалоговое окно для выбора даты
                        dataSt.get(Calendar.YEAR),
                        0,
                        1)
                        .show();
            }
        });

        dataFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dtf,   // отображаем диалоговое окно для выбора даты
                        dataFin.get(Calendar.YEAR),
                        dataFin.get(Calendar.MONTH),
                        dataFin.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        List<BarEntry> barEntries = new ArrayList<>();                          // Заполняем первую диаграмму
        barEntries.add(new BarEntry(3585f, 0));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");
        BarData theData = new BarData(barDataSet);
        theData.setBarWidth(0.9f);

        List<PieEntry> pieEntries = new ArrayList<>();                          // Заполняем круговые диаграммы
        for (int i = 0; i < expensest.length; i++) {
            pieEntries.add(new PieEntry(expensest[i], category[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "Ваши расходы за месяц");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);

        //Рисуем диаграммы
        barChart.setData(theData);
        barChart.animateY(2000);
        barChart.invalidate();

        cost_Chart.setData(data);
        cost_Chart.animateY(1500);
        cost_Chart.invalidate();

        income_Chart.setData(data);
        income_Chart.animateY(1500);
        income_Chart.invalidate();

        return v;
    }

    DatePickerDialog.OnDateSetListener dts = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dataSt.set(Calendar.YEAR, year);
            dataSt.set(Calendar.MONTH, monthOfYear);
            dataSt.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dataStart.setText(DateOfString(dataSt));
        }
    };

    DatePickerDialog.OnDateSetListener dtf = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dataFin.set(Calendar.YEAR, year);
            dataFin.set(Calendar.MONTH, monthOfYear);
            dataFin.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dataFinish.setText(DateOfString(dataFin));
        }

    };

    private String DateOfString(Calendar c){
        String arrmounts[] = {"Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"};
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String mounts = arrmounts[c.get(Calendar.MONTH)];
        String year = String.valueOf(c.get(Calendar.YEAR));
        return day + " " + mounts + " " + year;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}