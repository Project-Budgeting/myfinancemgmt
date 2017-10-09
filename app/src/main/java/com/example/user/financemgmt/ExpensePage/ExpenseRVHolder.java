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

public class ExpenseRVHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpenseCV{
    private FinanceCVPresenter presenterCV;
    //public boolean clientTrigger; //true - decreasable, false - usage
    public CardView itemView;
    public TextView itemName;
    public TextView cashAmount;
    public ExpenseRVHolder(View itemView) {
        super(itemView);
        itemName = (TextView) itemView.findViewById(R.id.forExpenseCardName);
        cashAmount = (TextView) itemView.findViewById(R.id.forExpenseCardCash);
        this.itemView = (CardView) itemView.findViewById(R.id.forExpenseCard);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        presenterCV.bindView(this);
        presenterCV.onItemClicked(getAdapterPosition());
    }
  //  public void setClientTrigger(boolean clientTrigger) {
   //     this.clientTrigger = clientTrigger;
   // }

    public void bindPresenter(FinanceCVPresenter presenter) {
        this.presenterCV = presenter;
        presenter.bindView(this);
    }
    public void unbindPresenter(){
        presenterCV = null;
    }

    @Override
    public void selectView() {
    itemView.setCardBackgroundColor(Color.RED);
    }

    @Override
    public void updateName() {
        itemName.setText(presenterCV.);
    }

    @Override
    public void updateCashAmount() {

    }
}

