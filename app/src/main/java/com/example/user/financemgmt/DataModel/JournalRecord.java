package com.example.user.financemgmt.DataModel;

import com.example.user.financemgmt.DAO.DriverDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

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

    public TypesOfCashObjects getType() {
        return type;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public static JournalRecord makeRecordInJournal(Object event, long amount,
                                                    String name, String additionalSettings ){
        JournalRecord jr = new JournalRecord(amount, name, additionalSettings);
       //TODO переписать так, чтобы дата создавалась только в формате Год, месяц, день
        jr.date = new GregorianCalendar();
        switch (event.getClass().getName()) {
            case "CashSource":  jr.type = TypesOfCashObjects.CASH_SOURCE;
                                break;
            case "Usage":       jr.type = TypesOfCashObjects.USAGE;
        }
        DriverDao.insertRecordInJournal(jr);
        return jr;

    }

    //создать пустую Map с ключами-датами за определенный период
    //TODO проверить, работает ли метод
    public static HashMap<GregorianCalendar,ArrayList<JournalRecord>> createEmptyMapForPeriod (
            GregorianCalendar startDate,
            GregorianCalendar endDate
    ){
        GregorianCalendar pointerDate = startDate;
        HashMap<GregorianCalendar,ArrayList<JournalRecord>> emptyMap = new HashMap<>();

        while (!compareDatesByYMD(pointerDate,endDate)) {
            emptyMap.put(pointerDate, null);
            pointerDate.add(Calendar.DAY_OF_MONTH,1);
        }
        return emptyMap;
    }

    //Сравнить две даты по полям Год, Месяц, День меяца.
    //TODO проверить работоспособность метода
    public static boolean compareDatesByYMD(GregorianCalendar date1, GregorianCalendar date2){
        if ((date1.YEAR==date2.YEAR) &
            (date1.MONTH==date2.MONTH) &
            (date1.DAY_OF_MONTH==date2.DAY_OF_MONTH))
            return true;
        else return false;
    }
}
