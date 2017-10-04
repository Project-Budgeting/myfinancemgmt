package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.JournalRecord;
import com.example.user.financemgmt.TestStorageForDataObjects.JournalStorage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by PalibinFamily on 03.10.2017.
 */

public class JavaObjectJournalDao extends JavaObjectDaoFactory implements JournalDao {

    @Override
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

    private static HashMap<GregorianCalendar,ArrayList<JournalRecord>> getStorageMap() {
        return JournalStorage.getJournalStorage().getStorageMap();
    }

}
