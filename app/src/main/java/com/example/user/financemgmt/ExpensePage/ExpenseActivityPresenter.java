package com.example.user.financemgmt.ExpensePage;

/**
 * Created by Palibin
 */
public class ExpenseActivityPresenter {
    private ExpenseActivity view;
    public void bindView(ExpenseActivity view) {
        this.view = view;
        }


    interface ActivityCV {
       void bindPresenter();
    }
}
