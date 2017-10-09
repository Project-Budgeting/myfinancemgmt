package com.example.user.financemgmt.ExpensePage;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Palibin
 */

public class DecreasableCVPresenter extends FinanceCVPresenter<Decreasable> {



    @Override
    public void updateView(int position) {
        view.get().updateName(getItemName(position));
        view.get().updateCashAmount(Long.toString(getItemCash(position)));
    }


    public String getItemName(int position) {
        return ((Decreasable) DriverDao.getDecreasableList().get(position)).getName();
    }

    public long getItemCash(int position) {
        return ((Decreasable) DriverDao.getDecreasableList().get(position)).getCashAmount();
    }

    public int getModelSize() {
        return DriverDao.getDecreasableList().size();
    }

    @Override
    public void onBindViewHolder(int position) {
        updateView(position);
    }
}
