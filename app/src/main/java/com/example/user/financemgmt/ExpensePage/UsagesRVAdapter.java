package com.example.user.financemgmt.ExpensePage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.DataModel.Usage;
import com.example.user.financemgmt.R;

import java.util.ArrayList;

/**
 * Created by user on 06.10.2017.
 */

public class UsagesRVAdapter extends RecyclerView.Adapter<ExpenseRVHolder> {
    private UsageCVPresenter presenter;


    public UsagesRVAdapter() {
       presenter = new UsageCVPresenter(); //TODO сделать кнопку "Добавить"
    }

    @Override
    public ExpenseRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_for_expense, parent, false);

        return new ExpenseRVHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpenseRVHolder holder, int position) {
            holder.bindPresenter(presenter);
            holder.itemName.setText(presenter.getItemName(position));
            holder.cashAmount.setText("");
    }

    @Override
    public int getItemCount() {
        return presenter.getModelSize();
    }
}
