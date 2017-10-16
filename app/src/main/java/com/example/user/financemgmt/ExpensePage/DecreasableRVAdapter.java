package com.example.user.financemgmt.ExpensePage;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.DataModel.Usage;
import com.example.user.financemgmt.R;

import java.util.ArrayList;

/**
 * Created by user on 06.10.2017.
 */

public class DecreasableRVAdapter extends RecyclerView.Adapter<ExpenseRVHolder>
                                    implements SelectableRecyclerViewItem{
    ArrayList<Decreasable> dataList;
    private int selectedPosition = -1;
    private int oldSelectedPosition = -1;
    private FinanceFragmentView listener;
    private Context context;

    public DecreasableRVAdapter(ArrayList<Decreasable> decreasableArrayList, FinanceFragmentView listener, Context context) {
        this.dataList = decreasableArrayList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ExpenseRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_for_expense, parent, false);
        return new ExpenseRVHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ExpenseRVHolder holder, int position) {
        holder.itemName.setText(dataList.get(position).getName());
        holder.cashAmount.setText(Long.toString(dataList.get(position).getCashAmount()));
        if ((oldSelectedPosition != selectedPosition) &(position==selectedPosition)) {
            holder.itemView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.markedcolor));
        }
        if ((position == oldSelectedPosition & oldSelectedPosition == selectedPosition) |
            ((oldSelectedPosition == position)& (selectedPosition != position) |
             (selectedPosition ==-1 & oldSelectedPosition == -1))) {
            holder.itemView.setCardBackgroundColor(ContextCompat.getColor(context,R.color.cardsColor));
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public void setSelections(int oldSelectedPosition, int selectedPosition) {
        this.oldSelectedPosition = oldSelectedPosition;
        this.selectedPosition = selectedPosition;
    }


    @Override
    public void onItemClicked(int position) {
        listener.onFinanceClicked(position);
    }
}



