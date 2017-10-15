package com.example.user.financemgmt.ExpensePage;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.financemgmt.R;

/**
 * Created by Palibin on 05.10.2017.
 */

public class ExpenseActivity extends AppCompatActivity implements ExpenseActivityPresenter.ActivityCV{
   ExpenseActivityPresenter presenter;
   ForExpenseFragment decreasableFragment;
   ForExpenseFragment usageFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_activity);
        bindPresenter();
        FragmentManager fm =getSupportFragmentManager();
        decreasableFragment = new ForExpenseFragment();
        decreasableFragment.setFragmentTrigger(true);
        usageFragment = new ForExpenseFragment();
        usageFragment.setFragmentTrigger(false);
        fm.beginTransaction()
          .add(R.id.decreaseFragmentContainer,decreasableFragment)
          .commit();
        fm.beginTransaction()
          .add(R.id.usageFragmentContainer,usageFragment)
          .commit();

    }

    @Override
    public void bindPresenter() {
        this.presenter = new ExpenseActivityPresenter();
        presenter.bindView(this);
    }

    @Override
    public void showExpenseDialog(String sName, String uName, final ExpenseActivityPresenter presenter) {
        LayoutInflater li = LayoutInflater.from(this);
        View expenseDialog = li.inflate(R.layout.expense_creating_dialog, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(expenseDialog);
        TextView message = (TextView) expenseDialog.findViewById(R.id.creatingExpenseHeader);
        message.setText(getString(R.string.source_for_expense) + sName +
                                  getString(R.string.usage_for_expense) + uName +"\n" +
                                  getString(R.string.enter_text_for_expense));
        final EditText editCost = (EditText) expenseDialog.findViewById(R.id.creatingExpenseEdit);
        dialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onOkPressedInDialog(editCost.getText().toString());
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onCancelPressedInDialog();
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog expenseCreatingDialog = dialogBuilder.create();
        expenseCreatingDialog.show();

    }

    @Override
    public void unselectFragments() {
        if (decreasableFragment!=null) decreasableFragment.unselectPresenters();
        if (usageFragment!=null) usageFragment.unselectPresenters();
    }

    public ExpenseActivityPresenter getPresenter() {
        return presenter;
    }
}
