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
        // следующие данные созданы просто для тестирования
        arrayList.add(new CashSource("Зарплата",50000));
        arrayList.add(new CashSource("Дивиденды",10000));
        this.storageList = arrayList;
    }

    public static CashSourcesStorage getCashSourcesStorage(){
        if (singleStorage==null) {
            singleStorage = new CashSourcesStorage();
        }
        return singleStorage;
    }

}
