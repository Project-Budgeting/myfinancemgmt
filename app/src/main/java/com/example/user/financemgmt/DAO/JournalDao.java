package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.JournalRecord;

import java.util.GregorianCalendar;

/**
 * Created by PalibinFamily on 03.10.2017.
 */

public interface JournalDao {
    void insertJournalRecordByDate(JournalRecord record);
}
