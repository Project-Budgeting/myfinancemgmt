package com.example.user.financemgmt.ExpensePage;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.financemgmt.DataModel.Expense;
import com.example.user.financemgmt.R;

/**
 * Created by Palibin on 05.10.2017.
 */

public class ExpenseRVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public CardView itemView;
    public TextView itemName;
    public TextView cashAmount;
    private SelectableRecyclerViewItem listener;
    public ExpenseRVHolder(View itemView, SelectableRecyclerViewItem listener) {
        super(itemView);
        this.listener = listener;
        itemName = (TextView) itemView.findViewById(R.id.forExpenseCardName);
        cashAmount = (TextView) itemView.findViewById(R.id.forExpenseCardCash);
        this.itemView = (CardView) itemView.findViewById(R.id.forExpenseCard);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        listener.onItemClicked(getAdapterPosition());
    }

}

