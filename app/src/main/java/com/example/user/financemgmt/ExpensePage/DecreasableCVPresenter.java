package com.example.user.financemgmt.ExpensePage;

import android.graphics.Color;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Palibin
 */

public class DecreasableCVPresenter extends FinanceCVPresenter<Decreasable> {


    public DecreasableCVPresenter(changingParentAdapter listener) {
        super(listener);
    }

    @Override
    public void updateView(int position) {
        view.get().updateName(getItemName(position));
        view.get().updateCashAmount(Long.toString(getItemCash(position)));
        if (hasSelection){ //Если выделение существует на текущем элементе
            view.get().changeBG(Color.RED);         //выделить его красным
        } else view.get().changeBG(R.color.defaultForTest);
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
