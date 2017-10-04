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

    @Override
    public long setSourceById(CashSource cashSource) {
       ArrayList<CashSource> storage = getStorage();
        for (CashSource cs: storage) {
            if (cs.getId()==cashSource.getId()) {
                cs.setAvailableCash(cashSource.getAvailableCash());
                return cs.getId();
            }
        }
        return -1;
    }

    @Override
    //TODO работать пока не будет, нужно подменить ссылку
    public CashSource getSourceById(long id) {
        CashSource cashSource = null;
        for (CashSource cs: getStorage()) {
            if (cs.getId()==id) cashSource=cs;
        }
        return cashSource;
    }

    protected static ArrayList<CashSource> getStorage(){
        return CashSourcesStorage.getCashSourcesStorage().getStorageList();
    }
}
