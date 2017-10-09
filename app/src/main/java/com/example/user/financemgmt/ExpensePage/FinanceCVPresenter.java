package com.example.user.financemgmt.ExpensePage;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by PalibinFamily on 08.10.2017.
 */

public abstract class FinanceCVPresenter<M> {
    protected ArrayList<M> model; //Тип данных: Usage или CashSource
    protected WeakReference<ExpenseCV> view; // Вьюшка, на которую мы подписывамся
    int selectedItem;


    public void bindView (ExpenseCV view) {
        this.view = new WeakReference<ExpenseCV>(view);
    }

    public void setModel(ArrayList<M> model){
        this.model = model;
    }

    public void onItemClicked(int position) {
        /*
        * Нужно: сохранить номер выделенного элемента в списке
        * TODO: отправить информацию на уровень выше
        * Отправить команду на выделение вьюхи
        * TODO: Сделать выделение одинарным
        */
        selectedItem = position;
        view.get().selectView();

    }

    public abstract void updateView();

    public abstract String getItemName(int position);

    public abstract long getItemCash(int position);

    public abstract int getModelSize();

    public abstract void onBindViewHolder(int position);
}
