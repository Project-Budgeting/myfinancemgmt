package com.example.user.financemgmt.ExpensePage;

import android.graphics.Color;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by PalibinFamily on 08.10.2017.
 */

public abstract class FinanceCVPresenter<M> {
    protected ArrayList<M> model; //Тип данных: Usage или CashSource
    protected WeakReference<ExpenseCV> view; // Вьюшка, на которую мы подписывамся
    protected int selectedItem = -1;    //-1, если не выбран элемент.
    protected int oldPosition;
    protected boolean hasSelection;
    protected boolean[] selectionArray= new boolean[model.size()];
    protected changingParentAdapter listener;
    public FinanceCVPresenter(changingParentAdapter listener) {
        //todo переделать
        for (int i = 0; i<(selectionArray.length-1); i++) selectionArray[i] = false;
        this.listener = listener;
    }

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
        oldPosition = selectedItem;
        selectedItem = position;
        if((selectedItem==oldPosition)&(oldPosition!=-1)) { //если позиция выбрана повторно
            listener.refreshItem(position);
            selectedItem = -1;  // отменить выделение
            oldPosition = -1;

        } else {                //в случае, если есть старое выделение и нужно новое
            selectionArray[position] = true;
            listener.refreshItem(position);     // почему это говно запускается только тогда, когда закончит выполнение метод onItemClicked;
            if (oldPosition!=-1) {
                selectionArray[oldPosition] = false;
                listener.refreshItem(oldPosition);
            }
        }
       // if (hasSelection){ //Если выделение существует на текущем элементе
       // view.get().changeBG(Color.RED);         //выделить его красным
    //} else view.get().changeBG(R.color.defaultForTest);


    }

    public abstract void updateView(int position);

    public abstract String getItemName(int position);

    public abstract long getItemCash(int position);

    public abstract int getModelSize();

    public abstract void onBindViewHolder(int position);

    interface changingParentAdapter {
        void refreshItem(int position);
    }
}
