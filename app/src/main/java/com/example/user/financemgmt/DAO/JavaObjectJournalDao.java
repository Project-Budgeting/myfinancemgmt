package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.JournalRecord;
import com.example.user.financemgmt.DataModel.TypesOfCashObjects;
import com.example.user.financemgmt.TestStorageForDataObjects.JournalStorage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PalibinFamily on 03.10.2017.
 */

public class JavaObjectJournalDao extends JavaObjectDaoFactory implements JournalDao {

    @Override
    //Вставка записи в журнал
    public void insertJournalRecordByDate(JournalRecord record) {
        ArrayList<JournalRecord> dayList = getStorageMap().get(record.getDate());
        if (dayList==null) {
            //Если событий в этот день нет, создаем лист с событиями
            ArrayList<JournalRecord> newDayList =new ArrayList<JournalRecord>();
            newDayList.add(record);
            getStorageMap().put(record.getDate(),newDayList);
        } else {
            //Если события уже есть, добавляем новое событие в лист
            dayList.add(record);
            getStorageMap().put(record.getDate(),dayList);
        }
    }

    @Override
    //получение журнала ввиде событий ввиде HashMap
    //TODO проверить как работает метод
    public HashMap<GregorianCalendar, ArrayList<JournalRecord>> getCustomMapFromStorage
            (GregorianCalendar startDate, GregorianCalendar endDate, TypesOfCashObjects type) {
        HashMap<GregorianCalendar, ArrayList<JournalRecord>> storageMap = getStorageMap();
        HashMap<GregorianCalendar, ArrayList<JournalRecord>> customMap =
                JournalRecord.createEmptyMapForPeriod(startDate,endDate);

        // по каждому ключу элементов customMap проверяем аналогичный ключ у storage map
        // и в зависимости от заданного параметра type в сигнатуре метода - заполняем customMap
        for (Map.Entry<GregorianCalendar,ArrayList<JournalRecord>> entry: customMap.entrySet()){
            ArrayList <JournalRecord> customDayList = new ArrayList<>();
            //Записать в временный лист содержимое Листа из хранилища по имеющемуся ключу
           // (по заданной дате)
            ArrayList<JournalRecord> actualStorageDayList = storageMap.get(entry.getKey());
            //если в сигнатуре метода передан null, то целиком копируем базу
            // в пределах указанного периода
            if (type==null) entry.setValue(actualStorageDayList);
            else {
                for (JournalRecord jr: actualStorageDayList) {
                    if (jr.getType().equals(type)) customDayList.add(jr);
                }
                entry.setValue(customDayList);
            }
        }

        return customMap;
    }

    private static HashMap<GregorianCalendar,ArrayList<JournalRecord>> getStorageMap() {
        return JournalStorage.getJournalStorage().getStorageMap();
    }

}
