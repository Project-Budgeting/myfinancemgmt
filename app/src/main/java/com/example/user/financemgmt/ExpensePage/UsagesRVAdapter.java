package com.example.user.financemgmt.ExpensePage;

import android.support.v4.app.FragmentActivity;
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

public class UsagesRVAdapter extends RecyclerView.Adapter<ExpenseRVHolder> implements FinanceCVPresenter.changingParentAdapter{
    private UsageCVPresenter presenter;


    public UsagesRVAdapter(FragmentActivity activity) {
       presenter = new UsageCVPresenter(this);
       presenter.bindActivity(activity);//TODO сделать кнопку "Добавить"
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
            presenter.onBindViewHolder(position);

    }

    @Override
    public int getItemCount() {
        return presenter.getModelSize();
    }

    @Override
    public void refreshItem(int position) {
        notifyItemChanged(position);
    }
}
