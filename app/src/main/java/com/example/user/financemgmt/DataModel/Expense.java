package com.example.user.financemgmt.DataModel;

import com.example.user.financemgmt.DAO.DriverDao;

import java.util.GregorianCalendar;

/**
 * Created by Palibin
 * Объект расходов
 */

public class Expense {
    private Decreasable source; //объект списания средств
    private Usage usage; //Назначение траты
    private long cost; //Стоимость траты
    private GregorianCalendar payDate; //Дата совершение платежа
    private long id;
    private static long count = 0;

    public Expense(Decreasable source, long cost, GregorianCalendar Date , Usage usage) {
        this.source = source;
        this.cost = cost;
        this.payDate=Date;
        this.usage = usage;
        this.id = generateid();
        count++;
        DriverDao.insertRecordInJournal(
                new JournalRecord(cost,usage.getName(),
                        /*В поле AdditionalSettings попадает наименование расхода. Нужно для
                        дальнейшего добавления категории долгов в приложение*/
                        source.getName(),
                        id)
        );
        DriverDao.decreaseFreeCashOfBallance(cost);
        source.decrease(cost);
    }
public long getId() {
        return id;
    }

    private long generateid(){
        return count+1;
    }
}
