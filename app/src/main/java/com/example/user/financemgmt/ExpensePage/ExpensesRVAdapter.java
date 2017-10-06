package com.example.user.financemgmt.ExpensePage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.financemgmt.DataModel.CashSource;
import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.DataModel.Usage;
import com.example.user.financemgmt.R;
import com.example.user.financemgmt.ViewHold;

import java.util.ArrayList;

/**
 * Created by PalibinFamily on 05.10.2017.
 */

// В названии не надо указывать RV. Это лишняя информация. Просто ExpensesAdapter
public class ExpensesRVAdapter extends RecyclerView.Adapter<ExpenseRVHolder> {
    private boolean typeTrigger; // если true, то адаптер для decresable. false - для Usage
    public ArrayList<Usage> usageList ;
    public ArrayList<Decreasable> decreasableList;

    public ExpensesRVAdapter(boolean typeTrigger, ArrayList list) {
        this.typeTrigger = typeTrigger;
        if (typeTrigger) {
            decreasableList = (ArrayList<Decreasable>) list;
            //Добавление кнопки "добавить" на первую позицию
            decreasableList.add(0, new Decreasable() {
                @Override
                public void decrease(long amount) {

                }

                @Override
                public String getName() {
                    return "+";
                }

                @Override
                public long getCashAmount() {
                    return 0;
                }
            });
        }
        else usageList = (ArrayList<Usage>) list; //TODO сделать кнопку "Добавить"
    }

    @Override
    public ExpenseRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.card_for_expense, parent, false);

        return new ExpenseRVHolder(itemView);
    }

//     Лучше сделать 2 класса ViewHolder - DecreasableViewHolder и UsageViewHolder и реализовать в них метод
//     bind(Decreasable/Usage). А в методе onBindViewHolder вызывать их в зависимости от typeTrigger 
    @Override
    public void onBindViewHolder(ExpenseRVHolder holder, int position) {
        if(typeTrigger) {
            Decreasable currentSource = decreasableList.get(position);
            holder.itemName.setText(currentSource.getName());
            holder.cashAmount.setText(Long.toString(currentSource.getCashAmount()));
        } else {
            Usage currentUsage = usageList.get(position);
            holder.itemName.setText(currentUsage.getName());
            holder.cashAmount.setText("");
        }
    }

//     надо возвращать длину соответствующего списка
    @Override
    public int getItemCount() {
        return 0;
    }
}
