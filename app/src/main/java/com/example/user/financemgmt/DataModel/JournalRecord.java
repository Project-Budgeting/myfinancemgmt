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
    private String id; // id Записи в журнале
    private String idOfEvent; // id события
    private static long count = 0;

    public JournalRecord(long amount, String name, String additionalSettings, String idOfEvent) {
        this.amount = amount;
        this.name = name;
        this.additionalSettings = additionalSettings;
        this.idOfEvent = idOfEvent;
        this.id = generateid();
        count++;
    }

    /*конструктор для тестирования*/
    public JournalRecord(long amount, String name, String additionalSettings, TypesOfCashObjects type) {
        this.amount = amount;
        this.name = name;
        this.additionalSettings = additionalSettings;
        this.type = type;
    }

    private String generateid() {
        return Long.toString(count + 1);
    }

    public TypesOfCashObjects getType() {
        return type;
    }

    public String getIdOfEvent() {
        return idOfEvent;
    }

    public String getAdditionalSettings(){return additionalSettings;}

    public GregorianCalendar getDate() {
        return date;
    }

    public long getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public long getAmount(String n) {
        if (n.equals(this.name)) {
            return amount;
        } else {
            return 0;
        }
    }

    public static JournalRecord makeRecordInJournal(Object event, long amount,
                                                    String name, String additionalSettings, String idOfEvent) {
        JournalRecord jr = new JournalRecord(amount, name, additionalSettings, idOfEvent);
        GregorianCalendar dateOfNow = new GregorianCalendar();
        jr.date = new GregorianCalendar(dateOfNow.get(Calendar.YEAR), dateOfNow.get(Calendar.MONTH), dateOfNow.get(Calendar.DAY_OF_MONTH));
        switch (event.getClass().getName()) {
            case "CashSource":
                jr.type = TypesOfCashObjects.CASH_SOURCE;
                break;
            case "Usage":
                jr.type = TypesOfCashObjects.USAGE;
                break;
        }
        DriverDao.insertRecordInJournal(jr);
        return jr;

    }



    //создать пустую Map с ключами-датами за определенный период
    //TODO проверить, работает ли метод
    public static HashMap<GregorianCalendar, ArrayList<JournalRecord>> createEmptyMapForPeriod(
            GregorianCalendar startDate,
            GregorianCalendar endDate
    ) {
        GregorianCalendar pointerDate = startDate;
        HashMap<GregorianCalendar, ArrayList<JournalRecord>> emptyMap = new HashMap<>();

        while (!compareDatesByYMD(pointerDate, endDate)) {
            emptyMap.put(pointerDate, null);
            pointerDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        return emptyMap;
    }

    //Сравнить две даты по полям Год, Месяц, День меяца.
    //TODO проверить работоспособность метода
    public static boolean compareDatesByYMD(GregorianCalendar date1, GregorianCalendar date2) {
        return ((date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) &
                (date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)) &
                (date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH)));
    }
}
