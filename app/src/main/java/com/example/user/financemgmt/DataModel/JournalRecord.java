package com.example.user.financemgmt.DataModel;

import com.example.user.financemgmt.DAO.DriverDao;

import java.util.GregorianCalendar;

/**
 * Created by Palibin
 * Класс представляет собой запись в журнале любого события, изменяющего финансовое состояние User
 */

public class JournalRecord {
    private long amount; //Колличественный показатель финансового события
    private String name; //Название объекта, создавшего событие
    // Поле для заполнения данными, которые необходимы для понимания значения объекта, но отличаются
    //в зависимости от классов
    private String additionalSettings;
    private TypesOfCashObjects type; //тип события
    private GregorianCalendar date; //дата наступления события

    public JournalRecord(long amount, String name, String additionalSettings) {
        this.amount = amount;
        this.name = name;
        this.additionalSettings = additionalSettings;
    }
    /*конструктор для тестирования*/

    public JournalRecord(long amount, String name, String additionalSettings, TypesOfCashObjects type) {
        this.amount = amount;
        this.name = name;
        this.additionalSettings = additionalSettings;
        this.type = type;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public static JournalRecord makeRecordInJournal(Object event, long amount,
                                                    String name, String additionalSettings ){
        JournalRecord jr = new JournalRecord(amount, name, additionalSettings);
        jr.date = new GregorianCalendar();
        switch (event.getClass().getName()) {
            case "CashSource":  jr.type = TypesOfCashObjects.CASH_SOURCE;
                                break;
            case "Usage":       jr.type = TypesOfCashObjects.USAGE;
        }
        DriverDao.insertRecordInJournal(jr);
        return jr;

    }
}
