package com.example.user.financemgmt;

/*
05.10.2017 Добавлен Butter Knife
*/

import android.app.DatePickerDialog;
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

import com.example.user.financemgmt.DataModel.TypesOfCashObjects;
import com.example.user.financemgmt.Presenter.ChartsMagicPresenter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    private final ChartsMagicPresenter presenter = new ChartsMagicPresenter();

    GregorianCalendar dataSt = new GregorianCalendar();
    GregorianCalendar dataFin = new GregorianCalendar();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chart_scroll, container, false);

        unbinder = ButterKnife.bind(this, v); //Butter Knife Refactoring

        // установка начальных даты и времени

        /*
        presenter.setDataFin(dataFin); // чуть не забыл про то что мы же должн отправить значение выбора даты в магическую Preentary
        presenter.setDataSt(dataSt);   // Есть подозрения что иммено из-за того что я не отправлял данный графики не стороились, лошара что тут скажешь
        */

        dataFinish.setText(DateOfString(presenter.getDataFin())); //а тут мы пишем на кнопках даты старта и конца периода детализации
        dataStart.setText(DateOfString(presenter.getDataSt()));   // по умолчанию текущий год

        dataStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dts,  // отображаем диалоговое окно для выбора даты
                        presenter.getDataSt().get(GregorianCalendar.YEAR),
                        presenter.getDataSt().get(GregorianCalendar.MONTH),
                        presenter.getDataSt().get(GregorianCalendar.DAY_OF_MONTH))
                        .show();
            }
        });

        dataFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dtf,   // отображаем диалоговое окно для выбора даты
                        presenter.getDataFin().get(GregorianCalendar.YEAR),
                        presenter.getDataFin().get(GregorianCalendar.MONTH),
                        presenter.getDataFin().get(GregorianCalendar.DAY_OF_MONTH))
                        .show();
            }
        });

        //Рисуем диаграммы
        BarCgarShow();
        PieCgarShow();

        return v;
    }

    DatePickerDialog.OnDateSetListener dts = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dataSt.set(GregorianCalendar.YEAR, year);
            dataSt.set(GregorianCalendar.MONTH, monthOfYear);
            dataSt.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth);
            presenter.setDataSt(dataSt);
            dataStart.setText(DateOfString(dataSt));
            BarCgarShow();
            PieCgarShow();
        }
    };

    DatePickerDialog.OnDateSetListener dtf = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dataFin.set(GregorianCalendar.YEAR, year);
            dataFin.set(GregorianCalendar.MONTH, monthOfYear);
            dataFin.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth);
            dataFinish.setText(DateOfString(dataFin));
            presenter.setDataFin(dataFin);
            BarCgarShow();
            PieCgarShow();
        }

    };

    private void BarCgarShow(){
        barChart.setData(presenter.GetBarData());
        barChart.animateY(2000);
        barChart.groupBars(0.9f, 0.06f, 0.02f);
        barChart.setFitBars(true);
        barChart.invalidate();
    }

    private void PieCgarShow(){
        cost_Chart.setData(presenter.GetPieData(TypesOfCashObjects.CASH_SOURCE));
        cost_Chart.animateY(1500);
        cost_Chart.invalidate();
        income_Chart.setData(presenter.GetPieData(TypesOfCashObjects.USAGE));
        income_Chart.animateY(1500);
        income_Chart.invalidate();
    }

    private String DateOfString(GregorianCalendar c){
        String arrmounts[] = {"Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"};
        String mounts = arrmounts[c.get(Calendar.MONTH)];
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String year = String.valueOf(c.get(Calendar.YEAR));
        return day + " " + mounts + " " + year;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}