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

    public void ChartMagicPresenter(){
        this.dataFin.getInstance();
        this.dataSt.getInstance();
        this.dataSt.set(GregorianCalendar.MONTH,0);
        this.dataSt.set(GregorianCalendar.DAY_OF_MONTH,1);
    }

    public void setDataSt(GregorianCalendar ds) {
        //Задаем период для постраения графиков
        // по задумке эти значения будут получаться из GraphFragmentOne
        this.dataSt = ds;
    }

    public void setDataFin(GregorianCalendar df) {
        //Задаем период для постраения графиков
        // по задумке эти значения будут получаться из GraphFragmentOne
        this.dataFin = df;
    }

    public GregorianCalendar getDataSt() {
        //получаем начальную дату детализации
        return this.dataSt;
    }

    public GregorianCalendar getDataFin() {
        //получаем конечную дату детализации
        return this.dataFin;
    }

    public BarData GetBarData() { //Магия пока не работает(((
        ArrayList<BarEntry> barEntriesCosts = new ArrayList<>();
        ArrayList<BarEntry> barEntriesIncome = new ArrayList<>();
        GregorianCalendar toDay = new GregorianCalendar();
        toDay = getDataSt();
        float costs = 3000f;
        float income = 4000f;
        float x = dataSt.get(GregorianCalendar.MONTH);
        int yearSt = dataSt.get(GregorianCalendar.YEAR);
        int mountSt = dataSt.get(GregorianCalendar.MONTH);
        int daySt = dataSt.get(GregorianCalendar.DAY_OF_MONTH);
        int mountFn = 11;
        int daysFn = dataSt.getActualMaximum(GregorianCalendar.MONTH);
        for (int y = yearSt; y <= dataFin.get(GregorianCalendar.YEAR); y++) {
            if (y == dataFin.get(GregorianCalendar.YEAR)) {                      //проверяем а совпадают ли у нас года
                mountFn = dataFin.get(GregorianCalendar.MONTH);                  // если совпали то добро пожаловать mountFn будет равен крайнему месяцу детализации
            }
            for (int m = mountSt; m <= mountFn; m++) {                           // мы же должны проверить каждый месяц
                if (y == dataFin.get(GregorianCalendar.YEAR) && m == mountFn) {  //таже хрень что и коментом выше только проверяем а не тот же гот и месяц
                    daysFn = dataFin.get(GregorianCalendar.DAY_OF_MONTH);
                }
                for (int d = daySt; d <= daysFn; d++) {
                    toDay.set(GregorianCalendar.DAY_OF_MONTH, d);                // тут мы задаем текщую дату
                    toDay.set(GregorianCalendar.MONTH, m);
                    toDay.set(GregorianCalendar.YEAR, y);
                    if (jouSt.get(toDay) != null) {                              //проверяем а есть ли данные за эту дату
                        jouRL = jouSt.get(toDay);                                // если они всетаки есть то мы создаем Arraylist<JournalRecord>
                        for (int i = 0; i < jouRL.size(); i++) {                 // лезим по элементно Arraylist<JournalRecord>
                            jouR = jouRL.get(i);                                 // берем i элемент JournalRecord
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
                daysFn = toDay.getActualMaximum(GregorianCalendar.MONTH);                                // дней в следующем месяцее
                costs = 0f;                                                        // обнуляем данные за месяц
                income = 0f;
                x++;                                                                // значения оси Х по месяцам начиная с dataSt(GregorianCalendar.Mounth)
            }
            mountFn = 11;
        }
        BarDataSet barDataSetCosts, barDataSetIncome;

        barDataSetCosts = new BarDataSet(barEntriesCosts, "Расходы");
        barDataSetCosts.setColors(Color.RED);
        barDataSetIncome = new BarDataSet(barEntriesIncome, "Доходы");
        barDataSetIncome.setColors(Color.BLUE);
        BarData data = new BarData(barDataSetIncome, barDataSetCosts);
        data.setBarWidth(0.9f);
        return data;
    }

    public PieData GetPieData(TypesOfCashObjects type) {// магия для круговых диаграмм. тоже не работает(
        List<PieEntry> pieEntries = new ArrayList<>();
        GregorianCalendar toDay = dataSt;
        ArrayList<Long> expensest = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        String name = "";
        Random cl = new Random();
        ArrayList<Integer> colors = new ArrayList<>();
        long money = 0;
        int yearSt = dataSt.get(GregorianCalendar.YEAR);
        int mountSt = dataSt.get(GregorianCalendar.MONTH);
        int daySt = dataSt.get(GregorianCalendar.DAY_OF_MONTH);

        int mountFn = 11;
        int daysFn = 31;
        for (int y = yearSt; y <= dataFin.get(GregorianCalendar.YEAR); y++) {
            if (y == dataFin.get(GregorianCalendar.YEAR)) {     //проверяем а не тот ли же года, тогда нужно не доконца годапроверять
                mountFn = dataFin.get(GregorianCalendar.MONTH);
            }
            for (int m = mountSt; m <= mountFn; m++) {
                if (y == dataFin.get(GregorianCalendar.YEAR) && m == mountFn) { //таже хрень что и коментом выше только проверяем а не тот же гот и месяц
                    daysFn = dataFin.get(GregorianCalendar.DAY_OF_MONTH);
                }
                for (int d = daySt; d <= daysFn; d++) {
                    toDay.set(GregorianCalendar.DAY_OF_MONTH, d);   // тут мы задаем текщую дату
                    toDay.set(GregorianCalendar.MONTH, m);
                    toDay.set(GregorianCalendar.YEAR, y);
                    if (jouSt.get(toDay) != null) {                 // а здесь мы должны спросить а есть ли у нас данные за этот день
                        jouRL = jouSt.get(toDay);                   // если они есть то мы писаем кипятком от счастья(херас два)
                        for (int i = 0; i < jouRL.size(); i++) {    // если же мы всетаки прошли кучу всякой хрени то тут мы прогонем по ArrayList<JournalRecord>
                            jouR = jouRL.get(i);                    // получаем i JournalRecord
                            if (jouR.getType() == type) {           // сначала проверяем а тот ли тип данных нам нужен
                                if (category.contains(jouR.getName())) {   //проверка а вдруг м уже записывали данные из этой категории
                                    money = expensest.get(category.indexOf(jouR.getName())) + jouR.getAmount(); //если записывали, то перезаписываем начения
                                    expensest.set(category.indexOf(jouR.getName()), money);
                                } else {
                                    category.add(jouR.getName()); //если же нет этой категории то записываем
                                    expensest.add(category.indexOf(jouR.getName()), jouR.getAmount()); //и значение
                                }
                            }


                        }
                    }
                }
                daysFn = 31;
            }
            mountFn = 11;
        }
        // по идее у нас должны быть сформированы два массива данных  категория и сумма. осталось все это проверить и спать)

        for (int i = 0; i < expensest.size(); i++) {
            pieEntries.add(new PieEntry(expensest.get(i), category.get(i))); // поэтапно кидаем полученые даные в pieEntries
            colors.add(Color.rgb(cl.nextInt(255), cl.nextInt(255), cl.nextInt(255)));
        }
        if (type == TypesOfCashObjects.CASH_SOURCE) {
            name = "Расходы";
        }
        if (type == TypesOfCashObjects.USAGE) {
            name = "Доходы";
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, name);
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        return data;
    }

}
