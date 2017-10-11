package com.example.user.financemgmt.ExpensePage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Palibin on 11.10.2017.
 */

public abstract class FinanceFragmentPresenter<M> {
    protected ArrayList<M> model;
    protected WeakReference<FinanceFragmentView> view; // Сюда получим наш фрагмент.
    protected int oldSelectedPosition = -1;
    protected int selectedPosition = -1;
    public FinanceFragmentPresenter() {
        getModel();
    }

    public void onAdapterCreated(){
        view.get().updateSelections(oldSelectedPosition,selectedPosition);
    }
    public void onItemSelected(int position) {
//первое выделение
        if (oldSelectedPosition == -1 & selectedPosition == -1) {
            this.selectedPosition = position;
            view.get().updateSelections(this.oldSelectedPosition, this.selectedPosition);
            view.get().updateCardView(position);
        } else
        //повторное выделение
            if (position == selectedPosition) {
                this.oldSelectedPosition = position;
                view.get().updateSelections(this.oldSelectedPosition, this.selectedPosition);
                view.get().updateCardView(position);
                this.oldSelectedPosition = -1;
                this.selectedPosition = -1;
                view.get().updateSelections(this.oldSelectedPosition, this.selectedPosition);
            } else
                //выделение айтема, при уже имеющемся выделенном элементе
                if (position != selectedPosition) {
                oldSelectedPosition = selectedPosition;
                selectedPosition = position;
                view.get().updateSelections(this.oldSelectedPosition, this.selectedPosition);
                view.get().updateCardView(selectedPosition);
                view.get().updateCardView(oldSelectedPosition);

            }
    }
    public ArrayList<M> getDataList() {
        return model;
    }
    public void bindView(FinanceFragmentView view){
        this.view = new WeakReference<FinanceFragmentView>(view);
    }
    public abstract ArrayList<M> getModel();
}
