package com.example.user.financemgmt.ExpensePage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.financemgmt.DataModel.Expense;
import com.example.user.financemgmt.R;

/**
 * Created by Palibin on 05.10.2017.
 */

public class ExpenseRVHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView cashAmount;
    public ExpenseRVHolder(View itemView) {
        super(itemView);
        itemName = (TextView) itemView.findViewById(R.id.forExpenseCardName);
        cashAmount = (TextView) itemView.findViewById(R.id.forExpenseCardCash);
    }
}
