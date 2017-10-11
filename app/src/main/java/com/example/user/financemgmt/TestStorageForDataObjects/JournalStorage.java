package com.example.user.financemgmt.TestStorageForDataObjects;

import com.example.user.financemgmt.DataModel.JournalRecord;
import com.example.user.financemgmt.DataModel.TypesOfCashObjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by Palibin
 * Журнал записей
 */

public class JournalStorage {
    private static JournalStorage singleStorage;
    private HashMap<GregorianCalendar, ArrayList<JournalRecord>> storageMap;

    public HashMap<GregorianCalendar, ArrayList<JournalRecord>> getStorageMap() {
        return this.storageMap;
    }

    public JournalStorage() {
        HashMap<GregorianCalendar, ArrayList<JournalRecord>> hashMap = new HashMap<GregorianCalendar, ArrayList<JournalRecord>>();
        /***************************
         *Инициализация временного хранилища для тестирования
         * TODO удалить на релизе
         **********************************/
        ArrayList<JournalRecord> day1 = new ArrayList<>();
        ArrayList<JournalRecord> day2 = new ArrayList<>();
        ArrayList<JournalRecord> day3 = new ArrayList<>();
        String e = "Еда";
        String shb = "Шабашки";
        String zp = "Зарплата";
        String ku = "Коммунальные услуги";
        day1.add(new JournalRecord(1000, e, zp, TypesOfCashObjects.USAGE));
        day1.add(new JournalRecord(500, e, zp, TypesOfCashObjects.USAGE));
        day1.add(new JournalRecord(3000, ku, zp, TypesOfCashObjects.USAGE));
        day1.add(new JournalRecord(1000, "Отдых", shb, TypesOfCashObjects.USAGE));
        day1.add(new JournalRecord(1000, zp, null, TypesOfCashObjects.CASH_SOURCE));
        day1.add(new JournalRecord(5000, zp, null, TypesOfCashObjects.CASH_SOURCE));
        day1.add(new JournalRecord(13000, shb, null, TypesOfCashObjects.CASH_SOURCE));
        day1.add(new JournalRecord(12000, shb, null, TypesOfCashObjects.CASH_SOURCE));
        day2.add(new JournalRecord(1000, e, zp, TypesOfCashObjects.USAGE));
        day2.add(new JournalRecord(500, "Штраф", zp, TypesOfCashObjects.USAGE));
        day2.add(new JournalRecord(3000, "Штраф", zp, TypesOfCashObjects.USAGE));
        day2.add(new JournalRecord(1000, "Отдых", shb, TypesOfCashObjects.USAGE));
        day2.add(new JournalRecord(1000, zp, null, TypesOfCashObjects.CASH_SOURCE));
        day2.add(new JournalRecord(5000, "Спорт", null, TypesOfCashObjects.CASH_SOURCE));
        day2.add(new JournalRecord(3000, shb, null, TypesOfCashObjects.CASH_SOURCE));
        day2.add(new JournalRecord(1000, shb, null, TypesOfCashObjects.CASH_SOURCE));
        day3.add(new JournalRecord(1000, e, zp, TypesOfCashObjects.USAGE));
        day3.add(new JournalRecord(500, "Подарок", zp, TypesOfCashObjects.USAGE));
        day3.add(new JournalRecord(3000, ku, zp, TypesOfCashObjects.USAGE));
        day3.add(new JournalRecord(1000, "Отдых", shb, TypesOfCashObjects.USAGE));
        day3.add(new JournalRecord(1000, zp, null, TypesOfCashObjects.CASH_SOURCE));
        day3.add(new JournalRecord(2000, zp, null, TypesOfCashObjects.CASH_SOURCE));
        day3.add(new JournalRecord(14000, shb, null, TypesOfCashObjects.CASH_SOURCE));
        day3.add(new JournalRecord(1000, shb, null, TypesOfCashObjects.CASH_SOURCE));

        hashMap.put(new GregorianCalendar(2017, 9, 3), day1);
        hashMap.put(new GregorianCalendar(2017, 9, 2), day2);
        hashMap.put(new GregorianCalendar(2017, 9, 1), day3);
        /****************************************************************/
        this.storageMap = hashMap;
    }

    public static JournalStorage getJournalStorage() {
        if (singleStorage == null) {
            singleStorage = new JournalStorage();
        }
        return singleStorage;
    }


}
