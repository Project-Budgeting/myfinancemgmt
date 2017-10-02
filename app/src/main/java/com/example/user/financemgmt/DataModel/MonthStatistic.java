package com.example.user.financemgmt.DataModel;

import java.util.GregorianCalendar;

/**
 * Created by Palibin
 * класс содержит в себе общую статистику за месяц.()
 */

public class MonthStatistic {
    GregorianCalendar startDate; // Начало периода учета TODO когда создается первый объект?
    long SourcesValue; //Сумма всех доходов замесяц
    long Expenseves; //Сумма всех расходов за месяц
    long Reserved; //Сумма всех зарезервированных средств за месяц


}
