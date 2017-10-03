package com.example.user.financemgmt.DataModel;

import java.util.GregorianCalendar;

/**
 * Created by Palibin
 * Объект расходов
 */

public class Expense {
    private Decreasable source; //объект списания средств
    // private Usage usage; //TODO дописать объект Usage(категория назначение платежа, трата)
    private long cost; //Стоимость траты
    GregorianCalendar payDate; //Дата совершение платежа

    public Expense(Decreasable source, long cost, GregorianCalendar Date /*, Usage usage*/) {
        this.source = source;
        this.cost = cost;
        this.payDate=Date;
        //this.usage = usage;
    }
}
