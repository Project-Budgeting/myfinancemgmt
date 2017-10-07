package com.example.user.financemgmt.ExpensePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Expense;
import com.example.user.financemgmt.R;

/**
 * Created by Palibin on 05.10.2017.
 */

public class ForExpenseFragment extends Fragment {
    RecyclerView rvExpense;
    ExpensesRVAdapter adapter;
    private boolean fragmentTrigger;// true - фрагмент Decreasable, false - фрагмент Usage

    public boolean isFragmentTrigger() {
        return fragmentTrigger;
    }

    public void setFragmentTrigger(boolean fragmentTrigger) {
        this.fragmentTrigger = fragmentTrigger;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View expenseFragmentView = inflater.inflate(R.layout.for_expense_fragment, container,false);

        RecyclerView rvExpense = (RecyclerView) expenseFragmentView.findViewById(R.id.expenseHorizontalRecycler);
        if (fragmentTrigger) {
            adapter = new ExpensesRVAdapter(true, DriverDao.getDecreasableList());
            rvExpense.setAdapter(adapter);
            rvExpense.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            adapter = new ExpensesRVAdapter(false, DriverDao.getCategoryUsageList());
            rvExpense.setAdapter(adapter);
            rvExpense.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return expenseFragmentView;
    }
}
