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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.github.mikephil.charting.components.Legend.LegendPosition.RIGHT_OF_CHART_CENTER;


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
        dataFin.getInstance();
        dataSt.getInstance();
        dataSt.set(GregorianCalendar.MONTH, 0);
        dataSt.set(GregorianCalendar.DAY_OF_MONTH, 1);
        presenter.setDataFin(dataFin); // чуть не забыл про то что мы же должн отправить значение выбора даты в магическую Preentary
        presenter.setDataSt(dataSt);   // Есть подозрения что иммено из-за того что я не отправлял данный графики не стороились, лошара что тут скажешь
        dataFinish.setText(DateOfString(presenter.getDataFin())); //а тут мы пишем на кнопках даты старта и конца периода детализации
        dataStart.setText(DateOfString(presenter.getDataSt()));   // по умолчанию текущий год

        dataStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dts,  // отображаем диалоговое окно для выбора даты
                        dataSt.get(GregorianCalendar.YEAR),
                        dataSt.get(GregorianCalendar.MONTH),
                        dataSt.get(GregorianCalendar.DAY_OF_MONTH))
                        .show();
            }
        });

        dataFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), dtf,   // отображаем диалоговое окно для выбора даты
                        dataFin.get(GregorianCalendar.YEAR),
                        dataFin.get(GregorianCalendar.MONTH),
                        dataFin.get(GregorianCalendar.DAY_OF_MONTH))
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
            presenter.setDataFin(dataFin);
            dataFinish.setText(DateOfString(dataFin));
            BarCgarShow();
            PieCgarShow();
        }

    };

    private void BarCgarShow() {
        barChart.setData(presenter.GetBarData());
        barChart.animateY(2000);
        barChart.groupBars(-0.5f, 0.24f, 0.08f);
        barChart.setFitBars(true);
        barChart.setScaleYEnabled(false);
        Legend legend_bar = barChart.getLegend();
        legend_bar.setForm(Legend.LegendForm.CIRCLE);

        String[] xValues = presenter.getCount();
        XAxis barXAxis = barChart.getXAxis();
        barXAxis.setValueFormatter(new BarValueFormated(xValues));
        barXAxis.setGranularity(1);
        YAxis barYAxis = barChart.getAxisLeft();
        barYAxis.setAxisMinimum(0);
        barChart.getAxisRight().setEnabled(false);
        barChart.invalidate();
    }

    private class BarValueFormated implements IAxisValueFormatter {
        private String[] mValue;

        public BarValueFormated(String[] values) {
            this.mValue = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValue[(int) value];
        }
    }

    private void PieCgarShow() {
        cost_Chart.setData(presenter.GetPieData(TypesOfCashObjects.USAGE));
        cost_Chart.animateY(1500);
        cost_Chart.setCenterText("Расходы");
        cost_Chart.invalidate();
        Legend legend_cost = cost_Chart.getLegend();
        legend_cost.setForm(Legend.LegendForm.CIRCLE);
        cost_Chart.notifyDataSetChanged();
        cost_Chart.setNoDataText("За выбраный период нет данных");
        cost_Chart.setUsePercentValues(true);
        cost_Chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry pieEntry = (PieEntry) e;
                cost_Chart.setCenterText(pieEntry.getLabel() + "\n" + pieEntry.getValue());
            }

            @Override
            public void onNothingSelected() {
                cost_Chart.setCenterText("Расходы");
            }
        });

        income_Chart.setData(presenter.GetPieData(TypesOfCashObjects.CASH_SOURCE));
        income_Chart.animateY(1500);
        income_Chart.setCenterText("Доходы");
        Legend legend_income = income_Chart.getLegend();
        legend_income.setForm(Legend.LegendForm.CIRCLE);
        income_Chart.notifyDataSetChanged();
        income_Chart.setNoDataText("За выбраный период нет данных");
        income_Chart.setUsePercentValues(true);
        income_Chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry pieEntry = (PieEntry) e;
                income_Chart.setCenterText(pieEntry.getLabel() + "\n" + pieEntry.getValue());
            }

            @Override
            public void onNothingSelected() {
                income_Chart.setCenterText("Доходы");
            }
        });
    }

    // private void   interface OnChartValueSelectedListener {
    // }


    private String DateOfString(GregorianCalendar c) {
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