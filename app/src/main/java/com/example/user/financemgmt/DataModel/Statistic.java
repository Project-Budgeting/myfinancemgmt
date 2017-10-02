package com.example.user.financemgmt.DataModel;

import java.util.GregorianCalendar;

/**
 * Created by Palibin
 * класс содержит в себе Поля и методы для извлечения данных из хранилища в целях их обработки
 * и дальнейшего получения статистики. Статистика осу
 */

public class Statistic {
    GregorianCalendar startDate; // Начало периода учета TODO когда создается первый объект?
    GregorianCalendar endDate;
    long sourcesValue; //Сумма всех доходов за период
    long expenseves; //Сумма всех расходов за период
    long reserved; //Сумма всех зарезервированных (включая отложенные на цели) средств за период
    long targets; // сумма всех созданных целей.

    public Statistic() {

    }
}
