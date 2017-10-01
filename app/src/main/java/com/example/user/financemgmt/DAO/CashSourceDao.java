package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.CashSource;

import java.util.ArrayList;

/**
 * Created by PalibinFamily on 01.10.2017.
 */

public interface CashSourceDao {
    public void insertCashSource(CashSource cs);

    public int updateCashSource(CashSource cs);
    public ArrayList<CashSource> fillStorage();
}
