package com.example.user.financemgmt.DAO;

import com.example.user.financemgmt.DataModel.CashSource;
import com.example.user.financemgmt.TestStorageForDataObjects.CashSourcesStorage;

import java.util.ArrayList;

/**
 * Created by Palibin
 * Данный класс определяет какими методами наш DriverDao будет извлекать информацию из хранилища
 * CashSourcesStorage, исполненного ввиде ArrayList. У каждого объекта данных модели свои поля,
 * поэтому каждый из них будет иметь свой аналогичный обработчик полей класса.

 */

public class JavaObjectCashSourceDao extends JavaObjectDaoFactory implements CashSourceDao {

    @Override
    public void insertCashSource(CashSource cs) {
        getStorage().add(cs);
    }

    @Override
    public int updateCashSource(CashSource cs) {
       int index = getStorage().indexOf(cs);
        if (index!=-1) {
            getStorage().set(index,cs);
            return index;
        }
        else return -1;
    }

    @Override
    public ArrayList<CashSource> fillStorage() {

        return  getStorage();
    }

    protected static ArrayList<CashSource> getStorage(){
        return CashSourcesStorage.getCashSourcesStorage().getStorageList();
    }
}
