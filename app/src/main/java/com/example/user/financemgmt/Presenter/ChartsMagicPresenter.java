package com.example.user.financemgmt.Presenter;


import com.example.user.financemgmt.DataModel.JournalRecord;
import com.example.user.financemgmt.TestStorageForDataObjects.JournalStorage;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vladislav Khambikov on 08.10.2017.
 * Здесь просходит вся магия работы с графиками
 */

public class ChartsMagicPresenter {
    int chartColorCost[] = {};
    int chartColorIncome[] = {};
    private GregorianCalendar dataSt; // начальный период дл постранения графиков
    private GregorianCalendar dataFin; // конечный период для построения графиков
    private JournalStorage jouSt;  // создаем данные за период
    //Словарь для обращения

    public void setData(GregorianCalendar ds, GregorianCalendar df) {
        //Задаем период для постраения графиков
        // по задумке эти значения будут получаться из GraphFragmentOne
        dataSt = ds;
        dataFin = df;

    }

    public GregorianCalendar getDataSt() {
        //получаем начальную дату детализации
        return this.dataSt;
    }

    public GregorianCalendar getDataFin() {
        //получаем конечную дату детализации
        return dataFin;
    }

    public void GetBarData() { //Магия пока не работает(((
        List<BarEntry> barEntries = new ArrayList<>();
        GregorianCalendar toDay = dataSt;
        int mounts = 11;
        int days = 31;
        float costs = 0;
        float income = 0;
        for (int y = dataSt.get(GregorianCalendar.YEAR); y <= dataFin.get(GregorianCalendar.YEAR); y++) {
            if (y == dataFin.get(GregorianCalendar.YEAR)) {     //проверяем а не тот ли же года, тогда нужно не доконца годапроверять
                mounts = dataFin.get(GregorianCalendar.MONTH);
            }
            for (int m = dataSt.get(GregorianCalendar.MONTH); m <= mounts; m++) {
                if (y == dataFin.get(GregorianCalendar.YEAR) && m == dataFin.get(GregorianCalendar.MONTH)) { //таже хрень что и коментом выше только проверяем а не тот же гот и месяц
                    days = dataFin.get(GregorianCalendar.DAY_OF_MONTH);
                }
                for (int d = dataSt.get(GregorianCalendar.DAY_OF_MONTH); d <= days; d++) {
                    toDay.set(GregorianCalendar.DAY_OF_MONTH, d);
                    toDay.set(GregorianCalendar.MONTH, m);
                    toDay.set(GregorianCalendar.YEAR, y);



                        ;
                }
                days = 31;
            }
            mounts = 11;
        }


    }


}
