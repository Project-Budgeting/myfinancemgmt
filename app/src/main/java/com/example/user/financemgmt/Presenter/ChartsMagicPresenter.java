package com.example.user.financemgmt.Presenter;


import android.graphics.Color;

import com.example.user.financemgmt.DataModel.JournalRecord;
import com.example.user.financemgmt.DataModel.TypesOfCashObjects;
import com.example.user.financemgmt.TestStorageForDataObjects.JournalStorage;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Vladislav Khambikov on 08.10.2017.
 * Здесь просходит вся магия работы с графиками
 */

public class ChartsMagicPresenter {

    private final JournalStorage repositories = new JournalStorage();

    private GregorianCalendar dataSt = new GregorianCalendar(); // начальный период дл постранения графиков
    private GregorianCalendar dataFin = new GregorianCalendar(); // конечный период для построения графиков
    private HashMap<GregorianCalendar, ArrayList<JournalRecord>> jouSt = repositories.getStorageMap();
    ; // создаем данные за период
    private ArrayList<JournalRecord> jouRL; //
    private JournalRecord jouR;

    public void setDataSt(GregorianCalendar ds) {
        //Задаем период для постраения графиков
        // по задумке эти значения будут получаться из GraphFragmentOne
        dataSt = ds;
    }

    public void setDataFin(GregorianCalendar df) {
        //Задаем период для постраения графиков
        // по задумке эти значения будут получаться из GraphFragmentOne
        dataFin = df;
    }

    public GregorianCalendar getDataSt() {
        //получаем начальную дату детализации
        return dataSt;
    }

    public GregorianCalendar getDataFin() {
        //получаем конечную дату детализации
        return dataFin;
    }

    private int daysNextMount(int m) {
        int d[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (m != 11) {
            return d[m];
        } else {
            return d[0];
        }
    }


    public BarData GetBarData() { //Магия пока не работает(((
        ArrayList<BarEntry> barEntriesCosts = new ArrayList<>();
        ArrayList<BarEntry> barEntriesIncome = new ArrayList<>();
        int dayStart = dataSt.get(GregorianCalendar.DAY_OF_MONTH);
        int dayFinish;

        int mounthStart = dataSt.get(GregorianCalendar.MONTH);
        int mountFinish = 11;

        int yearStart = dataSt.get(GregorianCalendar.YEAR);
        int yearFinish = dataFin.get(GregorianCalendar.YEAR);

        long costs = 0;
        long income = 0;
        float x = 0f;

        for (int y = yearStart; y <= yearFinish; y++) {
            if (y == yearFinish) {
                mountFinish = dataFin.get(GregorianCalendar.MONTH);
            }
            for (int m = mounthStart; m <= mountFinish; m++) {
                if (y == yearFinish & m == mountFinish) {
                    dayFinish = dataFin.get(GregorianCalendar.DAY_OF_MONTH);
                } else {
                    dayFinish = daysNextMount(m);
                }
                for (int d = dayStart; d <= dayFinish; d++) {
                    if (jouSt.get(new GregorianCalendar(y, m, d)) != null) {
                        jouRL = jouSt.get(new GregorianCalendar(y, m, d));
                        for (int i = 0; i < jouRL.size(); i++) {
                            jouR = jouRL.get(i);
                            if (jouR.getType() == TypesOfCashObjects.USAGE) {    // проверка а не траты ли это
                                costs = costs + jouR.getAmount();                // сумма денег потраченых за месяц????
                            }
                            if (jouR.getType() == TypesOfCashObjects.CASH_SOURCE) { //да это же доходы твоюж за ногу
                                income = income + jouR.getAmount();                 // суммируем баблище за месяц
                            }
                        }
                    }
                }
                barEntriesCosts.add(new BarEntry(x, costs));                       // вот тут то мы и записываем наши данные которых почемуто нет(
                barEntriesIncome.add(new BarEntry(x, income));
                costs = 0;                                                        // обнуляем данные за месяц
                income = 0;
                x = x + 1f;
                dayStart = 0;

            }
            mounthStart = 0;
        }

        BarDataSet barDataSetCosts, barDataSetIncome;
        barDataSetCosts = new BarDataSet(barEntriesCosts, "Расходы");
        barDataSetCosts.setColors(Color.RED);
        barDataSetIncome = new BarDataSet(barEntriesIncome, "Доходы");
        barDataSetIncome.setColors(Color.BLUE);
        BarData data = new BarData(barDataSetIncome, barDataSetCosts);
        data.setBarWidth(0.05f);
        return data;
    }

    public PieData GetPieData(TypesOfCashObjects type) {// магия для круговых диаграмм. тоже не работает(
        List<PieEntry> pieEntries = new ArrayList<>();
        ArrayList<Long> expensest = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        Random cl = new Random();
        ArrayList<Integer> colors = new ArrayList<>();
        long money = 0;


        int dayStart = dataSt.get(GregorianCalendar.DAY_OF_MONTH);
        int dayFinish;

        int mounthStart = dataSt.get(GregorianCalendar.MONTH);
        int mountFinish = 11;

        int yearStart = dataSt.get(GregorianCalendar.YEAR);
        int yearFinish = dataFin.get(GregorianCalendar.YEAR);

        for (int y = yearStart; y <= yearFinish; y++) {
            if (y == yearFinish) {
                mountFinish = dataFin.get(GregorianCalendar.MONTH);
            }
            for (int m = mounthStart; m <= mountFinish; m++) {
                if (y == yearFinish & m == mountFinish) {
                    dayFinish = dataFin.get(GregorianCalendar.DAY_OF_MONTH);
                } else {
                    dayFinish = daysNextMount(m);
                }
                for (int d = dayStart; d <= dayFinish; d++) {
                    if (jouSt.get(new GregorianCalendar(y, m, d)) != null) {
                        jouRL = jouSt.get(new GregorianCalendar(y, m, d));
                        for (int i = 0; i < jouRL.size(); i++) {
                            jouR = jouRL.get(i);                                                                // получаем i JournalRecord
                            if (jouR.getType() == type) {                                                       // сначала проверяем а тот ли тип данных нам нужен
                                if (category.contains(jouR.getName())) {                                        //проверка а вдруг м уже записывали данные из этой категории
                                    money = expensest.get(category.indexOf(jouR.getName())) + jouR.getAmount(); //если записывали, то перезаписываем начения
                                    expensest.set(category.indexOf(jouR.getName()), money);
                                } else {
                                    category.add(jouR.getName());                                                //если же нет этой категории то записываем
                                    expensest.add(category.indexOf(jouR.getName()), jouR.getAmount());          //и значение
                                }
                            }
                        }
                    }
                }
                dayStart = 0;
            }
            mounthStart = 0;
        }

        // по идее у нас должны быть сформированы два массива данных  категория и сумма. осталось все это проверить и спать)

        for (int i = 0; i < expensest.size(); i++) {
            pieEntries.add(new PieEntry(expensest.get(i), category.get(i))); // поэтапно кидаем полученые даные в pieEntries
            colors.add(Color.rgb(cl.nextInt(255), cl.nextInt(255), cl.nextInt(255)));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        return data;
    }

}
