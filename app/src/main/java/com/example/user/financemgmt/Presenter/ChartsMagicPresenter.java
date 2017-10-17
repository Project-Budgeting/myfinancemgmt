package com.example.user.financemgmt.Presenter;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.JournalRecord;
import com.example.user.financemgmt.DataModel.TypesOfCashObjects;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;

/**
 * Created by Vladislav Khambikov on 08.10.2017.
 * Здесь просходит вся магия работы с графиками
 */

public class ChartsMagicPresenter {
    //private final DriverDao repositories = new DriverDao();
    private GregorianCalendar dataSt = new GregorianCalendar(); // начальная дата для постранения графиков
    private GregorianCalendar dataFin = new GregorianCalendar(); // конечная дата для построения графиков
    private ArrayList<JournalRecord> jouRL;
    private JournalRecord jouR;
    int size = 0;

    public void setDataSt(GregorianCalendar ds) {       // устанавливаем стартовую дату необходимого периода времени
        dataSt = ds;
    }

    public void setDataFin(GregorianCalendar df) {       // устанавливаем конечную дату необходимого периода
        dataFin = df;
    }

    public GregorianCalendar getDataSt() {              // получаем начальную дату
        return dataSt;
    }

    public GregorianCalendar getDataFin() {             //получаем конечную дату
        return dataFin;
    }

    public BarData GetBarData() {                                       // Магия построения LinerCharts
        ArrayList<BarEntry> barEntriesCosts = new ArrayList<>();        // массив данных для записи расходов
        ArrayList<BarEntry> barEntriesIncome = new ArrayList<>();        // массив данных для записи доходов
        GregorianCalendar toDay = new GregorianCalendar();
        int mount;
        long costs = 0;                                                  // расходы
        long income = 0;                                                 // доходы
        long oldcosts = 0;
        long oldincome = 0;
        boolean first = false;
        boolean firstDate = false;
        int year;
        int day;
        float x = 0;                                                    //ось Х уебищная
        // сама логика отбора данных за необходимый период
        toDay.set(dataSt.get(GregorianCalendar.YEAR), dataSt.get(GregorianCalendar.MONTH), dataSt.get(GregorianCalendar.DAY_OF_MONTH));
        while (toDay.compareTo(dataFin) <= 0) {
            year = toDay.get(GregorianCalendar.YEAR);
            mount = toDay.get(GregorianCalendar.MONTH);
            day = toDay.get(GregorianCalendar.DAY_OF_MONTH);
            if (DriverDao.getDataForDay(new GregorianCalendar(year, mount, day)) != null) {    // проверка а есть ли в базе данные за текущюю дату
                jouRL = DriverDao.getDataForDay(new GregorianCalendar(year, mount, day));
                for (int i = 0; i < jouRL.size(); i++) {                                      // ползем по массиву JournalRecord
                    jouR = jouRL.get(i);
                    if (jouR.getType() == TypesOfCashObjects.USAGE) {
                        costs = costs + jouR.getAmount();
                    }
                    if (jouR.getType() == TypesOfCashObjects.CASH_SOURCE) {
                        income = income + jouR.getAmount();
                    }
                }
            }
            if ((oldcosts != 0 && oldincome != 0) || (costs != 0 && income != 0)) {
                first = true;
            }
            if (first) {
                if (!firstDate) {
                    dataSt.set(toDay.get(GregorianCalendar.YEAR), toDay.get(GregorianCalendar.MONTH), toDay.get(GregorianCalendar.DAY_OF_MONTH));
                    firstDate = true;
                }
                toDay.add(GregorianCalendar.DAY_OF_MONTH, 1);
                if (mount != toDay.get(GregorianCalendar.MONTH)) {
                    barEntriesCosts.add(new BarEntry(x, costs));
                    barEntriesIncome.add(new BarEntry(x, income));
                    oldcosts = costs;
                    oldincome = income;
                    costs = 0;
                    income = 0;
                    x++;
                }
            } else {
                toDay.add(GregorianCalendar.DAY_OF_MONTH, 1);
            }
        }
        barEntriesCosts.add(new BarEntry(x, costs));
        barEntriesIncome.add(new BarEntry(x, income));

        size = (int) x;
        BarDataSet barDataSetCosts, barDataSetIncome;
        barDataSetCosts = new BarDataSet(barEntriesCosts, "Расходы");
        barDataSetCosts.setColors(RED);
        barDataSetIncome = new BarDataSet(barEntriesIncome, "Доходы");
        barDataSetIncome.setColors(BLUE);
        BarData data = new BarData(barDataSetIncome, barDataSetCosts);
        data.setBarWidth(0.3f);
        data.setHighlightEnabled(false);
        return data;
    }

    public String[] getCount() {
        //boolean mount2 = dataSt.get(Calendar.YEAR) == dataFin.get(Calendar.YEAR) && (dataFin.get(GregorianCalendar.MONTH) - dataSt.get(GregorianCalendar.MONTH) < 2);
        String[] list = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        //int[] listday = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        ArrayList<String> arrList = new ArrayList<>();
        int count = dataSt.get(GregorianCalendar.MONTH);
        //int dayStart = dataSt.get(GregorianCalendar.DAY_OF_MONTH);

        for (int i = 0; i <= size * 15; i++) {
            if (count > 11) {
                count = 0;
            }
            //if (mount2) {
            //    for (int d = dayStart; d <= listday[count]; d++) {
            //        arrList.add(String.valueOf(d));
            //    }
            //   dayStart = 1;
            //} else {
            arrList.add(list[count]);
            //}
            count++;
        }
        String[] listX = new String[arrList.size()];
        for (int i = 0; i < listX.length; i++) {
            listX[i] = arrList.get(i);
        }
        return listX;
    }

    public PieData GetPieData(TypesOfCashObjects type) {// магия для круговых диаграмм
        List<PieEntry> pieEntries = new ArrayList<>();
        ArrayList<Long> expensest = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        GregorianCalendar toDay = new GregorianCalendar();
        long money = 0;
        int mount;
        int year;
        int day;

        // сама логика отбора данных за необходимый период
        toDay.set(dataSt.get(GregorianCalendar.YEAR), dataSt.get(GregorianCalendar.MONTH), dataSt.get(GregorianCalendar.DAY_OF_MONTH));
        while (toDay.compareTo(dataFin) <= 0) {
            year = toDay.get(GregorianCalendar.YEAR);
            mount = toDay.get(GregorianCalendar.MONTH);
            day = toDay.get(GregorianCalendar.DAY_OF_MONTH);
            if (DriverDao.getDataForDay(new GregorianCalendar(year, mount, day)) != null) {    // проверка а есть ли в базе данные за текущюю дату
                jouRL = DriverDao.getDataForDay(new GregorianCalendar(year, mount, day));
                for (int i = 0; i < jouRL.size(); i++) {
                    jouR = jouRL.get(i);

                    if (jouR.getType() == type) {
                        if (category.contains(jouR.getName())) {
                            money = expensest.get(category.indexOf(jouR.getName())) + jouR.getAmount();
                            expensest.set(category.indexOf(jouR.getName()), money);
                        } else {
                            category.add(jouR.getName());
                            expensest.add(category.indexOf(jouR.getName()), jouR.getAmount());
                        }
                    }
                }
            }
            toDay.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }

        for (
                int i = 0; i < expensest.size(); i++)

        {
            pieEntries.add(new PieEntry(expensest.get(i), category.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setSelectionShift(14);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        return data;
    }
}
