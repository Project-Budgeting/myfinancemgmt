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

/* Как уже говорилось Presenter должен создаваться для Activity или Fragment, а не для каждой
view адаптера.
 */

public class DecreasableCVPresenter extends FinanceCVPresenter<Decreasable> {
/*
protected ArrayList<Decreasable> model; //Тип данных: Usage или CashSource
    protected WeakReference<ExpenseCV> view; // Вьюшка, на которую мы подписывамся
    int selectedItem;
    Вот этот код был правильный. presenter должен хранить данные, чтобы внутри себя с ними
    работать, а не каждый раз лезть в DAO
 */

    public DecreasableCVPresenter(changingParentAdapter listener) {
        super(listener);
    }

    @Override
    public void updateView(int position) {
        view.get().updateName(getItemName(position));
        view.get().updateCashAmount(Long.toString(getItemCash(position)));
        if (selectionArray[position]){
            view.get().changeBG(Color.RED);
        } else view.get().changeBG(Color.YELLOW);
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
