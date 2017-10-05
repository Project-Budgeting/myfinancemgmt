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

    //TODO Удалить
    @Override
    public ArrayList<CashSource> fillStorage() {

        return  getStorage();
    }

    @Override
    public long updateCashSource(CashSource cashSource) {
       ArrayList<CashSource> storage = getStorage();
        for (CashSource cs: storage) {
            if (cs.getId()==cashSource.getId()) {
                cs.setAvailableCash(cashSource.getAvailableCash());
                cs.setName(cashSource.getName());
                return cs.getId();
            }
        }
        return -1;
    }

    @Override
    public CashSource getSourceById(long id) {
        CashSource cashSource = null;
        for (CashSource cs: getStorage()) {
            //Замена ссылки, чтобы не изменять объект напрямую.
            if (cs.getId()==id) cashSource = new CashSource(cs);
        }
        return cashSource;
    }

    protected static ArrayList<CashSource> getStorage(){
        return CashSourcesStorage.getCashSourcesStorage().getStorageList();
    }
}
