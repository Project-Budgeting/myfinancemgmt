package com.example.user.financemgmt.TestStorageForDataObjects;

import com.example.user.financemgmt.DataModel.CashSource;

import java.util.ArrayList;

/**
 * Created by PalibinFamily on 01.10.2017.
 */

public class CashSourcesStorage {
    private static CashSourcesStorage singleStorage;
    private ArrayList<CashSource> storageList;

    public ArrayList<CashSource> getStorageList() {
        return storageList;
    }
    public void setStorageList(ArrayList<CashSource> list){
        this.storageList=list;
    }

    private CashSourcesStorage() {
        ArrayList<CashSource> arrayList = new ArrayList<>();
        this.storageList = arrayList;
        // следующие данные созданы просто для тестирования
        arrayList.add(new CashSource(50000,"Зарплата"));
        arrayList.add(new CashSource(10000,"Дивиденды"));
    }

    public static CashSourcesStorage getCashSourcesStorage(){
        if (singleStorage==null) {
            singleStorage = new CashSourcesStorage();

        }
        return singleStorage;
    }

}
