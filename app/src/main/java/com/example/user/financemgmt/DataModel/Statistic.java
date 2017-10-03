package com.example.user.financemgmt.DataModel;

import java.util.GregorianCalendar;

/**
 * Created by Palibin
 * класс содержит в себе Поля и методы для извлечения данных из хранилища в целях их обработки
 * и дальнейшего получения статистики. Статистика осу
 */

public class Statistic {
    private GregorianCalendar startDate; // Начало периода учета TODO когда создается первый объект?
    private GregorianCalendar endDate;

    //TODO А может на хрен оно не нужно будет?
    long sourcesValue;          //Сумма всех доходов за период
    long expenseves;            //Сумма всех расходов за период
    long reserved;              //Сумма всех зарезервированных средств на определенные траты за период
    long targets;               // сумма всех созданных целей.
    long reservedForTargets;    //Отложено на выполнение целей.

    //средства, полученные через доходы за указанный период времени
    //TODO как передавть даты для указания периода
    //TODO Указать Сигнатуры методов
    public long getSourceSumm() {

    }

    //Сумма всех затрат за указанный период
    public long getExpensesSumm() {

    }

    //Сумма стоимостей возникших в указанный период целей
    public long getNewTargetsSumm() {

    }

    //Сумма отложенных средств на выполнение целей в указанный период
    public long getTargetReservs() {

    }

    //Сумма отложенных средств, выделенных для определенной группы затрат
    public long getReservedCash() {

    }

    //Доход с указаного источника в указанный период
    public long getSourceSummByName() {

    }

    //Сумма, затраченная на указанную категорию затрат в заданный период
    public long getExpensesSummByUsage() {

    }

}
