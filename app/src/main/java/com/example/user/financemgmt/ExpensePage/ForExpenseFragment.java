package com.example.user.financemgmt.ExpensePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.financemgmt.DAO.DriverDao;
import com.example.user.financemgmt.DataModel.Decreasable;
import com.example.user.financemgmt.DataModel.Expense;
import com.example.user.financemgmt.DataModel.Usage;
import com.example.user.financemgmt.R;

/**
 * Created by Palibin on 05.10.2017.
 */

public class ForExpenseFragment extends Fragment implements FinanceFragmentView{
    private RecyclerView rvExpense;
    private DecreasableRVAdapter decAdapter;
    private UsagesRVAdapter usegesAdapter;
    private boolean fragmentTrigger;// true - фрагмент Decreasable, false - фрагмент Usage
    private UsageFragmentPresenter usageFragmentPresenter;
    private DecreasableFragmentPresenter decreasableFragmentPresenter;

    public boolean isFragmentTrigged() {
        return fragmentTrigger;
    }

    public void setFragmentTrigger(boolean fragmentTrigger) {
        this.fragmentTrigger = fragmentTrigger;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (fragmentTrigger) {
            decreasableFragmentPresenter = DecreasableFragmentPresenter.getInstance();
            decreasableFragmentPresenter.bindView(this);

        } else {
            usageFragmentPresenter = UsageFragmentPresenter.getInstance();
            usageFragmentPresenter.bindView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View expenseFragmentView = inflater.inflate(R.layout.for_expense_fragment, container,false);

        rvExpense = (RecyclerView) expenseFragmentView.findViewById(R.id.expenseHorizontalRecycler);
        if (fragmentTrigger) {
            decAdapter = new DecreasableRVAdapter(decreasableFragmentPresenter.getModel(), this);
            rvExpense.setAdapter(decAdapter);
            decreasableFragmentPresenter.onAdapterCreated();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvExpense.setLayoutManager(linearLayoutManager);
        } else {
            usegesAdapter = new UsagesRVAdapter(usageFragmentPresenter.getModel(), this);
            rvExpense.setAdapter(usegesAdapter);
            usageFragmentPresenter.onAdapterCreated();
            //TODO как подстроить количество столбцов в гриде к экрану?
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
            rvExpense.setLayoutManager(gridLayoutManager);
        }

        return expenseFragmentView;
    }

    @Override
    public void updateCardView(int position) {
        rvExpense.getAdapter().notifyItemChanged(position);
    }

    @Override
    public void updateSelections(int old, int selected) {
    //какой то нечеловеческий способ по моему
        if (fragmentTrigger) {
            ((DecreasableRVAdapter)rvExpense.getAdapter()).setSelections(old, selected);
        } else ((UsagesRVAdapter)rvExpense.getAdapter()).setSelections(old, selected);
    }

    @Override
    public void onFinanceClicked(int position) {
        if (fragmentTrigger) decreasableFragmentPresenter.onItemSelected(position);
     else usageFragmentPresenter.onItemSelected(position);
    }
}
